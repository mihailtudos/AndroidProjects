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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity {
    //variable declarations
    MyBookAdaptor myBookAdaptor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button btnSearch;
    EditText searchedBook;
    Cursor cursor;
    RecyclerView rv;
    List<MyViewBookHolder> retriedBooks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the corresponding layout
        setContentView(R.layout.activity_library);
        //creates a new db object
        openHelper = new DatabaseHelper(this);
        //creates a readable db object
        db = openHelper.getReadableDatabase();
        /*Creates the link between layout widgets (elements) and activity objects  */
        btnSearch = findViewById(R.id.buttonSearch);
        searchedBook = findViewById(R.id.searchedBook);
        rv = findViewById(R.id.rvBooks);
        //initiate an adapter object
        myBookAdaptor = new MyBookAdaptor(this, retriedBooks);
        //set layout type (how to display the cards)
        rv.setLayoutManager(new LinearLayoutManager(this));

        //sets a click listener on Search button and its tasks
        btnSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //clears the list of retrieved books
                retriedBooks.clear();
                //local  variable declaration
                String choice, ISBN, title, author, edition;
                choice = searchedBook.getText().toString().trim();
                //takes the first letter ot the input and capitalize it to make sure it matches the db records
                choice = choice.substring(0, 1).toUpperCase() + choice.substring(1);

                //try block will help to run the sql action safe and will throw exception if something went wrong
                try {
                    //retrieve from db all the books found on the searched category
                    cursor = db.rawQuery("SELECT _ISBN_, _title_, _edition_, (_fn_ || \" \" || _sn_) as _author_\n" +
                            "FROM  _book_ , _author_, _has_written_\n" +
                            "where _book_._ISBN_ = _has_written_._book_id_ and _author_._author_id_ = _has_written_._author_id_ and _book_._catergory_type_ =? ", new String[]{choice});
                    //if error caught than write on the stackTrace about the error
                    //which will be later user of debugging the error
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //if records found then stores the data are get data from each field and store in the earlier declared variable
                //and use them to construct each card depending on the card structure of the card using predefined card structure
                if (cursor != null && cursor.moveToFirst()) {
                        do {
                            ISBN = cursor.getString(cursor.getColumnIndex("_ISBN_"));
                            title = cursor.getString(cursor.getColumnIndex("_title_"));
                            author = cursor.getString(cursor.getColumnIndex("_author_"));
                            edition = cursor.getString(cursor.getColumnIndex("_edition_"));
                            //stores retrieved data into the list according holders structure (object structure)
                            retriedBooks.add(new MyViewBookHolder(ISBN,title,author,edition));
                            cursor.toString();
                            //set an adapter which adopts the data from the list on the cards
                            rv.setAdapter(myBookAdaptor);
                        } while (cursor.moveToNext());
                    } else {
                    //inform the user that no book was found
                        Toast.makeText(getApplicationContext(), "No books found", Toast.LENGTH_LONG).show();
                    }
                }
        });
    }
    //menu bar displayed on the top
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu from resources adn adopt into the ActionBar
        getMenuInflater().inflate(R.menu.menu_dash, menu);
        //set a  title on the ActionBar
        setTitle("Library");

        return true;
    }
    //menu item click handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get the id of the option pressed
        int id=item.getItemId();
        //start activity profile where the user can change settings
        if (id==R.id.settingsMenu){
            Intent goToProfile = new Intent(Library.this, Profile.class);
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
