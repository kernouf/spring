package fr.univlille.redspring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.univlille.redspring.pojo.Fichier;

/**
 * Classe Repository (DAO : Objet d'accès aux données) permettant de communiquer
 * directement avec la base de données Elle hérite de l'interface CrudRepository
 * pour le type Fichier
 * 
 * @see https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * @author Equipe Rouge
 */
public interface FichierRepository extends CrudRepository<Fichier, Integer> {

	/**
	 * Renvoi les fichiers de l'utilisateur username
	 * 
	 * @param username l'utilisateur dont on veut les fichiers
	 * @return un objet conteneur qui contiendra la liste des fichiers de
	 *         l'utilisateur
	 */
	@Query("select f from Fichier f left JOIN f.projet p where (p.createur.login = :username)")
	public Optional<List<Fichier>> findByUsername(String username);

	public Optional<List<Fichier>> findByProjetId(Integer id);
	
}
