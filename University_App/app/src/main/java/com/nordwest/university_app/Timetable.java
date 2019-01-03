package com.nordwest.university_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Timetable extends AppCompatActivity  /* ListActivity implements AdapterView.OnItemClickListener*/ {
    //variable declaration
    TimetableAdaptor timetableAdaptor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    RecyclerView rv;
    TextView groupID;
    Button floorPlan, checkMoodle;
    List<TimetableClassHolder> retrivedClasses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set corresponding layout onCreate of TimetableActivity
        setContentView(R.layout.activity_timetable);
        //links the fields with the XML widgets
        groupID = findViewById(R.id.libraryGroupID);
        checkMoodle = findViewById(R.id.checkMoodle);
        floorPlan = findViewById(R.id.goToFloorplan);
        //set the group ID on the field for group name according to the user's group
        groupID.setText(Contract.StudentEntry.actualUserGroupName);

        //initiate a db object
        openHelper = new DatabaseHelper(this);
        //makes the db readable as it requires information from db
        db = openHelper.getReadableDatabase();
        //links the recyclerView with the widget from XML layout
        rv = findViewById(R.id.rvClasses);
        //set the arrayList on the adapter
        timetableAdaptor = new TimetableAdaptor(this, retrivedClasses);
        //set layout type for recyclerView
        rv.setLayoutManager(new LinearLayoutManager(this));
        //create some local variables used to store data retried from database
        String choice, time, date, room, subject;
        //the choice will be user's group therefore, data retrieved from db should be related to the group
        choice = Contract.StudentEntry.actualUserGroupName.trim();

        //try block will help to run the sql action safe and will throw exception if something went wrong
        try{
            //retrieve from db all classes the user (group) has
        cursor = db.rawQuery("SELECT _classes_._time_ as  'Starting time' , _date_ AS 'Date', _module_._module_name_ as 'Subject' " +
                "from _classes_, _module_ " +
                "where _group_id_ =? and _classes_._module_code_ = _module_._module_code_  ORDER BY _date_ ASC ", new String[]{choice});
            //if the records found then set retrieved data accordingly to be later displayed in the recyclerView
            if (cursor != null && cursor.moveToFirst()) {
                do {
                        //stores the time in time
                        time = cursor.getString(cursor.getColumnIndex("Starting time")).trim();
                        //stores the subject into subject variable
                        subject = cursor.getString(cursor.getColumnIndex("Subject")).trim();
                       /* as the given timetable is for first half of the year and all the classes are on Monday and Tuesday therefore,
                        the timetable will be created for the same days of the week instead of storing same classes multiple times
                        in database

                        As in database the classes are stored under two dates 11-12 and 11-13 and the study days are Monday and Tuesday
                        the day on the card will be displayed corresponding to the date record was stored into db */
                        if ((cursor.getString(cursor.getColumnIndex("Date")).trim()).equals("2018-11-12")){
                            date = "Monday";
                        }else{
                            date = "Tuesday";
                        }
                        /*as IT groups always have classes in the same room RG01 the if statement will check if the group is IT
                        * then when pressed on the card of class the floor plan will be displayed depending on the room  */
                        if (Contract.StudentEntry.actualUserGroupName.equals("IT01")){
                            room = "RG01";
                        /*as the time table was created just for two groups IT01 and MA01 and assuming that MA01 has classes
                        * same as IT groups in the same class therefore, the floorplan will be displayed depending on where the
                        * room RL01 is*/
                        }else {
                            room = "RL01";
                        }
                        /*the retrieved data are stored through the adapter on recyclerView according to the constructor of the
                        timetable cards*/
                        retrivedClasses.add(new TimetableClassHolder(time, subject, date, room));
                        //sets adapter for recyclerView after the item was constructed
                        rv.setAdapter(timetableAdaptor);
                        //the cycle will be repeated until the cursor gets to the end meaning all retrieved data are displayed
                } while (cursor.moveToNext());
            } else {
                //in case that the student ahs no timetable a error message will be displayed
                Toast.makeText(getApplicationContext(), "Timetable for " +Contract.StudentEntry.actualUserGroupName +" was not found"
                        , Toast.LENGTH_LONG).show();
            }
            //if error caught than write on the stackTrace about the error
            //which will be later user of debugging the error
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //launches the web dashboard in the webView where the student can check the timetable as well
        checkMoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent goToMoodle = new Intent(Timetable.this, WebDashboard.class);
               startActivity(goToMoodle);
            }
        });
        //if the option to check all floor plans select it will launch slider activity which will display all floor plans
        floorPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeFlorPlanSlider = new Intent(Timetable.this, SliderActivity.class);
                startActivity(seeFlorPlanSlider);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu from resources adn adopt into the ActionBar
        getMenuInflater().inflate(R.menu.menu_dash, menu);
        //set a  title on the ActionBar
        setTitle("Timetable");

        return true;
    }
    //menu item click handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get the id of the option pressed
        int id=item.getItemId();
        //start activity profile where the user can change settings
        if (id==R.id.settingsMenu){
            Intent goToProfile = new Intent(Timetable.this, Profile.class);
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
        }
        //returns selected option
        return super.onOptionsItemSelected(item);
    }




}