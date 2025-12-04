package dev.emre.librarymanagementsystem.models;

import dev.emre.librarymanagementsystem.models.enums.Genre;

public class Book {
    private long id;
    private String title;
    private String author;
    private Genre genre;
    private int totalCopies;
    private int availableCopies;

    public Book(
            String title,
            String author,
            Genre genre,
            int totalCopies
    ) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        if(totalCopies < 0) throw new IllegalArgumentException("Total copies cannot be negative.");
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;

    }
    public Book(){}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title =  title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Titel: %s, Author: %s Genre: %s", title, author, genre);
    }
}
