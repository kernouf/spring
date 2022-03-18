package fr.univlille.redspring.parser.bpmn.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Processus {

	private final String id;

	private Acteur acteur;

	private List<CatchEvent> catchEvents = new ArrayList<>();

	private List<ThrowEvent> throwEvents = new ArrayList<>();

	public void bindFlux(Flux messageFlow) {
		for (CatchEvent evenement : catchEvents) {
			if (evenement.getId().equals(messageFlow.getIdTarget())) {
				messageFlow.setTarget(evenement);
				evenement.setFlux(messageFlow);
			}
		}

		for (ThrowEvent evenement : throwEvents) {
			if (evenement.getId().equals(messageFlow.getIdSource())) {
				messageFlow.setSource(evenement);
				evenement.setFlux(messageFlow);
			}
		}
	}

}
