package com.example.a15017523.p05_ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15031795 on 23/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndpsongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_STARS = "stars";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR = "year";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE Song(_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT, singers TEXT, year INTEGER, stars INTEGER )";
        db.execSQL(createNoteTableSql);
        Log.i("info", createNoteTableSql + "\ncreated tables");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Song");
        onCreate(db);
    }

    public long insertSong(String title, String singers, int year, int stars) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, Integer.valueOf(year));
        values.put(COLUMN_STARS, Integer.valueOf(stars));
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", BuildConfig.FLAVOR + result);
        return result;
    }

    public ArrayList<Song> getAllNotes() {
        ArrayList<Song> songslist = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id,title,singers,year,stars FROM Song", null);
        if (cursor.moveToFirst()) {
            do {
                songslist.add(new Song(cursor.getInt(0), cursor.getString(DATABASE_VERSION), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songslist;
    }

    public ArrayList<Song> getAllNotesbyStars(int starsFilter) {
        ArrayList<Song> songslist = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String[] args = new String[DATABASE_VERSION];
        args[0] = String.valueOf(starsFilter);
        Cursor cursor = db.query(TABLE_SONG, columns, "stars>= ?", args, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                songslist.add(new Song(cursor.getInt(0), cursor.getString(DATABASE_VERSION), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songslist;
    }

    public int updateNote(Song data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, Integer.valueOf(data.getYearReleased()));
        values.put(COLUMN_STARS, Integer.valueOf(data.getStars()));
        String[] args = new String[DATABASE_VERSION];
        args[0] = String.valueOf(data.getId());
        int result = db.update(TABLE_SONG, values, "_id= ?", args);
        db.close();
        return result;
    }

    public int deleteNote(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[DATABASE_VERSION];
        args[0] = String.valueOf(id);
        int result = db.delete(TABLE_SONG, "_id= ?", args);
        db.close();
        return result;
    }
}