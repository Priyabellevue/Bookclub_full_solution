/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.model;

import javax.validation.constraints.NotEmpty;

import nonapi.io.github.classgraph.json.Id;

public class BookOfTheMonth {
    @Id
    private String id;

    private int month;

    @NotEmpty(message="ISBN is a required field.")
    private String isbn;
    
    public BookOfTheMonth(){

    }

    public BookOfTheMonth(String id, int month, String isbn){
        setId(id);
        setMonth(month);
        setIsbn(isbn);
    }

    public String getId(){
        return id;
    }

    public int getMonth(){
        return month;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setId(String newId){
        this.id = newId;
    }

    public void setMonth(int newMonth){
        this.month = newMonth;
    }

    public void setIsbn(String newIsbn){
        this.isbn = newIsbn;
    }

    @Override
    public String toString(){
        String template = "Book Of The Month{id=%s, month=%s, isbn=%s}";
        String output = String.format(template, getId(), getMonth(), getIsbn().toString());
        return output;
    }
}