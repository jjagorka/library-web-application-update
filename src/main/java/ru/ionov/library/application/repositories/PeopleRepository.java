package ru.ionov.library.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ionov.library.application.models.Person;


import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByFullName(String full_name);
    Optional<Person> findById(int id);

}
