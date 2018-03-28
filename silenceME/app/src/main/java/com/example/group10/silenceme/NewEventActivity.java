package com.example.group10.silenceme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.EditTextPreference;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.group10.silenceme.R.array.repeat;
import static java.lang.Integer.parseInt;

public class NewEventActivity extends AppCompatActivity {
    private static final String TAG="com.example.group10";

    private DatePicker startDateInput;
    private DatePicker endDateInput;
    private TimePicker endTimeInput;
    private TimePicker startTimeInput;
    private EditText eventName;
    private EditText location;
    private Spinner repeat;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_view);


        //TODO save and cancel button


        eventName = (EditText) findViewById(R.id.ae_event_name_et);
        location = (EditText) findViewById(R.id.ae_location_et);
        startDateInput = (DatePicker) findViewById(R.id.ae_start_date_datep);
        endDateInput = (DatePicker) findViewById(R.id.ae_end_date_datep);
        startTimeInput = (TimePicker) findViewById(R.id.ae_start_time_timep);
        endTimeInput = (TimePicker) findViewById(R.id.ae_end_time_timep);
        repeat = (Spinner) findViewById(R.id.ae_repeat_spinner);

        Intent i = getIntent();
        String className = i.getStringExtra("class");
//        Log.i(TAG,"class name = "+className);

        if(className.equals(".WeeklySchedule")) {
            Calendar start = (Calendar) i.getSerializableExtra("calendar");
            //TODO check null
            startDateInput.init(start.get(Calendar.YEAR),start.get(Calendar.MONTH),start.get(Calendar.DAY_OF_MONTH),null);
            endDateInput.init(start.get(Calendar.YEAR),start.get(Calendar.MONTH),start.get(Calendar.DAY_OF_MONTH),null);
            startTimeInput.setHour(start.get(Calendar.HOUR_OF_DAY));
            startTimeInput.setMinute(start.get(Calendar.MINUTE));
            endTimeInput.setHour(start.get(Calendar.HOUR_OF_DAY));
            endTimeInput.setMinute(start.get(Calendar.MINUTE));
            repeat.setSelection(2);

        }
        if(className.equals(".BasicCalendar")){
            Calendar cal = Calendar.getInstance();
            int year = i.getIntExtra("year",cal.get(Calendar.YEAR));
            int month = i.getIntExtra("month",cal.get(Calendar.MONTH));
            int day = i.getIntExtra("day",cal.get(Calendar.DAY_OF_MONTH));
            startDateInput.init(year,month,day,null);
            endDateInput.init(year,month,day,null);
        }
    }

    public void aeButtonPressed(View v){
        switch (v.getId()){
            case R.id.ae_save_button:{
                String eventTitle = eventName.getText().toString();
                String eventLocation = location.getText().toString();
                String repeatOption = repeat.getSelectedItem().toString().toLowerCase();

                Calendar cal = Calendar.getInstance();

                Calendar startCal = Calendar.getInstance();
                startCal.set(startDateInput.getYear(), startDateInput.getMonth(), startDateInput.getDayOfMonth(),startTimeInput.getHour(),startTimeInput.getMinute(),0);

                Calendar endCal= Calendar.getInstance();
                endCal.set(endDateInput.getYear(),endDateInput.getMonth(),endDateInput.getDayOfMonth(),endTimeInput.getHour(),endTimeInput.getMinute(),0);

                if(startCal.compareTo(cal)<0){
                    Toast.makeText(getApplicationContext(),"Start date cannot be earlier than current date",Toast.LENGTH_SHORT).show();
                }
                else if(startCal.compareTo(endCal)>0){
                    Toast.makeText(getApplicationContext(),"Start date cannot be greater than end date",Toast.LENGTH_SHORT).show();
                }
                else{

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss", Locale.getDefault());

                    String startDate = sdf.format(startCal.getTime());
                    String endDate = sdf.format(endCal.getTime());

                    Intent i = new Intent(NewEventActivity.this,AddEvent.class);
                    i.putExtra("event_name",eventTitle);
                    i.putExtra("location",eventLocation);
                    i.putExtra("start_date",startDate);
                    i.putExtra("end_date",endDate);
                    i.putExtra("repeat",repeatOption);
                    i.putExtra("start_cal",startCal);
                    i.putExtra("end_cal",endCal);
                    startService(i);
                    Toast.makeText(getApplicationContext(),"Your event has been created",Toast.LENGTH_LONG).show();

                    int repeatFreq = repeatFrequency(repeatOption);

                    if(repeatFreq==-1){
                        Intent silencerIntent = new Intent(NewEventActivity.this, Silencer.class);
                        PendingIntent pendingSilencer = PendingIntent.getService(this, 0, silencerIntent, 0);
                        AlarmManager alarm1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarm1.set(AlarmManager.RTC_WAKEUP, startCal.getTimeInMillis(), pendingSilencer);

                        Intent unsilencerIntent = new Intent(NewEventActivity.this, UnSilencer.class);
                        PendingIntent pendingUnsilencer = PendingIntent.getService(this, 0, unsilencerIntent, 0);
                        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarm.set(AlarmManager.RTC_WAKEUP, endCal.getTimeInMillis(), pendingUnsilencer);
                    }
                    else {
                        Intent silencerIntent = new Intent(NewEventActivity.this, Silencer.class);
                        PendingIntent pendingSilencer = PendingIntent.getService(this, 0, silencerIntent, 0);
                        AlarmManager alarm1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarm1.setRepeating(AlarmManager.RTC_WAKEUP, startCal.getTimeInMillis(), 1000 * 60 * 60 * 24 * repeatFreq, pendingSilencer);

                        Intent unsilencerIntent = new Intent(NewEventActivity.this, UnSilencer.class);
                        PendingIntent pendingUnsilencer = PendingIntent.getService(this, 0, unsilencerIntent, 0);
                        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarm.setRepeating(AlarmManager.RTC_WAKEUP, endCal.getTimeInMillis(), 1000 * 60 * 60 * 24 * repeatFreq, pendingUnsilencer);
                    }


                    onBackPressed();

                }


                break;
            }
            case R.id.ae_cancel_button:{
                onBackPressed();
                break;
            }
        }

    }
    public void onBackPressed(){
        super.onBackPressed();
    }
    public int repeatFrequency(String repeat){
        if(repeat.equals("once"))
            return -1;
        else if(repeat.equals("daily"))
            return 1;
        else if(repeat.equals("weekly"))
            return 7;
        else
            return 30;
    }
}
