package com.example.group10.silenceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UpcomingEventsView extends AppCompatActivity {
    private static final String TAG = "com.example.group10";

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> eventList;
    String eventNames[];
    String startDates[];
    String endDates[];
    String locations[];
    int eventIds[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events_view);
    }

    public void onResume()
    {
        super.onResume();
        Database db = new Database(getApplicationContext());
        eventList = db.events();

        if(eventList.size()==0){
            Toast.makeText(getApplicationContext(), "No event has been created yet!", Toast.LENGTH_LONG).show();
        }else{

            eventNames = new String[eventList.size()];

            eventIds = new int[eventList.size()];

            for(int i = 0; i< eventList.size(); i++){
                eventNames[i] = eventList.get(i).get("event_name");

                eventIds[i] = Integer.parseInt(eventList.get(i).get("event_id"));

            }

            lv = (ListView) findViewById(R.id.ue_list_lv);

            adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.event_name, eventNames);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    Intent intent = new Intent(getApplicationContext(), EventDetail.class);
                    intent.putExtra("event_id", (int) eventIds[arg2]);
                    startActivityForResult(intent,0);

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.upcoming_events, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_add:
                addEvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addEvent() {
        Intent i = new Intent(this, NewEventActivity.class);
        i.putExtra("class",getComponentName().getShortClassName());
        startActivity(i);
    }
}
