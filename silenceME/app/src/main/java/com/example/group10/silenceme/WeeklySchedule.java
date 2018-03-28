package com.example.group10.silenceme;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 *
 */
public class WeeklySchedule extends com.example.group10.silenceme.BaseActivity {
    private static final String TAG = "com.example.group10";

    ArrayList<HashMap<String, String>> eventList;
    String eventNames[];
    String startDates[];
    String endDates[];
    String locations[];
    Calendar startCalendars[];
    Calendar endCalendars[];
    int eventIds[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

//        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
//        Log.i(TAG,"buraya girildi:1");
//
//        Database db = new Database(getApplicationContext());
////        Log.i(TAG,"buraya girildi:2");
//        eventList = db.events();
////        Log.i(TAG,"buraya girildi:3");
//        if(eventList.size()==0){
//            Toast.makeText(getApplicationContext(), "No event has been created yet!", Toast.LENGTH_LONG).show();
//        }else {
//            eventNames = new String[eventList.size()];
//            Log.i(TAG,"buraya girildi:2");
//            eventIds = new int[eventList.size()];
//
//            startDates = new String[eventList.size()];
//
//            endDates = new String[eventList.size()];
//
//            locations = new String[eventList.size()];
//
//            startCalendars = new Calendar[eventList.size()];
//
//            endCalendars = new Calendar[eventList.size()];
//
//
//            Log.i(TAG,"buraya girildi:3");
//            for (int i = 0; i < eventList.size(); i++) {
//
//                eventNames[i] = eventList.get(i).get("event_name");
//                startDates[i] = eventList.get(i).get("start_date");
//                endDates[i] = eventList.get(i).get("end_date");
//                locations[i] = eventList.get(i).get("event_location");
//
//                eventIds[i] = Integer.parseInt(eventList.get(i).get("event_id"));
//
//            }
//            Log.i(TAG,"buraya girildi:4");
//            startCalendars = stringToCalendar(startDates);
//            endCalendars = stringToCalendar(endDates);
//            Log.i(TAG,"buraya girildi:5");
//            for(int i = 0;i<eventList.size();i++){
//                Log.i(TAG,"buraya girildi:6");
//                WeekViewEvent event = new WeekViewEvent(i+1,eventNames[i],locations[i],startCalendars[i],endCalendars[i]);
//                Log.i(TAG,"buraya girildi:7");
//                int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
//                Log.i(TAG,"buraya girildi:8");
//                event.setColor(randomAndroidColor);
//                Log.i(TAG,"buraya girildi:9");
//                events.add(event);
//                Log.i(TAG,"buraya girildi:10");
//            }
//
//        }


//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 1);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 4);
//        endTime.set(Calendar.MINUTE, 30);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 4);
//        startTime.set(Calendar.MINUTE, 20);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 5);
//        endTime.set(Calendar.MINUTE, 0);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 2);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        startTime.add(Calendar.DATE, 1);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 15);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 1);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
//        startTime.set(Calendar.HOUR_OF_DAY, 15);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);


//        Log.i(TAG,"buraya girildi:11");
        return events;

    }

//    private Calendar[] stringToCalendar(String[] Dates) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss", Locale.getDefault());
//        Log.i(TAG,"buraya girildi:metod1");
//        Calendar calArray[] = new Calendar[startDates.length];
//        Log.i(TAG,"buraya girildi:metod2");
//        try {
//            Log.i(TAG,"buraya girildi:metod3");
//            for (int i = 0; i < startDates.length; i++) {
//                Log.i(TAG,"buraya girildi:metod4");
//                Date date = sdf.parse(startDates[i]);
//
//                Log.i(TAG,"buraya girildi:metod5");
//                Calendar cal = Calendar.getInstance();
//                Log.i(TAG,"buraya girildi:metod6");
//                cal.setTime(date);
//                Log.i(TAG,"buraya girildi:metod7");
//                startCalendars[i]=cal;
//                Log.i(TAG,"buraya girildi:metod8");
//
//            }
//        }
//        catch (ParseException p){
//            p.printStackTrace();
//        }
//        Log.i(TAG,"buraya girildi:metod9");
//        return calArray;
//    }

}


