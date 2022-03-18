package fr.univlille.redspring.parser.bpmn.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Bpmn {

	private List<Processus> processus;

	private List<Processus> processusPrincipaux;

	private List<Acteur> acteurs;
	
	private List<Flux> flux;

}
