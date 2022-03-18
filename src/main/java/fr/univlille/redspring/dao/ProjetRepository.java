package fr.univlille.redspring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.univlille.redspring.pojo.Projet;

/**
 * Classe Repository (DAO : Objet d'accès aux données) permettant de communiquer
 * directement avec la base de données Elle hérite de l'interface CrudRepository
 * pour le type Projet
 * 
 * @see https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * @author Equipe Rouge
 */
public interface ProjetRepository extends CrudRepository<Projet, Integer> {

	/**
	 * Renvoi les projets de l'utilisateur username
	 * 
	 * @param username l'utilisateur dont on veut les projets
	 * @return un objet conteneur qui contiendra la liste des projets de
	 *         l'utilisateur
	 */
	@Query("select p from Projet p left JOIN p.membres m ") // where (p.createur.login = :username) ")//or (m.id.membre
															// = :username)")
	public Optional<List<Projet>> findByUsername(String username);

}
