//package edu.neu.madcourse.numad22sp_team36_news;
//
//import static android.app.Service.START_STICKY;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Handler;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class NotificationService extends Service {
//    Timer timer;
//    TimerTask timerTask;
//    String TAG = "Timers";
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.e(TAG, "onStartCommand");
//        super.onStartCommand(intent, flags, startId);
//
//        startTimer();
//
//        return START_STICKY;
//    }
//
//    @Override
//    public void onCreate() {
//        Log.e(TAG, "onCreate");
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e(TAG, "onDestroy");
//        stopTimerTask();
//        super.onDestroy();
//    }
//
//    // we are going to use a handler to be able to run in our TimerTask
//    final Handler handler = new Handler();
//
//    public void startTimer() {
//        //set a new Timer
//        timer = new Timer();
//
//        // initialize the TimerTask's job
//        initializeTimerTask();
//
//        // schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
//        // timer.schedule(timerTask, 5000, 10000); //
//        timer.schedule(timerTask, 5, 1000); //
//    }
//
//    public void stopTimerTask() {
//        // stop the timer, if it's not already null
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//    }
//
//    public void initializeTimerTask() {
//        timerTask = new TimerTask() {
//            public void run() {
//                // notification
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    NotificationChannel channel = new NotificationChannel("Returning Notification", "Returning Notification", NotificationManager.IMPORTANCE_DEFAULT);
//                    NotificationManager manager = getSystemService(NotificationManager.class);
//                    manager.createNotificationChannel(channel);
//                }
//                String message = "Haven't seen you for a while. Latest news is ready. Come back and enjoy reading!";
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, "Returning Notification");
//                builder.setContentTitle("We miss you...");
//                builder.setContentText(message);
//                builder.setSmallIcon(R.drawable.ic_launcher_jiangfeng_foreground);
//                builder.setAutoCancel(true);
//
//                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotificationService.this);
//                managerCompat.notify(2, builder.build());
//            }
//        };
//    }
//}
