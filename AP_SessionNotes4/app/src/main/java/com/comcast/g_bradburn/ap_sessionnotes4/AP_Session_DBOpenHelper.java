package com.comcast.g_bradburn.ap_sessionnotes4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Greg on 2/14/2016.
 */
public class AP_Session_DBOpenHelper extends SQLiteOpenHelper {
    public static final String LOGTAG = "APSession";

    public static final String DATABASE_NAME = "AP_Session_dataBase1.db";
    public static final int DATABASE_VERSION = 1;

    public static final String AP_SESSION_TABLE = "AP_Session1";
    public static final String AP_SESSION_COLUMN_ID = "id";
    public static final String AP_SESSION_COLUMN_SESSION_NAME = "session";
    public static final String AP_SESSION_COLUMN_LOCATION = "loc";
    public static final String AP_SESSION_COLUMN_TARGET = "target";
    public static final String AP_SESSION_COLUMN_IMAGE_TYPE = "imageType";
    public static final String AP_SESSION_COLUMN_TIMESTAMP = "timestamp";
    public static final String AP_SESSION_COLUMN_MOMENTARY_INTERFERENCE = "interf(m)";
    public static final String AP_SESSION_COLUMN_LATCHED_INTERFERENCE = "interf(l)";
    public static final String AP_SESSION_COLUMN_CAMERA_ID = "cameraId";
    public static final String AP_SESSION_COLUMN_CAMERA_TIME_ZONE = "cameraTimezone";
    public static final String AP_SESSION_COLUMN_CAMERA_DST_SET = "cameraDstSet";
    public static final String AP_SESSION_COLUMN_ISO = "iso";
    public static final String AP_SESSION_COLUMN_EXPOSURE_TIME = "expTime";
    public static final String AP_SESSION_COLUMN_MIRROR_LOCKUP_SET = "mirrorLockup";
    public static final String AP_SESSION_COLUMN_ANDROID_ID = "androidId";
    public static final String AP_SESSION_COLUMN_ANDROID_TIME_ZONE = "androidTimezone";
    public static final String AP_SESSION_COLUMN_ANDROID_DST_SET = "androidDstSet";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + AP_SESSION_TABLE + " (" +
                    AP_SESSION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AP_SESSION_COLUMN_SESSION_NAME + " TEXT, " +
                    AP_SESSION_COLUMN_LOCATION + " TEXT, " +
                    AP_SESSION_COLUMN_TARGET + " TEXT, " +
                    AP_SESSION_COLUMN_IMAGE_TYPE + " TEXT, " +
                    AP_SESSION_COLUMN_TIMESTAMP + " TEXT, " +
                    AP_SESSION_COLUMN_MOMENTARY_INTERFERENCE + " TEXT, " +
                    AP_SESSION_COLUMN_LATCHED_INTERFERENCE + " TEXT, " +
                    AP_SESSION_COLUMN_CAMERA_ID + " TEXT, " +
                    AP_SESSION_COLUMN_CAMERA_TIME_ZONE + " TEXT, " +
                    AP_SESSION_COLUMN_CAMERA_DST_SET + " INTEGER DEFAULT 0, " +
                    AP_SESSION_COLUMN_ISO + " NUMERIC, " +
                    AP_SESSION_COLUMN_EXPOSURE_TIME + " NUMERIC, " +
                    AP_SESSION_COLUMN_MIRROR_LOCKUP_SET + " INTEGER DEFAULT 0, " +
                    AP_SESSION_COLUMN_ANDROID_ID + " TEXT, " +
                    AP_SESSION_COLUMN_ANDROID_TIME_ZONE + " TEXT, " +
                    AP_SESSION_COLUMN_ANDROID_DST_SET + " INTEGER DEFAULT 0 " +
                    ")";

//    public static final String FILE_NAME_KEY = "file_name";
//    public static final String VIBRATION_KEY = "bump_or_wind_gust";
//    public static final String CLOUD_KEY = "cloud";
//    public static final String FLASHLIGHT_KEY = "flashlight";
//    public static final String CAR_LIGHTS_KEY = "car_lights";
//    public static final String AIRPLANE_KEY = "airplane";
//    public static final String SATELLITE_KEY = "satellite";
//    public static final String OTHER_INTERFERENCE_KEY = "other_interference";
//    public static final String METEOR_KEY = "meteor";


    public AP_Session_DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created.");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO:  May want to do something else to change versions.
        db.execSQL("DROP TABLE IF EXISTS " + AP_SESSION_TABLE);
        onCreate(db);
    }

    public boolean insertRecord(String session_name,
                                String location,
                                String target,
                                String image_type,
                                String timestamp,
                                String mom_interf,
                                String latched_interf,
                                String camera_id,
                                String camera_tz,
                                boolean camera_dst_set,
                                String iso,
                                String exp,
                                boolean lockup,
                                String android_id,
                                String android_tz,
                                boolean android_dst_set) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AP_SESSION_COLUMN_SESSION_NAME, session_name);
        contentValues.put(AP_SESSION_COLUMN_LOCATION, location);
        contentValues.put(AP_SESSION_COLUMN_TARGET, target);
        contentValues.put(AP_SESSION_COLUMN_IMAGE_TYPE, image_type);
        contentValues.put(AP_SESSION_COLUMN_TIMESTAMP, timestamp);
        contentValues.put(AP_SESSION_COLUMN_MOMENTARY_INTERFERENCE, mom_interf);
        contentValues.put(AP_SESSION_COLUMN_LATCHED_INTERFERENCE, latched_interf);
        contentValues.put(AP_SESSION_COLUMN_CAMERA_ID, camera_id);
        contentValues.put(AP_SESSION_COLUMN_CAMERA_TIME_ZONE, camera_tz);
        contentValues.put(AP_SESSION_COLUMN_CAMERA_DST_SET, camera_dst_set);
        contentValues.put(AP_SESSION_COLUMN_ISO, iso);
        contentValues.put(AP_SESSION_COLUMN_EXPOSURE_TIME, exp);
        contentValues.put(AP_SESSION_COLUMN_MIRROR_LOCKUP_SET, lockup);
        contentValues.put(AP_SESSION_COLUMN_ANDROID_ID, android_id);
        contentValues.put(AP_SESSION_COLUMN_ANDROID_TIME_ZONE, android_tz);
        contentValues.put(AP_SESSION_COLUMN_ANDROID_DST_SET, android_dst_set);
        return true;
    }

    public Cursor getData (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + AP_SESSION_TABLE + " where id="+id+"", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, AP_SESSION_TABLE);
        return numRows;
    }

//    public boolean updateSessionRecord() {
//        // TODO:  Not sure that I want to edit records
//        return true;
//    }

//    public Integer deleteSessionRecord() {
//        // TODO:  Not sure that I want to edit records
//    }

    public ArrayList<String> getAllSessionRecords() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + AP_SESSION_TABLE + "", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            arrayList.add(res.getString(res.getColumnIndex(AP_SESSION_COLUMN_SESSION_NAME)));
            res.moveToNext();
        }
        return arrayList;
    }
}
