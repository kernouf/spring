package fr.univlille.redspring.parser.mfc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;
import fr.univlille.redspring.parser.mfc.exception.IncorrectFluxTypeException;
import fr.univlille.redspring.parser.mfc.exception.IncorrectHeaderLineException;
import fr.univlille.redspring.parser.mfc.exception.SIAlreadyExistException;
import fr.univlille.redspring.parser.mfc.pojo.Acteur;
import fr.univlille.redspring.parser.mfc.pojo.Flux;
import fr.univlille.redspring.parser.mfc.pojo.Mfc;
import fr.univlille.redspring.parser.mfc.pojo.Objectif;

@Component
public class MfcParser {

	private static final String ARROW = "Arrow";
	private static final String NONE = "None";

	public Optional<Mfc> parse(InputStream stream)
			throws SIAlreadyExistException, IncorrectFluxTypeException, IncorrectHeaderLineException {

		try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
			return Optional.of(parseMcf(reader));// Optional.of(parseMcf(lines));
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public Mfc parseMcf(CSVReader reader)
			throws SIAlreadyExistException, IOException, IncorrectFluxTypeException, IncorrectHeaderLineException {
		Mfc mfc = new Mfc();

		String[] header = reader.readNext();
		String[] reference = new String[] { "Id", "Nom", "Bibliothèque de formes", "ID de page", "Contenu par",
				"Groupe", "Source de la ligne", "Destination de la ligne", "Source de la flèche",
				"Destination de la flèche", "Statut", "Zone de texte 1" };

		if (!Arrays.equals(header, reference)) {
			throw new IncorrectHeaderLineException();
		}

		List<String[]> lignes = reader.readAll();

		for (String[] ligne : lignes) {
			parseligne(mfc, ligne);
		}

		return mfc;
	}

	private void parseligne(Mfc mfc, String[] ligne) throws SIAlreadyExistException, IncorrectFluxTypeException {

		String id = ligne[0];
		String type = ligne[1];
		String name = ligne[11];

		switch (type) {
		case "Conteneur rectangulaire":
			if (mfc.getName() != null)
				throw new SIAlreadyExistException();
			else
				mfc.setName(name);
			break;
		case "Acteur":
			mfc.getActeurs().add(new Acteur(id, name));
			break;
		case "Cas d'utilisation":
			mfc.getObjectives().add(new Objectif(id, name));
			break;
		case "Ligne":
			String id1 = ligne[6];
			String id2 = ligne[7];
			String type1 = ligne[8];
			String type2 = ligne[9];

			if (type1.equals(NONE) && type2.equals(ARROW))
				mfc.getFluxs().add(new Flux(id, name, id1, id2));
			else if (type1.equals(ARROW) && type2.equals(NONE))
				mfc.getFluxs().add(new Flux(id, name, id2, id1));
			else
				throw new IncorrectFluxTypeException(id, name);
			break;
		default:
			break;
		}
	}

}
