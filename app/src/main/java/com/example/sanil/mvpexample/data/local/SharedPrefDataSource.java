package com.example.sanil.mvpexample.data.local;

/**
 * Created by sanil on 11-05-2018.
 */

public interface SharedPrefDataSource {

    void putString(String key ,String value);
    String getString(String key,String defaultValue);
}
