package com.example.sanil.mvpexample;

import android.app.Application;


import com.example.sanil.mvpexample.di.DaggerAppcomponent;
import com.example.sanil.mvpexample.di.module.Appmodule;
import com.example.sanil.mvpexample.di.Appcomponent;



/**
 * Created by sanil on 11-05-2018.
 */

public class MVPApp extends Application {

    Appcomponent appcomponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appcomponent = DaggerAppcomponent.builder().appmodule(new Appmodule(this)).build();
    }

    public Appcomponent getAppcomponent()
    {
        return appcomponent;
    }
}
