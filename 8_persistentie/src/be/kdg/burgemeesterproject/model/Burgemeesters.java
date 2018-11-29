package be.kdg.burgemeesterproject.model;

import java.io.Serializable;
import java.util.*;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 3:29 PM
 */
public class Burgemeesters implements Serializable, be.kdg.burgemeesterproject.persist.BurgemeestersDao {
	private TreeSet<Burgemeester> elements = new TreeSet<>();

	@Override
	public boolean voegToe(Burgemeester b) {
		return this.elements.add(b);
	}

	@Override
	public boolean verwijder(String naam, String gemeente) {
		return this.elements.remove(this.zoek(naam, gemeente));
	}

	/**
	 *
	 * @param naam Naam van de te zoeken burgemeester
	 * @param gemeente Naam van de gemeente waar de burgemeester zit
	 * @return Burgemeester object of null
	 */
	@Override
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

	@Override
	public List<Burgemeester> gesorteerdOpNaam() {
		List<Burgemeester> ret = new ArrayList<>(this.elements);
		Collections.sort(ret, new Comparator<Burgemeester>() {
			@Override
			public int compare(Burgemeester o1, Burgemeester o2) {
				return o1.getNaam().compareTo(o2.getNaam());
			}
		});
		return ret;
	}

	@Override
	public List<Burgemeester> gesorteerdOpGeboortedatum() {
		List<Burgemeester> ret = new ArrayList<>(this.elements);
		Collections.sort(ret, new Comparator<Burgemeester>() {
			@Override
			public int compare(Burgemeester o1, Burgemeester o2) {
				return o1.getGeboortedatum().compareTo(o2.getGeboortedatum());
			}
		});
		return ret;
	}

	@Override
	public List<Burgemeester> gesorteerdOpTermijnen() {
		List<Burgemeester> ret = new ArrayList<>(this.elements);
		Collections.sort(ret, new Comparator<Burgemeester>() {
			@Override
			public int compare(Burgemeester o1, Burgemeester o2) {
				return o1.getTermijnen() - o2.getTermijnen();
			}
		});
		return ret;
	}
}
