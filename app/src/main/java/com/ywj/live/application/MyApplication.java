package com.ywj.live.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       //基本使用
        Fresco.initialize(this);
    }
}
