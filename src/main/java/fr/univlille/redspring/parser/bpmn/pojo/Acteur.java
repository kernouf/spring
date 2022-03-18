package fr.univlille.redspring.parser.bpmn.pojo;

import lombok.Data;

@Data
public class Acteur {

	private final String id;

	private final String name;

	private final String idProcess;

	private Processus process;

}
