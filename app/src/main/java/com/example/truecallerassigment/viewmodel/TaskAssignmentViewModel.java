package com.example.truecallerassigment.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truecallerassigment.utils.Status;
import com.example.truecallerassigment.utils.Utility;
import com.example.truecallerassigment.viewmodel.controller.TrueCaller10thCharacterHelper;
import com.example.truecallerassigment.viewmodel.controller.TrueCallerEvery10thCharacterHelper;
import com.example.truecallerassigment.viewmodel.controller.TrueCallerDistinctWordCountHelper;
import com.example.truecallerassigment.repository.Repository;
import com.example.truecallerassigment.utils.ApiResponse;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TaskAssignmentViewModel extends ViewModel {

    private Repository newsRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<String> tengthCharacterResult = new MutableLiveData<>();
    private final MutableLiveData<String> every10thCharacterResult = new MutableLiveData<>();
    private final MutableLiveData<String> wordCountResult = new MutableLiveData<>();
    private final MutableLiveData<Status> tengthCharacterResultStatus = new MutableLiveData<>();
    private final MutableLiveData<Status> every10thCharacterResultStatus = new MutableLiveData<>();
    private final MutableLiveData<Status> wordCountResultStatus = new MutableLiveData<>();


    public void init() {

        if (newsRepository != null) {
            return;
        }
        newsRepository = Repository.getInstance();
    }

    public void hitRequestForTenthCharacter() {
        if(!IsRepositoryReady()) {
            tengthCharacterResultStatus.postValue(Status.ERROR);
            tengthCharacterResult.postValue( "Unknown error occurred !");
            return;
        };
        disposables.add(newsRepository.fetchTrueCallerContent(Utility.URL)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnSubscribe((d) -> tengthCharacterResultStatus.setValue(Status.LOADING))
                .subscribe(
                        result -> {
                            tengthCharacterResultStatus.postValue(Status.SUCCESS);
                            ResolveProblem1(ApiResponse.success(result,
                                    ApiResponse.RequestId.tenthCharacterRequest).data);
                        },
                        throwable -> {
                            tengthCharacterResultStatus.postValue(Status.ERROR);
                            tengthCharacterResult.postValue(ApiResponse.error
                                    (throwable, ApiResponse.RequestId.tenthCharacterRequest).data);
                        }
                ));

    }

    private boolean IsRepositoryReady(){
        return newsRepository != null;
    }

    public void hitRequestForEvery10thCharacter() {
        if(!IsRepositoryReady()) {
            every10thCharacterResultStatus.postValue(Status.ERROR);
            every10thCharacterResult.postValue( "Unknown error occurred !");
            return;
        };
        disposables.add(newsRepository.fetchTrueCallerContent(Utility.URL)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnSubscribe((d) -> every10thCharacterResultStatus.setValue(Status.LOADING))
                .subscribe(
                        result -> {
                            every10thCharacterResultStatus.postValue(Status.SUCCESS);
                            ResolveProblem2(ApiResponse.success
                                    (result, ApiResponse.RequestId.everyTenthCharacterRequest).data);

                        },
                        throwable -> {
                            every10thCharacterResultStatus.postValue(Status.ERROR);
                            every10thCharacterResult.postValue(ApiResponse.error
                                    (throwable, ApiResponse.RequestId.everyTenthCharacterRequest).data);
                        }
                ));
    }

    public void hitRequestForWordCount() {
        if(!IsRepositoryReady()) {
            wordCountResultStatus.postValue(Status.ERROR);
            wordCountResult.postValue( "Unknown error occurred !");
            return;
        };
        disposables.add(newsRepository.fetchTrueCallerContent(Utility.URL)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnSubscribe((d) -> wordCountResultStatus.setValue(Status.LOADING))
                .subscribe(
                        result -> {
                            wordCountResultStatus.postValue(Status.SUCCESS);
                            ResolveProblem3(ApiResponse.success
                                    (result, ApiResponse.RequestId.wordCountRequest).data);
                        },
                        throwable -> {
                            wordCountResultStatus.postValue(Status.ERROR);
                            wordCountResult.postValue(ApiResponse.error
                                    (throwable, ApiResponse.RequestId.wordCountRequest).data);
                        }
                ));
    }

    public MutableLiveData<Status> getTenthCharacterResponseStatus() {
        return tengthCharacterResultStatus;
    }
    public MutableLiveData<Status> getEveryTenthCharacterResponseStatus() {
        return every10thCharacterResultStatus;
    }
    public MutableLiveData<Status> getWordCountResponseStatus() {
        return wordCountResultStatus;
    }


    private void ResolveProblem1(String data) {
        TrueCaller10thCharacterHelper.getInstance().setValueTrueCaller10thCharacterHelper(data);
        Object object = TrueCaller10thCharacterHelper.getInstance().getFormattedData();
        Log.d(this.getClass().getName(), "ResolveProblem1 --" + object);
        tengthCharacterResult.postValue(object.toString());
    }

    private void ResolveProblem2(String data) {
        TrueCallerEvery10thCharacterHelper.getInstance().setValueTrueCallerEvery10thCharacterHelper(data);
        Map<Integer, Character> object = TrueCallerEvery10thCharacterHelper.getInstance().getFormattedData();
        Log.d(this.getClass().getName(), "ResolveProblem2 --" + object);
        every10thCharacterResult.postValue(object.values().toString());
    }

    private void ResolveProblem3(String data) {
        TrueCallerDistinctWordCountHelper.getInstance().setValueTrueCallerDistinctWordCountHelper(data);
        Object object = TrueCallerDistinctWordCountHelper.getInstance().getFormattedData();
        Log.d(this.getClass().getName(), "ResolveProblem3 --" + object);
        wordCountResult.postValue(object.toString());
    }

    public MutableLiveData<String> get10thCharacterResult() {
        return tengthCharacterResult;
    }

    public MutableLiveData<String> getEvery10thCharacterResult() {
        return every10thCharacterResult;
    }

    public MutableLiveData<String> getWordCountCharacterResult() {
        return wordCountResult;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
