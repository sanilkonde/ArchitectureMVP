package com.example.sanil.mvpexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sanil on 09-05-2018.
 */

public class ApiResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("data")
    @Expose
    private String data;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getData() {
        return data;
    }

}
