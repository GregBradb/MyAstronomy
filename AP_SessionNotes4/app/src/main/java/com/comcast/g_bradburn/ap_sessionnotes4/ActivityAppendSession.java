package com.comcast.g_bradburn.ap_sessionnotes4;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityAppendSession extends AppCompatActivity {
    // This class is to allow the user to capture changes in the session.  When the Floating Action Button (fab)
    //  is clicked, it should:
    // TODO:  capture the status of all the image detail controls.
    // TODO:  capture a timestamp from the operating system
    // TODO:  write any changes in state to the database along with the timestamp
    // TODO:  update the current state
    // TODO:  will need to keep track of the current state - or be able to read the current state back from the dataBase

    // TODO:  This should not be called when the "APPEND TO AN EXISTING SESSION" button is pressed.  Instead it should
    //          call an intermediate activity which prompts for the existing session's name and then
    //          enters this activity.

    String[] mImageTypes = {"POLAR_ALIGN", "FOCUS", "LIGHTS",
            "DARKS", "BIAS", "FLAT", "CALIBRATION", "OTHER"};
    int mPolAln = 0;
    int mFcs = 1;
    int mLght = 2;
    int mDrk = 3;
    int mBs = 4;
    int mFlt = 5;
    int mCal = 6;
    int mOthrRb = 7;

    String mImageType;
    int mExpTime;
    String mExpTimeUnits;
    int mISO;
    Boolean mLockup;
    String mTargetID;

    Boolean mVib, mCld, mFlshLght, mCrLghts, mAirpln, mStllte, mOthrInt, mMtr;

    private SharedPreference sharedPreference;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Save", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                // Read and display the shared preferences
                sharedPreference = new SharedPreference();

                EditText expTimeEdtTxt = (EditText) findViewById(R.id.expTimeEditTxt);
                String mExpTime = expTimeEdtTxt.getText().toString();
                int myExpTime;
                if (mExpTime.equals("")) {
                    myExpTime = 0;
                    mExpTime = "Exposure time not set.";
                } else {
                    myExpTime = Integer.parseInt(mExpTime);
                }
                // saveString the shared preferences
                sharedPreference.saveInt(context, SharedPreference.EXPOSURE_TIME_KEY, myExpTime);
                Toast toast = Toast.makeText(ActivityAppendSession.this, mExpTime, Toast.LENGTH_SHORT);
                toast.show();

                EditText isoEdtTxt = (EditText) findViewById(R.id.editISO);
                String mISO = isoEdtTxt.getText().toString();
                int myISO;
                if (mISO.equals("")) {
                    myISO = -999;
                    mISO = "ISO not set.";
                } else {
                    myISO = Integer.parseInt(mISO);
                }
                sharedPreference.saveInt(context, SharedPreference.ISO_KEY, myISO);
                toast = Toast.makeText(ActivityAppendSession.this, mISO, Toast.LENGTH_SHORT);
                toast.show();

                String lckup = " false.";
                CheckBox mirrorLockupChkBx = (CheckBox) findViewById(R.id.mirrorLockupCBx);
                if (mirrorLockupChkBx.isChecked()) {
                    mLockup = true;
                    lckup = " true.";
                } else {
                    mLockup = false;
                }
                sharedPreference.saveBoolean(context, SharedPreference.MIRROR_LOCKUP_KEY, mLockup);
                toast = Toast.makeText(ActivityAppendSession.this, "Lockup: " + lckup, Toast.LENGTH_SHORT);
                toast.show();

                mirrorLockupChkBx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(ActivityAppendSession.this, "Mirror LockUp", Toast.LENGTH_SHORT);
                        toast.show();

                        String lckup = " false.";
                        CheckBox mirrorLockupChkBx = (CheckBox) findViewById(R.id.mirrorLockupCBx);
                        if (mirrorLockupChkBx.isChecked()) {
                            mLockup = true;
                            lckup = " true.";
                        } else {
                            mLockup = false;
                        }
                        SharedPreference sharedPreference2 = new SharedPreference();

                        sharedPreference2.saveBoolean(context, SharedPreference.MIRROR_LOCKUP_KEY, mLockup);
                        toast = Toast.makeText(ActivityAppendSession.this, "Lockup: " + lckup, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                EditText targetEdtTxt = (EditText) findViewById(R.id.editTargetID);
                String mTargetID = targetEdtTxt.getText().toString();

                sharedPreference.saveString(context, SharedPreference.TARGET_ID_KEY, mTargetID);
                toast = Toast.makeText(ActivityAppendSession.this, mTargetID, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.imageTypeRadioGroup);

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // is anything checked?
                boolean checked = ((RadioButton) v).isChecked();

                int selectedId = radioGroup.getCheckedRadioButtonId();

                // check which button is checked
                switch (selectedId) {
                    case R.id.radioButtonPolarAlign:
                        if (checked)
                            mImageType = mImageTypes[mPolAln];
                        break;
                    case R.id.radioButtonFocus:
                        if (checked)
                            mImageType = mImageTypes[mFcs];
                        break;
                    case R.id.radioButtonLights:
                        if (checked)
                            mImageType = mImageTypes[mLght];
                        break;
                    case R.id.radioButtonDarks:
                        if (checked)
                            mImageType = mImageTypes[mDrk];
                        break;
                    case R.id.radioButtonBias:
                        if (checked)
                            mImageType = mImageTypes[mBs];
                        break;
                    case R.id.radioButtonFlat:
                        if (checked)
                            mImageType = mImageTypes[mFlt];
                        break;
                    case R.id.radioButtonCalibration:
                        if (checked)
                            mImageType = mImageTypes[mCal];
                        break;
                    case R.id.radioButtonOther:
                        if (checked)
                            mImageType = mImageTypes[mOthrRb];
                        break;
                }
                sharedPreference = new SharedPreference();

                sharedPreference.saveString(context, SharedPreference.IMAGE_TYPE_KEY, mImageType);
                Toast toast = Toast.makeText(ActivityAppendSession.this, "Radio Button = " + mImageType, Toast.LENGTH_SHORT);

                toast.show();


            }
        });

        CheckBox vibChkBx = (CheckBox) findViewById(R.id.chkBxVibration);
        vibChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vib;
                CheckBox vibChkBx = (CheckBox) findViewById(R.id.chkBxVibration);
                if (vibChkBx.isChecked()) {
                    mVib = true;
                    vib = " true.";
                } else {
                    mVib = false;
                    vib = " false.";
                }

                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.VIBRATION_KEY, vibChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.vibration_text) + vib, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox cloudChkBx = (CheckBox) findViewById(R.id.chkBxCloud);
        cloudChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cloud;
                CheckBox cloudChkBx = (CheckBox) findViewById(R.id.chkBxCloud);
                if (cloudChkBx.isChecked()) {
                    mCld = true;
                    cloud = " true.";
                } else {
                    mCld = false;
                    cloud = " false.";
                }

                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.CLOUD_KEY, cloudChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.cloud_text) + cloud, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox flashlightChkBx = (CheckBox) findViewById(R.id.chkBxFlashlight);
        flashlightChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flashlight;
                CheckBox flashlightChkBx = (CheckBox) findViewById(R.id.chkBxFlashlight);
                if (flashlightChkBx.isChecked()) {
                    mFlshLght = true;
                    flashlight = " true.";
                } else {
                    mFlshLght = false;
                    flashlight = " false.";
                }

                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.FLASHLIGHT_KEY, flashlightChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.flashlight_text) + flashlight, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox carLightsChkBx = (CheckBox) findViewById(R.id.chkBxCarLights);
        carLightsChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carLights;
                CheckBox carLightsChkBx = (CheckBox) findViewById(R.id.chkBxCarLights);
                if (carLightsChkBx.isChecked()) {
                    mCrLghts = true;
                    carLights = " true.";
                } else {
                    mCrLghts = false;
                    carLights = " false.";
                }
                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.CAR_LIGHTS_KEY, carLightsChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.car_text) + carLights, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox airplaneChkBx = (CheckBox) findViewById(R.id.chkBxAirplane);
        airplaneChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String airplane;
                CheckBox airplaneChkBx = (CheckBox) findViewById(R.id.chkBxAirplane);
                if (airplaneChkBx.isChecked()) {
                    mAirpln = true;
                    airplane = " true.";
                } else {
                    mAirpln = false;
                    airplane = " false.";
                }
                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.AIRPLANE_KEY, airplaneChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.airplane_text) + airplane, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox satelliteChkBx = (CheckBox) findViewById(R.id.chkBxSatellite);
        satelliteChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String satellite;
                CheckBox satelliteChkBx = (CheckBox) findViewById(R.id.chkBxSatellite);
                if (satelliteChkBx.isChecked()) {
                    mStllte = true;
                    satellite = " true.";
                } else {
                    mStllte = false;
                    satellite = " false.";
                }
                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.SATELLITE_KEY, satelliteChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.satellite_text) + satellite, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox otherChkBx = (CheckBox) findViewById(R.id.chkBxOtherInterference);
        otherChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String other_interference;
                CheckBox otherChkBx = (CheckBox) findViewById(R.id.chkBxOtherInterference);
                if (otherChkBx.isChecked()) {
                    mOthrInt = true;
                    other_interference = " true.";
                } else {
                    mOthrInt = false;
                    other_interference = " false.";
                }
                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.OTHER_INTERFERENCE_KEY, otherChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.other_interference_text) + other_interference, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        CheckBox meteorChkBx = (CheckBox) findViewById(R.id.chkBxMeteor);
        meteorChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meteor;
                CheckBox meteorChkBx = (CheckBox) findViewById(R.id.chkBxMeteor);
                if (meteorChkBx.isChecked()) {
                    mMtr = true;
                    meteor = " true.";
                } else {
                    mMtr = false;
                    meteor = " false.";
                }
                sharedPreference = new SharedPreference();
                sharedPreference.saveBoolean(context, SharedPreference.CAR_LIGHTS_KEY, meteorChkBx.isChecked());
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.meteor_text) + meteor, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
