package edu.neu.madcourse.numad22sp_team36_news.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;

import edu.neu.madcourse.numad22sp_team36_news.R;

public class NotificationService extends Service {
    private Timer timer;
    private TimerTask timerTask;
    private final int MILLIS_PER_SEC = 1000;
    private final int SEC_PER_HOUR = 60 * 60;
    private final int INTERVAL_HOURS = 8;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        stopTimerTask();
        super.onDestroy();
    }

    // we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        // initialize the TimerTask's job
        initializeTimerTask();

        // schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        // timer.schedule(timerTask, 5000, 10000); // test
        // push notification after the user leaves 8 hours
        timer.schedule(timerTask, MILLIS_PER_SEC * SEC_PER_HOUR * INTERVAL_HOURS, MILLIS_PER_SEC * SEC_PER_HOUR * INTERVAL_HOURS);
    }

    public void stopTimerTask() {
        // stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                // notification
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("Returning Notification", "Returning Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }
                String message = "Haven't seen you for a while. Latest news is ready. Come back and enjoy reading!";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, "Returning Notification");
                builder.setContentTitle("We miss you...");
                builder.setContentText(message);
                builder.setSmallIcon(R.drawable.ic_launcher_jiangfeng_foreground);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotificationService.this);
                managerCompat.notify(2, builder.build());
            }
        };
    }
}
