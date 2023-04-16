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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String full_name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?",new Object[]{full_name},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public Person show(int id_person) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id_person=?",new Object[]{id_person},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person showPersonWhoPutTheBook(int id_book) {
        return jdbcTemplate.query("select person.full_name, person.id_person from book join person on book.id_person = person.id_person where id_book = ?",
                new Object[]{id_book},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(new Person(0,null,0));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, year_of_birth) VALUES (?,?)",
                person.getFull_name(),person.getYear_of_birth());
    }
    public void update(int id_person, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person set full_name=?,year_of_birth=? where id_person=?",
                updatedPerson.getFull_name(), updatedPerson.getYear_of_birth(), id_person);
    }

    public void delete(int id_person) {
        jdbcTemplate.update("DELETE FROM person WHERE id_person=?",id_person);
    }
}
