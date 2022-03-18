package fr.univlille.redspring.parser.mfc;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import fr.univlille.redspring.parser.mfc.pojo.Acteur;
import fr.univlille.redspring.parser.mfc.pojo.Flux;
import fr.univlille.redspring.parser.mfc.pojo.Mfc;
import fr.univlille.redspring.parser.mfc.pojo.Objective;


public class MfcParser {

	private Mfc mcf;


	public void parse(String path) {

		try (CSVReader reader = new CSVReader(new FileReader(path))) {
			List<String[]> result = reader.readAll();
			result.forEach(this::parseMcf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parseMcf(String[] row) {
		String id = row[0];
		String type = row[1];
		String name = row[11];
		switch(type) {
		case "Conteneur rectangulaire" : 
			this.mcf.setName(name);
			break;
		case "Acteur" : 
			Acteur acteur = new Acteur(name);
			this.mcf.getActeurs().add(acteur);
			break;
		case "Cas d'utilisation" : 
			Objective objective = new Objective(name);
			this.mcf.getObjectives().add(objective);
			break;
		case "Ligne" : 
			String idSource = row[6];
			String idTarget = row[7];
			Flux flux = new Flux(id, name, idSource, idTarget);
			this.mcf.getFluxs().add(flux);
			break;
		default : 
			break;
		}
	}

}
