package com.cashpp.cash;

import android.app.Application;
import android.util.Log;

/**
 * Created by lopa on 6/17/16.
 */
public class CashppApplication extends Application{
    private static final String TAG = "CashppApplication";
    private static CashppApplication instance = null;

    public static CashppApplication getInstance() {
        return instance;    // Singleton
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "CashppApplication.onCreate()");
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CashppApplication.Terminate()");
    }
}
