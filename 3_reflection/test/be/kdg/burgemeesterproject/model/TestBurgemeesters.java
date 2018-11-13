package be.kdg.burgemeesterproject.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 9:27 PM
 */
public class TestBurgemeesters {
	private Burgemeesters burgemeesters;
	private Burgemeester b1;
	private Burgemeester b2;

	@Before
	public void setUp() throws Exception {
		this.burgemeesters = new Burgemeesters();
		b1 = new Burgemeester();
		b2 = new Burgemeester("Van den Sande Joppe", LocalDate.of(1997, 6, 20), "Heist-op-den-Berg", 0.295, 0, Partij.CDENV);
	}

	/*
	Voorzie een methode om het toevoegen uit te testen
	 */
	@Test
	public void testInsert() {
		try {
			assertTrue("Insert mislukt: false ontvangen van methode voegToe", burgemeesters.voegToe(b1));
			assertTrue("Insert mislukt: false ontvangen van methode voegToe", burgemeesters.voegToe(b2));
		} catch (Exception e) {
			fail("Er trad een exception op: " + e.getMessage());
		}
	}

	/*
	Voorzie een methode om het verwijderen uit te proberen
	 */
	@Test
	public void testRemove() {
		burgemeesters.voegToe(b1);
		burgemeesters.voegToe(b2);
		try {
			assertTrue("Remove mislukt: false ontvangen van methode voor bestaand object", burgemeesters.verwijder("Onbekend", "Onbekend"));
//			assertFalse("Remove mislukt: dit object zou al verwijderd moeten zijn in de vorige test", burgemeesters.verwijder("Onbekend", "Onbekend"));
//			assertFalse("Dit object zou niet mogen bestaan", burgemeesters.verwijder("Jos Peeters", "Antwerpen"));
			assertTrue("Zou moeten bestaan", burgemeesters.verwijder("Van den Sande Joppe", "Heist-op-den-Berg"));

		} catch (Exception e) {
			fail("Er trad een exception op: " + e.getMessage());
		}
	}

	@Test(expected = NullPointerException.class)
	public void testRemovedEarlier() {
		burgemeesters.voegToe(b1);
		assertTrue("Remove mislukt: false ontvangen van methode voor bestaand object", burgemeesters.verwijder("Onbekend", "Onbekend"));
		assertFalse("Remove mislukt: dit object zou al verwijderd moeten zijn in de vorige test", burgemeesters.verwijder("Onbekend", "Onbekend"));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveNotExisting() {
		assertFalse("Dit object zou niet mogen bestaan", burgemeesters.verwijder("Jos Peeters", "Antwerpen"));
	}
}