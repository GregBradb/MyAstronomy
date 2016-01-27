package com.comcast.g_bradburn.ap_sessionnotes4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityImageDetails extends AppCompatActivity {
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
    int mOthr = 7;

    String mImageType;
    int mExpTime;
    String mExpTimeUnits;
    int mISO;
    Boolean mLockup;
    String mTargetID;

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
                Snackbar.make(view, "Save", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                EditText expTimeEdtTxt = (EditText) findViewById(R.id.expTimeEditTxt);
                String mExpTime = expTimeEdtTxt.getText().toString();
                Toast toast = Toast.makeText(ActivityImageDetails.this, mExpTime, Toast.LENGTH_LONG);
                toast.show();

                EditText isoEdtTxt = (EditText) findViewById(R.id.editISO);
                String mISO = isoEdtTxt.getText().toString();
                toast = Toast.makeText(ActivityImageDetails.this, mISO, Toast.LENGTH_LONG);
                toast.show();

                String lckup = " false.";
                CheckBox mirrorLockupChkBx = (CheckBox) findViewById(R.id.mirrorLockupCBx);
                if (mirrorLockupChkBx.isChecked())
                {
                    mLockup = true;
                    lckup = " true.";
                }
                else
                {
                    mLockup = false;
                }
                toast = Toast.makeText(ActivityImageDetails.this, "Lockup: " + lckup, Toast.LENGTH_LONG);
                toast.show();

                mirrorLockupChkBx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(ActivityImageDetails.this, "Mirror LockUp", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });


                EditText targetEdtTxt = (EditText) findViewById(R.id.editTargetID);
                String mTargetID = targetEdtTxt.getText().toString();
                toast = Toast.makeText(ActivityImageDetails.this, mTargetID, Toast.LENGTH_LONG);
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
                            mImageType = mImageTypes[mOthr];
                        break;
                }

                Toast toast = Toast.makeText(ActivityImageDetails.this, "Radio Button = " + mImageType, Toast.LENGTH_LONG);

                toast.show();
            }
        });

        Button vibBtn = (Button) findViewById(R.id.btnVibration);
        vibBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ActivityImageDetails.this, "Vibration", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button flashlightBtn = (Button) findViewById(R.id.btnFlashlight);
        flashlightBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ActivityImageDetails.this, "Flashlight", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button carLightsBtn = (Button) findViewById(R.id.btnCarLights);
        carLightsBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ActivityImageDetails.this, "Car Lights", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button airplaneBtn = (Button) findViewById(R.id.btnAirplane);
        airplaneBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ActivityImageDetails.this, "Airplane", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button satelliteBtn = (Button) findViewById(R.id.btnSatellite);
        satelliteBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ActivityImageDetails.this, "Satellite", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button meteorBtn = (Button) findViewById(R.id.btnMeteor);
        meteorBtn.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ActivityImageDetails.this, "Meteor", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
