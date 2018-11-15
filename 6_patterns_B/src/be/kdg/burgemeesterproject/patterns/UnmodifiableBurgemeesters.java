package be.kdg.burgemeesterproject.patterns;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import be.kdg.burgemeesterproject.model.BurgemeestersInterface;

import java.util.Collections;
import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 5:31 PM
 */
public class UnmodifiableBurgemeesters implements BurgemeestersInterface {
	private Burgemeesters burgemeesters;

	public UnmodifiableBurgemeesters(Burgemeesters burgemeesters) {
		this.burgemeesters = burgemeesters;
	}

	/*
	Alle methoden die iets willen wijzigen aan de data van de multiklasse worden afgeblokt. Doe daar een throw van een UnsupportedOperationException
	 */

	@Override
	public boolean voegToe(Burgemeester b) {
		throw new UnsupportedOperationException("Unmodifiable: lijst kan niet worden gewijzigd");
	}

	@Override
	public boolean verwijder(String naam, String gemeente) {
		throw new UnsupportedOperationException("Unmodifiable: lijst kan niet worden gewijzigd");
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
		return Collections.unmodifiableList(burgemeesters.gesorteerdOpNaam());
	}

	@Override
	public List<Burgemeester> gesorteerdOpGeboortedatum() {
		return Collections.unmodifiableList(burgemeesters.gesorteerdOpGeboortedatum());
	}

	@Override
	public List<Burgemeester> gesorteerdOpTermijnen() {
		return Collections.unmodifiableList(burgemeesters.gesorteerdOpTermijnen());
	}
}
