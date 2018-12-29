package shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    private ArrayList<Book> books = null;
    public Library(){
    }

    public void setBooks(ArrayList books) {
        this.books = books;
    }

    public ArrayList getBooks() {
        return books;
    }

    public void addBook(Book book){
        Book tmp = getBookById(book.getId());
        if(tmp == null){
            books.add(book);
        } else {
            books.remove(tmp);
            books.add(book);
        }

    }

    public Book getBook(int index) {
        if(index >=0 && index < books.size()){
            return books.get(index);
        }
        return null;
    }

    public Book getBookById(int id) {
        if(id > 0) {
            for (Book book : books) {
                if (book.getId() == id)
                    return book;
            }
        }
        return null;
    }

    public boolean removeBook(int index) {
        if(index >=0 && index < books.size()){
            books.remove(index);
            return true;
        }
        return false;
    }

    public boolean removeBookWithId(int id) {
        if(id > 0) {
            int index = -1;
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId() == id) {
                    index = i;
                    break;
                }
            }
            if(index >= 0)
                removeBook(index);
            return true;
        }
        return false;
    }

    public void sortById(){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getId() - o2.getId();
            }
        });
    }

    public void sortByAuthor(){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().compareToIgnoreCase(o2.getAuthor());
            }
        });
    }

    public void sortByTitle(){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
    }

    public void sortByNumPages(){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getNumPages() - o2.getNumPages();
            }
        });
    }

    public void sortByReleaseYear(){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getReleaseYear() - o2.getReleaseYear();
            }
        });
    }

    public void sortByDateAdded(){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getDateAdded().compareTo(o2.getDateAdded());
            }
        });
    }
}
