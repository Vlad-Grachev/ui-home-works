package client.uielements;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import shared.Book;

import java.util.Date;

public class BooksCellTable extends CellTable<Book> {
    private Book selectedBook = null;

    public BooksCellTable(){
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void construct() {
        TextColumn<shared.Book> id = new TextColumn<shared.Book>() {
            @Override
            public String getValue(shared.Book book) {
                return Integer.toString(book.getId());
            }
        };
        addColumn(id, "Id");

        TextColumn<shared.Book> author = new TextColumn<shared.Book>() {
            @Override
            public String getValue(shared.Book book) {
                return book.getAuthor();
            }
        };
        addColumn(author, "Author");

        TextColumn<shared.Book> name = new TextColumn<shared.Book>() {
            @Override
            public String getValue(shared.Book book) {
                return book.getTitle();
            }
        };
        addColumn(name, "Name");

        TextColumn<shared.Book> numPages = new TextColumn<shared.Book>() {
            @Override
            public String getValue(shared.Book book) {
                return Integer.toString(book.getNumPages());
            }
        };
        addColumn(numPages, "Number of pages");

        TextColumn<shared.Book> releaseYear = new TextColumn<shared.Book>() {
            @Override
            public String getValue(shared.Book book) {
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
