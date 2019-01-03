package com.nordwest.university_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FloorPlan extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the corresponding layout
        setContentView(R.layout.activity_floor_plan);

        //links the java object with the corresponding widget on XML layout
        RecyclerView recyclerView =  findViewById(R.id.rv_list);
        // list declaration which will store the floor plans
        List<ItemViewConstructor> mlist = new ArrayList<>();
        //adding in the list all objects (flor plan structure) considering the constructor created in ItemViewConstructor
        mlist.add(new ItemViewConstructor(R.drawable.levelone,"Level First", R.drawable.bookmark, "GL01, GL02 and the Library"));
        mlist.add(new ItemViewConstructor(R.drawable.secondlevel,"Level Second", R.drawable.date, "RG1, RG2, RG3 and RG4"));
        mlist.add(new ItemViewConstructor(R.drawable.thirdlevel,"Level Three", R.drawable.contact, "RL1, RL2, RL3 and Tutor Area"));
        mlist.add(new ItemViewConstructor(R.drawable.ground,"Ground Level", R.drawable.chat, "Student Service, Reception and others"));
        //initialization of an adapter
        FloorPlanAdaptor adaptor = new FloorPlanAdaptor(this, mlist);
        //setting the adapter to the recyclerView initiated earlier(adopt the mlist items to the predefined card structure)
        recyclerView.setAdapter(adaptor);
        //set a layout manager to the recyclerView (how to represent the recycled items)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }


}
