package com.netcracker.client.uielements;

import com.netcracker.client.LibraryServiceAsync;
import com.netcracker.client.uielements.BooksCellTable;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.netcracker.shared.Book;
import com.netcracker.shared.FieldVerifier;

import java.util.ArrayList;
import java.util.Date;

public class AddBookDialog extends DialogBox {
    private LibraryServiceAsync libService;
    private BooksCellTable table;

    private TextBox idTB = new TextBox();
    private TextBox authorTB = new TextBox();
    private TextBox titleTB = new TextBox();
    private TextBox numPagesTB = new TextBox();
    private TextBox yearTB = new TextBox();

    Button addButton = new Button("Ok");
    Button closeButton = new Button("Cancel");

    public AddBookDialog() {
        setText("Add new book");
        setAnimationEnabled(true);
        setModal(true);
        VerticalPanel dialogVP = new VerticalPanel();
        HorizontalPanel dialogHP1 = new HorizontalPanel();
        HorizontalPanel dialogHP2 = new HorizontalPanel();
        idTB.getElement().setPropertyString("placeholder", "Id");
        dialogHP1.add(idTB);
        authorTB.getElement().setPropertyString("placeholder", "Author");
        dialogHP1.add(authorTB);
        titleTB.getElement().setPropertyString("placeholder", "Title");
        dialogHP1.add(titleTB);
        numPagesTB.getElement().setPropertyString("placeholder", "Num of pages");
        dialogHP1.add(numPagesTB);
        yearTB.getElement().setPropertyString("placeholder", "Release year");
        dialogHP1.add(yearTB);
        dialogHP2.add(addButton);
        dialogHP2.add(closeButton);
        dialogVP.add(dialogHP1);
        dialogVP.add(dialogHP2);
        setWidget(dialogVP);

        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (verify()) {
                    Book newBook = new Book(
                            Integer.parseInt(idTB.getValue()),
                            authorTB.getValue(),
                            titleTB.getValue(),
                            Integer.parseInt(numPagesTB.getValue()),
                            Integer.parseInt(yearTB.getValue()),
                            new Date()
                    );
                    libService.addBook(newBook, new Callback());
                    clearTextBoxes();
                    hide();
                }
            }
        });

        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                hide();
                clearTextBoxes();
            }
        });
    }

    public void configure(LibraryServiceAsync libService, BooksCellTable table) {
        this.libService = libService;
        this.table = table;
    }

    public void clearTextBoxes() {
        idTB.setText("");
        authorTB.setText("");
        titleTB.setText("");
        numPagesTB.setText("");
        yearTB.setText("");
        setValidStyle();
    }

    private void setValidStyle() {
        idTB.setStyleName("valid_input");
        authorTB.setStyleName("valid_input");
        titleTB.setStyleName("valid_input");
        numPagesTB.setStyleName("valid_input");
        yearTB.setStyleName("valid_input");
    }

    private boolean verify() {
        setValidStyle();
        boolean result = true;

        if (!FieldVerifier.isIdValid(idTB.getValue())) {
            result = false;
            idTB.setStyleName("invalid_input");
        }
        if (!FieldVerifier.isAuthorValid(authorTB.getValue())){
            result = false;
            authorTB.setStyleName("invalid_input");
        }
        if (!FieldVerifier.isTitleValid(titleTB.getValue())){
            result = false;
            titleTB.setStyleName("invalid_input");
        }
        if (!FieldVerifier.isNumPagesValid(numPagesTB.getValue())){
            result = false;
            numPagesTB.setStyleName("invalid_input");
        }
        if (!FieldVerifier.isReleaseYearValid(yearTB.getValue())){
            result = false;
            yearTB.setStyleName("invalid_input");
        }
        return result;
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

