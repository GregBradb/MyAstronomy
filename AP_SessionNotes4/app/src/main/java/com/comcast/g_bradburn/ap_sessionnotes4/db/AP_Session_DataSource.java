package com.comcast.g_bradburn.ap_sessionnotes4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.comcast.g_bradburn.ap_sessionnotes4.model.SessionElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 2/16/2016.
 */
public class AP_Session_DataSource {

    public static final String LOGTAG = "APSession";

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ID,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_SESSION_NAME,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_LOCATION,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_TARGET,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_IMAGE_TYPE,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_TIMESTAMP,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_MOMENTARY_INTERFERENCE,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_LATCHED_INTERFERENCE,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_ID,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_TIME_ZONE,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_DST_SET,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ISO,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_EXPOSURE_TIME,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_MIRROR_LOCKUP_SET,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_ID,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_TIME_ZONE,
            AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_DST_SET};

    public AP_Session_DataSource(Context context) {

        dbHelper = new AP_Session_DBOpenHelper(context);

    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        Log.i(LOGTAG, "Database opened.");
    }

    public void close() {
        dbHelper.close();
        Log.i(LOGTAG, "Database opened.");
    }

    public SessionElement create(SessionElement sessionElement) {
        ContentValues values = new ContentValues();
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ID, sessionElement.getId());

        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_SESSION_NAME, sessionElement.getSessionName());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_LOCATION, sessionElement.getLocationID());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_TARGET, sessionElement.getTargetID());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_IMAGE_TYPE, sessionElement.getImageType());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_TIMESTAMP, sessionElement.getTimeStamp());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_MOMENTARY_INTERFERENCE, sessionElement.getMomentaryInterference());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_LATCHED_INTERFERENCE, sessionElement.getLatchedInterference());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_ID, sessionElement.getCameraID());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_TIME_ZONE, sessionElement.getCameraTimeZone());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_DST_SET, sessionElement.getCameraDST_Set());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ISO, sessionElement.getIso());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_EXPOSURE_TIME, sessionElement.getExposureTime_ms());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_MIRROR_LOCKUP_SET, sessionElement.getCameraMirrorLockup());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_ID, sessionElement.getAndroidID());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_TIME_ZONE, sessionElement.getAndroidTimeZone());
        values.put(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_DST_SET, sessionElement.getAndroidDST_Set());

        long insertID = database.insert(AP_Session_DBOpenHelper.AP_SESSION_TABLE, null, values);
        sessionElement.setId(insertID);

        return sessionElement;
    }

    public List<SessionElement> findAll() {
        List<SessionElement> sessionElements = new ArrayList<SessionElement>();
        Cursor cursor = database.query(AP_Session_DBOpenHelper.AP_SESSION_TABLE, allColumns,
                null, null, null, null, null);
        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                SessionElement sessionElement = new SessionElement();
                sessionElement.setId(cursor.getLong(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ID)));
                sessionElement.setSessionName(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_SESSION_NAME)));
                sessionElement.setLocationID(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_LOCATION)));
                sessionElement.setTargetID(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_TARGET)));
                sessionElement.setImageType(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_IMAGE_TYPE)));
                sessionElement.setTimeStamp(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_TIMESTAMP)));
                sessionElement.setMomentaryInterference(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_MOMENTARY_INTERFERENCE)));
                sessionElement.setLatchedInterference(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_LATCHED_INTERFERENCE)));
                sessionElement.setCameraID(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_ID)));
                sessionElement.setCameraTimeZone(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_TIME_ZONE)));
                sessionElement.setCameraDST_Set(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_CAMERA_DST_SET)));
                sessionElement.setIso(cursor.getLong(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ISO)));
                sessionElement.setExposureTime_ms(cursor.getLong(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_EXPOSURE_TIME)));
                sessionElement.setCameraMirrorLockup(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_MIRROR_LOCKUP_SET)));
                sessionElement.setAndroidID(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_ID)));
                sessionElement.setAndroidTimeZone(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_TIME_ZONE)));
                sessionElement.setAndroidDST_Set(cursor.getString(cursor.getColumnIndex(AP_Session_DBOpenHelper.AP_SESSION_COLUMN_ANDROID_DST_SET)));

                sessionElements.add(sessionElement);
            }
        }
        return sessionElements;
    };
}
