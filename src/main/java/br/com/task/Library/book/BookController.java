package br.com.task.Library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

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
