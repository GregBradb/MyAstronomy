package com.comcast.g_bradburn.ap_sessionnotes4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //  Somewhere I need to define an AstroSession class that will have the parameters and methods
    //    defined.  Then I need to incorporate that definition, either through an inport or an include statement.
    //    Probably can't share data between activities directly but can pass information via an intent.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button create_btn = (Button) findViewById(R.id.create_button);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent session_mgmt_intent = new Intent(MainActivity.this, SessionManagement.class);
                startActivity(session_mgmt_intent);
            }
        });

        Button open_btn = (Button) findViewById(R.id.open_button);
        open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent session_open_intent = new Intent(MainActivity.this, ActivityImageDetails.class);
                startActivity(session_open_intent);
            }
        });

        Button reviewBtn = (Button) findViewById(R.id.review_button);
        reviewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
        public  void onClick(View v){
                Intent session_review_intent = new Intent(MainActivity.this, ActivityReview.class);
                startActivity(session_review_intent);
            }
        });

        Button closeBtn = (Button) findViewById(R.id.close_button);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent session_close_intent = new Intent(MainActivity.this, CloseSession.class);
                startActivity(session_close_intent);
            }
        });

        Button deleteBtn = (Button) findViewById(R.id.delete_button);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent session_delete_intent = new Intent(MainActivity.this, DeleteSession.class);
                startActivity(session_delete_intent);
            }
        });

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
