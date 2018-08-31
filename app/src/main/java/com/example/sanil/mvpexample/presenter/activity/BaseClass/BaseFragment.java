package com.example.sanil.mvpexample.presenter.activity.BaseClass;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by sanil on 10-05-2018.
 */

public class BaseFragment extends Fragment implements IBaseFragmentView {

    ProgressDialog progressDialog;

    @Override
    public void showLoader() {

        if(progressDialog==null)
        {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Please wait...");
        }

        progressDialog.show();
    }

    @Override
    public void dismissLoader() {

        if(progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg, int duration) {

        Toast.makeText(getActivity(),msg,duration).show();
    }
}
