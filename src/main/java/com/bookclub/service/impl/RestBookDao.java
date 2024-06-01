/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

// A class that implements the BookDao interface
public class RestBookDao implements BookDao {

    public RestBookDao() {
    }

    @Override
    public List<Book> list(String key) {
        /*String isbnString = "ISBN:9780062060624,9780593593806,9780307593313,9781524796280,9780702300172";*/

        Object doc = getBooksDoc(key);

        List<Book> books = new ArrayList<Book>();

        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");

        for (int index = 0; index < titles.size(); index++){
            books.add(new Book(isbns.get(index), titles.get(index), infoUrls.get(index)));
        }
        return books;
    }

    @Override
    public Book find(String key) {
        Object doc = getBooksDoc(key);

        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String description = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        Book book = new Book(isbn, title, description, infoUrl, numOfPages);

        return book;
    }
    private Object getBooksDoc(String isbnString){
        String openLibrary = "https://openlibrary.org/api/books";

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibrary)
            .queryParam("bibkeys", isbnString)
            .queryParam("format", "json")
            .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = rest.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            entity,
            String.class);

        String jsonBooklist = response.getBody();

        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);
    }
}