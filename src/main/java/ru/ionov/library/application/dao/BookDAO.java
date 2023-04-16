package ru.ionov.library.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ionov.library.application.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        return jdbcTemplate.query("SELECT id_book,name_of_book,full_name,publishing_year FROM book",new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id_book) {
        return jdbcTemplate.query("SELECT id_book,name_of_book,full_name,publishing_year FROM book WHERE id_book=?",new Object[]{id_book},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public List<Book> showBusyBooks(int id_person) {
                return jdbcTemplate.query("select book.name_of_book,book.full_name,book.publishing_year from book join person on book.id_person = person.id_person where book.id_person =?",
                        new Object[]{id_person},new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name_of_book, full_name, publishing_year) VALUES (?,?,?)",
                book.getName_of_book(),book.getFull_name(),book.getPublishing_year());
    }
    public void update(int id_book, Book updatedBook) {
        jdbcTemplate.update("UPDATE book set name_of_book=?, full_name=?,publishing_year=? where id_book=?",
                updatedBook.getName_of_book(), updatedBook.getFull_name(),
                updatedBook.getPublishing_year(), id_book);
    }

    public void updateId_person(int id_book, Book updatedBook) {
        jdbcTemplate.update("UPDATE book set id_person=? where id_book=?",
                updatedBook.getId_person(),id_book);
    }

    public void release(int id_book){
        System.out.println(id_book);
        jdbcTemplate.update("UPDATE book set id_person=null where id_book=?",id_book);
        System.out.println("Четко");
    }

    public void delete(int id_book) {
        jdbcTemplate.update("DELETE FROM book WHERE id_book=?",id_book);
    }
}