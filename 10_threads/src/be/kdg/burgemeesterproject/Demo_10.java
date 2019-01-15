package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Partij;
import be.kdg.burgemeesterproject.threading.BurgemeesterRunnable;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Kristof Buts
 */
public class Demo_10 {
	private static final int TESTCOUNT = 100;
	private static List<Predicate<Burgemeester>> predicates;
	private static List<BurgemeesterRunnable> runnables;
	private static List<Thread> threads;

	public static void main(String[] args) {
		// detect nr of cores
		int nrOfCores = Runtime.getRuntime().availableProcessors();

		// create predicates
		predicates = new ArrayList<>();
		predicates.add(burgemeester -> {
				LocalDate dob = burgemeester.getGeboortedatum();
				Period diff = Period.between(LocalDate.now(), dob);
				return Math.abs(diff.getYears()) > 45;
			});
		predicates.add(burgemeester -> burgemeester.getPartij() != Partij.LOKAAL && burgemeester.getPartij() != Partij.ONBEKEND);
		predicates.add(burgemeester -> burgemeester.getTermijnen() > 2);
		predicates.add(burgemeester -> burgemeester.getProcentVoorkeursstemmen() >= 0.3);


		try {
			double totalTime = 0.0;
			for (int i = 0; i < TESTCOUNT; ++i) {
				threads = new ArrayList<>();
				runnables = new ArrayList<>();
				for (int j = 0; j < nrOfCores; ++j) {
					BurgemeesterRunnable r = new BurgemeesterRunnable(predicates.get(j));
					runnables.add(r);
					threads.add(new Thread(r));
				}

				long start = System.currentTimeMillis();
				for (Thread t: threads) {
					t.start();
					t.join();
				}
				long end = System.currentTimeMillis();
				long diff = end - start;
				totalTime += diff;

				for (BurgemeesterRunnable r: runnables) {
					r.getList().stream().limit(5).forEach(System.out::println);
				}
			}

			double avgTime = totalTime / TESTCOUNT;

			System.out.printf("4 threads verzamelden elke 1000 Burgemeester objecten. Gemiddelde uit %d runs: %.1fms%n",
					TESTCOUNT, avgTime);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
