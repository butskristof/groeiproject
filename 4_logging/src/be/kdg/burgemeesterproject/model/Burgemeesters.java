package be.kdg.burgemeesterproject.model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 3:29 PM
 */
public class Burgemeesters {
	private static Logger logger = Logger.getLogger(Burgemeesters.class.getName());

	private static TreeSet<Burgemeester> elements = new TreeSet<>();

	static {
		logger.setLevel(Level.FINER);
	}

	public Burgemeesters() {
		logger.setLevel(Level.FINER);
	}

	public boolean voegToe(Burgemeester b) {
		if (this.elements.add(b)) {
			logger.log(Level.FINER, "Toegevoegd: {0}", b.getNaam());
			return true;
		} else {
			return false;
		}
	}

	public boolean verwijder(String naam, String gemeente) {
		Burgemeester search = this.zoek(naam, gemeente);
		if (search == null) {
			return false;
		}

		if (this.elements.remove(search)) {
			logger.log(Level.FINER, "Verwijderd: {0}", search.getNaam());
			return true;
		} else {
			return false;
		}
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
