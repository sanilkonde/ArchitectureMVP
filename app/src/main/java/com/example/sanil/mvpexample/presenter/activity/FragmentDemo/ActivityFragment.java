package com.example.sanil.mvpexample.presenter.activity.FragmentDemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.sanil.mvpexample.presenter.activity.BaseClass.BaseActivity;
import com.example.sanil.mvpexample.presenter.activity.R;
import com.example.sanil.mvpexample.presenter.activity.databinding.ActivityFragmentBinding;


public class ActivityFragment extends BaseActivity implements IActivityFragmentView{



    ActivityFragmentPresenterImpl activityFragmentPresenter;
    ActivityFragmentBinding activityFragmentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);

        activityFragmentBinding = (ActivityFragmentBinding)getBinder(); //DataBindingUtil.setContentView(this,R.layout.activity_fragment);
//        activityFragmentBinding = DataBindingUtil.setContentView(this, R.layout.activity_fragment);
        activityFragmentPresenter = new ActivityFragmentPresenterImpl(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityFragmentPresenter.onResume();
    }


}
