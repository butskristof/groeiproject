package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.BurgemeesterFactory;
import be.kdg.burgemeesterproject.model.Partij;
import be.kdg.burgemeesterproject.threading.BurgemeesterAttacker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Kristof Buts
 */
public class Demo_11 {
	private static List<Predicate<Burgemeester>> predicates;
	private static List<BurgemeesterAttacker> runnables;
	private static List<Thread> threads;

	public static void main(String[] args) {
		List<Burgemeester> generated = new ArrayList<>();
		Stream.generate(BurgemeesterFactory::newRandomBurgemeester)
				.limit(1000)
				.forEach(generated::add);

		predicates = new ArrayList<>();
		predicates.add(burgemeester -> burgemeester.getPartij() != Partij.LOKAAL && burgemeester.getPartij() != Partij.ONBEKEND);
		predicates.add(burgemeester -> burgemeester.getTermijnen() > 2);
		predicates.add(burgemeester -> burgemeester.getProcentVoorkeursstemmen() >= 0.3);

		threads = new ArrayList<>();
		runnables = new ArrayList<>();
		for (int j = 0; j < 3; ++j) {
			BurgemeesterAttacker r = new BurgemeesterAttacker(generated, predicates.get(j));
			runnables.add(r);
			Thread t = new Thread(r, String.valueOf(j));
			threads.add(t);
			t.start();
		}

		try {
			for (Thread t: threads) {
				t.join();
			}

			System.out.println("Na uitzuivering:");
			System.out.printf("%-25s: %d%n", "Aantal niet van nationale partij", generated.stream().filter(predicates.get(0)).count());
			System.out.printf("%-25s: %d%n", "Aantal met minder dan twee termijnen", generated.stream().filter(predicates.get(1)).count());
			System.out.printf("%-25s: %d%n", "Aantal met minder dan 30% voorkeursstemmen", generated.stream().filter(predicates.get(2)).count());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
