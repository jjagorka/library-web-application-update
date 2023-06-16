package ru.ionov.library.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ionov.library.application.models.Book;
import ru.ionov.library.application.models.Person;
import ru.ionov.library.application.repositories.BooksRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getBooksByPersonId(Person person) {
        return booksRepository.findByPerson(person);
    }

    public Book findById(int id){
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public List<Book> findAllWithPagination(int page,int itemsPerPage){
        return  booksRepository.findAll(PageRequest.of(page,itemsPerPage)).getContent();
    }

    public List<Book> findAllWithSortByYear(){
        return booksRepository.findAll(Sort.by("publishingYear"));
    }

    public List<Book> findByNameOfBookStartingWith(String like){
        return booksRepository.findByNameOfBookStartingWith(like);
    }

    @Transactional
    public void release(int id){
        Book book = findById(id);
        book.setPerson(null);
        book.setTakingTime(null);
        booksRepository.save(book);
    }

    public Optional<Person> getBookOwner(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        return Optional.ofNullable(book.getPerson());
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book book = findById(id);
        book.setNameOfBook(updatedBook.getNameOfBook());
        book.setPublishingYear(updatedBook.getPublishingYear());
        book.setAuthor(updatedBook.getAuthor());
        save(book);
    }

    @Transactional
        public void updatePersonId(int id, Person person) {
        Book book = booksRepository.findById(id).orElse(null);
        book.setPerson(person);
        book.setTakingTime(LocalDateTime.now());
        save(book);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

}
