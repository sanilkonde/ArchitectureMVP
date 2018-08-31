package com.example.sanil.mvpexample;

import android.app.Activity;

/**
 * Created by sanil on 30-08-2018.
 */

public interface IPermission {

    void requestPermission(IPermission.permissionCallbacks permissionCallbacks);

    interface permissionCallbacks
    {
        void onPermissionGranted();
        void onPermissionDenied();
    }
}
