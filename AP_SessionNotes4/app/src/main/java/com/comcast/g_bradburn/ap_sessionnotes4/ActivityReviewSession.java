package com.comcast.g_bradburn.ap_sessionnotes4;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.comcast.g_bradburn.ap_sessionnotes4.db.AP_Session_DataSource;
import com.comcast.g_bradburn.ap_sessionnotes4.model.SessionElement;

import java.util.List;

public class ActivityReviewSession extends ListActivity {
//  public class ActivityReviewSession extends AppCompatActivity {
    //  This class will allow the operator to review a session, line by line
    //  TODO:  create a list of the possible sessions and populate a drop down for a list selection
    //  TODO:  Capture the name of the session to review
    //  TODO:  read the dataBase for the selected session and display as a listView

    public static final String LOGTAG = "APSession";
    AP_Session_DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        //  TODO:  Should be using something of the form:
        //  ListView listView = (ListView) findViewById(R.id.show_list);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Review an SQLite dataBase", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        List<SessionElement> elements = null;    //  use SQLite query to get the elements of the list

        dataSource = new AP_Session_DataSource(this);
        dataSource.open();

        List<SessionElement> sessionElements = dataSource.findAll();
        if (sessionElements.size() == 0) {
            createData();
            sessionElements = dataSource.findAll();
        }


        ArrayAdapter<SessionElement> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sessionElements);
        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void createData() {
        SessionElement sessionElement = new SessionElement();

        sessionElement.setSessionName("GSSP 2016");
        sessionElement.setLocationID("Redding");
        sessionElement.setTargetID("M51");
        sessionElement.setImageType("FOCUS");
        sessionElement.setTimeStamp("10:22 PM");
        sessionElement.setMomentaryInterference("NONE");
        sessionElement.setLatchedInterference("NONE");
        sessionElement.setCameraID("Rebel XTi");
        sessionElement.setCameraTimeZone("GMT - 8:00");
        sessionElement.setCameraDST_Set("false");
        long iso_x = 800, exp_x = 10000;
        sessionElement.setIso(iso_x);
        sessionElement.setExposureTime_ms(exp_x);
        sessionElement.setCameraMirrorLockup("true");
        sessionElement.setAndroidID("Tab2.7.0");
        sessionElement.setAndroidTimeZone("GMT - 8:00");
        sessionElement.setAndroidDST_Set("false");

        sessionElement = dataSource.create(sessionElement);
        Log.i(LOGTAG, "Session created with ID " + sessionElement.getId());

        sessionElement = new SessionElement();

        sessionElement.setSessionName("GSSP 2016");
        sessionElement.setLocationID("Redding");
        sessionElement.setTargetID("M51");
        sessionElement.setImageType("POLAR ALIGN");
        sessionElement.setTimeStamp("10:30 PM");
        sessionElement.setMomentaryInterference("NONE");
        sessionElement.setLatchedInterference("NONE");
        sessionElement.setCameraID("Rebel XTi");
        sessionElement.setCameraTimeZone("GMT - 8:00");
        sessionElement.setCameraDST_Set("false");
        long iso_y = 800, exp_y = 10000;
        sessionElement.setIso(iso_y);
        sessionElement.setExposureTime_ms(exp_y);
        sessionElement.setCameraMirrorLockup("true");
        sessionElement.setAndroidID("Tab2.7.0");
        sessionElement.setAndroidTimeZone("GMT - 8:00");
        sessionElement.setAndroidDST_Set("false");

        sessionElement = dataSource.create(sessionElement);
        Log.i(LOGTAG, "Session created with ID " + sessionElement.getId());

        sessionElement = new SessionElement();

        sessionElement.setSessionName("GSSP 2016");
        sessionElement.setLocationID("Redding");
        sessionElement.setTargetID("M51");
        sessionElement.setImageType("LIGHT");
        sessionElement.setTimeStamp("10:40 PM");
        sessionElement.setMomentaryInterference("NONE");
        sessionElement.setLatchedInterference("AIRPLANE");
        sessionElement.setCameraID("Rebel XTi");
        sessionElement.setCameraTimeZone("GMT - 8:00");
        sessionElement.setCameraDST_Set("false");
        long iso_z = 800, exp_z = 10000;
        sessionElement.setIso(iso_z);
        sessionElement.setExposureTime_ms(exp_z);
        sessionElement.setCameraMirrorLockup("true");
        sessionElement.setAndroidID("Tab2.7.0");
        sessionElement.setAndroidTimeZone("GMT - 8:00");
        sessionElement.setAndroidDST_Set("false");

        sessionElement = dataSource.create(sessionElement);
        Log.i(LOGTAG, "Session created with ID " + sessionElement.getId());
    }
}
