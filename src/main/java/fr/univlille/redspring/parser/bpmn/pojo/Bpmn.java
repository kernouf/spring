package fr.univlille.redspring.parser.bpmn.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Bpmn {

	private List<Processus> processus;

	private List<Flux> flux;

}
