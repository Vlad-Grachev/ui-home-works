package com.netcracker.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.netcracker.shared.Book;

import java.util.ArrayList;

@RemoteServiceRelativePath("library")
public interface LibraryService extends RemoteService {
    ArrayList<Book> getBooks() throws IllegalArgumentException;
    ArrayList<Book> addBook(Book book) throws IllegalArgumentException;
    ArrayList<Book> removeBook(int id) throws IllegalArgumentException;
    ArrayList<Book> saveBooks() throws IllegalArgumentException;
    ArrayList<Book> sortBooksById() throws IllegalArgumentException;
    ArrayList<Book> sortBooksByName() throws IllegalArgumentException;
    ArrayList<Book> sortBooksByAuthor() throws IllegalArgumentException;
    ArrayList<Book> sortBooksByPages() throws IllegalArgumentException;
    ArrayList<Book> sortBooksByReleaseYear() throws IllegalArgumentException;
    ArrayList<Book> sortBooksByAddedDate() throws IllegalArgumentException;
}
