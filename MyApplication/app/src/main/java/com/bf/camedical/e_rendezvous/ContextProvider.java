package com.bf.camedical.e_rendezvous;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sony on 19/11/2015.
 */
public class ContextProvider extends Application {
    private static Context mycontext;
    @Override
    public void onCreate(){
        super.onCreate();
        mycontext=getApplicationContext();
    }
    public static Context getAppContext(){
        return mycontext;
    }
}

