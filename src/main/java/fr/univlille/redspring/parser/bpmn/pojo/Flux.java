package fr.univlille.redspring.parser.bpmn.pojo;

import lombok.Data;

@Data
public class Flux {

	private final String id;

	private final String idSource;

	private final String idTarget;

	private ThrowEvent source;

	private CatchEvent target;

}
