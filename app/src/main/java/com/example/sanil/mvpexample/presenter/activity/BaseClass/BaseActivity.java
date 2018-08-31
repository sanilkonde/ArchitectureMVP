package com.example.sanil.mvpexample.presenter.activity.BaseClass;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanil.mvpexample.Constant;
import com.example.sanil.mvpexample.IPermission;
import com.example.sanil.mvpexample.data.remote.ErrorMessageFactory;
import com.example.sanil.mvpexample.presenter.activity.Login.LoginActivity;
import com.example.sanil.mvpexample.presenter.activity.R;
import com.example.sanil.mvpexample.presenter.activity.databinding.ActivityBaseBinding;


/**
 * Created by sanil on 09-05-2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView,IPermission{

    ProgressDialog progressDialog;
    Dialog errorDialog;

    ActivityBaseBinding baseBinder ;
    public ViewDataBinding binder;
    IPermission.permissionCallbacks permissionCallbacks;

    @Override
    public void showProgress() {

        if(progressDialog==null)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Please wait...");
        }
        Log.i("", "");
        progressDialog.show();
    }
    public static String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.RECORD_AUDIO,Manifest.permission.CAMERA,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_PHONE_STATE};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(Constant.TAG,"Base Oncreate");

        baseBinder  = DataBindingUtil.setContentView(this,R.layout.activity_base);
        binder = DataBindingUtil.inflate(LayoutInflater.from(this),getLayoutId() ,null, false);
        baseBinder.containerFrame.addView(binder.getRoot());


        baseBinder.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(100);
                finish();
            }
        });


    }

    @Override
    public void requestPermission(IPermission.permissionCallbacks permissionCallbacks) {

        this.permissionCallbacks = permissionCallbacks;

        if(checkForPerrmissions()){

            permissionCallbacks.onPermissionGranted();
        }
        else
        {
            if(checkForPermissionRationale())
            {
                permissionCallbacks.onPermissionDenied();
            }
            else
            {
                ActivityCompat.requestPermissions(BaseActivity.this,permissions,1000);
            }
        }
    }


    private boolean checkForPerrmissions()
    {
        boolean allGranted = false;
        for (String grantResult : permissions)
        {
            if (ActivityCompat.checkSelfPermission(this, grantResult) == PackageManager.PERMISSION_GRANTED)
            {
                allGranted = true;
            }
            else
            {
                allGranted = false;
                break;
            }
        }

        return allGranted;
    }


    private boolean checkForPermissionRationale()
    {
        boolean flag = false;

            for (String grantResult : permissions)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, grantResult))
                {
                    flag = true;
                }
                else
                {
                    flag = false;
                    break;
                }
            }

         return flag;
    }

    public ViewDataBinding getBinder()
    {
        return binder;
    }

    public abstract int getLayoutId();



    @Override
    public void hideProgress() {

        if(progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg, int duration) {

        Toast.makeText(getApplicationContext(),msg,duration).show();
    }

    @Override
    public void showErrorDialog(String errorCode, String errorMsg) {

        errorDialog = new Dialog(this);
        errorDialog.getWindow().setFlags(Window.FEATURE_NO_TITLE,Window.FEATURE_NO_TITLE);
        errorDialog.setContentView(R.layout.dialog_error);
        errorDialog.setCanceledOnTouchOutside(false);

        ((TextView)errorDialog.findViewById(R.id.errorCode)).setText("Error Code is "+ ErrorMessageFactory.getMessage(errorCode));
        ((TextView)errorDialog.findViewById(R.id.errorMsg)).setText("Error Msg is "+ errorMsg);

        errorDialog.show();
    }

    @Override
    public void hideErrorDialog() {

        if(errorDialog!=null)
        {
            errorDialog.dismiss();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(Constant.TAG,"result code "+resultCode);

        if(resultCode == 100)
        {
            if(!(this instanceof LoginActivity))
            {
                setResult(100);
                finish();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1000)
        {
//            requestPermission(new IPermission.permissionCallbacks() {
//
//                @Override
//                public void onPermissionGranted() {
//
//                    Log.i(Constant.TAG,"Granted onRequestPermissionsResult");
//                    Toast.makeText(getApplicationContext(),"Granted onRequestPermissionsResult",Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onPermissionDenied() {
//
//                    Toast.makeText(getApplicationContext(),"Denied onRequestPermissionsResult",Toast.LENGTH_SHORT).show();
//                    ActivityCompat.requestPermissions(BaseActivity.this,permissions,1000);
//                }
//            });

            if(checkForPerrmissions())
            {
                permissionCallbacks.onPermissionGranted();
            }
            else
            {
                //ActivityCompat.requestPermissions(BaseActivity.this,permissions,1000);
                permissionCallbacks.onPermissionDenied();
            }
        }
    }
}
