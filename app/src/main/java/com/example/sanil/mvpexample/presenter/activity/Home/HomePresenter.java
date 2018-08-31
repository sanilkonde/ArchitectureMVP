package com.example.sanil.mvpexample.presenter.activity.Home;

import com.example.sanil.mvpexample.presenter.activity.Login.ILoginView;

/**
 * Created by sanil on 20-04-2018.
 */

public interface HomePresenter {

    void onResume();
    void onDestroy();
    void click(int pos);
    void setHomePresenterView(IHomeView iHomeView);
}
