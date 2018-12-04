package be.kdg.burgemeesterproject.parsing;

import be.kdg.burgemeesterproject.model.Burgemeesters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 20:00
 */
public class BurgemeestersJaxbParser {
	public static void JaxbWriteXml(String file, Object root) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(root.getClass());
			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(root, new File(file));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(typeParameterClass);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();

			File f = new File(file);

			return (T) unmarshaller.unmarshal(f);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}
}
