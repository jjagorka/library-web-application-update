package ru.ionov.library.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ionov.library.application.models.Book;
import ru.ionov.library.application.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        return jdbcTemplate.query("SELECT * FROM book",new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

//    public List<Book> showBusyBooks(int id) {
//                return jdbcTemplate.query("select book.name_of_book,book.author,book.publishing_year from book join person on book.person_id = person.id where book.id =?",
//                        new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
//    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("select * from book where person_id =?",
                new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select person.* from book join person on book.person_id = person.id where book.id = ?",
                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name_of_book, author, publishing_year) VALUES (?,?,?)",
                book.getName_of_book(),book.getAuthor(),book.getPublishing_year());
    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book set name_of_book=?, full_name=?,publishing_year=? where id=?",
                updatedBook.getName_of_book(), updatedBook.getAuthor(),
                updatedBook.getPublishing_year(), id);
    }

    public void updateId_person(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book set person_id=? where id=?",
                updatedBook.getId(),id);
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE book set person_id=null where id=?",id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?",id);
    }
}