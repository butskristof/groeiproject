package be.kdg.burgemeesterproject.generics;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 4:23 PM
 */
public class PriorityQueue<T> implements FIFOQueue<T> {
	private TreeMap<Integer, LinkedList<T>> elements = new TreeMap<>(Comparator.reverseOrder());

	@Override
	public boolean enqueue(T element, int priority) {
		// TODO won't work
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

	@Override
	public T dequeue() {
		for (LinkedList<T> list :
				this.elements.values()) {
			if (list != null && !list.isEmpty()) {
				return list.removeFirst();
			}
		}

		return null;
	}

	@Override
	public int search(T element) {
		int elementsPassed = 0;
		for (LinkedList<T> list :
				this.elements.values()) {
			if (list != null && !list.isEmpty() && list.contains(element)) {
				return elementsPassed + list.indexOf(element) + 1;
			} else {
				elementsPassed += list.size();
			}
		}

		return -1;
	}

	@Override
	public int getSize() {
		int totalElements = 0;
		for (LinkedList<T> list :
				this.elements.values()) {
			totalElements += list.size();
		}

		return totalElements;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();

		for (Map.Entry<Integer, LinkedList<T>> entry :
				this.elements.entrySet()) {
			entry.getValue().forEach((el) -> {
				ret.append(String.format("%d: %s%n", entry.getKey(), el.toString()));
			});
		}

		return ret.toString();
	}
}

