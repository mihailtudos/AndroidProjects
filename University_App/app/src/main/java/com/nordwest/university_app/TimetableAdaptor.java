package com.nordwest.university_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TimetableAdaptor extends RecyclerView.Adapter<TimetableAdaptor.myViewHolder> {
    //variable declaration
    Context context;
    List<TimetableClassHolder> mClasses;
    TextView closePopup;

    //adapter constructor
    //adaptor will adopt the given List of data to the context
    public TimetableAdaptor(Context context, List<TimetableClassHolder> mClasses) {
        this.context = context;
        this.mClasses = mClasses;
    }
    //inflates the data onCreate time and adopt it to the cardView
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.timetable_card_model, parent, false);
        return new myViewHolder(v);
    }

    //gets data from the List mClasses and adopt it (set) to the card on the recyclerView
    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        holder.dateClass.setText(mClasses.get(position).getDateClass());
        holder.subjectClass.setText(mClasses.get(position).getSubjectClass());
        holder.timeClass.setText(mClasses.get(position).getTimeClass());
        holder.roomClass.setText(mClasses.get(position).getRoomClass());

    }

    //returns the size of the list
    @Override
    public int getItemCount() {
        return mClasses.size();
    }

    //class holder used to link the card structure with java objects and sets click listeners on each card
    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //variable declaration
        TextView timeClass, subjectClass, dateClass, roomClass;

        //links the widgets of the cart with the object variables declared above
        public myViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            timeClass = itemView.findViewById(R.id.timeClass);
            subjectClass = itemView.findViewById(R.id.subjectClass);
            dateClass = itemView.findViewById(R.id.dateClass);
            roomClass = itemView.findViewById(R.id.roomClass);



        }
        //launch Singele_flor_planActivity which takes the room name and stores in FLOOR_ID variable therefore, will open the floor plan with corresponding room on it
        @Override
        public void onClick(View view) {
            //gets the position where the card was clicked
            int position = getAdapterPosition();
            //initiates new intent and send extra data to the new intent according to the clicked card
            Intent intent = new Intent(context, Singele_flor_planActivity.class).putExtra(Singele_flor_planActivity.FLOOR_ID, mClasses.get(position).getRoomClass());
            //launch the activity
            context.startActivity(intent);

        }
    }
}
