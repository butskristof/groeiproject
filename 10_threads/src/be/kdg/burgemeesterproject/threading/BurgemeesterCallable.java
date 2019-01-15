package be.kdg.burgemeesterproject.threading;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.BurgemeesterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Kristof Buts
 */
public class BurgemeesterCallable implements Callable<List<Burgemeester>> {
	private static final int TO_GENERATE = 1000;
	private Predicate<Burgemeester> filter;

	public BurgemeesterCallable(Predicate<Burgemeester> filter) {
		this.filter = filter;
	}

	@Override
	public List<Burgemeester> call() throws Exception {
		List<Burgemeester> generated = new ArrayList<>();

		Stream.generate(BurgemeesterFactory::newRandomBurgemeester)
				.filter(filter)
				.limit(TO_GENERATE)
				.forEach(generated::add);

		return generated;
	}
}
