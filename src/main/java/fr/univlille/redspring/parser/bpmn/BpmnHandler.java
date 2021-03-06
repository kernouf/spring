package fr.univlille.redspring.parser.bpmn;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import fr.univlille.redspring.parser.bpmn.pojo.*;

public class BpmnHandler extends DefaultHandler {

	private StringBuilder currentValue = new StringBuilder();

	private Bpmn result;

	private List<Flux> flows;
	private List<Acteur> acteurs;

	private List<Processus> processus;
	private List<Processus> processusPrincipaux;

	private Processus currentProcess;

	public Bpmn getResult() {
		return result;
	}

	@Override
	public void startDocument() {
		result = new Bpmn();
		processus = new ArrayList<>();
		processusPrincipaux = new ArrayList<>();
		flows = new ArrayList<>();
		acteurs = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		currentValue.setLength(0);

		if (qName.equalsIgnoreCase("bpmn:participant")) {
			this.acteurs.add(new Acteur(attributes.getValue("id"), attributes.getValue("name"),
					attributes.getValue("processRef")));
		}

		if (qName.equalsIgnoreCase("bpmn:messageFlow")) {
			this.flows.add(new Flux(attributes.getValue("id"), attributes.getValue("sourceRef"),
					attributes.getValue("targetRef")));
		}

		if (qName.equalsIgnoreCase("bpmn:process")) {
			this.currentProcess = new Processus(attributes.getValue("id"));
			processus.add(currentProcess);
		}

		if (qName.contains("Task") || qName.contains("task")) {
			this.currentProcess.isPrincipal(true);
		}

		if (qName.equalsIgnoreCase("bpmn:intermediateCatchEvent")) {
			CatchEvent event = new CatchEvent(attributes.getValue("id"), attributes.getValue("name"));
			this.currentProcess.getCatchEvents().add(event);
		}

		if (qName.equalsIgnoreCase("bpmn:intermediateThrowEvent")) {
			ThrowEvent event = new ThrowEvent(attributes.getValue("id"), attributes.getValue("name"));
			this.currentProcess.getThrowEvents().add(event);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {

		if (qName.equalsIgnoreCase("bpmn:process")) {
			if (this.currentProcess.isPrincipal())
				processusPrincipaux.add(currentProcess);

			flows.forEach(currentProcess::bindFlux);

			for (Acteur acteur : acteurs) {
				if (acteur.getIdProcess().equals(currentProcess.getId())) {
					currentProcess.setActeur(acteur);
					acteur.setProcess(currentProcess);
				}
			}
		}

	}

	@Override
	public void endDocument() {
		result.setFlux(flows);
		result.setActeurs(acteurs);
		result.setProcessus(processus);
		result.setProcessusPrincipaux(processusPrincipaux);
	}
}
