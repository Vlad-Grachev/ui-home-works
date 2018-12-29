package com.netcracker.client.uielements;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.netcracker.shared.Book;

import java.util.Date;

public class BooksCellTable extends CellTable<Book> {
    private Book selectedBook = null;

    public BooksCellTable(){
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void construct() {
        TextColumn<Book> id = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return Integer.toString(book.getId());
            }
        };
        addColumn(id, "Id");

        TextColumn<Book> author = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return book.getAuthor();
            }
        };
        addColumn(author, "Author");

        TextColumn<Book> name = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return book.getTitle();
            }
        };
        addColumn(name, "Name");

        TextColumn<Book> numPages = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return Integer.toString(book.getNumPages());
            }
        };
        addColumn(numPages, "Number of pages");

        TextColumn<Book> releaseYear = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return Integer.toString(book.getReleaseYear());
            }
        };
        addColumn(releaseYear, "Release year");

        DateCell dateCell = new DateCell();
        Column<Book, Date> dateColumn = new Column<Book, Date>(dateCell) {
            @Override
            public Date getValue(Book book) {
                return book.getDateAdded();
            }
        };
        addColumn(dateColumn, "Added in DB");

        setSelectionModel(createSelectionModel());
    }

    private SelectionModel createSelectionModel(){
        NoSelectionModel<Book> tableSelectionModel = new NoSelectionModel<>();
        SelectionChangeEvent.Handler tableHandler = new SelectionChangeEvent.Handler()
        {
            @Override
            public void onSelectionChange(SelectionChangeEvent event)
            {
                selectedBook = tableSelectionModel.getLastSelectedObject();
            }
        };
        tableSelectionModel.addSelectionChangeHandler( tableHandler );
        return tableSelectionModel;
    }



}
