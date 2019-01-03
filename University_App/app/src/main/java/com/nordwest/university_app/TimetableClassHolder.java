package com.nordwest.university_app;

import android.widget.TextView;

public class TimetableClassHolder {

    /*This is a constructor class for timetable cards used by the adaptor in recyclerView to display
    * each class of a specific group */

    String timeClass, subjectClass, dateClass, roomClass;

    //constructor that requires four parameters
    public TimetableClassHolder(String timeClass, String subjectClass, String dateClass, String roomClass) {
        this.timeClass = timeClass;
        this.subjectClass = subjectClass;
        this.dateClass = dateClass;
        this.roomClass = roomClass;
    }
    //empty constructor when no data is set
    public TimetableClassHolder() {
    }
    //setters and getters
    public String getTimeClass() {
        return timeClass;
    }

    public void setTimeClass(String timeClass) {
        this.timeClass = timeClass;
    }

    public String getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(String subjectClass) {
        this.subjectClass = subjectClass;
    }

    public String getDateClass() {
        return dateClass;
    }

    public void setDateClass(String dateClass) {
        this.dateClass = dateClass;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }
}
