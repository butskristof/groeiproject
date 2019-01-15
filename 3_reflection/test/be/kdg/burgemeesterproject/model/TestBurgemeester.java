package be.kdg.burgemeesterproject.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 9:09 PM
 */
public class TestBurgemeester {
	private Burgemeester b1;
	private Burgemeester b2;

	@Before
	public void setUp() {
		b1 = new Burgemeester();
		b2 = new Burgemeester("Van den Sande Joppe", LocalDate.of(1997, 6, 20), "Heist-op-den-Berg", 0.295, 0, Partij.CDENV);
	}

	/*
	Schrijf een methode testEquals, waarin je de equals methode van je basisklasse uittest. Je moet een positief (2 gelijke objecten) en een negatief (twee ongelijke objecten) geval in dezelfde methode testen.
	 */

	@Test
	public void testEquals() {
		assertEquals("Beide objecten zouden hetzelfde moeten zijn.", b1, new Burgemeester());
		assertEquals("Beide objecten zouden hetzelfde moeten zijn.", b2, new Burgemeester("Van den Sande Joppe", LocalDate.of(1997, 6, 20), "Heist-op-den-Berg", 0.295, 0, Partij.CDENV));
		b2.setPartij(Partij.ONBEKEND);
		assertEquals("Beide objecten zouden nog steeds hetzelfde moeten zijn aangezien niet op partij vergeleken wordt.", b2, new Burgemeester("Van den Sande Joppe", LocalDate.of(1997, 6, 20), "Heist-op-den-Berg", 0.295, 0, Partij.CDENV));

		assertNotEquals("Dit zijn totaal verschillende objecten.", b1, b2);
	}

	/*
	Schrijf een methode testOngeldig waarin je een ongeldige waarde aan een setter van de basisklasse meegeeft en dus een IllegalArgumentException verwacht (zie: Groeiproject_1 > Opdracht 2.2). Vergeet de fail niet!
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testOngeldig() {
		b1.setTermijnen(-5);
		fail("Er mag geen negatieve waarden aan het aantal termijnen worden toegekend.");
	}

	/*
	Schrijf een methode testGeldig waarin je via een setter een correcte waarde instelt en er dus GEEN IllegalArgumentException mag optreden.
	 */
	@Test
	public void testGeldig() {
		try {
			b1.setTermijnen(2);
		} catch (IllegalArgumentException e) {
			fail("Er trad een exception op: " + e.getMessage());
		}
	}

	/*
	Schrijf een methode testCompareTo waarin je de sorteervolgorde uittest
	 */
	@Test
	public void testCompareTo() {
		int diff1 = b1.compareTo(b2);
		int diff2 = b2.compareTo(b1);
		assertTrue("'Onbekend' komt voor 'Van den Sande Joppe'", diff1 < diff2);
	}

	/*
	Schrijf ook een testmethode waarin je assertEquals gebruikt om de gelijkheid van 2 doublewaarden te vergelijken met als laatste parameter een delta-waarde als tolerantie.
	 */
	@Test
	public void testEqualsDouble() {
		assertEquals("Waarden moeten binnen de opgegeven tolerantie liggen.",
				b2.getProcentVoorkeursstemmen(), 0.299, 0.005 );
	}
}