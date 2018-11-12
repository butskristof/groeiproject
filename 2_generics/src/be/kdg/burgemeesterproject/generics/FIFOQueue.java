package be.kdg.burgemeesterproject.generics;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 4:21 PM
 */
public interface FIFOQueue<T> {
	boolean enqueue(T element, int priority);
	T dequeue();
	int search(T element);
	int getSize();
}
