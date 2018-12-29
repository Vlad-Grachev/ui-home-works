package server;

import client.LibraryService;
import client.LibraryServiceAsync;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import shared.Book;
import shared.Library;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LibraryServiceImpl extends RemoteServiceServlet implements LibraryService {
    private String path;
    private Library library = null;

    @Override
    public void init() throws ServletException {
        library = new Library();
        path = getServletContext().getRealPath("books.json");
        readBooks();
    }

    @Override
    public ArrayList<Book> getBooks() throws IllegalArgumentException {
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> addBook(Book book) throws IllegalArgumentException {
        library.addBook(book);
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> removeBook(int id) throws IllegalArgumentException {
        library.removeBookWithId(id);
        return library.getBooks();
    }

    public void readBooks() {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(path);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String jsonImput = br.readLine();
            library.setBooks(mapper.readValue(jsonImput,
                    new TypeReference<ArrayList<Book>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> saveBooks() throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileOutputStream(path), library.getBooks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> sortBooksById() throws IllegalArgumentException {
        library.sortById();
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> sortBooksByName() throws IllegalArgumentException {
        library.sortByTitle();
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> sortBooksByAuthor() throws IllegalArgumentException {
        library.sortByAuthor();
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> sortBooksByPages() throws IllegalArgumentException {
        library.sortByNumPages();
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> sortBooksByReleaseYear() throws IllegalArgumentException {
        library.sortByReleaseYear();
        return library.getBooks();
    }

    @Override
    public ArrayList<Book> sortBooksByAddedDate() throws IllegalArgumentException {
        library.sortByDateAdded();
        return library.getBooks();
    }
}
