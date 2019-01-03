package com.mihailtudos.funfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {
    public static final String TAG = FunFactsActivity.class.getSimpleName();

    //declare our View variables
    private TextView mFactTextView;
    private Button mShowFactButton;
    private FactBook factBook = new FactBook();
    private ColorWheel colorWheel = new ColorWheel();
    private RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);
        //Assign the Views from the layout file to the corresponding variables
        mFactTextView = findViewById(R.id.factTextView);
        mShowFactButton = findViewById(R.id.showFactButton);
        mRelativeLayout = findViewById(R.id.funFactRelativeLayout);

        mShowFactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fact = factBook.getFact();
                int color = colorWheel.getColor();
                //Update the Layout background color
                mRelativeLayout.setBackgroundColor(color);
                mShowFactButton.setTextColor(color);
                //Update the screen with our new fact
                mFactTextView.setText(fact);

            }
        });
        //Toast.makeText(this, "Yay! Our activity was created!" , Toast.LENGTH_SHORT).show();
        Log.d(TAG,"We're logging from the onCreate() method");
    }
}
