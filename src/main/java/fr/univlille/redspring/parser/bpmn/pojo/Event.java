package fr.univlille.redspring.parser.bpmn.pojo;

import lombok.Data;

@Data
public class Event {

	private final String id;

	private final String name;

	private Flux flux;

}
