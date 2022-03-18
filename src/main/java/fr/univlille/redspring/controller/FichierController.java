package fr.univlille.redspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univlille.redspring.dto.FichierDTO;
import fr.univlille.redspring.service.FichierService;

@RestController
@RequestMapping("/api/fichier")

// /api/fichier/{id} -- affiche le fichier d'id id 
// sois on fait /api/projet/id/fichier -- ne fonctionne que si l'utilisateur fait partie du projet

/**
 * Classe Controller permettant d'être un chemin d'entrée des requêtes
 * commençant par /api/fichier
 * 
 * @author Equipe Rouge
 */
public class FichierController {

	@Autowired
	private FichierService fichierService;

	/*
	 * @GetMapping public ResponseEntity<Object>
	 * getAllFilesFromProject(@AuthenticationPrincipal UserDetails user) {
	 * List<FichierDTO> response =
	 * fichierService.getAllFilesFromProject(user.getUsername()); // TODO : c'est
	 * pas bien return new ResponseEntity<>(response, HttpStatus.OK); }
	 */

	/**
	 * Get - Permet d'obtenir le fichier d'id id
	 * 
	 * @param id l'identifiant du fichier que l'on veut obtenir
	 * @return réponse à notre requête sous forme d'un objet ResponseEntity
	 */
	@GetMapping("{id}")
	public ResponseEntity<Object> getFileById(@PathVariable Integer id) {
		FichierDTO response = fichierService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}