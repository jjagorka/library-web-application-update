package ru.ionov.library.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ionov.library.application.models.Book;
import ru.ionov.library.application.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findByPerson(Person person);
    List<Book> findByNameOfBookStartingWith(String like);
    Optional<Book> findById(int id);

}
