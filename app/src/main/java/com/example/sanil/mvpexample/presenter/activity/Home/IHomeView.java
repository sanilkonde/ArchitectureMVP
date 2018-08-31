package com.example.sanil.mvpexample.presenter.activity.Home;



import android.databinding.ObservableArrayList;

import com.example.sanil.mvpexample.presenter.activity.BaseClass.IBaseView;

import java.util.List;

/**
 * Created by sanil on 20-04-2018.
 */

public interface IHomeView extends IBaseView {

    void setItems(ObservableArrayList<String> items);
    void navigateToActivityfragment();
}
