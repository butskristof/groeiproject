package be.kdg.burgemeesterproject.patterns;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.model.BurgemeestersInterface;

import java.util.List;
import java.util.Observable;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 10:41 AM
 */
public class ObservableBurgemeesters extends Observable implements BurgemeestersInterface {
	private Burgemeesters burgemeesters;

	public ObservableBurgemeesters(Burgemeesters burgemeesters) {
		this.burgemeesters = burgemeesters;
	}

	@Override
	public boolean voegToe(Burgemeester b) {
		String update = "Toegevoegd: " + b.toString();
		boolean result = burgemeesters.voegToe(b);
		if (result) {
			setChanged();
			notifyObservers(update);
		}
		return result;
	}

	@Override
	public boolean verwijder(String naam, String gemeente) {
		String update = "Verwijderd: " + this.zoek(naam, gemeente).toString();
		boolean result = burgemeesters.verwijder(naam, gemeente);
		if (result) {
			setChanged();
			notifyObservers(update);
		}
		return result;
	}

	@Override
	public Burgemeester zoek(String naam, String gemeente) {
		return burgemeesters.zoek(naam, gemeente);
	}

	@Override
	public int getAantal() {
		return burgemeesters.getAantal();
	}

	@Override
	public List<Burgemeester> gesorteerdOpNaam() {
		return burgemeesters.gesorteerdOpNaam();
	}

	@Override
	public List<Burgemeester> gesorteerdOpGeboortedatum() {
		return burgemeesters.gesorteerdOpGeboortedatum();
	}

	@Override
	public List<Burgemeester> gesorteerdOpTermijnen() {
		return burgemeesters.gesorteerdOpTermijnen();
	}
}
