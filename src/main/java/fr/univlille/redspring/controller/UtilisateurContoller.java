package fr.univlille.redspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univlille.redspring.dto.UtilisateurDTO;
import fr.univlille.redspring.service.UtilisateurService;

/**
 * Classe Controller permettant d'être un chemin d'entrée des requêtes
 * commençant par /api/user
 * 
 * @author Anis
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UtilisateurContoller {

	@Autowired
	private UtilisateurService utilisateurService;

	/**
	 * GET - Permet d'obtenir tout les utilisateurs
	 * 
	 * @return réponse à la requête sous forme d'un objet ResponseEntity
	 */
	@GetMapping
	public ResponseEntity<Object> getUser() {
		Iterable<UtilisateurDTO> response = utilisateurService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * GET - Permet d'obtenir l'utilisateur d'id id
	 * 
	 * @param id id de l'utilisateur que l'on veut obtenir
	 * @return réponse à la requête sous forme d'un objet ResponseEntity
	 */
	@GetMapping("{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
		UtilisateurDTO response = utilisateurService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * POST - Permet d'ajouter un utilisateur dans la base
	 * 
	 * @param utilisateur l'utilisateur que l'on veut ajouter (objet UtilisateurDTO)
	 * @return réponse à la requête sous forme d'un objet ResponseEntity
	 */
	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody UtilisateurDTO utilisateur) {
		utilisateurService.saveCustomer(utilisateur);
		return new ResponseEntity<>("Créé", HttpStatus.CREATED);
	}
}
