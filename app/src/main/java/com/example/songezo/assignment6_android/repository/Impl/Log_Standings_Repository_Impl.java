package com.example.songezo.assignment6_android.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//import com.example.songezo.assignment6_android.conf.util.databases.DBConstants;
import com.example.songezo.assignment6_android.conf.databases.DBConstants;
import com.example.songezo.assignment6_android.domain.Log_Standings;
import com.example.songezo.assignment6_android.repository.Log_Standings_Repository;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-20.
 */
public class Log_Standings_Repository_Impl extends SQLiteOpenHelper
        implements Log_Standings_Repository{

    public static final String TABLE_NAME = "LOG_STANDINGS";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TEAM_NAME = "TEAM_NAME";
    public static final String COLUMN_GAMES_PLAYED = "GAMES_PLAYED";
    public static final String COLUMN_GAMES_WON = "GAMES_WON";
    public static final String COLUMN_GAMES_LOST = "GAMES_LOST";
    public static final String COLUMN_GAMES_DRAWN = "GAMES_DRAWN";
    public static final String COLUMN_POINTS = "POINTS";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " INTEGER , "
            + COLUMN_TEAM_NAME + " TEXT NOT NULL  , "
            + COLUMN_GAMES_PLAYED + " INTEGER , "
            + COLUMN_GAMES_WON + " INTEGER , "
            + COLUMN_GAMES_LOST + " INTEGER , "
            + COLUMN_GAMES_DRAWN + " INTEGER , "
            + COLUMN_POINTS + " INTEGER );";



    public Log_Standings_Repository_Impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }


    @Override
    public Log_Standings findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_GAMES_PLAYED,
                        COLUMN_GAMES_WON,
                        COLUMN_GAMES_LOST,
                        COLUMN_GAMES_DRAWN,
                        COLUMN_POINTS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Log_Standings log = new Log_Standings.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .teamName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME)))
                    .gamesPlayed(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_PLAYED)))
                    .gamesWon(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_WON)))
                    .gamesLost(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_LOST)))
                    .gamesDrawn(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_DRAWN)))
                    .points(cursor.getInt(cursor.getColumnIndex(COLUMN_POINTS)))
                    .build();
            return log;
        }
        else {
            return null;
        }
    }

    @Override
    public Log_Standings save(Log_Standings entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TEAM_NAME, entity.getTeamName());
        values.put(COLUMN_GAMES_PLAYED, entity.getGamesPlayed());
        values.put(COLUMN_GAMES_WON, entity.getGamesWon());
        values.put(COLUMN_GAMES_LOST, entity.getGamesLost());
        values.put(COLUMN_GAMES_DRAWN, entity.getGamesDrawn());
        values.put(COLUMN_POINTS, entity.getPoints());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Log_Standings insertedEntity = new Log_Standings.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public Log_Standings update(Log_Standings entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TEAM_NAME, entity.getTeamName());
        values.put(COLUMN_GAMES_PLAYED, entity.getGamesPlayed());
        values.put(COLUMN_GAMES_WON, entity.getGamesWon());
        values.put(COLUMN_GAMES_LOST, entity.getGamesLost());
        values.put(COLUMN_GAMES_DRAWN, entity.getGamesDrawn());
        values.put(COLUMN_POINTS, entity.getPoints());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Log_Standings delete(Log_Standings entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Log_Standings> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Log_Standings> standings = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Log_Standings log = new Log_Standings.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .teamName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME)))
                        .gamesPlayed(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_PLAYED)))
                        .gamesWon(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_WON)))
                        .gamesLost(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_LOST)))
                        .gamesDrawn(cursor.getInt(cursor.getColumnIndex(COLUMN_GAMES_DRAWN)))
                        .points(cursor.getInt(cursor.getColumnIndex(COLUMN_POINTS)))
                        .build();
                standings.add(log);
            }
            while (cursor.moveToNext());
        }


        return standings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading Database from version" + oldVersion + " to "
                + newVersion + ", which will destroy all the old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
