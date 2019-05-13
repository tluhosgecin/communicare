package com.hci.prototype.communicare.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hci.prototype.communicare.R;
import com.hci.prototype.communicare.Receiver.AlertReceiver;
import com.hci.prototype.communicare.Receiver.Listener.OnAlertListener;
import com.hci.prototype.communicare.Receiver.Structure.AlertStructure;

public class HomeActivity extends AppCompatActivity
{
    private Button   mButtonAlerts;
    private Button   mButtonAssist;
    private Button   mButtonBeds;
    private TextView mTextCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        mButtonAlerts = findViewById(R.id.button_home_alerts);
        mButtonAssist = findViewById(R.id.button_home_assist);
        mButtonBeds   = findViewById(R.id.button_home_beds);
        mTextCount    = findViewById(R.id.textview_home_count);

        mButtonAlerts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), AlertsActivity.class));
            }
        });

        mButtonAssist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), CategoryActivity.class));
            }
        });

        mButtonBeds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), BedsActivity.class));
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
                resolveAlertsCount();
            }
        });

        resolveAlertsCount();
        resolveButtonsEnabled(true);
    }

    @Override
    public void onBackPressed()
    {
        resolveButtonsEnabled(false);

        finishAffinity();
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonAlerts.setEnabled(value);
        mButtonAssist.setEnabled(value);
        mButtonBeds.setEnabled(value);
    }

    private void resolveAlertsCount()
    {
        mTextCount.setText(String.valueOf(AlertReceiver.getList().size()));
        mTextCount.setVisibility(AlertReceiver.getList().size() > 0 ? View.VISIBLE : View.INVISIBLE);
    }
}