package com.hci.prototype.communicare.Receiver.Structure;

import android.annotation.SuppressLint;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlertStructure
{
    public static final int BUTTON_RESOLVE = 0;
    public static final int BUTTON_ASSIST  = 1;

    private String               mName;
    private String               mNumber;
    private String               mMessage;
    private String               mTime;
    private View.OnClickListener mResolve;
    private View.OnClickListener mAssist;

    @SuppressLint("SimpleDateFormat")
    public AlertStructure(String name, String number, String message)
    {
        mName    = name;
        mNumber  = number;
        mMessage = message;
        mTime    = (new SimpleDateFormat("HH:mm").format(new Date()));
    }

    public String getName()
    {
        return mName;
    }

    public String getNumber()
    {
        return mNumber;
    }

    public String getMessage()
    {
        return mMessage;
    }

    public String getTime()
    {
        return mTime;
    }

    public void setOnClickListener(View.OnClickListener listener, int type)
    {
        switch (type)
        {
            case BUTTON_RESOLVE:
            {
                mResolve = listener;

                break;
            }

            case BUTTON_ASSIST:
            {
                mAssist = listener;

                break;
            }
        }
    }

    public View.OnClickListener getOnClickListener(int type)
    {
        switch (type)
        {
            case BUTTON_RESOLVE:
            {
                return mResolve;
            }

            case BUTTON_ASSIST:
            {
                return mAssist;
            }
        }

        return null;
    }
}
