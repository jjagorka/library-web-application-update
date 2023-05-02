package ru.ionov.library.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ionov.library.application.dao.BookDAO;
import ru.ionov.library.application.dao.PersonDAO;
import ru.ionov.library.application.models.Book;
import ru.ionov.library.application.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAODAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAODAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books",bookDAO.index());
        return "books/index";
    }

    @PatchMapping("/release/{id}")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/editIdPerson/{id}")
    public String updateIdPerson(@ModelAttribute("book") Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()){
            return "books/show";
        }

        bookDAO.updateId_person(id, book);
        return "redirect:/books";
    }

        @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {

        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
