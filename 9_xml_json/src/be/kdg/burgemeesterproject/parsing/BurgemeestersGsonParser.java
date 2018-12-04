package be.kdg.burgemeesterproject.parsing;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Burgemeesters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 04.12.18 20:37
 */
public class BurgemeestersGsonParser {
	public static void writeJson(Burgemeesters burgemeesters, String filename) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(burgemeesters);

		try (FileWriter writer = new FileWriter(filename)) {
			writer.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Burgemeesters readJson(String filename) {
		Burgemeesters ret = null;

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			ret = gson.fromJson(reader, Burgemeesters.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}
}
