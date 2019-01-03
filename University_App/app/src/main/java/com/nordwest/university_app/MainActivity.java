 package com.nordwest.university_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




 public class MainActivity extends AppCompatActivity {

    Button signUpButton, signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Linking the layout components with activity components */
        signUpButton = findViewById(R.id.btnSignUp);
        signInButton = findViewById(R.id.btnSignIn);

        /*When button pressed a call for method will take place*/
        signUpButton.setOnClickListener(mOnSignUpClickListener);
        signInButton.setOnClickListener(mOnSignInClickListener);


    }
    /*Methods should be declared private to prevent unauthorised data access from it. There are two methods that must initiate
    corresponding activities regarding button pressed */

     //calls and starts  RegistrationActivity
     private View.OnClickListener mOnSignUpClickListener = new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //
             Intent launchRegistration = new Intent(MainActivity.this, RegistrationActivity.class);
             startActivity(launchRegistration);
         }
     };
     //calls and starts LoginActivity
     private View.OnClickListener mOnSignInClickListener = new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent launchSignIn = new Intent(MainActivity.this, Login.class);
             startActivity(launchSignIn);
         }
     };

 }
