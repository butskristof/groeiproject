package be.kdg.burgemeesterproject.model;

import be.kdg.burgemeesterproject.reflection.CanRun;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 12:59 PM
 */
public class Persoon implements Comparable<Persoon> {
	private String naam;
	private LocalDate geboortedatum;

	public String getNaam() {
		return naam;
	}

	@CanRun("Jos Peeters")
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

	@CanRun
	public void setGeboortedatum(LocalDate geboortedatum) {
		if (Period.between(geboortedatum, LocalDate.now()).getYears() >= 18) {
			this.geboortedatum = geboortedatum;
		} else {
			throw new IllegalArgumentException("Burgemeester moet minstens 18 jaar zijn.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Persoon persoon = (Persoon) o;
		return Objects.equals(getNaam(), persoon.getNaam()) &&
				Objects.equals(getGeboortedatum(), persoon.getGeboortedatum());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNaam(), getGeboortedatum());
	}

	@Override
	public int compareTo(Persoon o) {
		if (!this.getNaam().equals(o.getNaam())) {
			return this.getNaam().compareTo(o.getNaam());
		} else {
			return this.getGeboortedatum().compareTo(o.getGeboortedatum());
		}
	}
}
