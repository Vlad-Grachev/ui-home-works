package com.netcracker.client;

import com.netcracker.client.uielements.AddBookDialog;
import com.netcracker.client.uielements.BooksCellTable;
import com.netcracker.client.uielements.RemoveDialog;
import com.netcracker.client.uielements.SortListBox;
import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.netcracker.shared.Book;

import java.util.ArrayList;

public class gwt implements EntryPoint {
    private VerticalPanel mainPanel = new VerticalPanel();
    private HorizontalPanel buttonsPanel = new HorizontalPanel();

    private BooksCellTable table = new BooksCellTable();
    private AddBookDialog addDialog = new AddBookDialog();
    private RemoveDialog removeDialog = new RemoveDialog();

    private ArrayList<Book> books = null;

    public void onModuleLoad(){
        Button addButton = new Button("Add book");
        Button removeButton = new Button("Remove book");
        Label sortLabel = new Label("Sort by: ");
        sortLabel.getElement().setId("sortLabel");
        SortListBox sortListBox = new SortListBox();
        Button saveButton = new Button("Save");

        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(sortLabel);
        buttonsPanel.add(sortListBox);
        buttonsPanel.add(saveButton);

        LibraryServiceAsync libraryService = GWT.create(LibraryService.class);
        libraryService.getBooks(new Callback());

        table.construct();
        table.setStyleName("books-table");
        sortListBox.configure(libraryService, table);
        addDialog.configure(libraryService, table);
        removeDialog.configure(libraryService, table);

        mainPanel.add(buttonsPanel);
        mainPanel.add(table);

        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addDialog.center();
                addDialog.show();
            }
        });

        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(table.getSelectedBook() != null) {
                    removeDialog.setSelectedBookId(table.getSelectedBook().getId());
                    removeDialog.center();
                    removeDialog.show();
                }
            }
        });

        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                libraryService.saveBooks(new Callback());
                Window.alert("All changes saved!");
            }
        });

        RootPanel.get("appUI").add(mainPanel);
    }

    private class Callback implements AsyncCallback<ArrayList<Book>> {
        @Override
        public void onFailure(Throwable caught) {
            Window.alert("Unable to obtain server response: "
                    + caught.getMessage());
        }
        @Override
        public void onSuccess(ArrayList<Book> result) {
            books = result;
            table.setRowData(books);
        }
    }

}
