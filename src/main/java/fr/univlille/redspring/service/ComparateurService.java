package fr.univlille.redspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.univlille.redspring.parser.bpmn.pojo.Bpmn;
import fr.univlille.redspring.parser.bpmn.pojo.Processus;
import fr.univlille.redspring.parser.mfc.pojo.Acteur;
import fr.univlille.redspring.parser.mfc.pojo.Mfc;
import fr.univlille.redspring.service.exception.ComparaisonException;

@Component
public class ComparateurService {

	public List<String> compare(Bpmn bpmn, Mfc mfc) throws TooMuchSIException, SINamesNotTheSameException {
		System.out.println(bpmn.getProcessusPrincipaux().size());
		List<String> probleme = new ArrayList<>();
		checkSI(bpmn, mfc);
		if (bpmn != null && mfc != null) {
			try {
				probleme.addAll(checkActeur(bpmn, mfc));
			} catch (ComparaisonException e) {
				probleme.add(e.getMessage());
			}
			try {
				checkFlux(bpmn, mfc);
			} catch (ComparaisonException e) {
				probleme.add(e.getMessage());
			}
		}
		return probleme;
	}

	private void checkSI(Bpmn bpmn, Mfc mfc) throws TooMuchSIException, SINamesNotTheSameException {
		if (bpmn != null) {
			int taillebpmn = bpmn.getProcessusPrincipaux().size();
			if (taillebpmn != 1)
				throw new TooMuchSIException();
			if (mfc != null) {
				IsSITheSame(bpmn, mfc);
			}
		}
	}

	private void IsSITheSame(Bpmn bpmn, Mfc mfc) throws SINamesNotTheSameException {
		String nomSI = bpmn.getProcessusPrincipaux().get(0).getActeur().getName();
		String nomMfc = mfc.getName();
		if (!nomSI.equalsIgnoreCase(nomMfc))
			throw new SINamesNotTheSameException(nomSI, nomMfc);
	}

	private List<String> checkActeur(Bpmn bpmn, Mfc mfc) throws NotSameActorSizeException {
		if (orTheActeurSizesEquals(bpmn, mfc))
			throw new NotSameActorSizeException();

		List<String> probleme = new ArrayList<>();
		for (Processus process : bpmn.getProcessus()) {
			if (!process.isPrincipal()) {
				try {
					checkAllNames(mfc, process);
				} catch (OrphanActeurException e) {
					probleme.add(e.getMessage());
				}
			}
		}
		return probleme;

	}

	private void checkAllNames(Mfc mfc, Processus process) throws OrphanActeurException {
		String nom = process.getActeur().getName();
		boolean match = false;
		for (Acteur acteur : mfc.getActeurs()) {
			if (!nom.equalsIgnoreCase(acteur.getName())) {
				match = true;
			}
		}
		if (!match)
			throw new OrphanActeurException(nom);
	}

	private boolean orTheActeurSizesEquals(Bpmn bpmn, Mfc mfc) {
		return bpmn.getProcessus().size() - bpmn.getProcessusPrincipaux().size() != mfc.getActeurs().size();
	}

	private void checkFlux(Bpmn bpmn, Mfc mfc) throws FluxNumberArentTheSameException {
		if (bpmn.getFlux().size() != mfc.getFluxs().size())
			throw new FluxNumberArentTheSameException();
	}

}
