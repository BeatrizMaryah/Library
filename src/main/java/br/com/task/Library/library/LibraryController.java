package br.com.task.Library.library;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/libraries")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	
	@PostMapping
	public ResponseEntity<LibraryDto> addLibrary(@RequestBody LibraryDto libraryDto){
		return libraryService.insertLibrary(libraryDto);
	}
	
	@GetMapping
	public ResponseEntity<List<LibraryDto>> all(){
		return libraryService.getLibraries();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LibraryDto> getById(@PathVariable(value = "id") Long id){
		return libraryService.getLibraryById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LibraryDto> updateLibrary(@RequestBody LibraryDto newLibraryDto, @PathVariable(value = "id") Long id){
		return libraryService.updateLibrary(newLibraryDto, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletLibrary(@PathVariable(value = "id") Long id) {
		libraryService.deletLibrary(id);
	}
	
	@RequestMapping("/login")
    public ResponseEntity<LibraryDto> login(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {
		return libraryService.login(username, password);
    }
}