package be.kdg.burgemeesterproject.reflection;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.lang.reflect.Constructor;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 1:00 PM
 */
public class ReflectionTools {
	public static void classAnalysis(Class aClass) {
		/*
		f. namen van private attributen
		g. getters, setters en andere methoden
		 */

//		a. volledige klassenaam (“fully qualified name”)
		System.out.println(aClass.getName());
//		b. naam van de superklasse
		System.out.println(aClass.getSuperclass().getName());
//		c. naam van de package
		System.out.println(aClass.getPackage().getName());
//		d. gebruikte interfaces
		for (Class c :
				aClass.getInterfaces()) {
			System.out.println(c.getName());
		}
//		e. aanwezige constructors
		System.out.println(aClass.getConstructors().getClass().getName());
		for (Constructor constructor:
				aClass.getDeclaredConstructors()) {
			System.out.println(constructor.getName());
		}
	}

}
