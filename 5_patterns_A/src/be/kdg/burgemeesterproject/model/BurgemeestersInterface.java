package be.kdg.burgemeesterproject.model;

import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 10:40 AM
 */
public interface BurgemeestersInterface {
	boolean voegToe(Burgemeester b);

	boolean verwijder(String naam, String gemeente);

	Burgemeester zoek(String naam, String gemeente);

	int getAantal();

	List<Burgemeester> gesorteerdOpNaam();

	List<Burgemeester> gesorteerdOpGeboortedatum();

	List<Burgemeester> gesorteerdOpTermijnen();
}
