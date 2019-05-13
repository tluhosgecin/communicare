package com.hci.prototype.communicare.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hci.prototype.communicare.Database.Helper.DeviceHelper;
import com.hci.prototype.communicare.Database.Structure.DeviceStructure;

import java.util.ArrayList;

public class DeviceDatabase
{
    private static final ArrayList<DeviceStructure> LIST = new ArrayList<>();

    public static void insert(SQLiteDatabase database, String name, String number)
    {
        if (database != null)
        {
            ContentValues lEntry = new ContentValues();

            lEntry.put(DeviceHelper.COLUMN_NAME_NUMBER, number);
            lEntry.put(DeviceHelper.COLUMN_NAME_NAME, name);

            if (database.insert(DeviceHelper.TABLE_NAME, null, lEntry) != -1)
            {
                synchronise(database);
            }
        }
    }

    public static void update(SQLiteDatabase database, Integer id, String name, String number)
    {
        if (database != null)
        {
            ContentValues lEntry = new ContentValues();

            lEntry.put(DeviceHelper.COLUMN_NAME_NUMBER, number);
            lEntry.put(DeviceHelper.COLUMN_NAME_NAME, name);

            String   lSelection = DeviceHelper.COLUMN_NAME_ID + " = ?";
            String[] lArguments = new String[] { String.valueOf(id) };

            if (database.update(DeviceHelper.TABLE_NAME, lEntry, lSelection, lArguments) != -1)
            {
                synchronise(database);
            }
        }
    }

    public static void delete(SQLiteDatabase database, Integer id)
    {
        if (database != null)
        {
            String   lSelection = DeviceHelper.COLUMN_NAME_ID + " = ?";
            String[] lArguments = new String[] { String.valueOf(id) };

            if (database.delete(DeviceHelper.TABLE_NAME, lSelection, lArguments) != -1)
            {
                synchronise(database);
            }
        }
    }

    public static void synchronise(SQLiteDatabase database)
    {
        if (database != null)
        {
            LIST.clear();

            Cursor lCursor = database.query(DeviceHelper.TABLE_NAME, null, null, null, null, null, null);

            while (lCursor.moveToNext())
            {
                Integer         lID     = lCursor.getInt(lCursor.getColumnIndexOrThrow(DeviceHelper.COLUMN_NAME_ID));
                String          lName   = lCursor.getString(lCursor.getColumnIndexOrThrow(DeviceHelper.COLUMN_NAME_NAME));
                String          lNumber = lCursor.getString(lCursor.getColumnIndexOrThrow(DeviceHelper.COLUMN_NAME_NUMBER));
                DeviceStructure lDevice = new DeviceStructure(lID, lName, lNumber);

                LIST.add(0, lDevice);
            }

            lCursor.close();
        }
    }

    public static ArrayList<DeviceStructure> getList()
    {
        return LIST;
    }
}