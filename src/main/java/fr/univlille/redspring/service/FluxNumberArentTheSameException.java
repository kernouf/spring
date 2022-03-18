package fr.univlille.redspring.service;

import fr.univlille.redspring.service.exception.ComparaisonException;

@SuppressWarnings("serial")
public class FluxNumberArentTheSameException extends ComparaisonException {

	@Override
	public String getMessage() {
		return "Le Bpmn et le MFC ne comportent pas le même nombre de flux.";
	}

}
