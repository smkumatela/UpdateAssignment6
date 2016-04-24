package com.example.songezo.assignment6_android.repository.Impl;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.songezo.assignment6_android.conf.databases.DBConstants;
import com.example.songezo.assignment6_android.domain.PremierSoccerLeague;
import com.example.songezo.assignment6_android.repository.PremierSoccerLeague_Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Songezo on 2016-04-21.
 */
public class PremierSoccerLeague_Repository_Impl extends SQLiteOpenHelper
        implements PremierSoccerLeague_Repository{

    public static final String TABLE_NAME = "PREMIER_SOCCER_LEAGUE";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN__LEAGUE_NAME = "leagueName";
    public static final String COLUMN_ABBREVIATION = "abbreviation";
    public static final String COLUMN_STADIUMS = "stadium";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN__LEAGUE_NAME + " TEXT , "
            + COLUMN_ABBREVIATION + " TEXT , "
            + COLUMN_STADIUMS + " TEXT );";

    public PremierSoccerLeague_Repository_Impl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public PremierSoccerLeague_Repository_Impl(Context context, String name,
//                                               SQLiteDatabase.CursorFactory factory,
//                                               int version, DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PremierSoccerLeague findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN__LEAGUE_NAME,
                        COLUMN_ABBREVIATION,
                        COLUMN_STADIUMS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            final PremierSoccerLeague psl = new PremierSoccerLeague.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .leagueName(cursor.getString(cursor.getColumnIndex(COLUMN__LEAGUE_NAME)))
                    .abbreviation(cursor.getString(cursor.getColumnIndex(COLUMN_ABBREVIATION)))
                    .stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUMS)))
                    .build();
            return psl;
        }
        else {
            return null;
        }
    }

    @Override
    public PremierSoccerLeague save(PremierSoccerLeague entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN__LEAGUE_NAME, entity.getLeagueName());
        values.put(COLUMN_ABBREVIATION, entity.getAbbreviation());
        values.put(COLUMN_STADIUMS, entity.getStadiums());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PremierSoccerLeague insertEntity = new PremierSoccerLeague.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public PremierSoccerLeague update(PremierSoccerLeague entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN__LEAGUE_NAME, entity.getLeagueName());
        values.put(COLUMN_ABBREVIATION, entity.getAbbreviation());
        values.put(COLUMN_STADIUMS, entity.getStadiums());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PremierSoccerLeague delete(PremierSoccerLeague entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PremierSoccerLeague> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PremierSoccerLeague> premierSoccerLeagueSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PremierSoccerLeague psl = new PremierSoccerLeague.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .leagueName(cursor.getString(cursor.getColumnIndex(COLUMN__LEAGUE_NAME)))
                        .abbreviation(cursor.getString(cursor.getColumnIndex(COLUMN_ABBREVIATION)))
                        .stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUMS)))
                        .build();
                premierSoccerLeagueSet.add(psl);
            }while (cursor.moveToNext());
        }
        return premierSoccerLeagueSet;
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
