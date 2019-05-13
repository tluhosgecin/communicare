package com.hci.prototype.communicare.Application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import com.hci.prototype.communicare.Receiver.AlertReceiver;
import com.hci.prototype.communicare.Service.AlertService;

public class Launcher extends Application
{
    private static final String CHANNEL_ID   = "AlertChannel";
    private static final String CHANNEL_NAME = "Alert Feed";

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel lChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            NotificationManager lManager = getSystemService(NotificationManager.class);

            lManager.createNotificationChannel(lChannel);
        }

        Intent lService = new Intent(this, AlertService.class)
                .putExtra("count", AlertReceiver.getList().size());

        startService(lService);
    }
}
