package com.hci.prototype.communicare.Receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.text.TextUtils;

import com.hci.prototype.communicare.Database.DeviceDatabase;
import com.hci.prototype.communicare.Database.Structure.DeviceStructure;
import com.hci.prototype.communicare.Receiver.Listener.OnAlertListener;
import com.hci.prototype.communicare.Receiver.Structure.AlertStructure;
import com.hci.prototype.communicare.Service.AlertService;

import java.util.ArrayList;

public class AlertReceiver extends BroadcastReceiver
{
    private static final String                    SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final ArrayList<AlertStructure> LIST         = new ArrayList<>();
    private static       OnAlertListener           LISTENER     = null;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (SMS_RECEIVED.equals(intent.getAction()))
        {
            Bundle lBundle = intent.getExtras();

            if (lBundle != null)
            {
                Object[] lPdus = (Object[]) lBundle.get("pdus");

                if (lPdus != null)
                {
                    SmsMessage[] lSmsMessage = new SmsMessage[lPdus.length];

                    for (int i = 0; i < lPdus.length; i++)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            lSmsMessage[i] = SmsMessage.createFromPdu((byte[]) lPdus[i], lBundle.getString("format"));
                        }
                        else
                        {
                            lSmsMessage[i] = SmsMessage.createFromPdu((byte[]) lPdus[i]);
                        }

                        String lNumber = lSmsMessage[i].getOriginatingAddress();

                        for (DeviceStructure lDevice : DeviceDatabase.getList())
                        {
                            if (lDevice.getNumber().equals(lNumber))
                            {
                                String         lName    = lDevice.getName();
                                String         lMessage = lSmsMessage[i].getMessageBody();
                                AlertStructure lAlert   = new AlertStructure(lName, lNumber, lMessage);

                                LIST.add(0, lAlert);

                                if (LISTENER != null)
                                {
                                    LISTENER.onAlert(lAlert);
                                }

                                Intent lService = new Intent(context, AlertService.class)
                                        .putExtra("count", AlertReceiver.getList().size());

                                context.startService(lService);

                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<AlertStructure> getList()
    {
        return LIST;
    }

    public static void setOnAlertListener(OnAlertListener listener)
    {
        if (LISTENER != listener)
        {
            LISTENER = listener;
        }
    }
}