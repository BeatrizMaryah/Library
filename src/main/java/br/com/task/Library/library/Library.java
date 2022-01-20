package br.com.task.Library.library;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.task.Library.book.Book;

@Entity
@Table(name="library") 
public class Library {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private String adress;
    
    @Column
    private Integer contact;
    
    @JsonIgnore
	@OneToMany(mappedBy = "library")
	private List<Book> books = new ArrayList<Book>();
    
//    @JsonIgnore
//	@OneToMany(mappedBy = "library")
//	private List<User> users = new ArrayList<User>();

    public Library() {}
    
	public Library(Long id, String name, String username, String password, String adress, int contact) {
		this.setId(id);
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setAdress(adress);
		this.setContact(contact);
	}
	
	public Library(String name) {
		this.setName(name);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}
}