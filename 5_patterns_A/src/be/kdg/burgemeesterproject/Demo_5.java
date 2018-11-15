package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.BurgemeesterFactory;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.patterns.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 10:49 AM
 */
public class Demo_5 {
	public static void main(String[] args) {
		// observer test
		System.out.println("Observer test");
		System.out.println("=======================================");
		Burgemeesters burgemeesters = new Burgemeesters();
		ObservableBurgemeesters ob = new ObservableBurgemeesters(burgemeesters);
		BurgemeestersObserver burgemeestersObserver = new BurgemeestersObserver();
		ob.addObserver(burgemeestersObserver);
		ob.voegToe(BurgemeesterFactory.newEmptyBurgemeester());
		ob.verwijder("Onbekend", "Onbekend");
		System.out.println();
		System.out.println();

		// factory test
		System.out.println("Factory test");
		System.out.println("=======================================");

		System.out.println("Empty Burgemeester");
		System.out.println(BurgemeesterFactory.newEmptyBurgemeester().toString());
		System.out.println();

		final int randomsToGenerate = 30;
		System.out.printf("%d random Burgemeester objects%n", randomsToGenerate);

		List<Burgemeester> randomBurgemeesters = new ArrayList<>(); // will only traverse, linked is more efficient
		for (int i = 0; i < randomsToGenerate; ++i) {
			randomBurgemeesters.add(BurgemeesterFactory.newRandomBurgemeester());
		}

		// print them out
		for (int i = 0; i < randomBurgemeesters.size(); ++i) {
			System.out.printf("%2d) %s%n", i+1, randomBurgemeesters.get(i).toString());
		}

	}
}
