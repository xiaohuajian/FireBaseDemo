package com.sjh.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        FirebaseMessaging.getInstance().subscribeToTopic("topic");
       // FirebaseMessaging.getInstance().send("topic");
    }


    public void clickView(View view){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        Log.d("tag", "clickView: ");
    }

    public void clickView1(View view){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("app_use_total_time", bundle);
        Log.d("tag", "clickView: ");
    }

    public void testCrash(View view){
        try {
            Log.d(TAG, "testCrash: ");
            //Crashlytics.getInstance().crash();
            throw new RuntimeException("人为制造一个崩溃");
        }catch (Exception e){
            Log.d(TAG, "testCrash: " + e.toString());
            Crashlytics.logException(e);
        }
    }


    public void customCrashLog(View view){
        try {
            Log.d(TAG, "customCrashLog ");
            //Crashlytics.getInstance().crash();
            throw new Exception("人为制造一个自定义的customCrashLog崩溃");
        }catch (Exception e){
            Log.d(TAG, "customCrashLog: " + e.toString());
            Crashlytics.log(Log.DEBUG, "customCrashLog" , e.toString());
        }
    }

    public void customCrashKey(View view){
        try {
            Log.d(TAG, "customCrashKey ");
            //Crashlytics.getInstance().crash();
            throw new RuntimeException("人为制造一个自定义的customCrashKey崩溃");
        }catch (Exception e){
            Log.d(TAG, "customCrashKey: " + e.toString());
            Crashlytics.setString("customCrashKey", e.toString());
        }
    }

    public void realCrash(View view){
        Crashlytics.setString("a / 0 ", "div");
        int a = 3 / 0 ;
    }

    public void makeCrash(View view){

        int[] a = new int[]{0 ,1};
        int b = a[2];
        Crashlytics.setString("indexOutOf", "数组越界");
    }

    public void makeCrashTwo(View view){
        Crashlytics.setString("indexOutOf", "数组越界");
        int[] a = new int[]{0 ,1};
        int b = a[2];

    }

    public void makeCrashThree(View view){
        Crashlytics.setString("indexOutOf makeCrashThree", "数组越界");
        int[] a = new int[]{0 ,1};
        int b = a[2];

    }

    public void enbaleCrash(View view){
        FirebaseCrash.setCrashCollectionEnabled(true);
    }
}
