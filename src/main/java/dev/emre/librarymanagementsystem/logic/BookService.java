package dev.emre.librarymanagementsystem.logic;
import dev.emre.librarymanagementsystem.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookService {
    private final List<Book> books = new ArrayList<>();
    private long nextId = 1;

    public List<Book> getAllBooks(){
        return new ArrayList<>(books);
    }
    public Optional<Book> getBookById(long id){
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }
    public void addBook(Book book){
        if(book == null)return;
        if(book.getTitle() == null || book.getAuthor() == null || book.getGenre() == null || book.getTotalCopies() <= 0){
            return;
        }
        book.setId(nextId++);
        books.add(book);
    }
    public boolean deleteBook(long id){
    return books.removeIf(book -> book.getId() == id);
    }
}
