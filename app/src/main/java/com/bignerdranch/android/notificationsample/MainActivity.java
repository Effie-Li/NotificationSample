package com.bignerdranch.android.notificationsample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Afei on 15-11-17.
 * Reference: http://androidideasblog.blogspot.co.uk/2011/07/alarmmanager-and-notificationmanager.html
 * Tester app for notification
 */

public class MainActivity extends AppCompatActivity {

    //member view components
    private Switch mNotificationSwitch;
    private TextView mNotificationSwitchLabel;

    //notification related fields
    SharedPreferences mySharedPreferences;
    public static final String MyPREFERENCES = "My Preferences";
    public static final String NotificationPREFERENCE = "Notification";
    private boolean notificationOn;
    private AlarmService alarmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //set initial notification state
        //will set to true if stored preference is not found
        alarmService = new AlarmService(MainActivity.this);
        notificationOn = mySharedPreferences.getBoolean(NotificationPREFERENCE, true);
        setAlarm(notificationOn);

        mNotificationSwitch = (Switch) findViewById(R.id.notification_switch);
        mNotificationSwitch.setChecked(notificationOn);
        mNotificationSwitchLabel = (TextView) findViewById(R.id.notification_switch_label);

        mNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set related views
                notificationOn = isChecked;

                //update value stored in shared preferences
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putBoolean(NotificationPREFERENCE, notificationOn);
                editor.apply();

                //update alarm service
                setAlarm(notificationOn);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setAlarm(boolean notificationOn){
        if (notificationOn)
            alarmService.startAlarm();
        else
            alarmService.stopAlarm();
    }

}
