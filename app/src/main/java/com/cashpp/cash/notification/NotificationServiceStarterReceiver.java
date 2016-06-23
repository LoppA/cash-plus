package com.cashpp.cash.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cashpp.cash.notification.NotificationEventReceiver;

import java.util.Calendar;

/**
 * Created by lopa on 6/21/16.
 */
public final class NotificationServiceStarterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        NotificationEventReceiver.setupAlarm(context, calendar.getTimeInMillis());
    }
}