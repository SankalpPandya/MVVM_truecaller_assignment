package com.example.truecallerassigment.view.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.truecallerassigment.R;
import com.example.truecallerassigment.utils.ApiResponse;
import com.example.truecallerassigment.utils.Status;
import com.example.truecallerassigment.utils.Utility;
import com.example.truecallerassigment.view.actvities.MainActivity;
import com.example.truecallerassigment.viewmodel.TaskAssignmentViewModel;



public class TaskAssignmentFragment extends Fragment implements View.OnClickListener {

    private Button AssignmentButton;
    private TextView textView10thCharacter;
    private TextView textViewEvery10thCharacter;
    private TextView textViewWordCounter;
    private TextView heading1;
    private TextView heading2;
    private TextView heading3;
    TaskAssignmentViewModel mViewModel;

    public static TaskAssignmentFragment NewInstance() {
        return new TaskAssignmentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homscreen_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        textView10thCharacter = (TextView) view.findViewById(R.id.textView1);
        textViewEvery10thCharacter = (TextView) view.findViewById(R.id.textView2);
        textViewWordCounter = (TextView) view.findViewById(R.id.textView3);
        AssignmentButton = (Button) view.findViewById(R.id.fetch_data);
        heading1 = (TextView) view.findViewById(R.id.textView);
        heading2 = (TextView) view.findViewById(R.id.headline2);
        heading3 = (TextView) view.findViewById(R.id.headline3);
        AssignmentButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        if(((MainActivity) getActivity()) ==null) return;
        mViewModel = ((MainActivity) getActivity()).getTaskAssignmentViewModel();
        mViewModel.init();
        mViewModel.getTenthCharacterResponseStatus().observe(getViewLifecycleOwner(), this::setTenthCharacterResponseStatus);
        mViewModel.getEveryTenthCharacterResponseStatus().observe(getViewLifecycleOwner(), this::setEveryTenthCharacterResponseStatus);
        mViewModel.getWordCountResponseStatus().observe(getViewLifecycleOwner(), this::setWordCountResponseStatus);
        mViewModel.get10thCharacterResult().observe(getViewLifecycleOwner(), this::setTextView10thCharacter);
        mViewModel.getEvery10thCharacterResult().observe(getViewLifecycleOwner(), this::setTextViewEvery10thCharacter);
        mViewModel.getWordCountCharacterResult().observe(getViewLifecycleOwner(), this::setTextViewWordCounter);
    }


    private void setTenthCharacterResponseStatus(Status status ){
        HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId.tenthCharacterRequest , status);
    }

    private void setEveryTenthCharacterResponseStatus(Status status ){
        HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId.everyTenthCharacterRequest , status);
    }
    private void setWordCountResponseStatus(Status status ){
        HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId.wordCountRequest , status);
    }



    private void HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId  requestId
            , Status status){

        switch (requestId)
        {
            case tenthCharacterRequest:
                if(status ==Status.ERROR){
                    heading1.setTextColor(getResources().getColor(R.color.request_status_error));
                }else{
                    heading1.setTextColor((status ==Status.LOADING) ? getResources().getColor
                            (R.color.request_status_loading):
                            getResources().getColor(R.color.request_status_success));
                }
                break;
            case everyTenthCharacterRequest:
                if(status ==Status.ERROR){
                    heading2.setTextColor(getResources().getColor(R.color.request_status_error));
                }else {
                    heading2.setTextColor((status ==Status.LOADING) ? getResources().getColor
                            (R.color.request_status_loading):
                            getResources().getColor(R.color.request_status_success));
                }
                break;
            case wordCountRequest:
                if(status ==Status.ERROR){
                    heading3.setTextColor(getResources().getColor(R.color.request_status_error));
                }else {
                    heading3.setTextColor((status ==Status.LOADING) ? getResources().getColor
                            (R.color.request_status_loading):
                            getResources().getColor(R.color.request_status_success));
                }
                break;
            default:
        }
    }

        private void setTextView10thCharacter(String text){
            textView10thCharacter.setText(text);
        }

    private void setTextViewEvery10thCharacter(String text){
        textViewEvery10thCharacter.setText(text);
    }
    private void setTextViewWordCounter(String text){
        textViewWordCounter.setText(text);
    }

    @Override
    public void onClick(View v) {
        try {
            if(!Utility.isNetworkConnected(getActivity())){
                HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId.wordCountRequest , Status.ERROR);
                HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId.tenthCharacterRequest , Status.ERROR);
                HandlerProgressBarVisibilityForRequest(ApiResponse.RequestId.everyTenthCharacterRequest , Status.ERROR);
                Toast.makeText(getActivity(),"Please Turn ON internet connection and try again !" , Toast.LENGTH_SHORT).show();
            }else {
                textViewWordCounter.setText("");
                textViewEvery10thCharacter.setText("");
                textView10thCharacter.setText("");
                mViewModel.hitRequestForTenthCharacter();
                mViewModel.hitRequestForEvery10thCharacter();
                mViewModel.hitRequestForWordCount();
            }
        }catch (Exception e){
            Log.e(this.getClass().getName(), "Failed to  request " + e);
        }
        finally {

        }
    }


}

