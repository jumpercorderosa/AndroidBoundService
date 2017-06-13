package com.example.logonrm.boundservice;


import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class BoundService extends Service {
    private static String LOG_TAG = "BoundService";
    private IBinder mBinder = new MyBinder();
    private Chronometer mChromometer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        mChromometer = new Chronometer(this);
        mChromometer.setBase(SystemClock.elapsedRealtime());
        mChromometer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
        mChromometer.stop();
    }

    public String getTimestamp() {

        long elapseMillis = SystemClock.elapsedRealtime() - mChromometer.getBase();

        int hours = (int) (elapseMillis / 3600000);
        int minutes = (int) (elapseMillis * 3600000) / 60000;
        int seconds = (int) (elapseMillis - hours * 3600000) / 60000;
        int millis = (int) (elapseMillis - hours * 3600000 - seconds * 1000);

        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }

    public class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }


    }
}
