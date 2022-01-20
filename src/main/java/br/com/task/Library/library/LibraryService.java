package br.com.task.Library.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.task.Library.exception.NotFoundException;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private LibraryMapper libraryMapper;
	
	public ResponseEntity<LibraryDto> insertLibrary(LibraryDto libraryDto){
		final Library libraryEntity = libraryMapper.toEntity(libraryDto);
		final Library librarySaved = libraryRepository.save(libraryEntity);
		LibraryDto dto = libraryMapper.toDto(librarySaved);
		
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<LibraryDto>> getLibraries (){
        List<Library> libraries = libraryRepository.findAll();
        return new ResponseEntity<>(libraryMapper.toDto(libraries), HttpStatus.OK);
    }
	
	public ResponseEntity<LibraryDto> getLibraryById(Long id){
		Library library = libraryRepository.getById(id);
		if(library != null) {
			LibraryDto dto = libraryMapper.toDto(library);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		throw new NotFoundException();
	}
	
	public ResponseEntity<LibraryDto> updateLibrary(LibraryDto newLibraryDto, Long id){
		Library library = libraryRepository.getById(id);
		library.setName(newLibraryDto.name);
		library.setUsername(newLibraryDto.username);
		library.setPassword(newLibraryDto.password);
		
		Library librarySaved = libraryRepository.save(library);
		LibraryDto dto = libraryMapper.toDto(librarySaved);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	public void deletLibrary(Long id) {
		Library libraryDelet = libraryRepository.getById(id);
		libraryRepository.delete(libraryDelet);
	}
	
	public ResponseEntity<LibraryDto> login (String username, String password){
		Library library = libraryRepository.findLibraryByUsername(username);
		
		if(library.getPassword().equals(password)) {
			LibraryDto dto = libraryMapper.toDto(library);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			throw new NotFoundException();
		}
	}
}