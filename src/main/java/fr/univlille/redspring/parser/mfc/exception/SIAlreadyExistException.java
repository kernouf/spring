package fr.univlille.redspring.parser.mfc.exception;

@SuppressWarnings("serial")
public class SIAlreadyExistException extends Exception {

	@Override
	public String getMessage() {
		return "Ce fichier comporte déjà un objet définissant le Système d'information.";
	}

	
	
}
