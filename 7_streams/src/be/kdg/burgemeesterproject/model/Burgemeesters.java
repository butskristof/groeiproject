package be.kdg.burgemeesterproject.model;

import java.util.*;
import java.util.function.Function;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 3:29 PM
 */
public class Burgemeesters {
	private TreeSet<Burgemeester> elements = new TreeSet<>();

	public boolean voegToe(Burgemeester b) {
		return this.elements.add(b);
	}

	public boolean verwijder(String naam, String gemeente) {
		return this.elements.remove(this.zoek(naam, gemeente));
	}

	/**
	 *
	 * @param naam Naam van de te zoeken burgemeester
	 * @param gemeente Naam van de gemeente waar de burgemeester zit
	 * @return Burgemeester object of null
	 */
	public Burgemeester zoek(String naam, String gemeente) {
		for (Burgemeester b :
				this.elements) {
			if (b.getNaam().equals(naam) && b.getGemeente().equals(gemeente)) {
				return b;
			}
		}
		return null;
	}

	public int getAantal() {
		return this.elements.size();
	}

	public List<Burgemeester> gesorteerdOp(Function<Burgemeester, Comparable> f) {
		List<Burgemeester> ret = new ArrayList<>(this.elements);

		Collections.sort(ret, Comparator.comparing(f));

		return ret;
	}
}
