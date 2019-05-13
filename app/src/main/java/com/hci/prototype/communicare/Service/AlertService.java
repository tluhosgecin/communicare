package com.hci.prototype.communicare.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.hci.prototype.communicare.Activity.AlertsActivity;
import com.hci.prototype.communicare.R;

public class AlertService extends Service
{
    private static final String  CHANNEL_ID = "AlertChannel";


    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        int    lCount = intent.getIntExtra("count", 0);
        String lMessage;

        switch (lCount)
        {
            case 0:
            {
                lMessage = "You have no unresolved alerts.";

                break;
            }

            case 1:
            {
                lMessage = "You have " + lCount + " unresolved alert.";

                break;
            }

            default:
            {
                lMessage = "You have " + lCount + " unresolved alerts.";

                break;
            }
        }

        startForeground(1, resolveNotification(lMessage));

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    private Notification resolveNotification(String message)
    {
        Intent        lContent = new Intent(this, AlertsActivity.class);
        PendingIntent lPending = PendingIntent.getActivity(this, 0, lContent, 0);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_brand_logo)
                .setContentTitle(getString(R.string.notification_alertstatus))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(lPending)
                .build();
    }
}
