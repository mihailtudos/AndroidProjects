package com.nordwest.university_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyBookAdaptor extends RecyclerView.Adapter<MyBookAdaptor.myViewHolder>  {

    //variable declaration
    Context context;
    List<MyViewBookHolder> mBooks;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    //adapter constructor
    //adaptor will adopt the given List of data to the context
    public MyBookAdaptor(Context context, List<MyViewBookHolder> mBooks) {
        this.context = context;
        this.mBooks = mBooks;
    }

    //inflates the data onCreate time and adopt it to the cardView
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.model_book, parent, false);

        return new myViewHolder(v);

    }

    //gets data from the List mClasses and adopt it (set) to the card on the recyclerView
    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.book_ISBN.setText(mBooks.get(position).getBookISBN());
        holder.book_title.setText(mBooks.get(position).getBookTitle());
        holder.book_author.setText(mBooks.get(position).getBookAuthor());
        holder.book_edition.setText(mBooks.get(position).getBookEdition());
        //set click listener for reserve book button
        holder.btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiates a db object
                openHelper = new DatabaseHelper(context);
                //initiates a contentValues Object
                ContentValues contentValues = new ContentValues();
                //if student has already reserved a book  should be denied from booking again another one
                if (Contract.StudentEntry.student_has_reservation){
                    //informing message as user is not allowed to reserve books
                    Toast.makeText(context, "You are not allowed to reserve this book", Toast.LENGTH_SHORT).show();

                }else {
                    //in case student doesn't have any reservations then reserve the book on his name and record it into db
                    //try block will help to run the sql action safe and will throw exception if something went wrong
                    try {
                        //gets the db into readable mode
                        db = openHelper.getWritableDatabase();
                        // takes the book the user was clicking onto and the user ID and put them into Context as values
                        contentValues.put(Contract.ReservationEntry.BOOK_ID, mBooks.get(position).getBookISBN());
                        contentValues.put(Contract.ReservationEntry.USER_ID, Contract.StudentEntry.actualUserStudentID);
                        //insert into db table _reservations_ the values saved in the content earlier
                        db.insert(Contract.ReservationEntry.TABLE_RESERVATION_NAME, null, contentValues);
                       //inform the user about the book being reserved
                        Toast.makeText(context, "Reserved successfully", Toast.LENGTH_LONG).show();
                        //seth the global variable and make the user unable to book anymore
                        Contract.StudentEntry.student_has_reservation = true;
                        //set reserve button invisible as the user is not allowed to book anymore
                        holder.btnReserve.setVisibility(View.INVISIBLE);
                        //set cancel button visible
                        holder.btnCancelReserv.setVisibility(View.VISIBLE);
                        //if error caught than write on the stackTrace about the error
                        //which will be later user of debugging the error
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //set click listener for cancel reservation button
        /*
        * when the button cancel is clicked the user will get able to reserve another book while previously reserved book will be
        * canceled and the user will be informed that the reservation was canceled
        * therefore, the button cancel gets invisible while the button reserve gets visible
        * and ultimately the record deleted from db*/
        holder.btnCancelReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHelper = new DatabaseHelper(context);
                db = openHelper.getWritableDatabase();
                db.delete(Contract.ReservationEntry.TABLE_RESERVATION_NAME, Contract.ReservationEntry.USER_ID +" =? ", new String[]{Contract.StudentEntry.actualUserStudentID});
                holder.btnReserve.setVisibility(View.VISIBLE);
                holder.btnCancelReserv.setVisibility(View.INVISIBLE);
                Contract.StudentEntry.student_has_reservation = false;
                //Toast a message to inform the user about the record being successfully inserted
                Toast.makeText(context, "Reservation canceled", Toast.LENGTH_SHORT).show();
            }
        });

        //set click listener for review button
        /*when the review button is clicked the user will be forwarded to the review activity
        * through the main threat will be sent some extra data (book ISBN) so the reviews are going to be retrieved only
         * if the activity is launched */
        holder.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stores the book ISBN where the card was clicked
                String bookISBN = mBooks.get(position).getBookISBN();
                context.startActivity(new Intent(context, ReviewBookActivity.class).putExtra(ReviewBookActivity.BOOK_ID_KEY, bookISBN));
            }
        });

    }

    //returns the size of the list
    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    //class holder used to link the card structure with java objects and sets click listeners on each card
    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     //variable declaration
     TextView book_ISBN, book_title,book_author, book_edition, searchedBook;
     Button btnReview, btnReserve, btnCancelReserv;
        //links the widgets of the cart with the object variables declared above

     public myViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        book_ISBN = itemView.findViewById(R.id.book_ISBN);
        book_title = itemView.findViewById(R.id.roomClass);
        book_author = itemView.findViewById(R.id.book_author);
        book_edition = itemView.findViewById(R.id.subjectClass);
        btnReview = itemView.findViewById(R.id.btnReview);
        searchedBook = itemView.findViewById(R.id.searchedBook);
        btnReserve = itemView.findViewById(R.id.btnReserve);
        btnCancelReserv = itemView.findViewById(R.id.btnCancel);
        //when the activity is launched the batons on the cards will get set accordingly due to user ability ob reserving books
        if (Contract.StudentEntry.student_has_reservation){
            btnReserve.setVisibility(View.INVISIBLE);
            btnCancelReserv.setVisibility(View.VISIBLE);

        }else {
            btnReserve.setVisibility(View.VISIBLE);
            btnCancelReserv.setVisibility(View.INVISIBLE);

        }
    }
        @Override
        public void onClick(View view) {
        }
    }
}

