package com.nordwest.university_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//extend with SQL class in order to create DB

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase database;
    private Contract dbHelper;


    //The SQL query for table _user_ creation
    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "+Contract.StudentEntry.TABLE_USER_NAME +
            "(" + Contract.StudentEntry.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             Contract.StudentEntry.STUDENT_FNAME + " TEXT NOT NULL," +
            Contract.StudentEntry.STUDENT_SNAME + " TEXT NOT NULL," +
            Contract.StudentEntry.STUDENT_EMAIL + " TEXT NOT NULL UNIQUE," +
            Contract.StudentEntry.STUDENT_GROUP + " TEXT NOT NULL," +
            Contract.StudentEntry.STUDENT_PASWD + " TEXT NOT NULL)";

    //The SQL query for table _reservations_ creation
    public static final String CREATE_RESERVE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " + Contract.ReservationEntry.TABLE_RESERVATION_NAME +
            "( "+Contract.ReservationEntry.RESERVATION_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Contract.ReservationEntry.USER_ID + " INTEGER NOT NULL," +
            Contract.ReservationEntry.BOOK_ID + "INTEGER NOT NULL," +
            Contract.ReservationEntry.RESERVATION_DATE + "date DEFAULT current_date, " +
            Contract.ReservationEntry.DUE_DATE + " date,"+
            "FOREIGN KEY(`_user_id_`) REFERENCES `_book_`(`_ISBN_`) ON UPDATE CASCADE," +
            "FOREIGN KEY(`_book_id_`) REFERENCES `_user_`(`_student_id_`) ON UPDATE CASCADE);";


    //The SQL query if table exists before creation
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + Contract.StudentEntry.TABLE_USER_NAME;
    public static final String DROP_TABLE_RESERVATION = "DROP TABLE IF EXISTS " + Contract.ReservationEntry.TABLE_RESERVATION_NAME;



    //constructor for this class
    //In order to create a data base we need this constructor which needs the following parameters
    public DatabaseHelper(Context context){
        super(context, Contract.DATABASE_NAME,null,Contract.DATABASE_VERSION);
        //A log message in order to debug easier
        Log.d("Database Operations","Database created");
    }


    //create the table
    @Override
    public void onCreate(SQLiteDatabase db) {

        //passing through execSQL the SQLiteDB parameter (db=CREATE_TABLE)
        // object in order to create the table

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_RESERVE_BOOK_TABLE);

        //A log message in order to debug easier
        Log.d("Database Operations","Table "+Contract.StudentEntry.TABLE_USER_NAME +" created");
    }

    //upload existing table

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //delete existing table though execSQL() by passing the DB object parameters
        //if table dropped call the onCreate method
        // in order to continue the table creation

        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_RESERVATION);
        onCreate(db);
    }

}
