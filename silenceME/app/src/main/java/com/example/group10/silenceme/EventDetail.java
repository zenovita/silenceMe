package com.example.group10.silenceme;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class EventDetail extends AppCompatActivity {
    private static final String TAG = "com.example.group10";
    Button b1;
    TextView t1,t2,t3,t4;
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        b1 = (Button)findViewById(R.id.button2);

        t1 = (TextView)findViewById(R.id.ed_name);
        t2 = (TextView)findViewById(R.id.ed_start_date);
        t3 = (TextView)findViewById(R.id.ed_end_date);
        t4 = (TextView)findViewById(R.id.ed_location);


        Intent intent=getIntent();
        id = intent.getIntExtra("event_id", 0);

        Database db = new Database(getApplicationContext());

        HashMap<String, String> map = db.eventDetails(id);



        t1.setText(map.get("event_name"));
        t2.setText(map.get("start_date"));
        t3.setText(map.get("end_date"));
        t4.setText(map.get("event_location"));




        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EventDetail.this);
                alertDialog.setTitle("Warning");
                alertDialog.setMessage("Do you really want to delete this event?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Database db = new Database(getApplicationContext());
                        db.deleteEventDB(id);
                        Toast.makeText(getApplicationContext(), "Event is deleted successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), UpcomingEventsView.class);
                        startActivity(intent);
                        finish();

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                    }
                });
                alertDialog.show();

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
