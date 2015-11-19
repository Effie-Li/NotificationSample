package com.bignerdranch.android.notificationsample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

/**
 * Created by Afei on 15-11-17.
 * Reference: http://androidideasblog.blogspot.co.uk/2011/07/alarmmanager-and-notificationmanager.html
 * Tester app for notification
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmService alarmService = new AlarmService(MainActivity.this);
        alarmService.startAlarm();
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

    public void onResume() {
        //reset alarm service
        super.onResume();
        AlarmService alarmService = new AlarmService(MainActivity.this);
        alarmService.startAlarm();
    }
}
