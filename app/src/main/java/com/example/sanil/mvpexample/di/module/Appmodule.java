package com.example.sanil.mvpexample.di.module;

import android.content.Context;

import com.example.sanil.mvpexample.MVPApp;
import com.example.sanil.mvpexample.data.NetworkRepository;
import com.example.sanil.mvpexample.data.local.SharedPrefRepository;
import com.example.sanil.mvpexample.data.remote.RemoteRepository;
import com.example.sanil.mvpexample.presenter.activity.Home.HomePresenterImpl;
import com.example.sanil.mvpexample.presenter.activity.Login.LoginPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sanil on 11-05-2018.
 */


@Module
public class Appmodule {

    private MVPApp mvpApp;

    public Appmodule(MVPApp mvpApp)
    {
        this.mvpApp = mvpApp;
    }

    @Provides @Singleton
    RemoteRepository getRemoteRepository()
    {
        return new RemoteRepository();
    }

    @Provides @Singleton
    SharedPrefRepository getSharedPrefRepository(Context context)
    {
        return new SharedPrefRepository(context);
    }



    @Provides @Singleton
    LoginPresenterImpl getLoginPresenterImpl()
    {
        return new LoginPresenterImpl();
    }

    @Provides @Singleton
    NetworkRepository getNetWorkRepository()
    {
        return new NetworkRepository();
    }

    @Provides @Singleton
    HomePresenterImpl getHomePresenterImpl()
    {
        return new HomePresenterImpl();
    }


    @Provides
    Context getContext()
    {
        return mvpApp;
    }
}
