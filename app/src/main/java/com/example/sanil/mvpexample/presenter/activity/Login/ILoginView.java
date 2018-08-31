package com.example.sanil.mvpexample.presenter.activity.Login;


import com.example.sanil.mvpexample.presenter.activity.BaseClass.IBaseView;

/**
 * Created by sanil on 19-04-2018.
 */

public interface ILoginView extends IBaseView {

    void loginValiationError();
    void navigateToHome();

}
