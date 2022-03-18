package fr.univlille.redspring.parser.mfc.exception;

@SuppressWarnings("serial")
public class IncorrectHeaderLineException extends Exception {

	@Override
	public String getMessage() {
		return "La première ligne du fichier n'est pas valide (l'entête csv).";
	}

}
