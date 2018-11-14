package be.kdg.burgemeesterproject;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Kristof Buts
 * @version 1.0 11/14/18 1:54 PM
 */
public class Demo_4 {
	public static void main(String[] args) {
		loadLogConfig();

		Burgemeester b = new Burgemeester();
		b.setNaam("w");
		b.setGeboortedatum(LocalDate.of(2010,1,1));
		b.setGemeente("a");
		b.setProcentVoorkeursstemmen(23.5);
		b.setTermijnen(-2);

		Burgemeesters bb = new Burgemeesters();
		bb.voegToe(b);
		bb.verwijder(b.getNaam(), b.getGemeente());
	}

	private static void loadLogConfig() {
		File f = new File("logging.properties");
		if (f.exists()) {
			try (FileInputStream fis = new FileInputStream(f)) {
				LogManager.getLogManager().readConfiguration(fis);
			} catch (IOException ex) {
				System.err.println("Configuratiebestand is corrupt.");
			}
		} else {
			System.err.println("Configuratiebestand niet gevonden.");
		}
	}
}
