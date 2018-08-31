package com.example.sanil.mvpexample.data.remote;

/**
 * Created by sanil on 09-05-2018.
 */

public class ErrorMessageFactory {

    public static String CODE_000 = "Please connect to Internet";
    public static String CODE_001 = "Invalid Response from Server";
    public static String CODE_002 = "Internal Error";



    public static String getMessage(String code)
    {
        if(code.equalsIgnoreCase("000"))
        {
            return CODE_000;
        }
        else if(code.equalsIgnoreCase("001"))
        {
            return CODE_001;
        }
        else if(code.equalsIgnoreCase("002"))
        {
            return CODE_002;
        }
        else
        {
            return "Unknown Error";
        }
    }
}
