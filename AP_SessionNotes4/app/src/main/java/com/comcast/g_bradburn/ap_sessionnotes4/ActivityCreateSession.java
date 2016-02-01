package com.comcast.g_bradburn.ap_sessionnotes4;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private SharedPreference sharedPreference;
    Activity context = this;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_create_session);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                SharedPreference sharedPreference = new SharedPreference();

                EditText ssnEditText = (EditText) findViewById(R.id.session_edit_text);
                mSessionName = ssnEditText.getText().toString();
                // saveString the shared preferences
                sharedPreference.saveString(context, SharedPreference.SESSION_NAME_KEY, mSessionName);
                Log.i(CREATE_SESSION_MESSAGE, "Session Name: " + mSessionName);

                EditText locEditText = (EditText) findViewById(R.id.location_edit_text);
                mLocation = locEditText.getText().toString();
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

                Toast toast = Toast.makeText(ActivityCreateSession.this, "File name is: " + mFileName, Toast.LENGTH_LONG);
                toast.show();

                String camera_str_1 = "Camera id:  " + mCameraID + ", Time Zone: " + mCameraTZ + ", DST ";
                if (mCameraDST) {
                    camera_str_1 = camera_str_1 + "ON.";
                }
                else{
                    camera_str_1 = camera_str_1 + "OFF.";
                }

                String android_str_1 = "Android id:  " + mAndroidID + ", Time Zone: " + mAndroidTZ + ", DST ";
                if (mAndroidDST) {
                    android_str_1 = android_str_1 + "ON.";
                }
                else{
                    android_str_1 = android_str_1 + "OFF.";
                }

                toast = Toast.makeText(ActivityCreateSession.this, camera_str_1 + ".\n" + android_str_1, Toast.LENGTH_LONG);
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
                toast = Toast.makeText(ActivityCreateSession.this, "The current path is: " + path, Toast.LENGTH_LONG);
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
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_management_create_session, container, false);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
