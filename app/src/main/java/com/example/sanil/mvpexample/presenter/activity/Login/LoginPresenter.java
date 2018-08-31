package com.example.sanil.mvpexample.presenter.activity.Login;

import com.example.sanil.mvpexample.IPermission;

/**
 * Created by sanil on 19-04-2018.
 */

public interface LoginPresenter {

    void login(String userName,String password);
    void onResume();
    void onDestroy();
    void setView(ILoginView view, IPermission iPermission);
    void createFiles();
}
