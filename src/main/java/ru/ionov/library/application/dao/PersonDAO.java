package ru.ionov.library.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

    //  Для валидации уникальности ФИО
    public Optional<Person> getPersonByFullName(String full_name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?",new Object[]{full_name},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, year_of_birth) VALUES (?,?)",
                person.getFull_name(),person.getYear_of_birth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person set full_name=?,year_of_birth=? where id=?",
                updatedPerson.getFull_name(), updatedPerson.getYear_of_birth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }
}
