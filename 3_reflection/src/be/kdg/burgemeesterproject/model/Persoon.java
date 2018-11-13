package be.kdg.burgemeesterproject.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 12:59 PM
 */
public class Persoon {
	private String naam;
	private LocalDate geboortedatum;

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if ( !(naam == null) && !naam.isEmpty() && naam.length() >= 2) {
			this.naam = naam;
		} else {
			throw new IllegalArgumentException("Naam moet minstens twee tekens bevatten.");
		}
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		if (Period.between(geboortedatum, LocalDate.now()).getYears() >= 18) {
			this.geboortedatum = geboortedatum;
		} else {
			throw new IllegalArgumentException("Burgemeester moet minstens 18 jaar zijn.");
		}
	}
}
