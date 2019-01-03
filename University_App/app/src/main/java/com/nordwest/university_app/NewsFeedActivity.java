package com.nordwest.university_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NewsFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set corresponding layout on onCreate time
        setContentView(R.layout.activity_wifi);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu from resources adn adopt into the ActionBar
        getMenuInflater().inflate(R.menu.menu_dash, menu);
        //set a  title on the ActionBar
        setTitle("Roehampton news");

        return true;
    }
    //menu item click handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get the id of the option pressed
        int id=item.getItemId();
        //start activity profile where the user can change settings
        if (id==R.id.settingsMenu){
            Intent goToProfile = new Intent(NewsFeedActivity.this, Profile.class);
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

}

