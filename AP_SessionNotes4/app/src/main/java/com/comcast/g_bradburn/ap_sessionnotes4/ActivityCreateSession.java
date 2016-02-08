package com.comcast.g_bradburn.ap_sessionnotes4;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class ActivityCreateSession extends AppCompatActivity {
    //  This class initializes a session
    //  TODO:  Read and use all controls to initialize a dataBase session
    //  TODO:  Return a reference to the session to the calling activity through an intent so it can be used for the session

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

//    PREF_1 = "Test";

    static String fileName = "temporary_data.txt";
    static File mDir;
    static FileOutputStream mFOS;

    private static final String CREATE_SESSION_MESSAGE = "CreateSessionMessage: ";

    private static final String MESSAGE_1 = "YOUR MESSAGE";

    private String mSessionName;
    private String mLocation;
    private String mFileName;

    private String mCameraID;
    private String mCameraTZ;
    private Boolean mCameraDST;

    private String mAndroidID;
    private String mAndroidTZ;
    private Boolean mAndroidDST;

    private SharedPreference sharedPreference, mSharedPreference;
    Activity context = this;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
//    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);

        mSharedPreference = new SharedPreference();
        sharedPreference = new SharedPreference();

        // TODO:  Check to see if this works even if the Shared Preference string is empty
        String tmpStrngSssn = mSharedPreference.getStringValue(this, SharedPreference.SESSION_NAME_KEY);
        EditText tmpSssnET = (EditText) findViewById(R.id.session_edit_text);
        if (tmpStrngSssn.equals("NOT INITIALIZED")) {
            // TODO:  Can I delete the following line in this place and all others?  They don't appear to be used.
            tmpStrngSssn = null;
        } else {
            tmpSssnET.setText(tmpStrngSssn);
        }

        // TODO:  Need a better way to handle this.  Right now a <CR> is needed to call this routine
        tmpSssnET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String mSessionName = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.SESSION_NAME_KEY, mSessionName);
                Toast toast = Toast.makeText(context, mSessionName, Toast.LENGTH_SHORT);
                toast.show();

                String mLocation = mSharedPreference.getStringValue(context, SharedPreference.LOCATION_ID_KEY);
                if (mLocation.equals("NOT INITIALIZED")) {
                    mFileName = mSessionName;
                } else {
                    if (mLocation.length() == 0) {
                        mFileName = mSessionName;
                    } else {
                        mFileName = mSessionName + mLocation;
                    }
                }

                TextView tmpFlNm = (TextView) findViewById(R.id.file_name_text_view);
                tmpFlNm.setText(mFileName);

                return false;
            }
        });

        String tmpStrngLctn = mSharedPreference.getStringValue(this, SharedPreference.LOCATION_ID_KEY);
        EditText tmpLctnET = (EditText) findViewById(R.id.location_edit_text);
        if (tmpStrngLctn.equals("NOT INITIALIZED")) {
            tmpStrngLctn = null;
        } else {
            tmpLctnET.setText(tmpStrngLctn);
        }

        tmpLctnET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String mLocation = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.LOCATION_ID_KEY, mLocation);
                Toast toast = Toast.makeText(context, mLocation, Toast.LENGTH_SHORT);
                toast.show();

                String mSessionName = mSharedPreference.getStringValue(context, SharedPreference.SESSION_NAME_KEY);
                if (mSessionName.equals("NOT INITIALIZED")) {
                    mFileName = mLocation;
                } else {
                    if (mSessionName.length() == 0) {
                        mFileName = mLocation;
                    } else {
                        mFileName = mSessionName + mLocation;
                    }
                }

                TextView tmpFlNm = (TextView) findViewById(R.id.file_name_text_view);
                tmpFlNm.setText(mFileName);

                return false;
            }
        });

        String mFilename = mSessionName + mLocation;
        TextView flnmTV = (TextView) findViewById(R.id.file_name_text_view);
        flnmTV.setText(mFilename);

        String tmpStrng = mSharedPreference.getStringValue(this, SharedPreference.FILE_NAME_KEY);
        TextView tmpFlNm = (TextView) findViewById(R.id.file_name_text_view);
        tmpFlNm.setText(tmpStrng);

