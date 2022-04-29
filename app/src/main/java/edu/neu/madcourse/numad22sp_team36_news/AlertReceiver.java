package edu.neu.madcourse.numad22sp_team36_news;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Returning Notification", "Returning Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        String message = "Haven't seen you for a while. Latest news is ready. Come back and enjoy reading!";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Returning Notification");
        builder.setContentTitle("We miss you...");
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.ic_launcher_jiangfeng_foreground);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(2, builder.build());
    }
}
