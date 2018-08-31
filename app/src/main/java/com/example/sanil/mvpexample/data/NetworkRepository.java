package com.example.sanil.mvpexample.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by sanil on 16-05-2018.
 */

public class NetworkRepository implements IDatasource.Network{

    @Inject
    Context context;

    @Override
    public boolean isNetWorkAvailable() {

        boolean value = false;
        try {

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                value = true;
            }
            return value;
        } catch (Exception e) {
            return value;
        }
    }
}
