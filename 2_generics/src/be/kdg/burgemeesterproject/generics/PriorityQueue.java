package be.kdg.burgemeesterproject.generics;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Klasse die een PriorityQueue implementeert zoals beschreven in de opdracht. Volgt hiervoor de FIFOQueue interface.
 *
 * @author Kristof Buts
 * @version 1.0 11/12/18 4:23 PM
 * @see <a href="https://en.wikipedia.org/wiki/Priority_queue">Priority Queue (Wikipedia)</a>
 */
public class PriorityQueue<T> implements FIFOQueue<T> {
	private Map<Integer, LinkedList<T>> elements = new TreeMap<>(Comparator.reverseOrder());

	/**
	 * Voegt een element toe. Er wordt eerst nagegaan of het object al aanwezig is. Indien niet, wordt het bij de
	 * gewenste prioriteit toegevoegd.
	 *
	 * @param element Object dat moet worden toegevoegd
	 * @param priority Gewenste prioriteit
	 * @return Aanduiding of het object correct is toegevoegd
	 */
	@Override
	public boolean enqueue(T element, int priority) {
		if (this.search(element) == -1) {
			// check if list is already created for this priority
			// we don't create them beforehand, lazy loading
			if (this.elements.get(priority) == null) {
				this.elements.put(priority, new LinkedList<>());
			}

			return this.elements.get(priority).add(element);
		} else {
			return false;
		}
	}

	/**
	 * Onttrek het element met een zo hoog mogelijke prioriteit dat al het langst in de queue zit
	 *
	 * @return Object met hoogste prioriteit, volgens FIFO
	 */
	@Override
	public T dequeue() {
		for (LinkedList<T> list : this.elements.values()) {
			if (list != null && !list.isEmpty()) {
				return list.removeFirst();
			}
		}

		return null;
	}

	/**
	 * Bepaal de positie in de queue
	 *
	 * @param element Te zoeken object
	 * @return Positie in de queue, -1 bij niet gevonden
	 */
	@Override
	public int search(T element) {
		int elementsPassed = 0;
		for (LinkedList<T> list : this.elements.values()) {
			if (list != null && !list.isEmpty() && list.contains(element)) {
				return elementsPassed + list.indexOf(element) + 1;
			} else {
				elementsPassed += list.size();
			}
		}

		return -1;
	}

	/**
	 * Bereken de totale grootte van de queue
	 *
	 * @return Totaal aantal elementen
	 */
	@Override
	public int getSize() {
		int totalElements = 0;
		for (LinkedList<T> list : this.elements.values()) {
			if (list != null) {
				totalElements += list.size();
			}
		}

		return totalElements;
	}

	/**
	 * Methode die een stringrepresentatie van het PriorityQueue object genereert
	 *
	 * @return Stringrepresentatie van het object
	 */
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();

		for (Map.Entry<Integer, LinkedList<T>> entry : this.elements.entrySet()) {
			LinkedList<T> currentList = entry.getValue();
			if (currentList != null) {
				currentList.forEach(el -> ret.append(String.format("%d: %s%n", entry.getKey(), el.toString())));
			}
		}

		return ret.toString();
	}
}

