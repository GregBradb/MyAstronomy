package com.comcast.g_bradburn.ap_sessionnotes4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //  Somewhere I need to define an AstroSession class that will have the parameters and methods
    //    defined.  Then I need to incorporate that definition, either through an inport or an include statement.
    //    Probably can't share data between activities directly but can pass information via an intent.

    static final int GET_FILE_STREAM = 1;

    static String fileName = "temporary_data.txt";
    static File mDir;
    static FileOutputStream mFOS;

    private SharedPreference sharedPreference;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Read and display the shared preferences
                SharedPreference sharedPreference = new SharedPreference();

                String temp = sharedPreference.getStringValue(context, SharedPreference.SESSION_NAME_KEY);
                Toast toast = Toast.makeText(context, "Session Name: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.LOCATION_ID_KEY);
                toast = Toast.makeText(context, "Location ID: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.FILE_NAME_KEY);
                toast = Toast.makeText(context, "File Name: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.CAMERA_ID_KEY);
                toast = Toast.makeText(context, "Camera ID: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.CAMERA_TIME_ZONE_KEY);
                toast = Toast.makeText(context, "Camera Time Zone: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                String myChkBx;
                Boolean tempBool = sharedPreference.getBooleanValue(context, SharedPreference.CAMERA_DST_KEY);
                if (tempBool) {
                    myChkBx = "is checked";
                }
                else {
                    myChkBx = "is NOT checked";
                }
                toast = Toast.makeText(context, "Camera DST: " + myChkBx, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.ANDROID_ID_KEY);
                toast = Toast.makeText(context, "Android ID: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.ANDROID_TIME_ZONE_KEY);
                toast = Toast.makeText(context, "Android Time Zone: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                tempBool = sharedPreference.getBooleanValue(context, SharedPreference.ANDROID_DST_KEY);
                if (tempBool) {
                    myChkBx = "is checked";
                }
                else {
                    myChkBx = "is NOT checked";
                }
                toast = Toast.makeText(context, "Android DST: " + myChkBx, Toast.LENGTH_SHORT);
                toast.show();

                int tempInt = sharedPreference.getIntValue(context, SharedPreference.EXPOSURE_TIME_KEY);
                toast = Toast.makeText(context, "Exposure Time: " + tempInt + " ms", Toast.LENGTH_SHORT);
                toast.show();

                tempInt = sharedPreference.getIntValue(context, SharedPreference.ISO_KEY);
                toast = Toast.makeText(context, "Exposure Time: " + tempInt + " ms", Toast.LENGTH_SHORT);
                toast.show();

                tempBool = sharedPreference.getBooleanValue(context, SharedPreference.MIRROR_LOCKUP_KEY);
                if (tempBool) {
                    myChkBx = "is checked";
                }
                else {
                    myChkBx = "is NOT checked";
                }
                toast = Toast.makeText(context, "Mirror LockUp: " + myChkBx, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.TARGET_ID_KEY);
                toast = Toast.makeText(context, "Target ID: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.IMAGE_TYPE_KEY);
                toast = Toast.makeText(context, "Image Type: " + temp, Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.VIBRATION_KEY);
                toast = Toast.makeText(context, "Vibration or Shake During Exposure", Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.FLASHLIGHT_KEY);
                toast = Toast.makeText(context, "Flashlight During Exposure", Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.CAR_LIGHTS_KEY);
                toast = Toast.makeText(context, "Car Lights During Exposure", Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.AIRPLANE_KEY);
                toast = Toast.makeText(context, "Airplane Lights During Exposure", Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.SATELLITE_KEY);
                toast = Toast.makeText(context, "Satellite During Exposure", Toast.LENGTH_SHORT);
                toast.show();

                temp = sharedPreference.getStringValue(context, SharedPreference.METEOR_KEY);
                toast = Toast.makeText(context, "Meteor During Exposure", Toast.LENGTH_SHORT);
                toast.show();            }
        });

        Button create_btn = (Button) findViewById(R.id.create_button);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO:  remove following temporary mFOS declaration
                mDir = getFilesDir();
                String path = mDir.getAbsolutePath();
                Toast toast = Toast.makeText(MainActivity.this, "The current path is: " + path, Toast.LENGTH_LONG);
                toast.show();
                try {
                    mFOS = openFileOutput(fileName, MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String tempString = "Try one line of text.\n";
                try {
                    mFOS.write(tempString.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mFOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent session_mgmt_intent = new Intent(MainActivity.this, ActivityCreateSession.class);
                startActivity(session_mgmt_intent);
                // TODO:  should request the file stream back using startActivityForResult.
                // startActivityForResult(session_mgmt_intent, GET_FILE_STREAM);
            }
        });

        Button open_btn = (Button) findViewById(R.id.append_button);
        open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent session_open_intent = new Intent(MainActivity.this, ActivityAppendSession.class);
                startActivity(session_open_intent);
            }
        });

        Button reviewBtn = (Button) findViewById(R.id.review_button);
        reviewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
        public  void onClick(View v){
                Intent session_review_intent = new Intent(MainActivity.this, ActivityReviewSession.class);
                startActivity(session_review_intent);
            }
        });

        Button closeBtn = (Button) findViewById(R.id.close_button);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent session_close_intent = new Intent(MainActivity.this, ActivityCloseSesion.class);
                startActivity(session_close_intent);
            }
        });

        Button deleteBtn = (Button) findViewById(R.id.delete_button);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent session_delete_intent = new Intent(MainActivity.this, ActivityDeleteSession.class);
                startActivity(session_delete_intent);
            }
        });

    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            // Check which request we're responding to
            switch (requestCode) {
                case GET_FILE_STREAM:
                    Uri fileOutputStream = data.getData();
                    // TODO:  Figure out how to get the stream from the URI using a ContentResolver???
                    Toast toast = Toast.makeText(this, "Getting activity result", Toast.LENGTH_LONG);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
