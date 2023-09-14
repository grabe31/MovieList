package controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import javax.persistence.EntityManager;
import model.Movie;

/**
 * @author Eric Grabe - egrabe
 * CIS175 - Fall 2023
 * Sep 13, 2023
 */
public class MovieHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("MovieList");

	public void insertMovie(Movie m) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Movie> showAllMovies(){
		EntityManager em = emfactory.createEntityManager();
		List<Movie> allMovies = em.createQuery("SELECT m FROM Movie m").getResultList();
		return allMovies;
	}
	
	public void deleteMovie(Movie toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Movie> typedQuery= em.createQuery("select m from Movie m where m.name = :selectedName and m.director = :selectedDirector", Movie.class);
	
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedDirector", toDelete.getDirector());
		//we only want one result
		typedQuery.setMaxResults(1);
		//get the result and save it into a new list item
		Movie result = typedQuery.getSingleResult();
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Movie> searchForMovieByName(String movieName) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Movie> typedQuery = em.createQuery("select m from Movie m where m.name = :selectedName", Movie.class);
		typedQuery.setParameter("selectedName", movieName);
		List<Movie> foundMovie = typedQuery.getResultList();
		em.close();
		
		return foundMovie;
	}
	
	public List<Movie> searchForMovieByDirector(String directorName) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Movie> typedQuery = em.createQuery("select m from Movie m where m.director = :selectedDirector", Movie.class);
		typedQuery.setParameter("selectedDirector", directorName);
		List<Movie> foundMovie = typedQuery.getResultList();
		em.close();
		return foundMovie;
		}
	
	public Movie searchForMovieById(int idToEdit) {
	
	EntityManager em = emfactory.createEntityManager();
	em.getTransaction().begin();
	Movie found = em.find(Movie.class, idToEdit);
	em.close();
	return found;
	}
	
	public void updateMovie(Movie toEdit) {
	
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp(){
		emfactory.close();
		}

	
} //end class
