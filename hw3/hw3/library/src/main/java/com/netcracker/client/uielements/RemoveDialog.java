package com.netcracker.client.uielements;

import com.netcracker.client.LibraryServiceAsync;
import com.netcracker.client.uielements.BooksCellTable;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.netcracker.shared.Book;

import java.util.ArrayList;

public class RemoveDialog extends DialogBox {
    private LibraryServiceAsync libService;
    private BooksCellTable table;
    private int selectedBookId = - 1;

    Label label = new Label("Are you sure?");
    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");

    public RemoveDialog() {
        setText("Confirmation dialog");
        setAnimationEnabled(true);
        setModal(true);
        VerticalPanel dialogVP = new VerticalPanel();
        HorizontalPanel dialogHP1 = new HorizontalPanel();
        dialogHP1.add(label);
        HorizontalPanel dialogHP2 = new HorizontalPanel();
        dialogHP2.add(yesButton);
        dialogHP2.add(noButton);
        dialogVP.add(dialogHP1);
        dialogVP.add(dialogHP2);
        setWidget(dialogVP);

        yesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(selectedBookId > -1)
                    libService.removeBook(selectedBookId, new Callback());
                hide();
            }
        });

        noButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                hide();
            }
        });
    }

    public void configure(LibraryServiceAsync libService, BooksCellTable table){
        this.libService = libService;
        this.table = table;
    }

    public void setSelectedBookId(int id) {
        selectedBookId = id;
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
