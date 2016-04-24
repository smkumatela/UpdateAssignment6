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
import com.example.songezo.assignment6_android.domain.Broadcaster;
import com.example.songezo.assignment6_android.domain.Cup_Game;
import com.example.songezo.assignment6_android.factories.Broadcaster_Factory;
import com.example.songezo.assignment6_android.repository.Cup_Game_Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-21.
 */
public class Cup_Game_Repository_impl extends SQLiteOpenHelper implements Cup_Game_Repository{

    public static final String TABLE_NAME = "settings";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE_OF_MATCH = "typeOfMatch";
    public static final String COLUMN_LEAGUE_GAME = "league_game";
    public static final String COLUMN_CUP_GAME = "cup_game";


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUP_GAME + " TEXT NOT NULL , "
            + COLUMN_LEAGUE_GAME + " TEXT NOT NULL , "
            + COLUMN_TYPE_OF_MATCH + " TEXT UNIQUE NOT NULL );";

    public Cup_Game_Repository_impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public Cup_Game_Repository_impl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Cup_Game findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TYPE_OF_MATCH,
                        COLUMN_CUP_GAME,
                        COLUMN_LEAGUE_GAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            final Broadcaster_Factory broadcaster = new Broadcaster_Factory
                    .Builder(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_OF_MATCH)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .cupGame(cursor.getString(cursor.getColumnIndex(COLUMN_CUP_GAME)))
                    .leagueGame(cursor.getString(cursor.getColumnIndex(COLUMN_LEAGUE_GAME)))
                    .build();
            return broadcaster;
        }
        else {
            return null;
        }
    }

    @Override
    public Cup_Game save(Cup_Game entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE_OF_MATCH, entity.typeOfMatch());
        values.put(COLUMN_CUP_GAME, entity.typeOfMatch());
        values.put(COLUMN_LEAGUE_GAME, entity.typeOfMatch());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Broadcaster_Factory insertedEntity = new Broadcaster_Factory
                .Builder(values.getAsString("typeOfMatch"))
                .copy((Broadcaster_Factory) entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Cup_Game update(Cup_Game entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE_OF_MATCH, entity.typeOfMatch());
        values.put(COLUMN_CUP_GAME, entity.typeOfMatch());
        values.put(COLUMN_LEAGUE_GAME, entity.typeOfMatch());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.typeOfMatch())}
        );
        return entity;
    }

    @Override
    public Cup_Game delete(Cup_Game entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.typeOfMatch())});
        return entity;
    }

    @Override
    public Set<Cup_Game> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Cup_Game> cup_gameSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Broadcaster_Factory broadcaster = new Broadcaster_Factory
                        .Builder(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_OF_MATCH)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .cupGame(cursor.getString(cursor.getColumnIndex(COLUMN_CUP_GAME)))
                        .leagueGame(cursor.getString(cursor.getColumnIndex(COLUMN_LEAGUE_GAME)))
                        .build();
                cup_gameSet.add(broadcaster);
            }
            while (cursor.moveToNext());
        }
        return cup_gameSet;
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
