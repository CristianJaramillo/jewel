package com.jccg.jewel;

import android.app.Application;

import io.realm.Realm;

/**
 *
 */
public class JewelApplication extends Application {

    /**
     *
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        Realm.init(this);
    }

}
