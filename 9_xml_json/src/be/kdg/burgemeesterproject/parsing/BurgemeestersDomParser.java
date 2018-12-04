package be.kdg.burgemeesterproject.parsing;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.model.Partij;
import be.kdg.burgemeesterproject.parsing.Utilities.DateAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 19:15
 */
public class BurgemeestersDomParser {
	public static Burgemeesters domReadXml(String fileName) {
		// prepare return object
		// TODO correct behaviour?
		Burgemeesters ret = new Burgemeesters();

		try {

			// create documentbuilder
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// create Document
			Document doc = docBuilder.parse(fileName);

			// read root element
			Element rootElement = doc.getDocumentElement();
			NodeList burgemeesterNodes = rootElement.getChildNodes();

			for (int i = 0; i < burgemeesterNodes.getLength(); ++i) {
				// check whether it's an XML element
				if (burgemeesterNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}


				// build element
				Element e = (Element) burgemeesterNodes.item(i);
				// check if it's the correct type of element
				if (e.getNodeName().compareTo("burgemeester") != 0) {
					continue;
				}


				Burgemeester b = new Burgemeester();
				b.setNaam(getChildText(e, "naam"));
				b.setGeboortedatum(DateAdapter.fromWriting(getChildText(e, "geboortedatum")));
				b.setGemeente(getChildText(e, "gemeente"));
				b.setProcentVoorkeursstemmen(Double.parseDouble(getChildText(e, "procent-voorkeursstemmen")));
				b.setTermijnen(Integer.parseInt(getChildText(e, "termijnen")));
				b.setPartij(Partij.valueOf(e.getAttribute("partij")));

				// add to return
				ret.voegToe(b);
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return ret;
	}

	private static String getChildText(Element parent, String name) {
		return parent.getElementsByTagName(name).item(0).getTextContent();
	}
}
