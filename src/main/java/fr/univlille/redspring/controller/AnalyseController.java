package fr.univlille.redspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univlille.redspring.service.AnalyseService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/analyse")
public class AnalyseController {

	@Autowired
	private AnalyseService analyse;

	@GetMapping("{id}")
	public ResponseEntity<Object> analyse(@PathVariable Integer id, @AuthenticationPrincipal UserDetails user) {
		Optional<List<String>> resultat = analyse.analyseProject(id);
		if (resultat.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.OK);
		else
			return new ResponseEntity<>(resultat.get(), HttpStatus.OK);
	}

}
