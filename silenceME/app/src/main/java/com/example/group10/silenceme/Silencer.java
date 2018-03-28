package com.example.group10.silenceme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

public class Silencer extends Service {
    private static final String TAG = "com.example.group10";
    private int currentMode;

    public Silencer() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String silenceMode = SettingsActivity.getSilenceMode();

//        Calendar calReceive = (Calendar) intent.getSerializableExtra("key");
//        Log.i(TAG,"calendar alındı: "+calReceive.toString());

        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

//        currentMode = audioManager.getRingerMode();
        Log.i(TAG,"buraya girildi:11 current_mode="+currentMode+" silenceMode="+silenceMode);

        if (silenceMode.equals("full silent")) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } else if (silenceMode.equals("vibration")) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }


//        Intent unsilencerIntent = new Intent(Silencer.this, UnSilencer.class);
////        unsilencerIntent.putExtra("modekey",currentMode);
//        PendingIntent pendingUnsilencer = PendingIntent.getService(this, 0, unsilencerIntent, 0);
//        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+150000, pendingUnsilencer);
        Log.i(TAG,"buraya girildi:12");

//        savePreference();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
//     public void savePreference(){
//         SettingsActivity.settings = PreferenceManager.getDefaultSharedPreferences(this);
//         SharedPreferences.Editor editor = SettingsActivity.settings.edit();
//         editor.putInt("currentMode", currentMode);
//         editor.apply();
//     }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
