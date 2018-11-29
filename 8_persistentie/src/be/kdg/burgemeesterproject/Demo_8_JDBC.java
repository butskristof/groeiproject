package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.persist.BurgemeestersDbDao;

/**
 * @author Kristof Buts
 * @version 1.0 29.11.18 11:01
 */
public class Demo_8_JDBC {
	public static void main(String[] args) {
		BurgemeestersDbDao db = new BurgemeestersDbDao("8_persistentie/db/burgemeestersDb");

		try {
			System.out.println("Starting tests");
			System.out.println("==============");

			System.out.println();

			System.out.println("Starting insertion of data");
			Data.getData().forEach(db::voegToe);
			System.out.println("Insertion of data done");

			System.out.println();
			System.out.println("Searching Joppe Van den Sande");
			Burgemeester search = db.zoek("Van den Sande Joppe", "Heist-op-den-Berg");
			System.out.println(search);

			System.out.println();

			String newName = "Vermeulen Joske";
			System.out.println("Changing name to " + newName);
			search.setNaam(newName);
			boolean updateOk = db.update(search);
			System.out.println("Name was changed successfully? " + updateOk);
			System.out.println(search);
			System.out.println();
			System.out.println("Deleting " + search.getNaam());
			boolean deleteOk = db.verwijder(search.getNaam(), search.getGemeente());

			System.out.println();

			System.out.println("Searching again to make sure");
			search = db.zoek(search.getNaam(), search.getGemeente());
			System.out.printf("Joske was %ssuccessfully deleted.%n", search == null ? "" : "un");

			System.out.println();
			System.out.println("Sorted by name");
			db.gesorteerdOpNaam().forEach(System.out::println);

			System.out.println();
			System.out.println("Sorted by date of birth");
			db.gesorteerdOpGeboortedatum().forEach(System.out::println);

			System.out.println();
			System.out.println("Sorted by terms");
			db.gesorteerdOpTermijnen().forEach(System.out::println);

			System.out.println();
			System.out.println("Filtered by > 35%");
			db.filter(0.35).forEach(System.out::println);
			System.out.println();
		} finally {
			db.close();
		}
	}
}
