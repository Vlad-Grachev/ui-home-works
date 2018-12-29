package com.netcracker.shared;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private int id;
    private String author;
    private String title;
    private int numPages;
    private int releaseYear;
    private Date dateAdded;

    public Book(){}

    public Book(int id, String author, String title, int numPages, int releaseYear, Date dateAdded) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.numPages = numPages;
        this.releaseYear = releaseYear;
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", numPages=" + numPages +
                ", releaseYear=" + releaseYear +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
}
