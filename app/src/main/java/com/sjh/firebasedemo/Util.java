package com.sjh.firebasedemo;

import android.annotation.TargetApi;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class Util {
    private static final String TAG = "Util";

    public static int luanchCount ;

    @SuppressWarnings("ResourceType")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static ArrayList<UsageStats> getUsageList(Context context, long startTime, long endTime) throws IllegalAccessException {
        SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");

        ArrayList<UsageStats> list = new ArrayList<>();

        UsageStatsManager mUsmManager = (UsageStatsManager) context.getSystemService("usagestats");
        Map<String, UsageStats> map = mUsmManager.queryAndAggregateUsageStats(startTime, endTime);
        for (Map.Entry<String, UsageStats> entry : map.entrySet()) {
            UsageStats stats = entry.getValue();
            if(stats.getPackageName().equals("com.sjh.firebasedemo") && stats.getTotalTimeInForeground() > 0){
                list.add(stats);
                Log.i(TAG," EventUtils-getUsageList()   stats:" + stats.getPackageName() + "   TotalTimeInForeground = " + (stats.getTotalTimeInForeground()));
                Field field = null;
                try {
                    field = stats.getClass().getDeclaredField("mLaunchCount");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                luanchCount = (int) field.get(stats);
                Log.i(TAG, "getUsageList: 次数" + luanchCount);
            }

        }

        return list;
    }


    public static int getLaunchCount(UsageStats usageStats) throws IllegalAccessException {
        Field field = null;
        try {
            field = usageStats.getClass().getDeclaredField("mLaunchCount");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (int) field.get(usageStats);
    }
}
