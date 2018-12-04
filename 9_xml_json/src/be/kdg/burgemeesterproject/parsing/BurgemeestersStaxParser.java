package be.kdg.burgemeesterproject.parsing;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.parsing.Utilities.DateAdapter;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 18:45
 */
public class BurgemeestersStaxParser {
	private Burgemeesters burgemeesters;
	private FileWriter fileWriter;
	private XMLStreamWriter xsw;

	public BurgemeestersStaxParser(Burgemeesters burgemeesters, String filepath) {
		try {
			this.burgemeesters = burgemeesters;
			this.fileWriter = new FileWriter(filepath);

			xsw = new IndentingXMLStreamWriter(XMLOutputFactory.newInstance().createXMLStreamWriter(this.fileWriter));
		} catch (IOException e) {
			System.err.println("Error opening file for writing.");
		} catch (XMLStreamException e) {
			System.err.println("Error creating XML writer.");
		}
	}

	public void writeXML() {
		try {

			// prepare document
			xsw.writeStartDocument();
			xsw.writeStartElement("burgemeesters");

			// build document
			// using a loop instead of a lambda so we can reuse the exception handling
			for (Burgemeester burgemeester : this.burgemeesters.gesorteerdOpNaam()) {
				writeBurgemeester(burgemeester);
			}

			// close file
			xsw.writeEndElement(); // burgemeesters
			xsw.writeEndDocument();
			xsw.close();
			fileWriter.close();
		} catch (XMLStreamException e) {
			System.err.println("Error writing XML.");
		} catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}

	private void writeBurgemeester(Burgemeester b) throws XMLStreamException {
		xsw.writeStartElement("burgemeester");
		xsw.writeAttribute("partij", b.getPartij().name());

		writeSimpleElement("naam", b.getNaam());
		writeSimpleElement("geboortedatum", DateAdapter.forWriting(b.getGeboortedatum()));
		writeSimpleElement("gemeente", b.getGemeente());
		writeSimpleElement("procent-voorkeursstemmen", String.valueOf(b.getProcentVoorkeursstemmen()));
		writeSimpleElement("termijnen", String.valueOf(b.getTermijnen()));

		xsw.writeEndElement(); // burgemeester
	}

	private void writeSimpleElement(String name, String content) throws XMLStreamException {
		xsw.writeStartElement(name);
		xsw.writeCharacters(content);
		xsw.writeEndElement();
	}
}
