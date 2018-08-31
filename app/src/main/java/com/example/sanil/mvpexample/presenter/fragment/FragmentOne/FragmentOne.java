package com.example.sanil.mvpexample.presenter.fragment.FragmentOne;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sanil.mvpexample.presenter.activity.BaseClass.BaseFragment;
import com.example.sanil.mvpexample.presenter.activity.R;
import com.example.sanil.mvpexample.presenter.activity.databinding.FragmentOneBinding;


public class FragmentOne extends BaseFragment {

    FragmentOneBinding fragmentOneBinding;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one,null,false);
        rootView = fragmentOneBinding.getRoot();

        return rootView;
    }

}
