package com.cashpp.cash.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by lopa on 6/21/16.
 */
public final class NotificationServiceStarterReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        NotificationEventReceiver2.setupAlarm(context, calendar.getTimeInMillis());
    }
}