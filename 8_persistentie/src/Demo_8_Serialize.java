import be.kdg.burgemeesterproject.data.Data;
import be.kdg.burgemeesterproject.model.Burgemeesters;

import java.io.*;

/**
 * @author Kristof Buts
 */
public class Demo_8_Serialize {
	private static final String FILENAME = "8_persistentie/db/burgemeesters.ser";

	public static void main(String[] args) {
		// Maak een nieuwe instantie van de multiklasse aan (in ons geval Dictators) en laat ze vullen door de klasse Data.
		Burgemeesters burgemeesters = new Burgemeesters();
		Data.getData().forEach(burgemeesters::voegToe);

		// Druk het multiobject ter controle af.
		System.out.println("Voor serialize:");
		burgemeesters.gesorteerdOpNaam().forEach(System.out::println);
		System.out.println();

		// Serialiseer het object naar een databestand in de directory db. Gebruik een relatief pad (in ons voorbeeld: "8_persistentie/db/dictators.ser"
		serialize(burgemeesters);

		// Deserialiseer vanuit het databestand in een nieuw object en toon het opnieuw ter controle.
		burgemeesters = deserialize();
		System.out.println("Voor serialize:");
		burgemeesters.gesorteerdOpNaam().forEach(System.out::println);
	}

	private static void serialize(Burgemeesters burgemeesters) {
		try (
				FileOutputStream fos = new FileOutputStream(FILENAME);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
		) {
			oos.writeObject(burgemeesters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Burgemeesters deserialize() {
		try (
				FileInputStream fis = new FileInputStream(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);
		) {
			return (Burgemeesters) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
