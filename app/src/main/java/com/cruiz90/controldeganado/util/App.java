package com.cruiz90.controldeganado.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.cruiz90.controldeganado.BuildConfig;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class App extends Application {
    // System version flags

    public final static class v {
        public final static boolean Gingerbread_9 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
        public final static boolean Gingerbread_10 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1;
        public final static boolean Honeycomb_11 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
        public final static boolean Honeycomb_12 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
        public final static boolean Honeycomb_13 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2;
        public final static boolean IceCreamSandwich_14 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH; // I scream "sandwich!"
        public final static boolean IceCreamSandwich_15 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
        public final static boolean JellyBean_16 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
        public final static boolean JellyBean_17 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
        public final static boolean JellyBean_18 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
        public final static boolean KitKat_19 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        public final static boolean KitKat_20 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
        public final static boolean Lollipop_21 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        public final static boolean Lollipop_22 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    // App variants and features

    public static class feat { // App features control

    }

    // Testing variables (all false in production)

    /**
     * debug mode
     */
    public final static boolean DEBUG = BuildConfig.DEBUG;

    //Constants
    public static final String APP_NAME = "ControlDeGanado";

    protected String userAgent;


    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    /**
     * Schedules runnable to be executed after seconds. Runs in a secondary thread
     */
    public static synchronized ScheduledFuture schedule(Runnable runnable, long seconds) {
        return SCHEDULER.schedule(runnable, seconds, TimeUnit.SECONDS);
    }

    // Application instance sky.com.App@85a2d0e

    /**
     * Shared application context
     */
    public static Context context;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {

        super.onCreate();
        Log.d("app", "getting context");
        context = this;
        sharedPreferences = getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);

        JodaTimeAndroid.init(App.context);
        DBConnection.getInstance();
    }
}
