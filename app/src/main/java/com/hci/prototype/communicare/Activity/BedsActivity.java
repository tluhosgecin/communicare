package com.hci.prototype.communicare.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hci.prototype.communicare.Adapter.DeviceAdapter;
import com.hci.prototype.communicare.Database.DeviceDatabase;
import com.hci.prototype.communicare.Database.Helper.DeviceHelper;
import com.hci.prototype.communicare.Database.Structure.DeviceStructure;
import com.hci.prototype.communicare.R;

import java.util.Objects;

public class BedsActivity extends AppCompatActivity
{
    private DeviceHelper mDeviceHelper;
    private Button       mButtonBack;
    private Button       mButtonAddBed;
    private ListView     mListBeds;
    private AlertDialog  mDialogRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beds);

        mDeviceHelper = new DeviceHelper(this);
        mButtonBack   = findViewById(R.id.button_beds_back);
        mButtonAddBed = findViewById(R.id.button_beds_add);
        mListBeds     = findViewById(R.id.listview_beds_list);
        mDialogRemove = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_trash_delete)
                .setTitle(R.string.dialog_remove_title)
                .setMessage(R.string.dialog_remove_message)
                .setPositiveButton(R.string.dialog_remove_confirm, null)
                .setNegativeButton(R.string.dialog_remove_cancel, null)
                .create();

        mDialogRemove.setCancelable(false);
        mDialogRemove.setCanceledOnTouchOutside(false);

        mButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }
        });

        mButtonAddBed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), AddBedActivity.class));
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        resolveBedListItems();
        resolveButtonsEnabled(true);
    }

    @Override
    protected void onDestroy()
    {
        mDeviceHelper.close();
        mDeviceHelper = null;

        super.onDestroy();
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonBack.setEnabled(value);
        mButtonAddBed.setEnabled(value);
    }

    private void resolveBedListItems()
    {
        int               lSize    = DeviceDatabase.getList().size();
        DeviceStructure[] lObjects = DeviceDatabase.getList().toArray(new DeviceStructure[lSize]);

        for (final DeviceStructure lDevice : Objects.requireNonNull(lObjects))
        {
            lDevice.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mDialogRemove.setOnShowListener(new DialogInterface.OnShowListener()
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
                                    DeviceDatabase.delete(mDeviceHelper.getWritableDatabase(), lDevice.getID());
                                    resolveBedListItems();
                                    mDialogRemove.dismiss();
                                }
                            });
                        }
                    });

                    mDialogRemove.show();
                }

            }, DeviceStructure.BUTTON_REMOVE);
        }

        mListBeds.setAdapter(new DeviceAdapter(this, lObjects));
    }
}