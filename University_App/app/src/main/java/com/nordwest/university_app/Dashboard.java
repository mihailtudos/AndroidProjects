package com.nordwest.university_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    //variable declaration
    private CardView wifi, dashboard, floorPlan, library, timetable, profile;
    private TextView userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //welcome the user to the dashboard message
        Toast.makeText(getApplicationContext(), "Welcome to the dashboard " + Contract.StudentEntry.actualUserFirstName +"!", Toast.LENGTH_LONG).show();

        //defining the cards and linking with the cardWidgets and fields
        wifi = findViewById(R.id.id_wifi);
        dashboard = findViewById(R.id.id_webDashboard);
        floorPlan = findViewById(R.id.id_floorPlan);
        library = findViewById(R.id.id_library);
        timetable = findViewById(R.id.id_timeTable);
        profile = findViewById(R.id.id_profile);
        userNameText = findViewById(R.id.dashUserName);

        //set user name field
        userNameText.setText(Contract.StudentEntry.actualUserFirstName + " " + Contract.StudentEntry.actualUserSecondName);




        //Grating  message when successfully login
        Toast.makeText(getBaseContext(),"Welcome to the Dashboard",Toast.LENGTH_LONG).show();

        //set clickListeners to each card
        wifi.setOnClickListener(this);
        dashboard.setOnClickListener(this);
        floorPlan.setOnClickListener(this);
        library.setOnClickListener(this);
        timetable.setOnClickListener(this);
        profile.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu from resources adn adopt into the ActionBar
        getMenuInflater().inflate(R.menu.menu_dash, menu);
        //set a  title on the ActionBar
        setTitle("Dashboard");

        return true;
    }
    //menu item click handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get the id of the option pressed
        int id=item.getItemId();
        //start activity profile where the user can change settings
        if (id==R.id.settingsMenu){
            Intent goToProfile = new Intent(Dashboard.this, Profile.class);
            startActivity(goToProfile);
        }
        /*when logout option is pressed will end all current tasks and will move startedIntent on the top of stack
            clearing all the stack.
        */
        if (id==R.id.logoutMenu){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }//returns selected option
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //initiate intent
        Intent intent;
        //tests which card was clicked in order to start correct intent (activity) and forward the user the selected activity
        switch (v.getId()){
            case R.id.id_wifi: intent = new Intent(this, NewsFeedActivity.class);
            startActivity(intent);
            break;
            case R.id.id_webDashboard: intent = new Intent(this, WebDashboard.class);
            startActivity(intent);
            break;
            case R.id.id_floorPlan: intent = new Intent(this, FloorPlan.class);
            startActivity(intent);
            break;
            case R.id.id_library: intent = new Intent(this, Library.class);
            startActivity(intent);
            break;
            case R.id.id_timeTable: intent = new Intent(this, Timetable.class);
            startActivity(intent);
            break;
            case R.id.id_profile: intent = new Intent(this, Profile.class);
            startActivity(intent);
            break;
            default: break;
            }
    }
}
