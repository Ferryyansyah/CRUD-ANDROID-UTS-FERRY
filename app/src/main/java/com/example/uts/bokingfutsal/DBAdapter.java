package com.example.uts.bokingfutsal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uts.bokingfutsal.domain.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cymas on 29/10/15.
 */
public class DBAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "bokingfutsal";
    private static final String TABLE_NAME = "boking";
    private static final String COL_ID = "id";
    private static final String COL_NAMA = "nama";
    private static final String COL_JAM = "jam";
    private static final String COL_WAKTU = "waktu";
    private static final String COL_BERAPAJAM = "berapajam";
    private static final String COL_LAPANGAN = "lapangan";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME + ";";

    private SQLiteDatabase sqliteDatabase = null;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL(DROP_TABLE);
    }

    public void openDB() {
        if (sqliteDatabase == null) {
            sqliteDatabase = getWritableDatabase();
        }
    }

    public void closeDB() {
        if (sqliteDatabase != null) {
            if (sqliteDatabase.isOpen()) {
                sqliteDatabase.close();
            }
        }
    }

    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COL_NAMA + " TEXT," + COL_JAM + " TEXT,"
                + COL_WAKTU + " TEXT," + COL_BERAPAJAM + " TEXT,"
                + COL_LAPANGAN + "TEXT);");
    }

    public void updatePegawai(Booking booking) {
        sqliteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAMA, booking.getNama());
        cv.put(COL_JAM, booking.getMulaijam());
        cv.put(COL_WAKTU, booking.getWaktu());
        cv.put(COL_BERAPAJAM, booking.getBerapajam());
        cv.put(COL_LAPANGAN, booking.getLapangan());
        String whereClause = COL_ID + "==?";
        String whereArgs[] = new String[] { booking.getId() };
        sqliteDatabase.update(TABLE_NAME, cv, whereClause, whereArgs);
        sqliteDatabase.close();
    }

    public void save(Booking booking) {
        sqliteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_NAMA, booking.getNama());
        cv.put(COL_JAM, booking.getMulaijam());
        cv.put(COL_WAKTU, booking.getWaktu());
        cv.put(COL_BERAPAJAM, booking.getBerapajam());
        cv.put(COL_LAPANGAN, booking.getLapangan());

        sqliteDatabase.insertWithOnConflict(TABLE_NAME, null,
                cv, SQLiteDatabase.CONFLICT_IGNORE);

        sqliteDatabase.close();
    }

    public void delete(Booking booking) {
        sqliteDatabase = getWritableDatabase();
        String whereClause = COL_ID + "==?";
        String[] whereArgs = new String[] { String.valueOf(booking.getId()) };
        sqliteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
        sqliteDatabase.close();
    }

    public void deleteAll() {
        sqliteDatabase = getWritableDatabase();
        sqliteDatabase.delete(TABLE_NAME, null, null);
        sqliteDatabase.close();
    }

    public List<Booking> getAllbooking() {
        sqliteDatabase = getWritableDatabase();

        Cursor cursor = this.sqliteDatabase.query(TABLE_NAME, new String[] {
                COL_ID, COL_NAMA, COL_JAM, COL_WAKTU , COL_BERAPAJAM, COL_LAPANGAN }, null, null, null, null, null, null);
        List<Booking> bookings = new ArrayList<Booking>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Booking booking = new Booking();
                booking.setId(cursor.getString(cursor.getColumnIndex(COL_ID)));
                booking.setNama(cursor.getString(cursor
                        .getColumnIndex(COL_NAMA)));
                booking.setMulaijam(cursor.getString(cursor
                        .getColumnIndex(COL_JAM)));
                booking.setWaktu(cursor.getString(cursor
                        .getColumnIndex(COL_WAKTU)));
                bookings.add(booking);
            }
            sqliteDatabase.close();
            return bookings;
        } else {
            sqliteDatabase.close();
            return new ArrayList<Booking>();
        }
    }
}
