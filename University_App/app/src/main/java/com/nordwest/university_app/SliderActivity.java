package com.nordwest.university_app;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SliderActivity extends AppCompatActivity {
    // variable declaration
    TextView closeReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the corresponding layout on onCreate time
        setContentView(R.layout.activity_slider);
        //instance of out viewPager object and link to the widget on the layout
        ViewPager viewPager = findViewById(R.id.viewPager);
        //initiates new ImageAdapter object
        ImageAdapter adapter = new ImageAdapter(this);
        //set to the ViewPager the ImageAdaptor to display all images
        viewPager.setAdapter(adapter);

        //makes the statue bar background transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //links the java object to the layout widget
        closeReturn = findViewById(R.id.closeReturnToTimetable);

        //set clickListener for close button on the top of the activity
        closeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send the threat to the previous activity
                SliderActivity.super.onBackPressed();

            }
        });

    }

}
