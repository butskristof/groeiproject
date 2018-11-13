package be.kdg.burgemeesterproject.model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 9:44 PM
 */
/*
Maak tenslotte een klasse TestRunner met een main-
methode, waarin je de TestSuite via code opstart en het
resultaat via een Result-object onderzoekt. Druk af: de
failures, succesful, het aantal testcases en de benodigde tijd.
 */
public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestSuite.class);

		System.out.printf("Failures: %d%n", result.getFailureCount());
		for (Failure f: result.getFailures()) {
			System.out.println(f.toString());
		}

		System.out.printf("Successful: %s%n", result.wasSuccessful());
		System.out.printf("Aantal test cases: %d%n", result.getRunCount());
		System.out.printf("Tijd: %d ms", result.getRunCount());
	}
}
