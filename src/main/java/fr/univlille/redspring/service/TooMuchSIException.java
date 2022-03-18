package fr.univlille.redspring.service;

import fr.univlille.redspring.service.exception.ComparaisonException;

@SuppressWarnings("serial")
public class TooMuchSIException extends ComparaisonException {

	public TooMuchSIException() {
		super();
	}

	@Override
	public String getMessage() {
		return "Le Bpmn contient plusieurs SI.";
	}

}
