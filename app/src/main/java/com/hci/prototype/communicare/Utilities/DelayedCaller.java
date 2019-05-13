package com.hci.prototype.communicare.Utilities;

import android.os.Handler;
import android.os.Looper;

public class DelayedCaller
{
    public static boolean execute(Runnable runnable, int delay)
    {
        try
        {
            return (new Handler(Looper.getMainLooper()).postDelayed(runnable, delay));
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
