package dev.emre.librarymanagementsystem.logic;
import dev.emre.librarymanagementsystem.models.Book;
import dev.emre.librarymanagementsystem.models.enums.BookCondition;
import dev.emre.librarymanagementsystem.models.enums.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookService {
    private final List<Book> books = new ArrayList<>();
    private long nextId = 1;

    // CRUD-Methoden für Book
    public boolean updateBook(Book updated) {
        if(updated == null)return false;
        long id = updated.getId();

        for(Book book : books) {
            if(book.getId() == id) {
                book.setTitle(updated.getTitle());
                book.setAuthor(updated.getAuthor());
                book.setGenre(updated.getGenre());
                book.addCopies( updated.getTotalCopies() - book.getTotalCopies());
                book.setAvailableCopies(updated.getAvailableCopies());
                book.setBookCondition(updated.getBookCondition());
                return true;
            }
        }
        return false;
    }
    public List<Book> getAllBooks(){
        return new ArrayList<>(books);
    }
    public Optional<Book> getBookById(long id){
        if(id <= 0)return Optional.empty();
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }
    public Book createBook(String title, String author, Genre genre, int totalCopies){
        if(title == null || author == null || genre == null)throw new IllegalArgumentException("Null values are not allowed!");
        long id = nextId++;
        Book book = new Book(id,title,author,genre,totalCopies);
        books.add(book);
        return book;

    }
    public boolean deleteBook(long id){
    return books.removeIf(book -> book.getId() == id);
    }
}
