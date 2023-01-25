package com.example.sqlite_operation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBconnection extends SQLiteOpenHelper {
    public DBconnection(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userdetails(name TEXT primary key, contact TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists userdetails");
    }



    //Function to update data
    public Boolean adduserdata(String name, String contact, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        long result = db.insert("userdetails", null, contentValues);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    //Function to update data
    public Boolean updateuserdata(String name, String contact, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        Cursor cursor = db.rawQuery("select * from userdetails where name=?",new String[] {name});

        if (cursor.getCount() > 0)
        {
            long result = db.update("userdetails", contentValues, "name=?", new String[] {name});
            if (result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }


    //Function to delete data
    public Boolean deleteuserdata(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userdetails where name=?",new String[] {name});

        if (cursor.getCount() > 0)
        {
            long result = db.delete("userdetails", "name=?", new String[] {name});
            if (result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }


    //Function to Show data
    public Cursor show()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userdetails", null);
        return cursor;
    }
}
