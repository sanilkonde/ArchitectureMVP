package com.example.sanil.mvpexample.presenter.activity.Login;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.sanil.mvpexample.Constant;
import com.example.sanil.mvpexample.IPermission;
import com.example.sanil.mvpexample.data.IDatasource;
import com.example.sanil.mvpexample.data.NetworkRepository;
import com.example.sanil.mvpexample.data.local.SharedPrefRepository;
import com.example.sanil.mvpexample.data.remote.ErrorMessageFactory;
import com.example.sanil.mvpexample.data.remote.RemoteRepository;
import com.example.sanil.mvpexample.presenter.activity.BaseClass.BaseActivity;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by sanil on 19-04-2018.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private ILoginView loginView;
    private IPermission iPermission;

    @Inject
    RemoteRepository remoteRepository;
    @Inject
    SharedPrefRepository sharedPrefRepository;
    @Inject
    NetworkRepository networkRepository;

    @Override
    public void login(final String userName, String password) {

        if(userName.length()==0 || password.length()==0)
        {
            loginView.loginValiationError();
        }
        else
        {
            if(networkRepository.isNetWorkAvailable())
            {
                loginView.showProgress();
                remoteRepository.authenticateUser(userName, password, new IDatasource.RemoteCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i(Constant.TAG,"respons in onSuccess "+response);
                        loginView.hideProgress();

//                    sharedPrefRepository.putString("UserName",userName);


                        try
                        {
                            loginView.showToast(""+new JSONObject(response).getString("msg"),Toast.LENGTH_SHORT);
                            loginView.navigateToHome();
                        }
                        catch (Exception ex)
                        {
                            Log.i(Constant.TAG,"in onSuccess "+ex);
                        }
                    }

                    @Override
                    public void onFailure(String error_code, String msg) {
                        Log.i(Constant.TAG,"msg on Failure "+msg+"   errorcode "+error_code);

                        loginView.hideProgress();
                        loginView.showToast(msg,Toast.LENGTH_SHORT);
                    }
                });
            }
            else
            {
                loginView.showToast("No Internet",Toast.LENGTH_SHORT);
            }
        }

    }

    @Override
    public void onResume() {

        Log.i(Constant.TAG,"is network "+networkRepository.isNetWorkAvailable());

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setView(ILoginView view,IPermission iPermission) {

        loginView = view;
        this.iPermission = iPermission;
    }

    @Override
    public void createFiles() {

        iPermission.requestPermission(new IPermission.permissionCallbacks() {
            @Override
            public void onPermissionGranted() {

                loginView.showToast("Granted",Toast.LENGTH_SHORT);
            }

            @Override
            public void onPermissionDenied() {

                loginView.showToast("Denied",Toast.LENGTH_SHORT);
            }
        });
    }

}



