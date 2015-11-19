package com.bignerdranch.android.notificationsample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Afei on 15-11-17.
 * Reference: http://androidideasblog.blogspot.co.uk/2011/07/alarmmanager-and-notificationmanager.html
 */
public class AlarmService {
    private Context context;
    private PendingIntent mAlarmSender;

    public AlarmService(Context context) {
        this.context = context;
        mAlarmSender = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);
    }

    public void startAlarm(){
        // Set the alarm to 10 seconds from now
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);


        /* set notification time - hard code for now
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        */

        long NotificationTime = calendar.getTimeInMillis();

        // Schedule the alarm
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, NotificationTime, mAlarmSender);
    }
}
