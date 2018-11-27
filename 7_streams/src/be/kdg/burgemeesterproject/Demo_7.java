package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.model.*;
import be.kdg.burgemeesterproject.util.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Kristof Buts
 * @version 1.0 27.11.18 12:09
 */
public class Demo_7 {
	public static void main(String[] args) {
		Burgemeesters burgemeesters = new Burgemeesters();
//		for (Burgemeester b :
//				Data.getData()) {
//			burgemeesters.voegToe(b);
//		}
		Data.getData().forEach(burgemeesters::voegToe);

		System.out.println("Burgemeesters sorteerd op naam: ");
//		for (Burgemeester b :
//				burgemeesters.gesorteerdOp(Burgemeester::getNaam)) {
//			System.out.println(b);
//		}
		burgemeesters.gesorteerdOp(Burgemeester::getNaam).forEach(System.out::println);

		System.out.println();

		System.out.println("Burgemeesters sorteerd op geboortedatum: ");
//		for (Burgemeester b :
//				burgemeesters.gesorteerdOp(Burgemeester::getGeboortedatum)) {
//			System.out.println(b);
//		}
		burgemeesters.gesorteerdOp(Burgemeester::getGeboortedatum).forEach(System.out::println);

		System.out.println();

		System.out.println("Burgemeesters sorteerd op termijnen: ");
//		for (Burgemeester b :
//				burgemeesters.gesorteerdOp(Burgemeester::getTermijnen)) {
//			System.out.println(b);
//		}
		burgemeesters.gesorteerdOp(Burgemeester::getTermijnen).forEach(System.out::println);

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

		System.out.println();


		// STREAMS
		burgemeesterList = Data.getData();
		System.out.printf("Aantal burgemeesters van Open VLD: %d%n",
				burgemeesterList.stream().filter(b -> b.getPartij().equals(Partij.OPENVLD)).count());

		System.out.println();

		System.out.println("Burgemeesters gesorteerd op partij en geboortedatum: ");
		burgemeesterList
				.stream()
				.sorted(Comparator.comparing(Burgemeester::getPartij).thenComparing(Burgemeester::getGeboortedatum))
				.forEach(System.out::println);

		System.out.println();

		System.out.println("Alle gemeenten in hoofdletters, omgekeerd gesorteerd en zonder dubbels: ");
		String v33 = burgemeesterList
				.stream()
				.map(b -> b.getGemeente().toUpperCase())
				.sorted(Comparator.reverseOrder())
				.distinct()
				.collect(Collectors.joining(", "));
		System.out.println(v33);

		System.out.println();

		Optional<Burgemeester> randB = burgemeesterList.stream().filter(b -> b.getProcentVoorkeursstemmen() > 0.35).findAny();
		System.out.printf("Een willekeurige burgemeester met meer dan 35%% voorkeursstemmen: %n%s%n",
				randB.isPresent() ? randB.get() : "NIET GEVONDEN");

		System.out.println();

		System.out.printf("Burgemeester met de meeste voorkeursstemmen: %n%s%n",
				burgemeesterList.stream().max(Comparator.comparing(Burgemeester::getProcentVoorkeursstemmen)).get());

		System.out.println();

		System.out.printf("List met gesorteerde burgemeesternamen die beginnen met 'V': %n%s%n",
				burgemeesterList.stream().filter(b -> b.getNaam().charAt(0) == 'V').map(Burgemeester::getNaam).sorted().collect(Collectors.toList()));

		System.out.println();

		Map<Boolean, List<Burgemeester>> map =
			burgemeesterList
			.stream()
			.collect(Collectors.partitioningBy(b -> b.getAge() > 45));
		System.out.println("Sublist met burgemeesters ouder dan 45 jaar: ");
		map.get(true).forEach(System.out::println);
		System.out.println();
		System.out.println("Sublist met burgemeesters jonger dan 45 jaar: ");
		map.get(false).forEach(System.out::println);
	}
}
