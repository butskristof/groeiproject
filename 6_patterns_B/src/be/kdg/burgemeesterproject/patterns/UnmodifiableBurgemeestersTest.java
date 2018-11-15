package be.kdg.burgemeesterproject.patterns;

import be.kdg.burgemeesterproject.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 5:49 PM
 */
public class UnmodifiableBurgemeestersTest {
	private UnmodifiableBurgemeesters burgemeesters;

	@Before
	public void setUp() throws Exception {
		Burgemeesters bb = new Burgemeesters();
		bb.voegToe(new Burgemeester("Van den Sande Joppe", LocalDate.of(1997, 6, 20), "Heist-op-den-Berg", 0.295, 0, Partij.CDENV));
		bb.voegToe(new Burgemeester("Louis Tobback", LocalDate.of(1938, 5, 3), "Leuven", 0.314, 4, Partij.SPA));
		bb.voegToe(new Burgemeester("De Wever Bart", LocalDate.of(1970, 12, 21), "Antwerpen", 0.377, 1, Partij.NVA));
		bb.voegToe(new Burgemeester("Peeters Jan", LocalDate.of(1963,1,12), "Herentals", 0.253, 3, Partij.SPA));
		bb.voegToe(new Burgemeester("Claes Hilde", LocalDate.of(1967,10,27), "Hasselt", 0.33, 2, Partij.LOKAAL));
		burgemeesters = new UnmodifiableBurgemeesters(bb);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testInsert() {
		burgemeesters.voegToe(new Burgemeester());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		burgemeesters.verwijder("Claes Hilde", "Hasselt");
	}

	@Test
	public void testSearch() {
		assertEquals(burgemeesters.zoek("Claes Hilde", "Hasselt"), new Burgemeester("Claes Hilde", LocalDate.of(1967,10,27), "Hasselt", 0.33, 2, Partij.LOKAAL));
	}

	@Test
	public void testGetCount() {
		assertEquals(burgemeesters.getAantal(), 5);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUmodifiableReturnList1() {
		List<Burgemeester> ret1 = burgemeesters.gesorteerdOpNaam();
		ret1.add(new Burgemeester());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUmodifiableReturnList2() {
		List<Burgemeester> ret2 = burgemeesters.gesorteerdOpGeboortedatum();
		ret2.add(new Burgemeester());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUmodifiableReturnList3() {
		List<Burgemeester> ret3 = burgemeesters.gesorteerdOpTermijnen();
		ret3.add(new Burgemeester());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUmodifiableReturnList4() {
		List<Burgemeester> ret1 = burgemeesters.gesorteerdOpNaam();
		ret1.remove(1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUmodifiableReturnList5() {
		List<Burgemeester> ret1 = burgemeesters.gesorteerdOpGeboortedatum();
		ret1.remove(ret1.get(1));
	}
}