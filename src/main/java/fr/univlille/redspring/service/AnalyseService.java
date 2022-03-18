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
import fr.univlille.redspring.parser.mfc.MfcParser;
import fr.univlille.redspring.parser.mfc.exception.IncorrectFluxTypeException;
import fr.univlille.redspring.parser.mfc.exception.IncorrectHeaderLineException;
import fr.univlille.redspring.parser.mfc.exception.SIAlreadyExistException;
import fr.univlille.redspring.parser.mfc.pojo.Mfc;
import fr.univlille.redspring.pojo.Raw;
import fr.univlille.redspring.service.exception.ComparaisonException;

@Service
public class AnalyseService {

	@Autowired
	private ComparateurService comparateur;

	@Autowired
	private BpmnParser bpmnparser;

	@Autowired
	private MfcParser mfcparser;

	@Autowired
	private FichierRepository fRepo;

	public Optional<List<String>> analyseProject(Integer id) {
		List<String> problemes = new ArrayList<>();
		List<Bpmn> bpmns = new ArrayList<>();
		List<Mfc> mfcs = new ArrayList<>();

		fRepo.findByProjetId(id).orElseThrow().forEach(fichier -> {
			if (fichier.getNom().contains(".bpmn")) {
				bpmns.add(analyseBpmn((Raw) fichier, problemes));
			} else if (fichier.getNom().contains(".csv")) {
				mfcs.add(analyseMfc((Raw) fichier, problemes));
			}
		});

		Bpmn bpmn;
		if (bpmns.size() == 0)
			bpmn = null;
		else
			bpmn = bpmns.get(0);
		Mfc mfc;
		if (mfcs.size() == 0)
			mfc = null;
		else
			mfc = mfcs.get(0);

		try {
			problemes.addAll(comparateur.compare(bpmn, mfc));
		} catch (ComparaisonException e) {
			problemes.add(e.getMessage());
		}

		if (problemes.size() == 0)
			return Optional.empty();
		else
			return Optional.of(problemes);
	}

	private Bpmn analyseBpmn(Raw fichier, List<String> problemes) {
		try (InputStream is = fichier.getContenu().getBinaryStream()) {
			Bpmn bpmn = bpmnparser.parse(is).orElseThrow();
			return bpmn;
		} catch (IOException | SQLException | NoSuchElementException e) {
			problemes.add("Le fichier \n" + fichier.getNom() + "\nn'est pas un fichier bpmn  valide");
		} catch (SAXException e) {
			problemes.add("Le fichier \n" + fichier.getNom() + "\ncomporte des erreurs :\n" + e.getMessage());
		}
		return null;
	}

	private Mfc analyseMfc(Raw fichier, List<String> problemes) {
		try (InputStream is = fichier.getContenu().getBinaryStream()) {
			Mfc mfc = mfcparser.parse(is).orElseThrow();
			System.out.println(mfc);
			return mfc;
		} catch (IOException | SQLException | NoSuchElementException e) {
			problemes.add("Le fichier \"" + fichier.getNom() + "\"ne représente pas un fichier csv valide");
		} catch (SIAlreadyExistException | IncorrectHeaderLineException | IncorrectFluxTypeException e) {
			problemes.add("Le fichier \"" + fichier.getNom() + "\"comporte des erreurs :\n" + e.getMessage());
		}
		return null;
	}

}
