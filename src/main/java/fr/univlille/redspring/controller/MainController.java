package fr.univlille.redspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/api")
public class MainController {

	@GetMapping("/login")
	public ResponseEntity<Object> login(@AuthenticationPrincipal UserDetails user) {
		return new ResponseEntity<Object>(user.getUsername(), HttpStatus.OK);
	}

	@GetMapping("/signup")
	public ResponseEntity<Object> signUp() {
		return null;
	}
}
