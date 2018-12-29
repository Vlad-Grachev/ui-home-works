package com.netcracker.client;

import com.netcracker.shared.Book;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

public interface LibraryServiceAsync {
    void getBooks(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void addBook(Book book, AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void removeBook(int id, AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void saveBooks(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void sortBooksById(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void sortBooksByName(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void sortBooksByAuthor(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void sortBooksByPages(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void sortBooksByReleaseYear(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
    void sortBooksByAddedDate(AsyncCallback<ArrayList<Book>> async) throws IllegalArgumentException;
}
