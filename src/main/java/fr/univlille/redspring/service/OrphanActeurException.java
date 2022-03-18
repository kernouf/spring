package fr.univlille.redspring.service;

import fr.univlille.redspring.service.exception.ComparaisonException;

@SuppressWarnings("serial")
public class OrphanActeurException extends ComparaisonException {

	public OrphanActeurException(String nom) {
		super("L'acteur: \"" + nom + "\" du bpmn ne correspond à aucun acteur du mfc.");
	}

}
