package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.model.Partij;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 4:05 PM
 */
public class Demo_1 {

	public static void main(String[] args) {

		// use debugger for checking object values until we can use JUnit

//		a. Vraag een gevulde datalist op (zie 4.1)
		List<Burgemeester> rawData = Data.getData();

//		b. Voeg alle objecten van deze list toe aan de multiklasse (zie 3.2)
		Burgemeesters burgemeesters = new Burgemeesters();
		for (Burgemeester b : rawData) {
			burgemeesters.voegToe(b);
		}

//		c. Voeg ook eens een dubbel object toe (dat zou niet mogen lukken; zie 2.3)
		burgemeesters.voegToe(rawData.get(3));

//		d. Test de methoden zoek, verwijder en getAantal uit (zie 3.3)
		// test ok
		Burgemeester testSearch = burgemeesters.zoek("Tobback Louis", "Leuven");
		// test nok
		testSearch = burgemeesters.zoek("De Witte", "Zichem");
		boolean isDeleted = burgemeesters.verwijder("Tobback Louis", "Leuven");
		isDeleted = burgemeesters.verwijder("De Witte", "Zichem");

		burgemeesters.voegToe(rawData.get(1));

//		e. Druk de 3 gesorteerde listen af (zie 3.3)
		for (Burgemeester b : burgemeesters.gesorteerdOpNaam()) {
			System.out.println(b.toString());
		}

		System.out.println();

		for (Burgemeester b : burgemeesters.gesorteerdOpGeboortedatum()) {
			System.out.println(b.toString());
		}

		System.out.println();

		for (Burgemeester b : burgemeesters.gesorteerdOpTermijnen()) {
			System.out.println(b.toString());
		}
		System.out.println();

//		f. Test beide constructors uit en ook de IllegalArgumentException (zie 2.2)
		Burgemeester testCtor = new Burgemeester();
		testCtor = new Burgemeester("Jos Peeters", LocalDate.of(1980,1,1), "Zaffelare", 0.235, 0, Partij.LOKAAL);

		// exceptions
		// voorkeursstemmen
//		testCtor = new Burgemeester("Peeters Jos", LocalDate.of(1980,1,1), "Zaffelare", 23.5, 0, Partij.LOKAAL);
		// naam
//		testCtor = new Burgemeester("J", LocalDate.of(1980,1,1), "Zaffelare", 0.235, 0, Partij.LOKAAL);
		// geboortedatum
//		testCtor = new Burgemeester("Peeters Jos", LocalDate.of(2005,1,1), "Zaffelare", 0.235, 0, Partij.LOKAAL);
		// gemeente
//		testCtor = new Burgemeester("Peeters Jos", LocalDate.of(1980,1,1), "Z", 0.235, 0, Partij.LOKAAL);
		// termijnen
//		testCtor = new Burgemeester("Peeters Jos", LocalDate.of(1980,1,1), "Zaffelare", 0.235, -2, Partij.LOKAAL);
	}
}
