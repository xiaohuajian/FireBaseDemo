package com.sjh.firebasedemo;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.firebase.iid.FirebaseInstanceId;

import io.fabric.sdk.android.Fabric;


public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        init();
        configCrashlytics();
    }

    private void init() {
        String id = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "init: " + id);

    }


    // debug 状态不收集日志
    private void configCrashlytics(){
//        CrashlyticsCore crashlyticsCore = new CrashlyticsCore.Builder()
//                .disabled(BuildConfig.DEBUG).build();
//        Fabric.with(this, new Crashlytics.Builder().core(crashlyticsCore).build());
//

//        Crashlytics crashlyticsKit = new Crashlytics.Builder()
//                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
//                .build();
//
//// Initialize Fabric with the debug-disabled crashlytics.
//        Fabric.with(this, crashlyticsKit);


    }
}
