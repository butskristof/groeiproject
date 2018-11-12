package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.generics.PriorityQueue;
import be.kdg.burgemeesterproject.model.Burgemeester;

import java.util.Random;

/**
 * @author Kristof Buts
 * @version 1.0 11/12/18 4:42 PM
 */
public class Demo_2 {
	public static void main(String[] args) {
		PriorityQueue<String> myQueue = new PriorityQueue<>();
		myQueue.enqueue("alfa", 2);
		myQueue.enqueue("beta", 5);
		myQueue.enqueue("gamma", 2);
		myQueue.enqueue("delta", 3);
		System.out.println("Overzicht van de PriorityQueue:");
		System.out.println(myQueue.toString());
		System.out.println("aantal: " + myQueue.getSize());
		System.out.println("positie van gamma: " + myQueue.search("gamma"));
		for(int i = 0; i < 4; i++) {
			System.out.println("Dequeue: " + myQueue.dequeue());
		}
		System.out.println("Size na dequeue: " + myQueue.getSize());

		PriorityQueue<Burgemeester> burgemeesters = new PriorityQueue<>();
		Random rnd = new Random();
		for (Burgemeester b: Data.getData()) {
			burgemeesters.enqueue(b, rnd.nextInt(5) + 1);
		}

		System.out.println(burgemeesters.toString());
		boolean tryAddDuplicate = burgemeesters.enqueue(Data.getData().get(3), rnd.nextInt(5)+1);
		int items = burgemeesters.getSize();
		int index = burgemeesters.search(Data.getData().get(3));
		Burgemeester bb = burgemeesters.dequeue();
		items = burgemeesters.getSize();
	}
}