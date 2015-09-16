package com.example.jkt.wall2wall0;

import android.app.Application;
import android.content.Context;

/**
 * Created by James on 4/17/2015.
 */
public class ApplicationContextProvider extends Application {

    /**
     * Keeps a reference of the application context
     */
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();

    }

    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return sContext;
    }

}