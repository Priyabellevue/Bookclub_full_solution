/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.service;

import java.util.List;

// A generic interface with values E and K
public interface GenericDao<E, K> {
    List<E> list(K key); // Return a list of objects of type E.
    E find(K key); // Return an object of type E by type K value.
}