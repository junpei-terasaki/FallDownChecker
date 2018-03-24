package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by STL on 2018/03/22.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context){
        super(context, "NameAddressDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table person("
                        + " name text ,"
                        + " address text"
                        + ");" +
                "create table result("
                        + " level text,"
                        + " training1 text,"
                        + " training2 text,"
                        + " training3 text,"
                        + " training4 text,"
                        + " training5 text"
                        + ");"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
