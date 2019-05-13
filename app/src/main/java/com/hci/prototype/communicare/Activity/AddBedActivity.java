package com.hci.prototype.communicare.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hci.prototype.communicare.Database.DeviceDatabase;
import com.hci.prototype.communicare.Database.Helper.DeviceHelper;
import com.hci.prototype.communicare.Database.Structure.DeviceStructure;
import com.hci.prototype.communicare.R;

public class AddBedActivity extends AppCompatActivity
{
    private DeviceHelper mDeviceHelper;
    private Button       mButtonBack;
    private Button       mButtonMenu;
    private Button       mButtonAdd;
    private EditText     mEditName;
    private EditText     mEditNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addbed);

        mDeviceHelper = new DeviceHelper(this);
        mButtonBack   = findViewById(R.id.button_addbed_back);
        mButtonMenu   = findViewById(R.id.button_addbed_menu);
        mButtonAdd    = findViewById(R.id.button_addbed_add);
        mEditName     = findViewById(R.id.edittext_addbed_name);
        mEditNumber   = findViewById(R.id.edittext_addbed_number);

        mButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), BedsActivity.class));
            }
        });

        mButtonMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String lName   = mEditName.getText().toString();
                String lNumber = mEditNumber.getText().toString();

                if (lName.equals("") || lNumber.equals(""))
                {
                    return;
                }

                for (DeviceStructure lDevice: DeviceDatabase.getList())
                {
                    if (lDevice.getName().equals(lName) || lDevice.getNumber().equals(lNumber))
                    {
                        return;
                    }
                }

                resolveButtonsEnabled(false);
                DeviceDatabase.insert(mDeviceHelper.getWritableDatabase(), lName, lNumber);
                startActivity(new Intent(getBaseContext(), BedsActivity.class));
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        resolveEditTextClear();
        resolveButtonsEnabled(true);
    }

    @Override
    protected void onDestroy()
    {
        mDeviceHelper.close();
        mDeviceHelper = null;

        super.onDestroy();
    }

    private void resolveEditTextClear()
    {
        mEditName.setText(null);
        mEditNumber.setText(null);
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonBack.setEnabled(value);
        mButtonMenu.setEnabled(value);
        mButtonAdd.setEnabled(value);
        mEditName.setEnabled(value);
        mEditNumber.setEnabled(value);
    }
}
