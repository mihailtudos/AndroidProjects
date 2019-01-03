package com.nordwest.university_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FloorPlanAdaptor extends RecyclerView.Adapter<FloorPlanAdaptor.myVireHolder> {

    //variable declaration
    Context mContext;
    List<ItemViewConstructor> mData;
    int i = 0;
    //adapter constructor
    //adaptor will adopt the given List of data to the context
    public FloorPlanAdaptor(Context mContext, List<ItemViewConstructor> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    //inflates the data onCreate time and adopt it to the cardView
    @Override
    public myVireHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        return new myVireHolder(v);
    }

    //gets data from the List mData and adopt it (set) to the card on the recyclerView
    @Override
    public void onBindViewHolder(myVireHolder holder, int position) {
        holder.background_img.setImageResource(mData.get(position).getBackground());
        holder.profile_photo.setImageResource(mData.get(position).getProfilePhoto());
        holder.tv_title.setText(mData.get(position).getProfileName());
        holder.tv_nbDescription.setText(mData.get(position).getFloorDescription());
    }

    //returns the size of the list
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //class holder used to link the card structure with java objects and sets click listeners on each card
    public class myVireHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //variable declaration
        ImageView profile_photo, background_img;
        TextView tv_title, tv_nbDescription;

        //links the widgets of the cart with the object variables declared above
        public  myVireHolder(View itemView){
            super(itemView);
            itemView.setTag(i++);
            itemView.setOnClickListener(this);
            profile_photo = itemView.findViewById(R.id.profile_img);
            background_img = itemView.findViewById(R.id.cardBackground);
            tv_title = itemView.findViewById(R.id.card_description);
            tv_nbDescription = itemView.findViewById(R.id.card_title);

        }

        //launch Singele_flor_planActivity which takes the room name and stores in FLOOR_ID variable therefore, will open the floor plan with corresponding room on it
        @Override
        public void onClick(View view) {
            //gets the position where the card was clicked
            int position = getAdapterPosition();
            //starts newly initiated intent and send extra data (room) to the new intent according to the clicked card
            mContext.startActivity(new Intent(mContext, Singele_flor_planActivity.class).putExtra(Singele_flor_planActivity.FLOOR_ID, mData.get(position).getFloorDescription()));
        }
    }
}
