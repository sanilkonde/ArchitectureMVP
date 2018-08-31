package com.example.sanil.mvpexample.data.remote;

import android.util.Log;

import com.example.sanil.mvpexample.Constant;
import com.example.sanil.mvpexample.model.ApiResponse;
import com.example.sanil.mvpexample.data.IDatasource;



import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sanil on 08-05-2018.
 */

public class RemoteRepository implements IDatasource.Remote {

    IMvpApiService mvpApiServices;

    private IMvpApiService mvpApiService()
    {
        if(mvpApiServices==null)
        {
            mvpApiServices = RetrofitConfiguration.getClient().create(IMvpApiService.class);
        }
        return mvpApiServices;
    }


    @Override
    public void authenticateUser(String userName, String password, final IDatasource.RemoteCallback<String> callback) {

        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",userName);
            jsonObject.put("password",password);

            mvpApiService().authenticateService(jsonObject.toString()).enqueue(new retrofit2.Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    parseResponse(callback,response);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                    parseFailure(callback,t);
                }
            });

        }
        catch (Exception ex)
        {
            parseFailure(callback,ex);
            Log.i(Constant.TAG,"authenticateUser ex "+ex);
        }
    }

    @Override
    public void getList(final IDatasource.RemoteCallback<String> callback) {

        try
        {
            mvpApiService().getListService().enqueue(new retrofit2.Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    Log.i(Constant.TAG,"in getList onSuccess "+response);
                    parseResponse(callback,response);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                    Log.i(Constant.TAG,"in getList onFailure "+t.getMessage());
                    parseFailure(callback,t);
                }
            });
        }
        catch (Exception ex)
        {
            Log.i(Constant.TAG,"getList Ex "+ex);
        }

    }

    private void parseFailure(IDatasource.RemoteCallback<String> callback,  Throwable t){
        callback.onFailure("002", ErrorMessageFactory.CODE_002+" "+t.getMessage());
    }

    private void parseResponse(IDatasource.RemoteCallback<String> callback,Response<ApiResponse> response)
    {
        try
        {
            if(response.isSuccessful())
            {
                if(response.body().getErrorCode().equalsIgnoreCase("0"))
                {
                    callback.onSuccess(response.body().getData());
                }
                else
                {
                    callback.onFailure(response.body().getErrorCode(),response.body().getErrorMessage());
                }
            }
            else
            {
                callback.onFailure(String.valueOf(response.code()),response.message());
            }
        }
        catch (Exception ex)
        {
            Log.i(Constant.TAG,"in parseResponse "+ex);
        }
    }
}
