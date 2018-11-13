package be.kdg.burgemeesterproject.reflection;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 1:00 PM
 */
public class ReflectionTools {
	public static void classAnalysis(Class aClass) {

		System.out.println("Analyse van de klasse: " + aClass.getSimpleName());
		System.out.println("=====================================");
//		a. volledige klassenaam (“fully qualified name”)
		System.out.printf("%-25s: %s%n","Fully qualified name", aClass.getName());
//		b. naam van de superklasse
		System.out.printf("%-25s: %s%n","Naam van de superklasse", aClass.getSuperclass().getSimpleName());
//		c. naam van de package
		System.out.printf("%-25s: %s%n","Naam van de package", aClass.getPackage());
//		d. gebruikte interfaces
		StringBuilder interfaces = new StringBuilder();
		for (Class c :
				aClass.getInterfaces()) {
			interfaces.append(c.getSimpleName() + " ");
		}
		System.out.printf("%-25s: %s%n","Interfaces", interfaces.toString());
//		e. aanwezige constructors
		StringBuilder constructors = new StringBuilder();
		for (Constructor c:
				aClass.getDeclaredConstructors()) {
			constructors.append("\t" + c + "\n");
		}
		System.out.printf("%-25s: %n%s","Constructors", constructors.toString());
//		f. namen van private attributen
		StringBuilder fields = new StringBuilder();
		for (Field f :
				aClass.getDeclaredFields()) {
			fields.append(f.getName() + " ");
		}
		System.out.printf("%-25s: %s%n","Attributen", fields.toString());
		/*
		g. getters, setters en andere methoden
		 */
		StringBuilder getters = new StringBuilder();
		StringBuilder setters = new StringBuilder();
		StringBuilder otherMethods = new StringBuilder();
		for (Method m :
				aClass.getDeclaredMethods()) {
			if (m.getName().startsWith("get")) {
				getters.append(m.getName() + " ");
			} else if (m.getName().startsWith("set")) {
				setters.append(m.getName() + " ");
			} else {
				otherMethods.append(m.getName() + " ");
			}
		}
		System.out.printf("%-25s: %s%n","Getters", getters.toString());
		System.out.printf("%-25s: %s%n","Setters", setters.toString());
		System.out.printf("%-25s: %s%n","Andere", otherMethods.toString());

		System.out.println();
	}

	public static Object runAnnotated(Class c) {
		try {
//		a. Maak een nieuwe instantie aan van deze klasse; roep daartoe de default constructor aan.
			Object o = c.newInstance();
//		b. Overloop de methoden van deze klasse en selecteer enkel de methoden die geannotteerd zijn via @CanRun en die als parameter één String hebben.
			for (Method m :	c.getMethods()) {

				if (
					m.getAnnotation(CanRun.class) != null
					&& m.getParameters().length == 1
					&& m.getParameters()[0].getType() == String.class
				) {
//				c. Voer deze methoden uit op het geïnstantieerde object met als parameter de value van de annotation.
					m.invoke(o, m.getAnnotation(CanRun.class).value());
				}
			}
//		d. Retourneer het gecreëerde object.
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