//        tmpFlNm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String mFileName = v.getText().toString();
//                sharedPreference.saveString(context, SharedPreference.FILE_NAME_KEY, mFileName);
//                Toast toast = Toast.makeText(context, mFileName, Toast.LENGTH_SHORT);
//                toast.show();
//                return false;
//            }
//        });

        tmpStrng = mSharedPreference.getStringValue(this, SharedPreference.CAMERA_ID_KEY);
        EditText tmpCmrIdET = (EditText) findViewById(R.id.camera_id);
        if (tmpStrng.equals("NOT INITIALIZED")) {
            tmpStrng = null;
        } else {
            tmpCmrIdET.setText(tmpStrng);
        }

        tmpCmrIdET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String mCameraID = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.CAMERA_ID_KEY, mCameraID);
                Toast toast = Toast.makeText(context, mCameraID, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        tmpStrng = mSharedPreference.getStringValue(this, SharedPreference.CAMERA_TIME_ZONE_KEY);
        EditText tmpCmrTmZnET = (EditText) findViewById(R.id.camera_time_zone);
        if (tmpStrng.equals("NOT INITIALIZED")) {
            tmpStrng = null;
        } else {
            tmpCmrTmZnET.setText(tmpStrng);
        }

        tmpCmrTmZnET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String mCameraTimeZone = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.CAMERA_TIME_ZONE_KEY, mCameraTimeZone);
                Toast toast = Toast.makeText(context, mCameraTimeZone, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        boolean tmpBl = mSharedPreference.getBooleanValue(this, SharedPreference.CAMERA_DST_KEY);
        CheckBox tmpCmrDstStCB = (CheckBox) findViewById(R.id.camera_dst_set);
        tmpCmrDstStCB.setChecked(tmpBl);

        tmpCmrDstStCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "Camera DST", Toast.LENGTH_SHORT);
                toast.show();

                String dstSet = " false.";
                CheckBox tmpCmrDstStCB = (CheckBox) findViewById(R.id.camera_dst_set);
                if (tmpCmrDstStCB.isChecked()) {
                    mCameraDST = true;
                    dstSet = " true.";
                } else {
                    mCameraDST = false;
                }
                SharedPreference sharedPreference2 = new SharedPreference();

                sharedPreference2.saveBoolean(context, SharedPreference.CAMERA_DST_KEY, mCameraDST);
                toast = Toast.makeText(context, "Camera DST set:" + dstSet, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        tmpStrng = mSharedPreference.getStringValue(this, SharedPreference.ANDROID_ID_KEY);
        EditText tmpAndrdIdET = (EditText) findViewById(R.id.android_id);
        if (tmpStrng.equals("NOT INITIALIZED")) {
            tmpStrng = null;
        } else {
            tmpAndrdIdET.setText(tmpStrng);
        }

        tmpAndrdIdET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String mAndroidID = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.ANDROID_ID_KEY, mAndroidID);
                Toast toast = Toast.makeText(context, mAndroidID, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        tmpStrng = mSharedPreference.getStringValue(this, SharedPreference.ANDROID_TIME_ZONE_KEY);
        EditText tmpAndrdTmZnET = (EditText) findViewById(R.id.android_time_zone);
        if (tmpStrng.equals("NOT INITIALIZED")) {
            tmpStrng = null;
        } else {
            tmpAndrdTmZnET.setText(tmpStrng);
        }

        tmpAndrdTmZnET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String mAndroidTimeZone = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.ANDROID_TIME_ZONE_KEY, mAndroidTimeZone);
                Toast toast = Toast.makeText(context, mAndroidTimeZone, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        tmpBl = mSharedPreference.getBooleanValue(this, SharedPreference.ANDROID_DST_KEY);
        CheckBox tmpAndrdDstStCB = (CheckBox) findViewById(R.id.android_dst_set);
        // TODO:  For all similar statements in this class (and others) need to figure out how
        // to handle the possible null pointer exceptions

        tmpAndrdDstStCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "Android DST", Toast.LENGTH_SHORT);
                toast.show();

                String dstSet = " false.";
                CheckBox tmpAndrdDstStCB = (CheckBox) findViewById(R.id.android_dst_set);
                if (tmpAndrdDstStCB.isChecked()) {
                    mAndroidDST = true;
                    dstSet = " true.";
                } else {
                    mAndroidDST = false;
                }
                SharedPreference sharedPreference2 = new SharedPreference();

                sharedPreference2.saveBoolean(context, SharedPreference.ANDROID_DST_KEY, mAndroidDST);
                toast = Toast.makeText(context, "Android DST set:" + dstSet, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreference sharedPreference = new SharedPreference();

                EditText ssnEdtTxt = (EditText) findViewById(R.id.session_edit_text);
                mSessionName = ssnEdtTxt.getText().toString();
                // saveString the shared preferences
                sharedPreference.saveString(context, SharedPreference.SESSION_NAME_KEY, mSessionName);
                Log.i(CREATE_SESSION_MESSAGE, "Session Name: " + mSessionName);

                EditText locEdtTxt = (EditText) findViewById(R.id.location_edit_text);
                mLocation = locEdtTxt.getText().toString();
                sharedPreference.saveString(context, SharedPreference.LOCATION_ID_KEY, mLocation);
                Log.i(CREATE_SESSION_MESSAGE, "Location: " + mLocation);

                TextView fname = (TextView) findViewById(R.id.file_name_text_view);
                mFileName = mLocation + "_" + mSessionName;
                fname.setText(mFileName);
                sharedPreference.saveString(context, SharedPreference.FILE_NAME_KEY, mFileName);
                Log.i(CREATE_SESSION_MESSAGE, "File Name will be: " + mFileName);

                EditText cameraID = (EditText) findViewById(R.id.camera_id);
                mCameraID = cameraID.getText().toString();
                sharedPreference.saveString(context, SharedPreference.CAMERA_ID_KEY, mCameraID);
                Log.i(CREATE_SESSION_MESSAGE, "Camera ID: " + mCameraID);

                EditText cameraTimeZone = (EditText) findViewById(R.id.camera_time_zone);
                mCameraTZ = cameraTimeZone.getText().toString();
                sharedPreference.saveString(context, SharedPreference.CAMERA_TIME_ZONE_KEY, mCameraTZ);
                Log.i(CREATE_SESSION_MESSAGE, "Camera TimeZone: " + mCameraTZ);

                CheckBox cameraChkBx = (CheckBox) findViewById(R.id.camera_dst_set);
                mCameraDST = cameraChkBx.isChecked();
                sharedPreference.saveBoolean(context, SharedPreference.CAMERA_DST_KEY, mCameraDST);
                Log.i(CREATE_SESSION_MESSAGE, "Camera DST set: " + mCameraDST.toString());

                EditText androidID = (EditText) findViewById(R.id.android_id);
                mAndroidID = androidID.getText().toString();
                sharedPreference.saveString(context, SharedPreference.ANDROID_ID_KEY, mAndroidID);
                Log.i(CREATE_SESSION_MESSAGE, "Android Device ID: " + mAndroidID);

                EditText androidTimeZone = (EditText) findViewById(R.id.android_time_zone);
                mAndroidTZ = androidTimeZone.getText().toString();
                sharedPreference.saveString(context, SharedPreference.ANDROID_TIME_ZONE_KEY, mAndroidTZ);
                Log.i(CREATE_SESSION_MESSAGE, "Android Device TimeZone: " + mAndroidTZ);

                CheckBox tabletChkBx = (CheckBox) findViewById(R.id.android_dst_set);
                mAndroidDST = tabletChkBx.isChecked();
                sharedPreference.saveBoolean(context, SharedPreference.ANDROID_DST_KEY, mAndroidDST);
                Log.i(CREATE_SESSION_MESSAGE, "Android DST set: " + mAndroidDST.toString());

                Toast toast = Toast.makeText(ActivityCreateSession.this, "File name is: " + mFileName, Toast.LENGTH_SHORT);
                toast.show();

                String camera_str_1 = "Camera id:  " + mCameraID + ", Time Zone: " + mCameraTZ + ", DST ";
                if (mCameraDST) {
                    camera_str_1 = camera_str_1 + "ON.";
                } else {
                    camera_str_1 = camera_str_1 + "OFF.";
                }

                String android_str_1 = "Android id:  " + mAndroidID + ", Time Zone: " + mAndroidTZ + ", DST ";
                if (mAndroidDST) {
                    android_str_1 = android_str_1 + "ON.";
                } else {
                    android_str_1 = android_str_1 + "OFF.";
                }

                toast = Toast.makeText(ActivityCreateSession.this, camera_str_1 + ".\n" + android_str_1, Toast.LENGTH_SHORT);
                toast.show();

                // TODO:  Create a session file / database with the appropriate file name.
                //          This might actually be a single database for all sessions with the current session file name as
                //          a key in the entries or it might be a separate file for each new session
                // TODO:  After creating a session it should go directly to the activity for capturing information for the session
                //          with a reference to the current session.  But it isn't clear what the parent will be
                //          for the new session.
                //          https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=android%20manifest%20multiple%20parent%20activities
                //          http://developer.android.com/design/patterns/navigation.html
                //          http://stackoverflow.com/questions/23475788/how-to-set-multiple-parent-activities-for-using-android-back-button

                mDir = getFilesDir();
                String path = mDir.getAbsolutePath();
                toast = Toast.makeText(ActivityCreateSession.this, "The current path is: " + path, Toast.LENGTH_SHORT);
                toast.show();

                // mFOS = FileOutputStream.

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_session_management, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
//    public class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_create_session, container, false);

            return rootView;
        }
    }
}
