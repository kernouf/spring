package fr.univlille.redspring.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import fr.univlille.redspring.dao.FichierRepository;
import fr.univlille.redspring.parser.bpmn.BpmnParser;
import fr.univlille.redspring.parser.bpmn.pojo.Bpmn;
import fr.univlille.redspring.pojo.Raw;

@Service
public class AnalyseService {

	@Autowired
	private BpmnParser bpmnparser;

	@Autowired
	private FichierRepository fRepo;

	public Optional<List<String>> analyseProject(Integer id) {
		List<String> problemes = new ArrayList<>();
		List<Bpmn> bpmns = new ArrayList<>();

		fRepo.findByProjetId(id).orElseThrow().forEach(fichier -> {
			if (fichier.getNom().contains(".bpmn")) {
				System.out.println("nice");
				bpmns.add(parseBpmn((Raw) fichier, problemes));

			} else {
				System.out.println("not nice");
			}
		});

		if (problemes.size() == 0)
			return Optional.empty();
		else
			return Optional.of(problemes);
	}

	private Bpmn parseBpmn(Raw fichier, List<String> problemes) {
		try (InputStream is = fichier.getContenu().getBinaryStream()) {
			Bpmn bpmn = bpmnparser.parse(is).orElseThrow();
			return bpmn;
		} catch (IOException | SQLException | NoSuchElementException e) {
			problemes.add("Le fichier " + fichier.getNom() + "n'est pas un fichier bpmn  valide");
		} catch (SAXException e) {
			problemes.add("Le fichier " + fichier.getNom() + "comporte des erreurs :\n" + e.getMessage());
		}
		return null;
	}

}
