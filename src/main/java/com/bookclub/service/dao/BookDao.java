/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;

// An interface that extends the GenericDao interface
public interface BookDao extends GenericDao<Book, String> {
}