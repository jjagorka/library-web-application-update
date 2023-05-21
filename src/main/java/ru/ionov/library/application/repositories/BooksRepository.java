package ru.ionov.library.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ionov.library.application.models.Book;
import ru.ionov.library.application.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findByPerson(Person person);
    List<Book> findByNameOfBookStartingWith(String like);
}
