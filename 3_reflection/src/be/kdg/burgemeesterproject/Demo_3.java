package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.*;
import be.kdg.burgemeesterproject.reflection.ReflectionTools;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 1:16 PM
 */
public class Demo_3 {
	public static void main(String[] args) {
		ReflectionTools.classAnalysis(Persoon.class);
		ReflectionTools.classAnalysis(Burgemeester.class);
		ReflectionTools.classAnalysis(Burgemeesters.class);

		Object o = ReflectionTools.runAnnotated(Burgemeester.class);
		System.out.println(o);
	}
}
