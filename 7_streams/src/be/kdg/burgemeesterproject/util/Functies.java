package be.kdg.burgemeesterproject.util;

import be.kdg.burgemeesterproject.model.Burgemeester;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * @author Kristof Buts
 * @version 1.0 27.11.18 12:13
 */
public class Functies {
	public static <T> List<T> filteredList(List<T> burgemeesterList, Predicate<T> predicate) {
//		List<T> ret = new ArrayList<>();

//		for (T b :
//				burgemeesterList) {
//			if (predicate.test(b)) {
//				ret.add(b);
//			}
//		}

//		burgemeesterList.forEach(b -> {
//			if (predicate.test(b)) {
//				ret.add(b);
//			}
//		});

		List<T> ret = burgemeesterList.stream().filter(predicate).collect(Collectors.toList());

		return ret;
	}

	public static <T> Double average(List<T> burgemeesterList, ToDoubleFunction<T> mapper) {
//		double avg = 0.0;
//
//		for (T b :
//				burgemeesterList) {
//			avg += mapper.applyAsDouble(b);
//		}
//
//		avg = avg / burgemeesterList.size();

		double avg = burgemeesterList.stream().mapToDouble(mapper).average().getAsDouble();

		return avg;
	}

	public static <T> long countIf(List<T> burgemeesterList, Predicate<T> predicate) {
//		int ctr = 0;
//
//		for (T b :
//				burgemeesterList) {
//			if (predicate.test(b)) {
//				++ctr;
//			}
//		}

		long ctr = burgemeesterList.stream().filter(predicate).count();

		return ctr;
	}
}
