package com.example.songezo.assignment6_android.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.songezo.assignment6_android.conf.databases.DBConstants;
import com.example.songezo.assignment6_android.domain.Stadium;
import com.example.songezo.assignment6_android.repository.Stadium_Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-21.
 */
public class Stadium_Repository_Impl extends SQLiteOpenHelper implements Stadium_Repository {

    public static final String TABLE_NAME = "STADIUMS";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT , "
            + COLUMN_LOCATION + " TEXT );";

//    public Stadium_Repository_Impl(Context context, String name,
//                                   SQLiteDatabase.CursorFactory factory,
//                                   int version, DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }

    public Stadium_Repository_Impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Stadium findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_LOCATION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            final Stadium stadiums = new Stadium.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .location(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)))
                    .build();
            return stadiums;
        }
        else {
            return null;
        }
    }

    @Override
    public Stadium save(Stadium entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOCATION, entity.getLocation());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Stadium insertedEntity = new Stadium.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Stadium update(Stadium entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOCATION, entity.getLocation());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Stadium delete(Stadium entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;    }

    @Override
    public Set<Stadium> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Stadium> stadiumSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Stadium stadiums = new Stadium.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .location(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)))
                        .build();
                stadiumSet.add(stadiums);
            }
            while (cursor.moveToNext());
        }
        return stadiumSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
