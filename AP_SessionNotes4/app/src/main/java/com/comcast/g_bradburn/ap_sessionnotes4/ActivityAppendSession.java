package com.comcast.g_bradburn.ap_sessionnotes4;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

    String[] mImageTypes = {"NO IMAGETYPE SELECTION", "POLAR_ALIGN", "FOCUS", "LIGHTS",
            "DARKS", "BIAS", "FLAT", "CALIBRATION", "OTHER"};
    int mDeflt = 0;     // this index will probably never be explicitly used.  It is just making it clear that the first element is reserved for the default case
    int mPolAln = 1;
    int mFcs = 2;
    int mLght = 3;
    int mDrk = 4;
    int mBs = 5;
    int mFlt = 6;
    int mCal = 7;
    int mOthrRb = 8;

    String mImageType = "not initialized";
    int myExpTime;
    int myISO;
    Boolean mLockup;
    String mTargetID;

    Boolean mVib, mCld, mFlshLght, mCrLghts, mAirpln, mStllte, mOthrInt, mMtr;

    private SharedPreference sharedPreference, mSharedPreference;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO:  Can I position the floating action button closer to the text inputs so it seems related to them?
        // TODO:  Should have listeners for the txt fields too so the FAB can be deleted.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Save Text Values to Shared Preferences", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                // Read and display the shared preferences
                sharedPreference = new SharedPreference();

                Toast toast;

                EditText expTimeEdtTxt = (EditText) findViewById(R.id.expTimeEditTxt);
                String mExpTime = expTimeEdtTxt.getText().toString();
                if (mExpTime.equals("")) {
                    myExpTime = 0;
                    mExpTime = "Exposure time not set.";
                } else {
                    myExpTime = Integer.parseInt(mExpTime);
                }
                // saveString the shared preferences
                sharedPreference.saveInt(context, SharedPreference.EXPOSURE_TIME_KEY, myExpTime);
                toast = Toast.makeText(ActivityAppendSession.this, mExpTime, Toast.LENGTH_SHORT);
                toast.show();

                EditText isoEdtTxt = (EditText) findViewById(R.id.editISO);
                String mISO = isoEdtTxt.getText().toString();
                if (mISO.equals("")) {
                    myISO = -999;
                    mISO = "ISO not set.";
                } else {
                    myISO = Integer.parseInt(mISO);
                }
                sharedPreference.saveInt(context, SharedPreference.ISO_KEY, myISO);
                toast = Toast.makeText(ActivityAppendSession.this, mISO, Toast.LENGTH_SHORT);
                toast.show();

                EditText targetEdtTxt = (EditText) findViewById(R.id.editTargetID);
                mTargetID = targetEdtTxt.getText().toString();
                sharedPreference.saveString(context, SharedPreference.TARGET_ID_KEY, mTargetID);
                toast = Toast.makeText(ActivityAppendSession.this, mTargetID, Toast.LENGTH_SHORT);
                toast.show();

//                There is no need to do the lockup check here as it will be captured to the shared preferences by the listener
//                String lckup = " false.";
//                CheckBox mirrorLockupChkBx = (CheckBox) findViewById(R.id.mirrorLockupCBx);
//                if (mirrorLockupChkBx.isChecked()) {
//                    mLockup = true;
//                    lckup = " true.";
//                } else {
//                    mLockup = false;
//                }
//                sharedPreference.saveBoolean(context, SharedPreference.MIRROR_LOCKUP_KEY, mLockup);
//                toast = Toast.makeText(ActivityAppendSession.this, "Lockup: " + lckup, Toast.LENGTH_SHORT);
//                toast.show();

