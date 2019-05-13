package com.hci.prototype.communicare.Database.Structure;

import android.view.View;

public class DeviceStructure
{
    public static final int BUTTON_REMOVE = 0;

    private int                  mID;
    private String               mName;
    private String               mNumber;
    private View.OnClickListener mRemove;

    public DeviceStructure(int id, String name, String number)
    {
        mID     = id;
        mName   = name;
        mNumber = number;
    }

    public int getID()
    {
        return mID;
    }

    public String getName()
    {
        return mName;
    }

    public String getNumber()
    {
        return mNumber;
    }

    public void setOnClickListener(View.OnClickListener listener, int type)
    {
        switch (type)
        {
            case BUTTON_REMOVE:
            {
                mRemove = listener;

                break;
            }
        }
    }

    public View.OnClickListener getOnClickListener(int type)
    {
        switch (type)
        {
            case BUTTON_REMOVE:
            {
                return mRemove;
            }
        }

        return null;
    }
}
