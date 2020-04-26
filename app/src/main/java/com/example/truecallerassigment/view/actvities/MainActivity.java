package com.example.truecallerassigment.view.actvities;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.truecallerassigment.R;
import com.example.truecallerassigment.view.fragments.TaskAssignmentFragment;
import com.example.truecallerassigment.viewmodel.TaskAssignmentViewModel;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TaskAssignmentViewModel taskAssignmentViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        showTaskAssignmentFragment();
        toolbar= findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    private void showTaskAssignmentFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .replace(R.id.base_frame_container,
                        TaskAssignmentFragment.NewInstance(),
                        TaskAssignmentFragment.class.getName())
                .addToBackStack(TaskAssignmentFragment.class.getName())
                .commit();
    }

    private void initViewModel() {
        taskAssignmentViewModel = ViewModelProviders.of(this).get(TaskAssignmentViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public TaskAssignmentViewModel getTaskAssignmentViewModel() {
        return taskAssignmentViewModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
