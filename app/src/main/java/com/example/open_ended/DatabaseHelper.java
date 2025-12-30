package com.example.open_ended;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HealthDB";
    public static final String TABLE_NAME = "health_table";

    // Columns
    public static final String COL_1 = "ID";
    public static final String COL_2 = "STEPS";
    public static final String COL_3 = "WEIGHT";
    public static final String COL_4 = "WATER";
    public static final String COL_5 = "CALORIES";
    public static final String COL_6 = "DATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, STEPS INTEGER, WEIGHT REAL, WATER REAL, CALORIES INTEGER, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int next) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int steps, float weight, float water, int calories, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, steps);
        cv.put(COL_3, weight);
        cv.put(COL_4, water);
        cv.put(COL_5, calories);
        cv.put(COL_6, date);
        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // ORDER BY ID DESC taake naya data sab se upar nazar aaye
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC", null);
    }
}