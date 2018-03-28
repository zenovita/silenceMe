package com.example.group10.silenceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "com.example.group10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buttonClicked(View v) {
        switch (v.getId()){
            case R.id.menu_add_event_button:{
                Intent addEventIntent = new Intent(getApplicationContext(),NewEventActivity.class);
                addEventIntent.putExtra("class",getComponentName().getShortClassName());
                startActivity(addEventIntent);
                break;
            }
            case R.id.menu_add_weekly_schedule_button:{
                Intent addWeeklyScheduleIntent = new Intent(getApplicationContext(),WeeklySchedule.class);
                startActivity(addWeeklyScheduleIntent);
                break;
            }
            case R.id.menu_calendar_button:{
                Intent calendarIntent = new Intent(getApplicationContext(),BasicCalendar.class);
                startActivity(calendarIntent);
                break;
            }
            case R.id.menu_upcoming_events_button:{
                Intent upcomingEventsIntent = new Intent(getApplicationContext(),UpcomingEventsView.class);
                startActivity(upcomingEventsIntent);
                Log.i(TAG,"buraya girildi:00");
                break;
            }
            case R.id.menu_settings_button:{
                Intent settingsIntent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            }

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
