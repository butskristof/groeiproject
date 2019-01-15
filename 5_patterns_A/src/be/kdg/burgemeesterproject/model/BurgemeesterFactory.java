package be.kdg.burgemeesterproject.model;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author Kristof Buts
 * @version 1.0 11/15/18 12:17 PM
 */
public class BurgemeesterFactory {
	private static final String[] firstNames = {
			"Jos", "Jan", "Peer", "Pol", "Joris", "Luc", "Korneel", "Piet", "Frank", "Edelhart", "Dirk", "Flor", "Gaston",
			"Leo", "Marijn", "Fons", "Martine", "Eufrazie", "Marie", "Veronique", "Sarah", "Lisa", "Michelle", "Karel"
	};
	private static final String[] lastNames = {
			"Van den Broeck", "Van den Bergh", "Buts", "Goovaerts", "Behiels", "Vochten", "de Grote", "Janssens", "Van de Pol", "Peeters",
			"Cornelis", "De Bue", "De Haes", "Verplancken", "Verschueren", "Vermeulen", "Berghmans", "De Valck", "Obama",
			"Ghysen", "Raes"
	};
	private static final char[] vowels = {'a','e','i','o','u'};
	private static final char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};

	private static Random generator = new Random();

	private BurgemeesterFactory() {
	}

	// Voorzie een static factory-methode newEmptyDictator die de defaultconstructor van de basisklasse oproept en dus een “leeg” basisobject retourneert.
	public static Burgemeester newEmptyBurgemeester() {
		return new Burgemeester();
	}

	// Voorzie een static factory-methode newFilledDictator die de andere constructor van de basisklasse oproept. Alle benodigde gegevens komen hier via parameters binnen en worden doorgegeven. Een “gevuld” basisobject wordt geretourneerd.
	public static Burgemeester newFilledBurgemeester(String naam, LocalDate geboortedatum, String gemeente, double procentVoorkeursstemmen, int termijnen, Partij partij) {
		return new Burgemeester(naam, geboortedatum, gemeente, procentVoorkeursstemmen, termijnen, partij);
	}

	/*
	Voorzie ook een static factory-methode newRandomDictator die geen parameters heeft, maar alle gegevens at random genereert en vervolgens via parameters doorgeeft aan de constructor van de basisklasse.
	Probeer deze random waarden zo realistisch mogelijk te genereren:
	- Datumvelden correct binnen een bepaald tijdsinterval (bijvoorbeeld tussen 1900 en 2000)
	- Getalwaarden correct binnen een bepaald bereik (bijvoorbeeld percentages tussen 0.0 en 1.0)
	- Strings realistisch: een bepaald aantal woorden, maxlengte van een woord, beginhoofdletter of niet. Maak hiervoor liefst een private hulpmethode generateString:
		private static String generateString(int maxWordLength, int wordCount, boolean camelCase)
	Om een realistische string samen te stellen, hanteer je volgende simplistische regel: 1 kans op 3 voor een klinker, 2 kansen op 3 voor een medeklinker.
	 */
	public static Burgemeester newRandomBurgemeester() {
		// generate data
		String naam = generateName();
		LocalDate geboortedatum = LocalDate.of(
				generator.nextInt(62) + 1938, // range [18,80] TODO check validity
				generator.nextInt(12) + 1,
				generator.nextInt(28) + 1 // max 28, simplicity
		);
		String gemeente = generateString(
				generator.nextInt(5) + 5, // wordlength 5-10
				generator.nextInt(2) + 1, // 1-3
				true // capitalize municipality names
		);
		double pctStemmen = generator.nextDouble();
		int termijnen = generator.nextInt(5);
		Partij partij = Partij.ONBEKEND; // TODO randomise

		// make object
		Burgemeester ret = newFilledBurgemeester(naam, geboortedatum, gemeente, pctStemmen, termijnen, partij);

		return ret;
	}

	private static String generateName() {
		String lastName = lastNames[generator.nextInt(lastNames.length)];
		String firstName = firstNames[generator.nextInt(firstNames.length)];

		return String.format("%s %s", lastName, firstName);
	}

	private static String generateString(int maxWordLength, int wordCount, boolean camelCase) {
		StringBuilder ret = new StringBuilder();

		for (int i = 0; i < wordCount; ++i) {
			StringBuilder word = new StringBuilder();
			int wordlength = generator.nextInt(maxWordLength) + 3; // should have at least three chars in word
			for (int j = 0; j < wordlength; ++j) {
				double k = generator.nextDouble();
				if (k <= 0.33) { // 1/3 vowel
					word.append(vowels[generator.nextInt(vowels.length)]);
				} else { // 2/3 consonant
					word.append(consonants[generator.nextInt(consonants.length)]);
				}
			}

			if (camelCase) {
				word.replace(0, 0, word.substring(0,0).toUpperCase());
			}

			ret.append(word.toString());
			ret.append(" ");
		}

		return ret.toString();
	}
}
