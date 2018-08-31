package com.example.sanil.mvpexample.data.local;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sanil on 11-05-2018.
 */

public class SharedPrefRepository implements SharedPrefDataSource{

    SharedPreferences sharedPreferences;
    String sharedPrefName = "MVPSharedPref";

    public SharedPrefRepository(Context context)
    {
        sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
    }




    @Override
    public void putString(String key, String value) {

        sharedPreferences.edit().putString(key,value).commit();
    }

    @Override
    public String getString(String key,String defaultValue) {
        return sharedPreferences.getString(key,defaultValue);
    }
}
