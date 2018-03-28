package com.example.group10.silenceme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;

public class AddEvent extends Service {
    private static final String TAG = "com.example.group10";
    public AddEvent() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String eventName = intent.getStringExtra("event_name");
        String location = intent.getStringExtra("location");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String repeatOption = intent.getStringExtra("repeat");
        Calendar startCal = (Calendar) intent.getSerializableExtra("start_cal");
        Calendar endCal = (Calendar) intent.getSerializableExtra("end_cal");

        //This event object is not used in the program but it could be useful after some improvements
        MainEvent event = new MainEvent(startDate,endDate,eventName,location);


        Database db = new Database(getApplicationContext());
        db.addEventDB(eventName,location,startDate,endDate);
        db.close();

        return Service.START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
