package fr.univlille.redspring.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univlille.redspring.dto.FichierDTO;
import fr.univlille.redspring.dto.ProjetDTO;
import fr.univlille.redspring.service.FichierService;
import fr.univlille.redspring.service.ProjetService;

/**
 * Classe Controller permettant d'être un chemin d'entrée des requêtes
 * commençant par /api/projet
 * 
 * @author Equipe Rouge
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/projet")
public class ProjetController {

	@Autowired
	private ProjetService projetService;

	@Autowired
	private FichierService fichierService;

	/**
	 * Get - Permet d'obtenir tout les projets de l'utilisateur user
	 * 
	 * @param user l'utilisateur dont on veut obtenir les projets
	 * @return réponse à la requête sous forme d'un ResponseEntity
	 */
	@GetMapping
	public ResponseEntity<Object> getAllProjectFromUser(@AuthenticationPrincipal UserDetails user) {
		List<ProjetDTO> response = projetService.getAllProjectFromUsername(user.getUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get - Permet d'obtenir le projet d'identifiant id
	 * 
	 * @param id identifiant du projet que l'on veut obtenir
	 * @return réponse à la requête sous forme d'un ResponseEntity
	 */
	@GetMapping("{id}")
	public ResponseEntity<Object> getProjectById(@PathVariable Integer id) {
		ProjetDTO response = projetService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("{id}/add/{iduser}")
	public ResponseEntity<Object> addUser(@PathVariable Integer id, @PathVariable Integer iduser) {
		// TODO ProjetDTO response = projetService.findById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "{id}/fichier", consumes = { "*" })
	public ResponseEntity<Object> addFile(@PathVariable Integer id, @PathParam(value = "name") String name,
			@RequestBody byte[] données) throws SerialException, SQLException, IOException {
		projetService.newFile(id, name, données);
		return new ResponseEntity<>(données, HttpStatus.CREATED);
	}

	@GetMapping("{id}/fichier")
	public ResponseEntity<Object> getFichiers(@PathVariable Integer id) {
		List<FichierDTO> res = fichierService.getAllFilesFromProject(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
