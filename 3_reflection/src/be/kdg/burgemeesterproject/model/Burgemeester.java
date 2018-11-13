package be.kdg.burgemeesterproject.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 3:29 PM
 */
public class Burgemeester extends Persoon implements Comparable<Persoon> {
	/*
	Burgemeester (naam: String, geboortedatum: LocalDate, gemeente: String,
		procentVoorkeursstemmen: double, termijnen: int, partij: enum)
	 */

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

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		if ( !(gemeente == null) && !gemeente.isEmpty() && gemeente.length() >= 2) {
			this.gemeente = gemeente;
		} else {
			throw new IllegalArgumentException("Gemeente moet minstens twee tekens bevatten.");
		}
	}

	public double getProcentVoorkeursstemmen() {
		return procentVoorkeursstemmen;
	}

	public void setProcentVoorkeursstemmen(double procentVoorkeursstemmen) {
		if (procentVoorkeursstemmen >= 0.0 && procentVoorkeursstemmen <= 1.0) {
			this.procentVoorkeursstemmen = procentVoorkeursstemmen;
		} else {
			throw new IllegalArgumentException("Percentage voorkeursstemmen niet geldig.");
		}
	}

	public int getTermijnen() {
		return termijnen;
	}

	public void setTermijnen(int termijnen) {
		if (termijnen >= 0) {
			this.termijnen = termijnen;
		} else {
			throw new IllegalArgumentException("Aantal termijnen moet positief zijn.");
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
		if (!super.equals(o)) return false;
		Burgemeester that = (Burgemeester) o;
		return Objects.equals(getGemeente(), that.getGemeente());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getGemeente());
	}

	@Override
	public int compareTo(Persoon o) {
		int ret;
		if (o instanceof Burgemeester) {
			if (!this.getNaam().equals(o.getNaam())) {
				ret =  this.getNaam().compareTo(o.getNaam());
			} else {
				Burgemeester toCompare = (Burgemeester) o;
				ret = this.getGemeente().compareTo(toCompare.getGemeente());
			}
		} else {
			ret = super.compareTo(o);
		}

		return ret;
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
