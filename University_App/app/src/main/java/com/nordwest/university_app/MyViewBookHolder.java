package com.nordwest.university_app;

public class MyViewBookHolder {
    /*This is a constructor class for book cards used by the adaptor in recyclerView to display
     * each class of a specific group */
    String bookTitle;
    String bookISBN;
    String bookAuthor;
    String bookEdition;

    //empty constructor for abstraction
    public MyViewBookHolder() {
    }

    //constructor that requires four parameters
    public MyViewBookHolder(String bookISBN, String bookTitle, String bookAuthor, String bookEdition) {
        this.bookISBN = bookISBN;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookEdition = bookEdition;
    }

    //setters and getters
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }
}
