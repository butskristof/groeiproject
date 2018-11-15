package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.patterns.*;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 10:49 AM
 */
public class Demo_5 {
	public static void main(String[] args) {
		Burgemeesters burgemeesters = new Burgemeesters();
		ObservableBurgemeesters ob = new ObservableBurgemeesters(burgemeesters);
		BurgemeestersObserver burgemeestersObserver = new BurgemeestersObserver();
		ob.addObserver(burgemeestersObserver);
		ob.voegToe(new Burgemeester());
		ob.verwijder("Onbekend", "Onbekend");
	}
}
