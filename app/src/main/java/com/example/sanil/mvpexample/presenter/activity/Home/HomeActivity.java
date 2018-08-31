package com.example.sanil.mvpexample.presenter.activity.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.sanil.mvpexample.Constant;

import com.example.sanil.mvpexample.MVPApp;
import com.example.sanil.mvpexample.data.remote.RemoteRepository;

import com.example.sanil.mvpexample.presenter.activity.BaseClass.BaseActivity;
import com.example.sanil.mvpexample.presenter.activity.FragmentDemo.ActivityFragment;
import com.example.sanil.mvpexample.presenter.activity.R;
import com.example.sanil.mvpexample.presenter.activity.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity implements IHomeView,CustomItemClickListener{//},AdapterView.OnItemClickListener{


    @Inject
    HomePresenterImpl presenter;
    ActivityHomeBinding activityHomeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        activityHomeBinding = (ActivityHomeBinding)getBinder(); //DataBindingUtil.setContentView(this,R.layout.activity_home);

//        listView.setOnItemClickListener(this);
//        presenter = new HomePresenterImpl(this);
        ((MVPApp)getApplication()).getAppcomponent().inject(this);
        presenter.setHomePresenterView(this);
        ((MVPApp)getApplication()).getAppcomponent().inject(presenter);

        Log.i(Constant.TAG,"onCreate called");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setItems(ObservableArrayList<String> items) {

//        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items)); // For ListView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityHomeBinding.recyclerView.setLayoutManager(layoutManager);
        activityHomeBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityHomeBinding.recyclerView.setAdapter(new RecyclerAdpter(items,this,this));
    }

    @Override
    public void navigateToActivityfragment() {

        Intent intent = new Intent(HomeActivity.this, ActivityFragment.class);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Constant.TAG,"onResume called !");
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        Log.i(Constant.TAG,"in onItemClick "+i);
//        presenter.click(i);
//    }

    @Override
    public void onItemClick(View v, int position) {
        presenter.click(position);
    }




}
