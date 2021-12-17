package br.com.task.Library.book;

import br.com.task.Library.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return bookService.insertBook(bookDto);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> all(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "author", required = false) String author) {
        return bookService.getBooks(name, author);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable(value = "id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto newbookDto, @PathVariable(value = "id") Long id){
        return bookService.updateBook(newbookDto, id);
    }

    @DeleteMapping
    public void deletBook(@RequestBody BookDto bookDto) {
        bookService.deletBook(bookDto);
    }
}
