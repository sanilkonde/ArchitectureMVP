package com.example.sanil.mvpexample.presenter.activity.Home;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.util.Log;
import android.widget.Toast;

import com.example.sanil.mvpexample.Constant;
import com.example.sanil.mvpexample.MVPApp;
import com.example.sanil.mvpexample.data.IDatasource;
import com.example.sanil.mvpexample.data.NetworkRepository;
import com.example.sanil.mvpexample.data.local.SharedPrefRepository;
import com.example.sanil.mvpexample.data.remote.RemoteRepository;
import com.example.sanil.mvpexample.presenter.activity.Login.ILoginView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sanil on 20-04-2018.
 */

public class HomePresenterImpl implements HomePresenter {

    IHomeView view;
    @Inject
    RemoteRepository remoteRepository;
    @Inject
    SharedPrefRepository sharedPrefRepository;
    @Inject
    NetworkRepository networkRepository;


    @Override
    public void onResume() {

       Log.i(Constant.TAG,"user name is "+sharedPrefRepository.getString("UserName","NA"));

       if(networkRepository.isNetWorkAvailable())
       {
           if (view != null) {
               view.showProgress();
           }

           remoteRepository.getList(new IDatasource.RemoteCallback<String>() {
               @Override
               public void onSuccess(String response) {

                   Log.i(Constant.TAG,"in onSuccess of getList "+response);
                   view.hideProgress();

                   try
                   {
                       JSONArray jsonArray = new JSONArray(response);
                       ObservableArrayList<String> list = new ObservableArrayList<>();
                       for (int i=0;i<jsonArray.length();i++)
                       {
                           JSONObject jsonObject = jsonArray.getJSONObject(i);
                           list.add(jsonObject.getString("item"));
                       }

                       view.setItems(list);
                   }
                   catch (Exception ex)
                   {
                       Log.i(Constant.TAG,"remoteRepository.getList ex "+ex);
                   }
               }

               @Override
               public void onFailure(String error_code, String error_message) {

                   Log.i(Constant.TAG,"in onFailure of getList "+error_message);
                   view.hideProgress();
               }
           });
       }
       else
       {
           view.showToast("No Internet",Toast.LENGTH_SHORT);
       }
    }

    @Override
    public void onDestroy() {

        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void click(int pos) {

        Log.i(Constant.TAG, "in click " + pos);
        view.showToast("position : " + pos, Toast.LENGTH_SHORT);
    }

    @Override
    public void setHomePresenterView(IHomeView iHomeView) {
        view = iHomeView;
    }
}
