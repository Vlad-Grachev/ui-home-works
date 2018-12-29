package com.netcracker.client.uielements;

import com.netcracker.client.LibraryServiceAsync;
import com.netcracker.client.uielements.BooksCellTable;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.netcracker.shared.Book;

import java.util.ArrayList;

public class SortListBox extends ListBox {
    private LibraryServiceAsync libService;
    private BooksCellTable table;

    public SortListBox() {
        getElement().setId("sortListBox");
        addItem("Id");
        addItem("Author");
        addItem("Title");
        addItem("Number of pages");
        addItem("Release year");
        addItem("Added in DB");

        addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                switch (getSelectedValue()) {
                    case "Id":
                        libService.sortBooksById(new Callback());
                        break;
                    case "Author":
                        libService.sortBooksByAuthor(new Callback());
                        break;
                    case "Title":
                        libService.sortBooksByName(new Callback());
                        break;
                    case "Number of pages":
                        libService.sortBooksByPages(new Callback());
                        break;
                    case "Release year":
                        libService.sortBooksByReleaseYear(new Callback());
                        break;
                    case "Added in DB":
                        libService.sortBooksByAddedDate(new Callback());
                        break;
                    default:
                        libService.sortBooksById(new Callback());
                }
            }
        });
    }

    public void configure(LibraryServiceAsync libService, BooksCellTable table){
        this.libService = libService;
        this.table = table;
    }

    private class Callback implements AsyncCallback<ArrayList<Book>> {
        @Override
        public void onFailure(Throwable caught) {
            Window.alert("Unable to obtain server response: "
                    + caught.getMessage());
        }

        @Override
        public void onSuccess(ArrayList<Book> result) {
            table.setRowData(result);
        }
    }
}
