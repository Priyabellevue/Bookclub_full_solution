/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
/*
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
*/
public class WishlistItem {

    @NotNull
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message = "Title is a required field.")
    private String title;

    @Id
    private String id;

    private String username;
    
    public WishlistItem() {
    }

    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public WishlistItem(String isbn, String title, String username){
        setIsbn(isbn);
        setTitle(title);
        setUsername(username);
    }

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

    public void setId(String newId){
        this.id = newId;
    }

    public String getId(){
        return id;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        return String.format("WishlistItem{id=%s, isbn=%s, title=%s, username=%s}", id, isbn, title, username);
    }
}