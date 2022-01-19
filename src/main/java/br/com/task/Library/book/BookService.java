package br.com.task.Library.book;

import br.com.task.Library.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public ResponseEntity<BookDto> insertBook(BookDto bookDto){
        if(!bookDto.name.isEmpty() && !bookDto.author.isEmpty()){
            final Book bookEntity = bookMapper.toEntity(bookDto);
            final Book bookSaved = bookRepository.save(bookEntity);
            BookDto dto = bookMapper.toDto(bookSaved);

            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            throw new NotFoundException();
        }
    }
    
    public ResponseEntity<List<BookDto>> getBooks (){
    	List<Book> books = bookRepository.findAll();
    	return ResponseEntity.ok().body(bookMapper.toDto(books));
    }

//    public ResponseEntity<List<BookDto>> getBooks (String name, String author){
//        List<Book> books = bookRepository.findAll();
//
//        if(name == null && author == null){
//            return ResponseEntity.ok().body(bookMapper.toDto(books));
//        } else if (name != null && author != null) {
//            return getBooksSameAuthorAndName(books, name, author);
//        } else if (name != null && author == null) {
//            return getBooksSameName(books, name);
//        } else if (author != null && name == null) {
//            return getBooksSameAuthor(books, author);
//        }
//        return new ResponseEntity<>(bookMapper.toDto(books), HttpStatus.OK);
//    }

    public ResponseEntity<List<BookDto>> getBooksSameAuthorAndName(List<Book> books, String name, String author){
        List<Book> booksSameAuthorAndName = new ArrayList<>();

        for (Book book : books) {

            if (book.getName().toLowerCase().contains(name.toLowerCase()) && book.getAuthor().toLowerCase().equals(author.toLowerCase())) {
                booksSameAuthorAndName.add(book);
            }
        }
        return new ResponseEntity<>(bookMapper.toDto(booksSameAuthorAndName), HttpStatus.OK);
    }

    public ResponseEntity<List<BookDto>> getBooksSameName(List<Book> books, String name){
        List<Book> booksSameName = new ArrayList<>();

        for (Book book : books) {
            if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                booksSameName.add(book);
            }
        }
        return ResponseEntity.ok().body(bookMapper.toDto(booksSameName));
    }
    public ResponseEntity<List<BookDto>> getBooksSameAuthor(List<Book> books, String author){
        List<Book> booksSameAuthor = new ArrayList<>();

        for(Book book : books){
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                booksSameAuthor.add(book);
            }
        }
        return new ResponseEntity<>(bookMapper.toDto(booksSameAuthor), HttpStatus.OK);
    }

    public ResponseEntity<BookDto> getBookById(Long id){
        Book book = bookRepository.getById(id);
        if (book != null) {
            BookDto dto = bookMapper.toDto(book);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        throw new NotFoundException();
    }

    public ResponseEntity<BookDto> updateBook(BookDto newbookDto, Long id){
        if(!newbookDto.name.isEmpty() && !newbookDto.author.isEmpty()){
            Book book = bookRepository.getById(id);
            book.setName(newbookDto.name);
            book.setAuthor(newbookDto.author);
            book.setCost(newbookDto.cost);

            Book bookSaved = bookRepository.save(book);
            BookDto dto = bookMapper.toDto(bookSaved);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else{
            throw new NotFoundException();
        }
    }

    public void deletBook(Long id){
            Book bookDelet = bookRepository.getById(id);
            bookRepository.delete(bookDelet);
    }
}
