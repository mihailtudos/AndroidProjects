package com.nordwest.university_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    //variable declaration
    TextView profileName ,profileSt_ID ,groupProfile ,EmailProfile;
    private static boolean isFabOpen;
    private FloatingActionButton fabMain;
    private FloatingActionButton fabOut;
    private FloatingActionButton fabBusinessCenter;
    private View bgFabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the corresponding layout on onCreate activity
        setContentView(R.layout.activity_profile);

        //defining the fields and linking with the widgets from layout
        profileName = findViewById(R.id.userProfileName);
        profileSt_ID = findViewById(R.id.studentProfileID);
        groupProfile = findViewById(R.id.groupProfile);
        EmailProfile = findViewById(R.id.studentEmailProfile);
        fabBusinessCenter = findViewById(R.id.fab_mainActivity);
        fabMain = findViewById(R.id.fab_main);
        fabOut = findViewById(R.id.fab_signOut);
        bgFabMenu = findViewById(R.id.bg_fab_menu);

        //set corresponding user information onto corresponding fields that helps to display and customize the profile
        profileName.setText(Contract.StudentEntry.actualUserFirstName + " " + Contract.StudentEntry.actualUserSecondName);
        profileSt_ID.setText(Contract.StudentEntry.actualUserSecondName.substring(0,3).toUpperCase()+ "101" + Contract.StudentEntry.actualUserStudentID);
        groupProfile.setText(Contract.StudentEntry.actualUserGroupName);
        EmailProfile.setText(Contract.StudentEntry.actualUserEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu from resources adn adopt into the ActionBar
        getMenuInflater().inflate(R.menu.menu_dash, menu);
        //set a  title on the ActionBar
        setTitle("Profile");

        return true;
    }
    //menu item click handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get the id of the option pressed
        int id=item.getItemId();
        //start activity profile where the user can change settings
        if (id==R.id.settingsMenu){
            Toast.makeText(this, "To be resolved in the next version", Toast.LENGTH_SHORT).show();
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

}
