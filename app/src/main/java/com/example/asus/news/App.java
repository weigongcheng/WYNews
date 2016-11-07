package com.example.asus.news;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.vov.vitamio.Vitamio;

/**
 * Created by ASUS on 2016/10/31.
 */

public class App extends Application {
    private RequestQueue requestQueue;

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Vitamio.isInitialized(this);
        requestQueue = Volley.newRequestQueue(this);
    }
}
