package com.example.sanil.mvpexample.data;

import android.app.Application;

/**
 * Created by sanil on 08-05-2018.
 */

public interface IDatasource {

    interface Local
    {
        void saveLoginState();
        void getLoginState();
    }

    interface Remote
    {
        void authenticateUser(String userName,String password,RemoteCallback<String> callback);
        void getList(RemoteCallback<String> callback);
    }

    interface Network
    {
        boolean isNetWorkAvailable();
    }

    interface RemoteCallback<T> {
        void onSuccess(T response);
        void onFailure(String error_code,String error_message);
    }
}
