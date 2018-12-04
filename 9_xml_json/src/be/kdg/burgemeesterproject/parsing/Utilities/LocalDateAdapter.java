package be.kdg.burgemeesterproject.parsing.Utilities;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 19:54
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
}
