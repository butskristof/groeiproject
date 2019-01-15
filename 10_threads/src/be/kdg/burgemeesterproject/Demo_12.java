package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Partij;
import be.kdg.burgemeesterproject.threading.BurgemeesterCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * @author Kristof Buts
 */
public class Demo_12 {
	private static final int TESTCOUNT = 100;
	private static List<Predicate<Burgemeester>> predicates;
	private static final int NR_OF_THREADS = 3;

	public static void main(String[] args) {
		predicates = new ArrayList<>();
		predicates.add(burgemeester -> burgemeester.getPartij() != Partij.LOKAAL && burgemeester.getPartij() != Partij.ONBEKEND);
		predicates.add(burgemeester -> burgemeester.getTermijnen() > 2);
		predicates.add(burgemeester -> burgemeester.getProcentVoorkeursstemmen() >= 0.3);

		try {
			double totalTime = 0.0;
			for (int i = 0; i < TESTCOUNT; ++i) {
				ExecutorService executor = Executors.newFixedThreadPool(NR_OF_THREADS);

				long start = System.currentTimeMillis();
				List<Future<List<Burgemeester>>> list = new ArrayList<>();
				for (int j = 0; j < NR_OF_THREADS; ++j) {
					BurgemeesterCallable callable = new BurgemeesterCallable(predicates.get(j));
					Future<List<Burgemeester>> future = executor.submit(callable);
					list.add(future);
				}

				for (Future<List<Burgemeester>> future: list) {
					future.get();
				}

				long end = System.currentTimeMillis();
				long diff = end - start;
				totalTime += diff;
			}

			double avgTime = totalTime / TESTCOUNT;

			System.out.printf("%d Futures verzamelden elk 1000 Burgemeester objecten. Gemiddelde uit %d runs: %.1fms%n",
					NR_OF_THREADS, TESTCOUNT, avgTime);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
