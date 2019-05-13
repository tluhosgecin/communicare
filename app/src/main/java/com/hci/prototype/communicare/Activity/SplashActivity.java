package com.hci.prototype.communicare.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hci.prototype.communicare.Database.DeviceDatabase;
import com.hci.prototype.communicare.Database.Helper.DeviceHelper;
import com.hci.prototype.communicare.R;
import com.hci.prototype.communicare.Utilities.DelayedCaller;

public class SplashActivity extends AppCompatActivity
{
    private DeviceHelper mDeviceHelper;
    private TextView     mTextFeedback;
    private Dialog       mDialogPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mDeviceHelper     = new DeviceHelper(this);
        mTextFeedback     = findViewById(R.id.textview_splash_feedback);
        mDialogPermission = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_padlock_unlocked)
                .setTitle(R.string.dialog_permission_title)
                .setMessage(R.string.dialog_permission_message)
                .setPositiveButton(R.string.dialog_permission_confirm, null)
                .create();

        mDialogPermission.setCancelable(false);
        mDialogPermission.setCanceledOnTouchOutside(false);

        switch (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS))
        {
            case PackageManager.PERMISSION_GRANTED:
            {
                resolveActivitySwitch(R.string.feedback_initialize, 1500);

                break;
            }

            case PackageManager.PERMISSION_DENIED:
            {
                resolvePermissionRequest(R.string.feedback_request);

                break;
            }

            default:
            {
                resolveApplicationClose(R.string.feedback_shutdown, 1500);
            }
        }
    }

    @Override
    protected void onDestroy()
    {
        mDeviceHelper.close();
        mDeviceHelper = null;

        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int result, String[] permissions, int[] granted)
    {
        switch (result)
        {
            case 0:
            {
                if (granted.length > 0 && granted[0] == PackageManager.PERMISSION_GRANTED)
                {
                    resolveActivitySwitch(R.string.feedback_initialize, 1500);
                }
                else
                {
                    resolvePermissionReminder(R.string.feedback_fail, 1500);
                }

                break;
            }
        }
    }

    private void resolvePermissionRequest(int id)
    {
        mTextFeedback.setText(getString(id));

        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECEIVE_SMS }, 0);
    }

    private void resolvePermissionReminder(int id, final int delay)
    {
        mTextFeedback.setText(getString(id));

        mDialogPermission.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            @Override
            public void onDismiss(DialogInterface dialog)
            {
                resolveApplicationClose(R.string.feedback_shutdown, delay);
            }
        });

        mDialogPermission.show();
    }

    private void resolveActivitySwitch(int id, int delay)
    {
        mTextFeedback.setText(getString(id));

        DelayedCaller.execute(new Runnable()
        {
            @Override
            public void run()
            {
                DeviceDatabase.synchronise(mDeviceHelper.getReadableDatabase());
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }

        }, delay);
    }

    private void resolveApplicationClose(int id, int delay)
    {
        mTextFeedback.setText(getString(id));

        DelayedCaller.execute(new Runnable()
        {
            @Override
            public void run()
            {
                finishAffinity();
            }

        }, delay);
    }
}