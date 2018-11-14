package be.kdg.burgemeesterproject.logging;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Kristof Buts
 * @version 1.0 11/14/18 2:27 PM
 */
public class SmallLogFormatter extends Formatter {
	@Override
	public String format(LogRecord record) {
		return String.format("%s Level: %s melding: \"%s\"%n",
				formatRecordMoment(record),
				record.getLevel(),
				MessageFormat.format(record.getMessage(), record.getParameters()));
	}

	private String formatRecordMoment(LogRecord record) {
		Instant i = Instant.ofEpochMilli(record.getMillis());
		LocalDateTime ldt = LocalDateTime.ofInstant(i, ZoneId.systemDefault());
//		 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		return ldt.format(dtf);
	}
}
