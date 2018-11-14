package be.kdg.burgemeesterproject.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 3:29 PM
 */
public class Burgemeester implements Comparable<Burgemeester> {
	/*
	Burgemeester (naam: String, geboortedatum: LocalDate, gemeente: String,
		procentVoorkeursstemmen: double, termijnen: int, partij: enum)
	 */

	private static final Logger logger = Logger.getLogger(Burgemeester.class.getName());

	private String naam;
	private LocalDate geboortedatum;
	private String gemeente;
	private double procentVoorkeursstemmen;
	private int termijnen;
	private Partij partij;

	public Burgemeester() {
		this("Onbekend", LocalDate.of(0,1,1), "Onbekend", 0.0, 0, Partij.ONBEKEND);
	}

	public Burgemeester(String naam, LocalDate geboortedatum, String gemeente, double procentVoorkeursstemmen, int termijnen, Partij partij) {
		this.setNaam(naam);
		this.setGeboortedatum(geboortedatum);
		this.setGemeente(gemeente);
		this.setProcentVoorkeursstemmen(procentVoorkeursstemmen);
		this.setTermijnen(termijnen);
		this.setPartij(partij);
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if ( !(naam == null) && !naam.isEmpty() && naam.length() >= 2) {
			this.naam = naam;
		} else {
//			throw new IllegalArgumentException("Naam moet minstens twee tekens bevatten.");
			logger.log(Level.SEVERE, "Naam {0} heeft niet minstens twee tekens.", naam);
		}
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		if (Period.between(geboortedatum, LocalDate.now()).getYears() >= 18) {
			this.geboortedatum = geboortedatum;
		} else {
//			throw new IllegalArgumentException("Burgemeester moet minstens 18 jaar zijn.");
			logger.log(Level.SEVERE, "Geboortedatum {0} is niet minstens 18 jaar geleden.", geboortedatum.toString());
		}
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		if ( !(gemeente == null) && !gemeente.isEmpty() && gemeente.length() >= 2) {
			this.gemeente = gemeente;
		} else {
//			throw new IllegalArgumentException("Gemeente moet minstens twee tekens bevatten.");
			logger.log(Level.SEVERE, "Gemeente {0} heeft niet minstens twee tekens.", gemeente);
		}
	}

	public double getProcentVoorkeursstemmen() {
		return procentVoorkeursstemmen;
	}

	public void setProcentVoorkeursstemmen(double procentVoorkeursstemmen) {
		if (procentVoorkeursstemmen >= 0.0 && procentVoorkeursstemmen <= 1.0) {
			this.procentVoorkeursstemmen = procentVoorkeursstemmen;
		} else {
//			throw new IllegalArgumentException("Percentage voorkeursstemmen niet geldig.");
			logger.log(Level.SEVERE, "Percentage {0} ligt niet tussen 0.0 en 1.0", procentVoorkeursstemmen);
		}
	}

	public int getTermijnen() {
		return termijnen;
	}

	public void setTermijnen(int termijnen) {
		if (termijnen >= 0) {
			this.termijnen = termijnen;
		} else {
//			throw new IllegalArgumentException("Aantal termijnen moet positief zijn.");
			logger.log(Level.SEVERE, "Aantal termijnen {0} is niet positief.", termijnen);
		}
	}

	public Partij getPartij() {
		return partij;
	}

	public void setPartij(Partij partij) {
		this.partij = partij;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Burgemeester that = (Burgemeester) o;
		return Objects.equals(getNaam(), that.getNaam()) &&
				Objects.equals(getGemeente(), that.getGemeente());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNaam(), getGemeente());
	}

	@Override
	public int compareTo(Burgemeester o) {
		if (!this.getNaam().equals(o.getNaam())) {
			return this.getNaam().compareTo(o.getNaam());
		} else {
			return this.getGemeente().compareTo(o.getGemeente());
		}
	}

	@Override
	public String toString() {
		/*
		Ayatollah Khomeini		(°1902) Iran		regime: Fundamentalisme		8,5 mln doden
		 */
		String ret = "";

		ret = String.format("%-25s\t%17s\t%-7s\t%10s\t%.3f%%\t%d termijnen", this.getNaam(), this.getGemeente(), this.getPartij(), this.getGeboortedatum(), this.getProcentVoorkeursstemmen() * 100, this.getTermijnen());

		return ret;
	}
}
