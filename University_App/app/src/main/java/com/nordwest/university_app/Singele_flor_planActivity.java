package com.nordwest.university_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Singele_flor_planActivity extends AppCompatActivity {
    //variable declaration
    TextView closeReturn;
    ImageView floorPlanImage;
    public static String FLOOR_ID = "FLOOR_ID";
    String floorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set corresponding layout
        setContentView(R.layout.activity_singele_flor_plan);

        //get the the extra data sent when the activity was launched
        Bundle bundle = getIntent().getExtras();
        //save in string format the bundled data gotten prom previous activity
        floorID = bundle.getString(FLOOR_ID).trim();

        //links the java object with the XML widget
        floorPlanImage = findViewById(R.id.singelFloorPlanImage);
        /*checks the first two letters of room sent through the main threat when the activity was launched. Compare with the known rooms on the floor plans and
        therefore, launch the corresponding floor plan according to the room where the user has classes, which was sent through the putExtra method*/
        if (floorID.substring(0,2).equals("RL")){
            floorPlanImage.setImageResource(R.drawable.level3);
        }else if(floorID.substring(0,2).equals("RO")){
            floorPlanImage.setImageResource(R.drawable.lavel1);
        }else if(floorID.substring(0,2).equals("RG")){
            floorPlanImage.setImageResource(R.drawable.lavel2);
        }else {
            floorPlanImage.setImageResource(R.drawable.ground);

        }

        //makes the statue bar background transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //links the java object with the widget on the layout
        closeReturn = findViewById(R.id.closeReturn);

        //set clickListener for close button on the top of the activity
        closeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send the threat to the previous activity
                Singele_flor_planActivity.super.onBackPressed();

            }
        });

    }
}
