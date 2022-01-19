package br.com.task.Library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.task.Library.library.Library;
import br.com.task.Library.library.LibraryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMapper {

	@Autowired
	private LibraryRepository libraryRepository;
	
    public BookDto toDto (Book book){
        final BookDto dto = new BookDto();
        dto.id = book.getId();
        dto.name = book.getName();
        dto.author = book.getAuthor();
        dto.cost = book.getCost();
//        dto.yearEdition = book.getYearEdition();
//        dto.publisher = book.getPublisher();
        dto.isBorrowed = book.getIsBorrowed();
        dto.idLibrary = book.getLibrary().getId();
        return dto;
    }

    public List<BookDto> toDto(List<Book> books){
        final ArrayList<BookDto> booksDto = new ArrayList<>();
        for(Book book : books){
            booksDto.add(toDto(book));
        }
        return booksDto;
    }

    public Book toEntity(BookDto dto){
        final Book book = new Book();
        book.setId(dto.id);
        book.setName(dto.name);
        book.setAuthor(dto.author);
        book.setCost(dto.cost);
//        book.setYearEdition(dto.yearEdition);
//        book.setPublisher(dto.publisher);
        book.setIsBorrowed(dto.isBorrowed);
        
        Library library = libraryRepository.getById(dto.idLibrary);
        book.setLibrary(library);
        
        return book;
    }
}
