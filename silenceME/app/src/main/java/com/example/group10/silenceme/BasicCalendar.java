package com.example.group10.silenceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;


public class BasicCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        CalendarView calendarView = (CalendarView) findViewById(R.id.cv_monthly_cv);
        Button btn = (Button) findViewById(R.id.cv_menu_button);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(),""+dayOfMonth+"."+month+"."+year,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),NewEventActivity.class);
                i.putExtra("class",getComponentName().getShortClassName());
                i.putExtra("year",year);
                i.putExtra("month",month);
                i.putExtra("day",dayOfMonth);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasicCalendar.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
