package fr.univlille.redspring.parser.mfc.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Mfc {

	private Integer id;

	private List<Acteur> acteurs;
	
	private String name;
	
	private List<Flux> fluxs;
	
	private List<Objective> objectives;
	
}