//                There is no need to do the RadioGroup check here as it will be captured to the shared preferences by the listener
//                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.imageTypeRadioGroup);
//                // is anything checked?
//
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//
//                // check which button is checked
//                switch (selectedId) {
//                    case R.id.radioButtonPolarAlign:
//                        mImageType = mImageTypes[mPolAln];
//                        break;
//                    case R.id.radioButtonFocus:
//                        mImageType = mImageTypes[mFcs];
//                        break;
//                    case R.id.radioButtonLights:
//                        mImageType = mImageTypes[mLght];
//                        break;
//                    case R.id.radioButtonDarks:
//                        mImageType = mImageTypes[mDrk];
//                        break;
//                    case R.id.radioButtonBias:
//                        mImageType = mImageTypes[mBs];
//                        break;
//                    case R.id.radioButtonFlat:
//                        mImageType = mImageTypes[mFlt];
//                        break;
//                    case R.id.radioButtonCalibration:
//                        mImageType = mImageTypes[mCal];
//                        break;
//                    case R.id.radioButtonOther:
//                        mImageType = mImageTypes[mOthrRb];
//                        break;
//                    default:
//                        mImageType = "NO IMAGETYPE SELECTION";
//                }
//                sharedPreference = new SharedPreference();
//                sharedPreference.saveString(context, SharedPreference.IMAGE_TYPE_KEY, mImageType);
//                Toast toast = Toast.makeText(ActivityAppendSession.this, "Radio Button = " + mImageType, Toast.LENGTH_SHORT);
//                toast.show();

            }
        });



        CheckBox mirrorLockupChkBx = (CheckBox) findViewById(R.id.mirrorLockupCBx);
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

        mSharedPreference = new SharedPreference();
        boolean mMrrrLckp = mSharedPreference.getBooleanValue(context, SharedPreference.MIRROR_LOCKUP_KEY);
        mirrorLockupChkBx.setChecked(mMrrrLckp);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.imageTypeRadioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int selectedId) {
                // is anything checked?

                // check which button is checked
                switch (selectedId) {
                    case R.id.radioButtonPolarAlign:
                        mImageType = mImageTypes[mPolAln];
                        break;
                    case R.id.radioButtonFocus:
                        mImageType = mImageTypes[mFcs];
                        break;
                    case R.id.radioButtonLights:
                        mImageType = mImageTypes[mLght];
                        break;
                    case R.id.radioButtonDarks:
                        mImageType = mImageTypes[mDrk];
                        break;
                    case R.id.radioButtonBias:
                        mImageType = mImageTypes[mBs];
                        break;
                    case R.id.radioButtonFlat:
                        mImageType = mImageTypes[mFlt];
                        break;
                    case R.id.radioButtonCalibration:
                        mImageType = mImageTypes[mCal];
                        break;
                    case R.id.radioButtonOther:
                        mImageType = mImageTypes[mOthrRb];
                        break;
                    default:
                        mImageType = "NO IMAGETYPE SELECTION";
                }
                sharedPreference = new SharedPreference();

                sharedPreference.saveString(context, SharedPreference.IMAGE_TYPE_KEY, mImageType);
                Toast toast = Toast.makeText(ActivityAppendSession.this, "Radio Button = " + mImageType, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        String mRdGrp = mSharedPreference.getStringValue(context, SharedPreference.IMAGE_TYPE_KEY);
        RadioButton rBtn;
        switch (mRdGrp) {
            case "NO IMAGETYPE SELECTION":
                break;
            case "POLAR_ALIGN":
                rBtn = (RadioButton) findViewById(R.id.radioButtonPolarAlign);
                rBtn.setChecked(true);
                break;
            case "FOCUS":
                rBtn = (RadioButton) findViewById(R.id.radioButtonFocus);
                rBtn.setChecked(true);
                break;
            case "LIGHTS":
                rBtn = (RadioButton) findViewById(R.id.radioButtonLights);
                rBtn.setChecked(true);
                break;
            case "DARKS":
                rBtn = (RadioButton) findViewById(R.id.radioButtonDarks);
                rBtn.setChecked(true);
                break;
            case "BIAS":
                rBtn = (RadioButton) findViewById(R.id.radioButtonBias);
                rBtn.setChecked(true);
                break;
            case "FLAT":
                rBtn = (RadioButton) findViewById(R.id.radioButtonFlat);
                rBtn.setChecked(true);
                break;
            case "CALIBRATION":
                rBtn = (RadioButton) findViewById(R.id.radioButtonCalibration);
                rBtn.setChecked(true);
                break;
            case "OTHER":
                rBtn = (RadioButton) findViewById(R.id.radioButtonOther);
                rBtn.setChecked(true);
                break;
        }

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
                sharedPreference.saveBoolean(context, SharedPreference.VIBRATION_KEY, mVib);
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.vibration_text) + vib, Toast.LENGTH_SHORT);
                toast.show();

                mVib = false; // The checked state of this checkbox should be momentary
                vibChkBx.setChecked(false); // The checked state of this checkbox should be momentary
                sharedPreference.saveBoolean(context, SharedPreference.VIBRATION_KEY, mVib);
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
                sharedPreference.saveBoolean(context, SharedPreference.METEOR_KEY, mMtr);
                Toast toast = Toast.makeText(ActivityAppendSession.this, getString(R.string.meteor_text) + meteor, Toast.LENGTH_SHORT);
                toast.show();

                mMtr = false; // The checked state of this checkbox should be momentary
                meteorChkBx.setChecked(false); // The checked state of this checkbox should be momentary
                sharedPreference.saveBoolean(context, SharedPreference.METEOR_KEY, mMtr);
            }
        });

        SharedPreference mSharedPreference = new SharedPreference();

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

        boolean mCld = mSharedPreference.getBooleanValue(context, SharedPreference.CLOUD_KEY);
        cloudChkBx.setChecked(mCld);

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

        boolean mFlshLght = mSharedPreference.getBooleanValue(context, SharedPreference.FLASHLIGHT_KEY);
        flashlightChkBx.setChecked(mFlshLght);

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

        boolean mCrLghts = mSharedPreference.getBooleanValue(context, SharedPreference.CAR_LIGHTS_KEY);
        carLightsChkBx.setChecked(mCrLghts);

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

        boolean mArpln = mSharedPreference.getBooleanValue(context, SharedPreference.AIRPLANE_KEY);
        airplaneChkBx.setChecked(mArpln);

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

        boolean mStllt = mSharedPreference.getBooleanValue(context, SharedPreference.SATELLITE_KEY);
        satelliteChkBx.setChecked(mStllt);

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

        boolean mOthr = mSharedPreference.getBooleanValue(context, SharedPreference.OTHER_INTERFERENCE_KEY);
        otherChkBx.setChecked(mOthr);

        otherChkBx.setChecked(mOthr);

