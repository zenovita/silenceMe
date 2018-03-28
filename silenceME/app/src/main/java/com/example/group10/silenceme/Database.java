package com.example.group10.silenceme;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.*;
import java.util.Calendar;


public class Database extends SQLiteOpenHelper {
    private static final String TAG = "com.example.group10";

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "silencemedata";
    //Table and Variable Names
    private static final String TABLE_NAME = "event_list";
    private static String EVENT_NAME = "event_name";
    private static String EVENT_ID = "event_id";
    private static String EVENT_START_DATE = "start_date";
    private static String EVENT_END_DATE = "end_date";
    private static String EVENT_LOCATION = "event_location";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EVENT_NAME + " TEXT,"
                + EVENT_START_DATE + " TEXT,"
                + EVENT_END_DATE + " TEXT,"
                + EVENT_LOCATION + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }


    // Adding Event to Database
    public void addEventDB(String event_name, String event_location, String start_date, String end_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event_name);
        values.put(EVENT_LOCATION, event_location);
        values.put(EVENT_START_DATE, start_date);
        values.put(EVENT_END_DATE, end_date);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteEventDB(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, EVENT_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public HashMap<String, String> eventDetails(int id) {
        HashMap<String, String> event = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE event_id=" + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "buraya girildi:1 selectQuery = " + selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.i(TAG, "buraya girildi:2");
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            event.put(EVENT_NAME, cursor.getString(1));
            event.put(EVENT_START_DATE, cursor.getString(2));
            event.put(EVENT_END_DATE, cursor.getString(3));
            event.put(EVENT_LOCATION, cursor.getString(4));
        }
        cursor.close();
        db.close();
        return event;
    }

    public ArrayList<HashMap<String, String>> events() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> eventlist = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                eventlist.add(map);
            } while (cursor.moveToNext());
        }
        db.close();

        return eventlist;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
