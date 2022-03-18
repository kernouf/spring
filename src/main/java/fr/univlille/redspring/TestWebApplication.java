package fr.univlille.redspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.univlille.redspring.dao.ProjetRepository;
import fr.univlille.redspring.dao.UtilisateurRepository;
import fr.univlille.redspring.pojo.Projet;
import fr.univlille.redspring.pojo.Utilisateur;

@SpringBootApplication
public class TestWebApplication implements ApplicationRunner {

	@Autowired
	private UtilisateurRepository userRepo;

	@Autowired
	private ProjetRepository projetRepo;

	public static void main(String[] args) {
		SpringApplication.run(TestWebApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Utilisateur Sylvain = new Utilisateur("camus");
		Utilisateur Sabrina = new Utilisateur("kernouf");
		Utilisateur Anis = new Utilisateur("sahed");

		Projet principal = new Projet("Story-map Analyse", Sabrina);

		userRepo.save(Sylvain);
		userRepo.save(Sabrina);
		userRepo.save(Anis);
		projetRepo.save(principal);
	}

}
