package com.example.sanil.mvpexample.di;


import android.app.Fragment;

import com.example.sanil.mvpexample.data.NetworkRepository;
import com.example.sanil.mvpexample.di.module.Appmodule;
import com.example.sanil.mvpexample.presenter.activity.Home.HomeActivity;
import com.example.sanil.mvpexample.presenter.activity.Home.HomePresenter;
import com.example.sanil.mvpexample.presenter.activity.Home.HomePresenterImpl;
import com.example.sanil.mvpexample.presenter.activity.Login.LoginActivity;
import com.example.sanil.mvpexample.presenter.activity.Login.LoginPresenterImpl;
import com.example.sanil.mvpexample.presenter.fragment.FragmentOne.FragmentOne;


import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by sanil on 11-05-2018.
 */


@Singleton @Component(modules = {Appmodule.class})
public interface Appcomponent {

    void inject(HomePresenterImpl homePresenter);
    void inject(LoginPresenterImpl loginPresenter);
    void inject(LoginActivity loginActivity);
    void inject(HomeActivity homeActivity);
    void inject(NetworkRepository networkRepository);

}
