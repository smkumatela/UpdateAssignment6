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
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.repository.Tournaments_Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-21.
 */
public class Tournaments_Repository_impl extends SQLiteOpenHelper implements Tournaments_Repository{

    public static final String TABLE_NAME = "TOURNAMENTS";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_T_NAME = "tName";
    public static final String COLUMN_NUM_OF_TEAMS = "numOfTeams";
    public static final String COLUMN_PRIZE_MONEY = "prizeMoney";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_T_NAME + " TEXT NOT NULL , "
            + COLUMN_NUM_OF_TEAMS + " TEXT NOT NULL , "
            + COLUMN_PRIZE_MONEY + " TEXT NOT NULL );";


    public Tournaments_Repository_impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public Tournaments_Repository_impl(Context context, String name,
                                       SQLiteDatabase.CursorFactory factory,
                                       int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Tournaments findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_T_NAME,
                        COLUMN_NUM_OF_TEAMS,
                        COLUMN_PRIZE_MONEY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            final Tournaments tournaments = new Tournaments.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .tName(cursor.getString(cursor.getColumnIndex(COLUMN_T_NAME)))
                    .numOfTeams(cursor.getInt(cursor.getColumnIndex(COLUMN_NUM_OF_TEAMS)))
                    .prizeMoney(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRIZE_MONEY)))
                    .build();
            return tournaments;
        }
        else {
            return null;
        }
    }

    @Override
    public Tournaments save(Tournaments entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_T_NAME, entity.gettName());
        values.put(COLUMN_NUM_OF_TEAMS, entity.getNumOfTeams());
        values.put(COLUMN_PRIZE_MONEY, entity.getPrizeMoney());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Tournaments insertedEntity = new Tournaments.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Tournaments update(Tournaments entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_T_NAME, entity.gettName());
        values.put(COLUMN_NUM_OF_TEAMS, entity.getNumOfTeams());
        values.put(COLUMN_PRIZE_MONEY, entity.getPrizeMoney());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Tournaments delete(Tournaments entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Tournaments> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Tournaments> tournamentsSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Tournaments tournaments = new Tournaments.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .tName(cursor.getString(cursor.getColumnIndex(COLUMN_T_NAME)))
                        .numOfTeams(cursor.getInt(cursor.getColumnIndex(COLUMN_NUM_OF_TEAMS)))
                        .prizeMoney(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRIZE_MONEY)))
                        .build();
                tournamentsSet.add(tournaments);
            }
            while (cursor.moveToNext());
        }
        return tournamentsSet;
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
