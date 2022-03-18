package fr.univlille.redspring.service;

import fr.univlille.redspring.service.exception.ComparaisonException;

@SuppressWarnings("serial")
public class SINamesNotTheSameException extends ComparaisonException {

	public SINamesNotTheSameException(String nomSI, String nomMfc) {
		super("Le Si du Bpmn : \"" + nomSI + "\" ne correspondant pas avec celui du mfc :\"" + nomMfc + "\".");
	}

}
