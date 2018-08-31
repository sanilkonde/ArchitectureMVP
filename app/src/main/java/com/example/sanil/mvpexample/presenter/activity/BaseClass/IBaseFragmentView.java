package com.example.sanil.mvpexample.presenter.activity.BaseClass;

/**
 * Created by sanil on 10-05-2018.
 */

public interface IBaseFragmentView {

    void showLoader();
    void dismissLoader();
    void showToast(String msg, int duration);
}
