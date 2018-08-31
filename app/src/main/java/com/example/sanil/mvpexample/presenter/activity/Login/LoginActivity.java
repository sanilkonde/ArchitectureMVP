package com.example.sanil.mvpexample.presenter.activity.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;




import com.example.sanil.mvpexample.Constant;
import com.example.sanil.mvpexample.IPermission;
import com.example.sanil.mvpexample.MVPApp;
import com.example.sanil.mvpexample.data.NetworkRepository;
import com.example.sanil.mvpexample.data.remote.RemoteRepository;

import com.example.sanil.mvpexample.presenter.activity.BaseClass.BaseActivity;
import com.example.sanil.mvpexample.presenter.activity.Home.HomeActivity;
import com.example.sanil.mvpexample.presenter.activity.R;
import com.example.sanil.mvpexample.presenter.activity.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements ILoginView{


    @Inject
    LoginPresenterImpl presenter;
    @Inject
    NetworkRepository networkRepository;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = (ActivityMainBinding) binder;

        //presenter = new LoginPresenterImpl(this);
        ((MVPApp)getApplication()).getAppcomponent().inject(this);
        presenter.setView(this,this);
        presenter.createFiles();
        ((MVPApp)getApplication()).getAppcomponent().inject(presenter);
        ((MVPApp)getApplication()).getAppcomponent().inject(networkRepository);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void loginTapped(View view)
    {
        String username = activityMainBinding.userName.getText().toString();
        String pass = activityMainBinding.password.getText().toString();

        presenter.login(username,pass);
    }


    @Override
    public void loginValiationError() {

        Toast.makeText(getApplicationContext(),"validation error !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivityForResult(intent,100);
    }


    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();
    }

}
