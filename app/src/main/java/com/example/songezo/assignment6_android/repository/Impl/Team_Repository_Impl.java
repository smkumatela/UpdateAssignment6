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
import com.example.songezo.assignment6_android.domain.Team;
import com.example.songezo.assignment6_android.repository.Team_Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-21.
 */
public class Team_Repository_Impl extends SQLiteOpenHelper implements Team_Repository {

    public static final String TABLE_NAME = "TEAM";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEAM_NAME = "teamName";
    public static final String COLUMN_TEAM_NICK_NAME = "teamNickName";
    public static final String COLUMN_TEAM_LOCATION = "teamLocation";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TEAM_NAME + " TEXT NOT NULL , "
            + COLUMN_TEAM_NICK_NAME + " TEXT NOT NULL , "
            + COLUMN_TEAM_LOCATION + " TEXT NOT NULL );";

    public Team_Repository_Impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public Team_Repository_Impl(Context context, String name,
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
    public Team findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TEAM_NAME,
                        COLUMN_TEAM_NICK_NAME,
                        COLUMN_TEAM_LOCATION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            final Team team = new Team.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .teamName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME)))
                    .teamNickName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NICK_NAME)))
                    .teamLocation(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_LOCATION)))
                    .build();
            return team;
        }
        else {
            return null;
        }
    }

    @Override
    public Team save(Team entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TEAM_NAME, entity.getTeamName());
        values.put(COLUMN_TEAM_NICK_NAME, entity.getTeamNickName());
        values.put(COLUMN_TEAM_LOCATION, entity.getTeamLocation());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Team insertedEntity = new Team.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Team update(Team entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TEAM_NAME, entity.getTeamName());
        values.put(COLUMN_TEAM_NICK_NAME, entity.getTeamNickName());
        values.put(COLUMN_TEAM_LOCATION, entity.getTeamLocation());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Team delete(Team entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Team> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Team> teamSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Team team = new Team.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .teamName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME)))
                        .teamNickName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NICK_NAME)))
                        .teamLocation(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_LOCATION)))
                        .build();
                teamSet.add(team);
            }
            while (cursor.moveToNext());
        }
        return teamSet;
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
