package com.example.group10.silenceme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "com.example.group10";
    public static final String PREFS_NAME = "settings";
    public static SharedPreferences settings;
    private int currentMode;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getSharedPreferences(PREFS_NAME, 0);

        radioGroup = (RadioGroup) findViewById(R.id.s_silence_mode_rg);
        radioButton1 = (RadioButton) findViewById(R.id.s_vibration_rb);
        radioButton2 = (RadioButton) findViewById(R.id.s_full_silent_rb);

        Log.i(TAG,"load Preference");
        radioGroup.check(loadPreference());


    }

    @Override
    protected void onDestroy() {
//        Log.i(TAG,"onDestroy is called");
        savePreference();
//        Log.i(TAG,"onDestroy is called2");
        super.onDestroy();
    }

//    public void onRadioButtonClicked(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//
//        String silenceMode = "";
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.s_vibration_rb:
//                if (checked)
//                    silenceMode = "vibration";
//                    break;
//            case R.id.s_full_silent_rb:
//                if (checked)
//                    silenceMode = "full silent";
//                    break;
//        }
//
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putString("silenceMode",silenceMode);
//        editor.apply();
//
//
//    }


    public void savePreference(){
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id", radioGroup.getCheckedRadioButtonId());
        editor.apply();
    }


    public int loadPreference(){
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        return settings.getInt("id",R.id.s_full_silent_rb);

    }

    public static String getSilenceMode(){

        int id =settings.getInt("id",R.id.s_full_silent_rb);

        String silenceMode;

        if(id == R.id.s_vibration_rb){
            silenceMode = "vibration";
        }
        else {
            silenceMode = "full silent";
        }
        return silenceMode;
    }

    public void tester(View v){
        savePreference();

        switch (v.getId()){
            case R.id.s_silence_now_button:{
//                AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//                currentMode = audioManager.getRingerMode();

                Intent silencerIntent = new Intent(SettingsActivity.this, Silencer.class);
                PendingIntent pendingSilencer = PendingIntent.getService(this, 0, silencerIntent, 0);
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3000, pendingSilencer);
                break;
            }
            case R.id.s_unsilence_now_button:{
                Intent unsilencerIntent = new Intent(SettingsActivity.this, UnSilencer.class);
                PendingIntent pendingUnsilencer = PendingIntent.getService(this, 0, unsilencerIntent, 0);
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3000, pendingUnsilencer);
                break;
            }
        }
    }
}
