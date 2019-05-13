package com.hci.prototype.communicare.Database.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeviceHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME      = "CommuniCare.db";
    private static final int    DATABASE_VERSION   = 1;
    public  static final String TABLE_NAME         = "Devices";
    public  static final String COLUMN_NAME_ID     = "ID";
    public  static final String COLUMN_NAME_NAME   = "Name";
    public  static final String COLUMN_NAME_NUMBER = "Number";

    public DeviceHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_NAME + " TEXT," +
                COLUMN_NAME_NUMBER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        onUpgrade(database, oldVersion, newVersion);
    }
}
