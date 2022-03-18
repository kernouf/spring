package fr.univlille.redspring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.univlille.redspring.pojo.Utilisateur;

/**
 * Classe Repository (DAO : Objet d'accès aux données) permettant de communiquer
 * directement avec la base de données Elle hérite de l'interface CrudRepository
 * pour le type Utilisateur
 * 
 * @see https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * @author Equipe Rouge
 */
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

	/**
	 * Renvoi l'id et le login de tout les utilisateurs
	 * 
	 * @return la liste de tout les utilisateurs contenant leurs et leurs login
	 */
	@Query("select new Utilisateur(id, login) from Utilisateur u")
	public List<Utilisateur> findAllShort();

	/**
	 * Renvoi l'utilisateur d'id id
	 * 
	 * @param id l'id de l'utilisateur
	 * @return un objet conteneur qui contiendra la liste des utilisateurs
	 */
	@Query("select u from Utilisateur u where u.id = :id")
	public Optional<Utilisateur> findById(@Param("id") Integer id);
}
