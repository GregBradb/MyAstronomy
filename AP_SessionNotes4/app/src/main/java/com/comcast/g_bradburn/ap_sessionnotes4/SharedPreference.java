package com.comcast.g_bradburn.ap_sessionnotes4;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Greg on 1/31/2016.
 */
public class SharedPreference {

    //  Declare the shared preferences from the CREATE Activity
    public static final String SESSION_NAME_KEY = "session_name";
    public static final String LOCATION_ID_KEY = "location_id";
    public static final String FILE_NAME_KEY = "file_name";
    public static final String CAMERA_ID_KEY = "camera_id";
    public static final String CAMERA_TIME_ZONE_KEY = "camera_time_zone";
    public static final String CAMERA_DST_KEY = "camera_dst";
    public static final String ANDROID_ID_KEY = "android_id";
    public static final String ANDROID_TIME_ZONE_KEY = "android_time_zone";
    public static final String ANDROID_DST_KEY = "android_dst";
    public static final String EXPOSURE_TIME_KEY = "exposure_time";
    public static final String ISO_KEY = "iso";
    public static final String MIRROR_LOCKUP_KEY = "mirror_lockup";
    public static final String TARGET_ID_KEY = "target_id";
    public static final String IMAGE_TYPE_KEY = "image_type";
    public static final String VIBRATION_KEY = "bump_or_wind_gust";
    public static final String FLASHLIGHT_KEY = "flashlight";
    public static final String CAR_LIGHTS_KEY = "car_lights";
    public static final String AIRPLANE_KEY = "airplane";
    public static final String SATELLITE_KEY = "satellite";
    public static final String METEOR_KEY = "meteor";

    //    private SharedPreferences session_state = PreferenceManager.getDefaultSharedPreferences(this);
    //    public SharedPreferences session_state;
    public static final String PREFS_FILE_NAME = "ap_session_prefs.xml";

    public SharedPreference(){
        super();
    }

    public void saveString(Context context, String keyName, String keyValue) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        // settings = PreferenceManager.getDefaultSharePreferences (context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(keyName, keyValue);

        editor.apply();
    }

    public void saveInt(Context context, String keyName, int keyValue) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        // settings = PreferenceManager.getDefaultSharePreferences (context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putInt(keyName, keyValue);

        editor.apply();
    }

    public void saveBoolean(Context context, String keyName, boolean keyValue) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        // settings = PreferenceManager.getDefaultSharePreferences (context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putBoolean(keyName, keyValue);

        editor.apply();
    }

    public String getStringValue(Context context, String keyName) {
        SharedPreferences settings;
        String keyValue;

        // settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        keyValue = settings.getString(keyName, "NOT INITIALIZED");
        return keyValue;
    }

    public int getIntValue(Context context, String keyName) {
        SharedPreferences settings;
        int keyValue;

        // settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        keyValue = settings.getInt(keyName, -999);
        return keyValue;
    }

    public Boolean getBooleanValue (Context context, String keyName){
        SharedPreferences settings;
        boolean keyValue;

        // settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        keyValue = settings.getBoolean(keyName, false);
        return keyValue;
    }

    public void clearSharedPreference (Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        // settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.apply();
    }

    public void removeValue (Context context){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        // settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(SESSION_NAME_KEY);
        editor.apply();
    }
}
