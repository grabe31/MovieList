package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eric Grabe - egrabe
 * CIS175 - Fall 2023
 * Sep 13, 2023
 */

@Entity
@Table(name= "Movies")
public class Movie {

	@Id
	@GeneratedValue
	@Column(name= "ID")
	private int id;
	@Column(name= "Name")
	private String name;
	@Column(name= "Director")
	private String director;
	
	public Movie() {
		super();
	}
	
	public Movie(String name, String director) {
		super();
		this.name = name;
		this.director = director;
	}
	
	public String returnMovieDetails() {
		return this.name + ":" + this.director;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}
}
