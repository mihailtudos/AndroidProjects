package com.nordwest.university_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationActivity extends AppCompatActivity {
    //variable declaration
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    Button _btn_register;
    Integer errorCode;
    EditText _txtFname_, _txtSname_, _txtGroup_, _txtPass_, _txtEmail_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the corresponding layout
        setContentView(R.layout.activity_register);
        //link the objects with the widgets
        openHelper = new DatabaseHelper(this);
        _btn_register = findViewById(R.id.btn_reg_regAct);
        _txtFname_ = findViewById(R.id.reg_Fname);
        _txtSname_ = findViewById(R.id.reg_Sname);
        _txtEmail_ = findViewById(R.id.reg_Email);
        _txtGroup_ = findViewById(R.id.reg_Group);
        _txtPass_ = findViewById(R.id.reg_Pass);
        _btn_register.setOnClickListener(mOnRegisterClickListener);
    }

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                //get the inserted text for the user
                String fName = _txtFname_.getText().toString().trim();
                String sName = _txtSname_.getText().toString().trim();
                String email = _txtEmail_.getText().toString().trim();
                String group = _txtGroup_.getText().toString().trim();
                String password = _txtPass_.getText().toString().trim();

                ContentValues contentValues = new ContentValues();
                //try block will help to run the sql action safe and will throw exception if something went wrong
                try {
                    //checks all inserted data for correctness before inserting into database provided content
                    if (isEmailValid(email) && isPasswordValid(password) && isGroupValid(group) && isFirstNameEmpty(fName) && isSecondNameEmpty(sName)) {
                        //get a writable db object
                        db = openHelper.getWritableDatabase();
                        //prepare the content (record)to be place into database
                        contentValues.put(Contract.StudentEntry.STUDENT_FNAME, fName);
                        contentValues.put(Contract.StudentEntry.STUDENT_SNAME, sName);
                        contentValues.put(Contract.StudentEntry.STUDENT_EMAIL, email);
                        contentValues.put(Contract.StudentEntry.STUDENT_GROUP, group);
                        contentValues.put(Contract.StudentEntry.STUDENT_PASWD, password);
                        //insert the content of values into database table _user_
                        db.insert(Contract.StudentEntry.TABLE_USER_NAME, null, contentValues);
                        //start ne intent after the registration was passed successfully the user is sent to the loginActivity
                        Intent intent = new Intent(RegistrationActivity.this, Login.class);
                        setBlank();
                        startActivity(intent);
                        //display a message to inform the registration pass successfully
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        //Switch block to display a specific error message to inform the user where the error occurred
                    }else {
                        switch (errorCode){
                            case 0:
                                Toast.makeText(getApplicationContext(), "Incorrect email provided", Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getApplicationContext(), "Account exists already", Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(), "Incorrect chosen group name", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(getApplicationContext(), "Incorrect first name", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(getApplicationContext(), "Incorrect second name", Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(getApplicationContext(), "Incorrect password used", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                    //if error caught than write on the stackTrace about the error
                    //which will be later user of debugging the error
                }catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    //check if the email is not empty and it match with and the inserted email has an email structure and return true or false
    private boolean isEmailValid(String userEmail){

        if (!TextUtils.isEmpty(userEmail) && Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            //only after the physical structure check passed let the app interact with database as it helps to save resources
            //if the email check was successful than open a database object and query if the inserted email was already used to prevent double registrations

            db = openHelper.getReadableDatabase();
            //try block will help to run the sql action safe and will throw exception if something went wrong

            try {
                cursor = db.rawQuery("SELECT * FROM " +Contract.StudentEntry.TABLE_USER_NAME + " WHERE " +Contract.StudentEntry.STUDENT_EMAIL +" =? ", new String[]{userEmail});
            }      //if error caught than write on the stackTrace about the error
            //which will be later user of debugging the error

            catch (SQLException e){
                e.printStackTrace();
            }

            //if no record was found return true meaning the email is not taken
            if (cursor.getCount()==0){
                return true;
            } else{
                //returns specific error code therefore, a specific error message will be displayed to inform the user about the incorrect input
                errorCode = 1;
                //will set the field black to show indicate the incorrect field
                _txtEmail_.setText("");
                //return false and prevent registration
                return false;
            }

        }else {
            //returns specific error code therefore, a specific error message will be displayed to inform the user about the incorrect input
            errorCode = 0;
            //will set the field black to show indicate the incorrect field
            _txtEmail_.setText("");
            //return false and prevent registration
            return false;
        }
    }




    //checks if the password is not empty and longer than eight characters
    public boolean isPasswordValid(String userPassword){
        //return true only if the password is not empty and is longer than 7 characters
        if (!TextUtils.isEmpty(userPassword)&& (userPassword.length() > 7)){
            return true;
        }else {
            Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
            //returns specific error code therefore, a specific error message will be displayed to inform the user about the incorrect input
            errorCode = 5;
            //will set the field black to show indicate the incorrect field
            _txtPass_.setText("");
            //return false and prevent registration
            return false;
        }
    }

    //checks if the first name is empty
    public boolean isFirstNameEmpty (String fName){
        //returns true in case when the field is not empty
        if (!TextUtils.isEmpty(fName)){
            return true;
        }else {
            //returns specific error code therefore, a specific error message will be displayed to inform the user about the incorrect input
            errorCode = 3;
            //will set the field black to show indicate the incorrect field
            _txtGroup_.setText("");
            //return false and prevent registration
            return false;
        }
    }

    //checks if the second name is empty
    public boolean isSecondNameEmpty (String sName){
        //returns true in case when the field is not empty
        if (!TextUtils.isEmpty(sName)){
            return true;
        }else {
            //returns specific error code therefore, a specific error message will be displayed to inform the user about the incorrect input
            errorCode = 4;
            //will set the field black to show indicate the incorrect field
            _txtGroup_.setText("");
            //return false and prevent registration
            return false;
        }
    }

    //checks if the inserted group is a valid group (IT01, IT02, MA01, BA01) and returns true or false depending on the check result
    public boolean isGroupValid(String userGroup){
        if (TextUtils.equals(userGroup,"IT01")|| TextUtils.equals(userGroup,"IT02") || TextUtils.equals(userGroup,"MA01") || TextUtils.equals(userGroup,"BA01")){
           //returns true if the inserted group number is valid
            return true;
        }else {
            //returns specific error code therefore, a specific error message will be displayed to inform the user about the incorrect input
            errorCode = 2;
            //will set the field black to show indicate the incorrect field
            _txtGroup_.setText("");
            //return false and prevent registration
            return false;
        }
    }

    //set all text fields to blank after registration pass
    private void setBlank(){
        _txtFname_.setText("");
        _txtSname_.setText("");
        _txtGroup_.setText("");
        _txtPass_.setText("");
        _txtEmail_.setText("");
    }
}
