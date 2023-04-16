package ru.ionov.library.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ionov.library.application.models.Book;
import ru.ionov.library.application.models.Person;
import ru.ionov.library.application.services.BooksService;
import ru.ionov.library.application.services.PeopleService;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private BooksService booksService;
    private PeopleService peopleService;
    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books",booksService.findAll());
        for (Book bk : booksService.findAll()){
            if (bk.getTakingTime()!=null){
                long milliseconds = new Date().getTime()-bk.getTakingTime().getTime();
                System.out.println((milliseconds / (24 * 60 * 60 * 1000)));
            }
        }
        return "books/index";
    }
    @GetMapping(params = {"page","books_per_page"})
    public String index(@RequestParam("page") int page, @RequestParam("books_per_page") int itemsPerPage,Model model){
        model.addAttribute("books",booksService.findAllWithPagination(page,itemsPerPage));
        return "books/index";
    }
    @GetMapping(params = {"sort_by_year"})
    public String index(@RequestParam("sort_by_year")boolean sortByYear, Model model){
        if (sortByYear){
            model.addAttribute("books",booksService.findAllWithSortByYear());
        }
        else {
            model.addAttribute("books",booksService.findAll());
        }

        return "books/index";
    }
    @PostMapping("/search")
    public String makeSearch(Model model,@RequestParam("startWith") String startWith){
        model.addAttribute("books",booksService.findByNameOfBookStartingWith(startWith));
        return "books/search";
    }

    @GetMapping("/search")
    public String search(){
        return "books/search";
    }

    @PatchMapping("/release/{id}")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/editIdPerson/{id}")
    public String updateIdPerson(@ModelAttribute("person") Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()){
            return "books/show";
        }
        booksService.updatePersonId(id,person);
        return "redirect:/books";
    }

        @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findById(id));
        Optional<Person> bookOwner = booksService.getBookOwner(id);
            if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
            else
                model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    @GetMapping("/new")
    public String getBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }
}
