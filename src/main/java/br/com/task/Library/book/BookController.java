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

//    @GetMapping
//    public ResponseEntity<List<BookDto>> all(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "author", required = false) String author) {
//        return bookService.getBooks(name, author);
//    }
    
    @GetMapping
    public ResponseEntity<List<BookDto>> all() {
    	return bookService.getBooks();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable(value = "id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(value = "id") Long id, @RequestBody BookDto newbookDto){
        return bookService.updateBook(newbookDto, id);
    }

    @DeleteMapping("/{id}")
    public void deletBook(@PathVariable(value = "id") Long id) {
        bookService.deletBook(id);
    }
}
