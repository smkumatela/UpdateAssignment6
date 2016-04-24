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
import com.example.songezo.assignment6_android.domain.Sponsor;
import com.example.songezo.assignment6_android.repository.Sponsor_Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-21.
 */
public class Sponsor_Repository_Impl extends SQLiteOpenHelper implements Sponsor_Repository {

    public static final String TABLE_NAME = "SPONSOR";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SPONSORS = "sponsors";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT , "
            + COLUMN_SPONSORS + " TEXT );";

    public Sponsor_Repository_Impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

//    public Sponsor_Repository_Impl(Context context, String name,
//                                   SQLiteDatabase.CursorFactory factory,
//                                   int version, DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Sponsor findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_SPONSORS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Sponsor sponsor = new Sponsor.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .sponsors(cursor.getString(cursor.getColumnIndex(COLUMN_SPONSORS)))
                    .build();
            return sponsor;
        }
        else {
            return null;
        }
    }

    @Override
    public Sponsor save(Sponsor entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SPONSORS, entity.getSponsors());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Sponsor insertedEntity = new Sponsor
                .Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Sponsor update(Sponsor entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SPONSORS, entity.getSponsors());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Sponsor delete(Sponsor entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Sponsor> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Sponsor> sponsorSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Sponsor sponsor = new Sponsor.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .sponsors(cursor.getString(cursor.getColumnIndex(COLUMN_SPONSORS)))
                        .build();
                sponsorSet.add(sponsor);
            }
            while (cursor.moveToNext());
        }
        return sponsorSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

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
