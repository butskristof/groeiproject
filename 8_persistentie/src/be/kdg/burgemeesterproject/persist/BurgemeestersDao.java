package be.kdg.burgemeesterproject.persist;

import be.kdg.burgemeesterproject.model.Burgemeester;

import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 29.11.18 10:46
 */
public interface BurgemeestersDao {
	boolean voegToe(Burgemeester b);

	boolean verwijder(String naam, String gemeente);

	Burgemeester zoek(String naam, String gemeente);

	List<Burgemeester> gesorteerdOpNaam();

	List<Burgemeester> gesorteerdOpGeboortedatum();

	List<Burgemeester> gesorteerdOpTermijnen();
}
