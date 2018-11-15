package be.kdg.burgemeesterproject.patterns;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 10:47 AM
 */
public class BurgemeestersObserver implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		System.out.printf("Observer meldt: %s%n", arg);
	}
}
