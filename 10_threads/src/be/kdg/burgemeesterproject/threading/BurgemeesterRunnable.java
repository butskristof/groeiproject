package be.kdg.burgemeesterproject.threading;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.BurgemeesterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Kristof Buts
 */
public class BurgemeesterRunnable implements Runnable {
	private static final int TO_GENERATE = 1000;
	private Predicate<Burgemeester> filter;
	private List<Burgemeester> generated;

	private static int counter;

	public BurgemeesterRunnable(Predicate<Burgemeester> filter) {
		this.filter = filter;
	}

	@Override
	public void run() {
		this.generated = new ArrayList<>();

		Stream.generate(BurgemeesterFactory::newRandomBurgemeester)
				.filter(filter)
				.limit(1000)
				.forEach(this.generated::add);
	}

	public List<Burgemeester> getList() {
		return generated;
	}
}
