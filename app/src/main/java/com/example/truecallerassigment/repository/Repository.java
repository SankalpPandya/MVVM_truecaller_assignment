package com.example.truecallerassigment.repository;
import android.util.Log;
import com.example.truecallerassigment.network.Api;
import com.example.truecallerassigment.network.AssignmentTaskAPIService;
import io.reactivex.Observable;

public class Repository {

    private static Repository newsRepository;
    private Api service;

    public static Repository getInstance() {
        if (newsRepository == null) {
            newsRepository = new Repository();
        }
        return newsRepository;
    }

    public Repository() {
        try {
            service = AssignmentTaskAPIService.createService(Api.class);
        }catch (Exception e){
            Log.e(this.getClass().getName(), "error occured while creating retrofit service");
            newsRepository =null;
        }finally {
        }
    }

    public Observable<String> fetchTrueCallerContent(String url) {
        return service.getTrueCallerUrlContent(url);
    }
}
