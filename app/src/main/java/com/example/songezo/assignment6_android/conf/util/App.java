package com.example.songezo.assignment6_android.conf.util;

import android.app.Application;
import android.content.Context;
import android.media.Image;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.songezo.assignment6_android.conf.databases.HashBitmapCache;

/**
 * Created by Songezo on 2016-04-20.
 */
public class App extends Application {

    private static Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public static App singleton;

    public void ocCreate(){
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext(){
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();

    public static synchronized App getInstance(){
        return singleton;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext(), new HashHttpStack());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if (imageLoader == null){
            imageLoader = new ImageLoader(this.requestQueue, new HashBitmapCache());
        }
        return this.imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPandingRequests(Object tag){
        if (requestQueue != null){
            requestQueue.cancelAll(tag);
        }
    }
}
