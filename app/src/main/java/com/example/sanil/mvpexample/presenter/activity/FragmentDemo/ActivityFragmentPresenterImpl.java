package com.example.sanil.mvpexample.presenter.activity.FragmentDemo;

import com.example.sanil.mvpexample.presenter.fragment.FragmentOne.FragmentOne;

/**
 * Created by sanil on 10-05-2018.
 */

public class ActivityFragmentPresenterImpl implements ActivityFragmentPresenter {


    IActivityFragmentView iActivityFragmentView;

    public ActivityFragmentPresenterImpl(IActivityFragmentView iActivityFragmentView)
    {
        this.iActivityFragmentView = iActivityFragmentView;
    }


    @Override
    public void onResume() {

        iActivityFragmentView.replaceFragment(new FragmentOne());
    }
}
