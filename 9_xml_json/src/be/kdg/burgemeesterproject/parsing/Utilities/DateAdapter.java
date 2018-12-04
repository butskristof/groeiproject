package be.kdg.burgemeesterproject.parsing.Utilities;

import java.time.LocalDate;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 19:01
 */
public class DateAdapter {
	// using an adapter class so we can change the implementation later

	// TODO check adapter pattern

	public static String forWriting(LocalDate ld) {
		return ld.toString();
	}

	public static LocalDate fromWriting(String s) {
		return LocalDate.parse(s);
	}
}
