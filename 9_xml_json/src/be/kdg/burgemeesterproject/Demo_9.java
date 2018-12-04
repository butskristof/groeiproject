package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.parsing.BurgemeestersDomParser;
import be.kdg.burgemeesterproject.parsing.BurgemeestersJaxbParser;
import be.kdg.burgemeesterproject.parsing.BurgemeestersStaxParser;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 19:07
 */
public class Demo_9 {

	private static final String filepathPrefix = "9_xml_json/files/";

	public static void main(String[] args) {
		Burgemeesters burgemeesters = new Burgemeesters();
		Data.getData().forEach(burgemeesters::voegToe);

		BurgemeestersStaxParser parser = new BurgemeestersStaxParser(burgemeesters, filepathPrefix + "burgemeesters.xml");
		parser.writeXML();

		Burgemeesters readWithDom = BurgemeestersDomParser.domReadXml(filepathPrefix + "burgemeesters.xml");
		System.out.println("Na wegschrijven via StAX en inlezen via DOM: ");
		readWithDom.gesorteerdOpNaam().forEach(System.out::println);

		System.out.println("===================================");

		BurgemeestersJaxbParser.JaxbWriteXml(filepathPrefix + "burgemeestersJAXB.xml", burgemeesters);
		Burgemeesters readWithJAXB = BurgemeestersJaxbParser.JaxbReadXml(filepathPrefix + "burgemeestersJAXB.xml", Burgemeesters.class);

		System.out.println("Na wegschrijven en inlezen via JAXB: ");
		readWithJAXB.gesorteerdOpNaam().forEach(System.out::println);
	}
}
