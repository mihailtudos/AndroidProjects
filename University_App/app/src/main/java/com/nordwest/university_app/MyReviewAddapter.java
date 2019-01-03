package com.nordwest.university_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyReviewAddapter extends RecyclerView.Adapter<MyReviewAddapter.myReviewViewHolder> {

    //variable declaration
    Context context;
    List<ReviewHolder> mReviews;
    SQLiteOpenHelper openHelper;

    //adapter constructor
    //adaptor will adopt the given List of data to the context
    public MyReviewAddapter(Context context, List<ReviewHolder> mReviews) {
        this.context = context;
        this.mReviews = mReviews;
    }

    //inflates the data onCreate time and adopt it to the cardView
    @Override
    public myReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.review_card_model, parent, false);
        return new myReviewViewHolder(v);    }

    //gets data from the List mReviews and adopt it (set) to the card on the recyclerView
    @Override
    public void onBindViewHolder(final myReviewViewHolder holder, int position) {
        holder.review_author.setText(mReviews.get(position).getReviewAuthor());
        holder.reviewID.setText(mReviews.get(position).getReviewID());
        holder.bookISBN.setText(mReviews.get(position).getBookISBN());
        holder.reviewText.setText(mReviews.get(position).getReviewText());
        openHelper = new DatabaseHelper(context);
    }
    //returns the size of the list
    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    //class holder used to link the card structure with java objects and sets click listeners on each card
    public class myReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //variable declaration
        TextView reviewID, bookISBN,review_author, reviewText;

        //links the widgets of the cart with the object variables declared above
        public myReviewViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            review_author = itemView.findViewById(R.id.startingTimeText);
            bookISBN = itemView.findViewById(R.id.dateClass);
            reviewID = itemView.findViewById(R.id.roomClass);
            reviewText = itemView.findViewById(R.id.subjectClass);
            
        }

        @Override
        public void onClick(View view) {

        }
    }
}
