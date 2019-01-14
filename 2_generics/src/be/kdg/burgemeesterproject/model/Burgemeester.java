package be.kdg.burgemeesterproject.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Klasse die een in Belgie besturende burgemeester voorstelt
 *
 * @author Kristof Buts
 * @version 1.0 11/12/18 3:29 PM
 * @see <a href="https://nl.wikipedia.org/wiki/Burgemeester">Burgemeester (Wikipedia)</a>
 */
public class Burgemeester implements Comparable<Burgemeester> {
	/*
	Burgemeester (naam: String, geboortedatum: LocalDate, gemeente: String,
		procentVoorkeursstemmen: double, termijnen: int, partij: enum)
	 */

	/**
	 * Naam van de burgemeester (minstens twee karakters)
	 */
	private String naam;
	/**
	 * Geboortedatum als LocalDate (minstens 18 jaar oud)
	 */
	private LocalDate geboortedatum;
	/**
	 * Gemeente waar de burgemeester bestuurt (minstens twee karakters)
	 */
	private String gemeente;
	/**
	 * Percentage voorkeursstemmen dat de burgemeester bij de laatste stemming behaalde (waarde tussen 0.0 en 1.0)
	 */
	private double procentVoorkeursstemmen;
	/**
	 * Aantal termijnen dat de burgemeester reeds bestuurd heeft (positief geheel getal)
	 */
	private int termijnen;
	/**
	 * Partij waar de burgemeester toe behoort
	 *
	 * @see Partij
	 */
	private Partij partij;

	/**
	 * Default constructor die een leeg Burgemeester object aanmaakt met default waarden
	 */
	public Burgemeester() {
		this("Onbekend", LocalDate.of(0,1,1), "Onbekend", 0.0, 0, Partij.ONBEKEND);
	}

	/**
	 * Maakt een Burgemeester object aan met alle bijhorende attributen
	 * @param naam Naam van de burgemeester
	 * @param geboortedatum Geboortedatum van de burgemeester
	 * @param gemeente Gemeente waar de burgemeester bestuurt
	 * @param procentVoorkeursstemmen Percentage voorkeursstemmen
	 * @param termijnen Aantal termijnen dat de burgemeester reeds bestuurde
	 * @param partij Partij waar de burgemeester toe behoort
	 */
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

	/**
	 * Methode die bepaalt of twee objecten identiek zijn op basis van de naam en de gemeente
	 *
	 * @param o object om mee te vergelijken
	 * @return Aanduiding of de objecten identiek zijn
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Burgemeester that = (Burgemeester) o;
		return Objects.equals(getNaam(), that.getNaam()) &&
				Objects.equals(getGemeente(), that.getGemeente());
	}

	/**
	 * Genereert een hashcode die het object voorstelt, gegenereerd op basis van naam en gemeente
	 *
	 * @return Hashcode van het object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getNaam(), getGemeente());
	}

	/**
	 * Methode die twee objecten vergelijkt om te sorteren, op naam en gemeente
	 *
	 * @param o Object om mee te vergelijken
	 * @return Integerwaarde die het verschil tussen beide objecten voorstelt
	 */
	@Override
	public int compareTo(Burgemeester o) {
		if (!this.getNaam().equals(o.getNaam())) {
			return this.getNaam().compareTo(o.getNaam());
		} else {
			return this.getGemeente().compareTo(o.getGemeente());
		}
	}

	/**
	 * Methode die een stringrepresentatie van het Burgemeester object genereert
	 *
	 * @return Stringrepresentatie van het object
	 */
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
