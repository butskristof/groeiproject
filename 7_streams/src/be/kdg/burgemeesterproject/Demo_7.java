package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.model.*;
import be.kdg.burgemeesterproject.util.*;

import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 27.11.18 12:09
 */
public class Demo_7 {
	public static void main(String[] args) {
		Burgemeesters burgemeesters = new Burgemeesters();
		for (Burgemeester b :
				Data.getData()) {
			burgemeesters.voegToe(b);
		}

		System.out.println("Burgemeesters sorteerd op naam: ");
		for (Burgemeester b :
				burgemeesters.gesorteerdOp(Burgemeester::getNaam)) {
			System.out.println(b);
		}

		System.out.println();

		System.out.println("Burgemeesters sorteerd op geboortedatum: ");
		for (Burgemeester b :
				burgemeesters.gesorteerdOp(Burgemeester::getGeboortedatum)) {
			System.out.println(b);
		}

		System.out.println();

		System.out.println("Burgemeesters sorteerd op termijnen: ");
		for (Burgemeester b :
				burgemeesters.gesorteerdOp(Burgemeester::getTermijnen)) {
			System.out.println(b);
		}

		System.out.println();

		List<Burgemeester> burgemeesterList = Data.getData();
		burgemeesterList = Functies.filteredList(burgemeesterList, b -> b.getTermijnen() > 0);
		burgemeesterList = Functies.filteredList(burgemeesterList, b -> b.getPartij().equals(Partij.NVA));
		burgemeesterList = Functies.filteredList(burgemeesterList, b -> b.getProcentVoorkeursstemmen() >= 0.3);

		System.out.println("Toepassing driemaal filteredList met telkens een ander Predicate");
		System.out.println("Alle NVA-burgemeesters met meer dan een termijn en minstens 30% van de voorkeursstemmen: ");
		burgemeesterList.forEach(System.out::println);

		System.out.println();

		burgemeesterList = Data.getData();
		System.out.printf("Gemiddeld aantal termijnen: %.1f%n",
				Functies.average(burgemeesterList, Burgemeester::getTermijnen));

		burgemeesterList = Data.getData();
		System.out.printf("Gemiddeld percentage voorkeursstemmen: %.1f%n",
				Functies.average(burgemeesterList, Burgemeester::getProcentVoorkeursstemmen));

		burgemeesterList = Data.getData();
		System.out.printf("Gemiddelde leeftijd: %.1f%n",
				Functies.average(burgemeesterList, Burgemeester::getAge));

		System.out.println();

		System.out.printf("Aantal burgemeester met percentage voorkeursstemmen > 35%%: %d%n",
				Functies.countIf(burgemeesterList, b -> b.getProcentVoorkeursstemmen() > 0.35));
		System.out.printf("Aantal burgemeester met meer dan twee termijnen: %d%n",
				Functies.countIf(burgemeesterList, b -> b.getTermijnen() > 2));
		System.out.printf("Aantal burgemeester van CD&V: %d%n",
				Functies.countIf(burgemeesterList, b -> b.getPartij().equals(Partij.CDENV)));
	}
}
