/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.model;

public class Book
{
    private String isbn;
    private String title;
    private String description;
    private String infoUrl;
    private int numOfPages;
    
    public Book() {}

    //default constructor and constructure to accept the fields
    public Book(String isbn, String title, String description, String infoUrl, int numOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.infoUrl = infoUrl;
        this.numOfPages = numOfPages;
    }

    public Book(String isbn, String title, String infoUrl){
        setIsbn(isbn);
        setTitle(title);
        setInfoUrl(infoUrl);
    }
    
    //getter and setter
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getInfoUrl() {
        return infoUrl;
    }
    
    //Override the classes toString method
    @Override
    public String toString() {
        return String.format("Book{isbn=%s, title=%s, description=%s, infoUrl=%s, numOfPages=%s}", isbn, title, description, infoUrl, numOfPages);
    }
}
