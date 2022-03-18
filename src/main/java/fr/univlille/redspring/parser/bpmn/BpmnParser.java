package fr.univlille.redspring.parser.bpmn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import fr.univlille.redspring.parser.bpmn.pojo.Bpmn;

@Component
public class BpmnParser {

	public Optional<Bpmn> parse(InputStream stream) throws SAXException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		} catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		try {

			SAXParser saxParser = factory.newSAXParser();

			BpmnHandler handler = new BpmnHandler();

			saxParser.parse(stream, handler);

			return Optional.of(handler.getResult());

		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