//        EditText expTimeEdtTxt = (EditText) findViewById(R.id.expTimeEditTxt);
//        String mExpTm = expTimeEdtTxt.getText().toString();
//        if (mExpTm.equals("")) {
//            myExpTime = 0;
//            mExpTm = "Exposure Time not set.";
//        } else {
//            myExpTime = Integer.parseInt(mExpTm);
//        }
//        sharedPreference.saveInt(context, SharedPreference.ISO_KEY, myISO);
//        Toast toast = Toast.makeText(ActivityAppendSession.this, mISO, Toast.LENGTH_SHORT);
//        toast.show();
//
//        EditText isoEdtTxt = (EditText) findViewById(R.id.editISO);
//        String mISO = isoEdtTxt.getText().toString();
//        if (mISO.equals("")) {
//            myISO = -999;
//            mISO = "ISO not set.";
//        } else {
//            myISO = Integer.parseInt(mISO);
//        }
//        sharedPreference.saveInt(context, SharedPreference.ISO_KEY, myISO);
//        toast = Toast.makeText(ActivityAppendSession.this, mISO, Toast.LENGTH_SHORT);
//        toast.show();

        EditText expTimeEdtTxt = (EditText) findViewById(R.id.expTimeEditTxt);
        // TODO:  Need a better way to handle this.  Right now a <CR> is needed to call this routine
        expTimeEdtTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String myExpTimeEdtTxt = v.getText().toString();
                myExpTime = Integer.parseInt(myExpTimeEdtTxt);
                sharedPreference.saveInt(context, SharedPreference.EXPOSURE_TIME_KEY, myExpTime);
                Toast toast = Toast.makeText(ActivityAppendSession.this, myExpTimeEdtTxt, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        EditText IsoEdtTxt = (EditText) findViewById(R.id.editISO);
        // TODO:  Need a better way to handle this.  Right now a <CR> is needed to call this routine
        IsoEdtTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String myIsoEdtTxt = v.getText().toString();
                myISO = Integer.parseInt(myIsoEdtTxt);
                sharedPreference.saveInt(context, SharedPreference.ISO_KEY, myISO);
                Toast toast = Toast.makeText(ActivityAppendSession.this, myIsoEdtTxt, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        EditText TargetIdEdtTxt = (EditText) findViewById(R.id.editTargetID);
        // TODO:  Need a better way to handle this.  Right now a <CR> is needed to call this routine
        TargetIdEdtTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String myTargetID = v.getText().toString();
                sharedPreference.saveString(context, SharedPreference.TARGET_ID_KEY, myTargetID);
                Toast toast = Toast.makeText(ActivityAppendSession.this, myTargetID, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        int mExpTime = mSharedPreference.getIntValue(context, SharedPreference.EXPOSURE_TIME_KEY);
        expTimeEdtTxt.setText(Integer.toString(mExpTime));

        EditText expIsoTxt = (EditText) findViewById(R.id.editISO);
        int mIso = mSharedPreference.getIntValue(context, SharedPreference.ISO_KEY);
        expIsoTxt.setText(Integer.toString(mIso));

        EditText targetId = (EditText) findViewById(R.id.editTargetID);
        String mTrgtId = mSharedPreference.getStringValue(context, SharedPreference.TARGET_ID_KEY);
        targetId.setText(mTrgtId);
    }
}
