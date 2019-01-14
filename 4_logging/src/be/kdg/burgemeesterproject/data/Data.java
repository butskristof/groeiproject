package be.kdg.burgemeesterproject.data;

import be.kdg.burgemeesterproject.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 4:01 PM
 */
public class Data {
	//	Maak een klasse Data in een aparte data-package. Daarin is er een static methode die een gevulde List retourneert. Voorzie minstens 15 elementen met realistische en uiteenlopende data.
//			→In ons voorbeeld dus een List met 15 Dictator-objecten.
	public static List<Burgemeester> getData() {
		List<Burgemeester> ret = new ArrayList<>();

//	public Burgemeester(String naam, LocalDate geboortedatum, String gemeente, double procentVoorkeursstemmen, int termijnen, Partij partij) {
		ret.add(new Burgemeester("Van den Sande Joppe", LocalDate.of(1997, 6, 20), "Heist-op-den-Berg", 0.295, 0, Partij.CDENV));
		ret.add(new Burgemeester("Tobback Louis", LocalDate.of(1938, 5, 3), "Leuven", 0.314, 4, Partij.SPA));
		ret.add(new Burgemeester("De Wever Bart", LocalDate.of(1970, 12, 21), "Antwerpen", 0.377, 1, Partij.NVA));
		ret.add(new Burgemeester("Peeters Jan", LocalDate.of(1963,1,12), "Herentals", 0.253, 3, Partij.SPA));
		ret.add(new Burgemeester("Claes Hilde", LocalDate.of(1967,10,27), "Hasselt", 0.33, 2, Partij.LOKAAL));

		ret.add(new Burgemeester("Heremans Patrick", LocalDate.of(1964, 7, 26), "Herenthout", 0.332, 1, Partij.LOKAAL));
		ret.add(new Burgemeester("Somers Bart", LocalDate.of(1964,5,12), "Mechelen", 0.339, 3, Partij.OPENVLD));
		ret.add(new Burgemeester("Celis Vera", LocalDate.of(1959,8,6), "Geel", 0.366, 1, Partij.NVA));
		ret.add(new Burgemeester("Rombouts Tinne", LocalDate.of(1979,9,29), "Hoogstraten", 0.321, 1, Partij.CDENV));
		ret.add(new Burgemeester("Van de Vyver Andre", LocalDate.of(1949,8,1), "Zwijndrecht", 0.314, 1, Partij.GROEN));

		ret.add(new Burgemeester("De Wispelaere Chris", LocalDate.of(1947,7,15), "Lovendegem", 0.386, 4, Partij.CDENV));
		ret.add(new Burgemeester("Van Quickenborne Vincent", LocalDate.of(1973,8,1), "Kortrijk", 0.33, 1, Partij.OPENVLD));
		ret.add(new Burgemeester("Gielis Tine", LocalDate.of(1967,8,16), "Laakdal", 0.35, 2, Partij.CDENV));
		ret.add(new Burgemeester("Boogaerts Frank", LocalDate.of(1944,11,16), "Lier", 0.32, 1, Partij.NVA));
		ret.add(new Burgemeester("Horemans Walter", LocalDate.of(1961,3,15), "Berlaar", 0.489, 2, Partij.CDENV));

		return Collections.unmodifiableList(ret);
	}
}
