package com.hci.prototype.communicare.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hci.prototype.communicare.Adapter.AlertAdapter;
import com.hci.prototype.communicare.R;
import com.hci.prototype.communicare.Receiver.AlertReceiver;
import com.hci.prototype.communicare.Receiver.Listener.OnAlertListener;
import com.hci.prototype.communicare.Receiver.Structure.AlertStructure;
import com.hci.prototype.communicare.Service.AlertService;

import java.util.Objects;

public class AlertsActivity extends AppCompatActivity
{
    private Button      mButtonBack;
    private ListView    mListAlerts;
    private AlertDialog mDialogResolve;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alerts);

        mButtonBack    = findViewById(R.id.button_alerts_back);
        mListAlerts    = findViewById(R.id.listview_alerts_list);
        mDialogResolve = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_trash_delete)
                .setTitle(R.string.dialog_resolve_title)
                .setMessage(R.string.dialog_resolve_message)
                .setPositiveButton(R.string.dialog_resolve_confirm, null)
                .setNegativeButton(R.string.dialog_resolve_cancel, null)
                .create();

        mDialogResolve.setCancelable(false);
        mDialogResolve.setCanceledOnTouchOutside(false);

        mButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        AlertReceiver.setOnAlertListener(new OnAlertListener()
        {
            @Override
            public void onAlert(AlertStructure alert)
            {
                resolveAlertListItems();
            }
        });

        resolveAlertListItems();
        resolveButtonsEnabled(true);
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonBack.setEnabled(value);
    }

    private void resolveAlertListRemove(AlertStructure alert)
    {
        AlertReceiver.getList().remove(alert);

        Intent lService = new Intent(this, AlertService.class)
                .putExtra("count", AlertReceiver.getList().size());

        startService(lService);
    }

    private void resolveAlertListItems()
    {
        int              lSize    = AlertReceiver.getList().size();
        AlertStructure[] lObjects = AlertReceiver.getList().toArray(new AlertStructure[lSize]);

        for (final AlertStructure lAlert : Objects.requireNonNull(lObjects))
        {
            lAlert.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mDialogResolve.setOnShowListener(new DialogInterface.OnShowListener()
                    {
                        @Override
                        public void onShow(DialogInterface dialog)
                        {
                            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                                    .setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View view)
                                        {
                                            resolveAlertListRemove(lAlert);
                                            resolveAlertListItems();
                                            mDialogResolve.dismiss();
                                        }
                                    });
                        }
                    });

                    mDialogResolve.show();
                }

            }, AlertStructure.BUTTON_RESOLVE);

            lAlert.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    resolveAlertListRemove(lAlert);
                    resolveAlertListItems();
                    startActivity(new Intent(getBaseContext(), CategoryActivity.class));
                }

            }, AlertStructure.BUTTON_ASSIST);
        }

        mListAlerts.setAdapter(new AlertAdapter(this, lObjects));
    }
}