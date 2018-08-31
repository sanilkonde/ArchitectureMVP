package com.example.sanil.mvpexample.presenter.activity.BaseClass;

/**
 * Created by sanil on 09-05-2018.
 */

public interface IBaseView {

    void showProgress();
    void hideProgress();
    void showToast(String msg, int duration);
    void showErrorDialog(String errorCode, String errorMsg);
    void hideErrorDialog();
}
