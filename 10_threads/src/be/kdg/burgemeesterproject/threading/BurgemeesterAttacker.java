package be.kdg.burgemeesterproject.threading;

import be.kdg.burgemeesterproject.model.Burgemeester;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Kristof Buts
 */
public class BurgemeesterAttacker implements Runnable {
	private List<Burgemeester> list;
	private Predicate<Burgemeester> predicate;

	public BurgemeesterAttacker(List<Burgemeester> list, Predicate<Burgemeester> predicate) {
		this.list = list;
		this.predicate = predicate;
	}

	@Override
	public void run() {
		synchronized (list) {
			this.list.removeIf(this.predicate);
		}
	}

	public List<Burgemeester> getList() {
		return list;
	}
}
