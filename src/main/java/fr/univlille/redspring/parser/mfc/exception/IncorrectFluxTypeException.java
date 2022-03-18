package fr.univlille.redspring.parser.mfc.exception;

@SuppressWarnings("serial")
public class IncorrectFluxTypeException extends Exception {

	public IncorrectFluxTypeException(String id, String nom) {
		super("Le type de la fléche (id: " + id + ",nom: " + nom + ") n'est pas valide.");
	}

}
