package com.example.asus.news.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ASUS on 2016/10/30.
 */

public class NetUtil {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .build();
    public static OkHttpClient getClient(){
        return client;
    }
    public static boolean getNetworkState(Context context){
        ConnectivityManager ss = (ConnectivityManager) context.getSystemService (context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = ss.getActiveNetworkInfo();
        //如果手机没联网或者网络不可用，则不执行网络请求
        if (activeNetwork == null || !activeNetwork.isAvailable()) {
            return false;
        }
        return true;
    }

}
