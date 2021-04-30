package com.example.gradesapp1801682004;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME = "Students_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "Name";
    public static final String COL3 = "Surname";
    public static final String COL4 = "Grade";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, GRADE INTEGER)");
        }

        @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String name, String surname, String grade) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL2, name);
            contentValues.put(COL3, surname);
            contentValues.put(COL4, grade);
            long result = db.insert(TABLE_NAME, null, contentValues);

            if(result == -1)
                return false;
            else
                return true;
        }

        public Cursor getAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor result = db.rawQuery("select * from "+TABLE_NAME, null);
            return result;
        }

        public boolean updateData(String id, String name, String surname, String grade) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, id);
            contentValues.put(COL2, name);
            contentValues.put(COL3, surname);
            contentValues.put(COL4, grade);
            db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
            return true;
        }

        public Integer deleteData (String id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
        }
}
