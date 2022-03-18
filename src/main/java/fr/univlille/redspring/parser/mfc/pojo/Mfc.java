package fr.univlille.redspring.parser.mfc.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Mfc {

	public Mfc() {
		this.acteurs = new ArrayList<>();
		this.fluxs = new ArrayList<>();
		this.objectives = new ArrayList<>();
	}

	private String name;

	private List<Acteur> acteurs;

	private List<Flux> fluxs;

	private List<Objectif> objectives;

}
