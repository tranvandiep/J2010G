package gokisoft.j2010g;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {
    static int count = 0;
    Handler handler;
    boolean isLive = true;

    public CounterService() {
        Log.d(CounterService.class.getName(), "CounterService");
        handler = new Handler();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(CounterService.class.getName(), "onCreate");

        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(CounterService.class.getName(), "onStart");

        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(CounterService.class.getName(), "onStartCommand");

        loopCounter();

        return super.onStartCommand(intent, flags, startId);
    }

    void loopCounter() {
        if(!isLive) return;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Gui du lieu len Activity
                Intent i = new Intent();
                i.setAction("ACTION_COUNTER");
                i.putExtra("counter", ++count);
                Log.d(CounterService.class.getName(), "counter: " + count);

                sendBroadcast(i);

                //Loop
                loopCounter();
            }
        }, 1000);
    }

    @Override
    public void onDestroy() {
        Log.d(CounterService.class.getName(), "onDestroy");
        isLive = false;
        super.onDestroy();
    }
}
