package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.reflection.ReflectionTools;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 1:16 PM
 */
public class Demo_3 {
	public static void main(String[] args) {
		ReflectionTools.classAnalysis(Burgemeester.class);
	}
}