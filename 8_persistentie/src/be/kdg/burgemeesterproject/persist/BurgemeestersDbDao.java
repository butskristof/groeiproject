package be.kdg.burgemeesterproject.persist;

import be.kdg.burgemeesterproject.model.Burgemeester;
import be.kdg.burgemeesterproject.model.Partij;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristof Buts
 * @version 1.0 29.11.18 10:47
 */
public class BurgemeestersDbDao implements BurgemeestersDao {
	private Connection connection;

	// Singleton pattern
	private static BurgemeestersDbDao burgemeestersDbDao;

	private BurgemeestersDbDao(String databasePath) {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			System.out.println("Driver loaded");
			this.connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath);
			System.out.println("Connection opened");

			this.maakTabel();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC driver not found.");
		} catch (SQLException e) {
			System.err.println("Could not open connection to database.");
		}
	}

	public static synchronized BurgemeestersDbDao getInstance(String databasePath) {
		if (burgemeestersDbDao == null) {
			burgemeestersDbDao = new BurgemeestersDbDao(databasePath);
		}
		return burgemeestersDbDao;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			// database niet meer nodig, geen probleem
		} finally {
			connection = null;
		}
	}

	private void maakTabel() {
		// try with resources
		try (Statement statement = connection.createStatement()) {
			statement.execute("DROP TABLE burgemeestertable IF EXISTS");
			StringBuilder createQuery = new StringBuilder();

			createQuery.append("CREATE TABLE burgemeestertable ");
			createQuery.append("(id INTEGER NOT NULL IDENTITY, ");
			createQuery.append("naam VARCHAR(50) NOT NULL, ");
			createQuery.append("geboortedatum DATE NOT NULL, ");
			createQuery.append("gemeente VARCHAR(50) NOT NULL, ");
			createQuery.append("procentVoorkeursstemmen DOUBLE NOT NULL, ");
			createQuery.append("termijnen TINYINT NOT NULL, ");
			createQuery.append("partij VARCHAR(50) NOT NULL)");

			statement.executeUpdate(createQuery.toString());
		} catch (SQLException e) {
			System.err.println("Error creating table");
		}
	}

	// create
	@Override
	public boolean voegToe(Burgemeester b) {
		// first construct sql string
		// id is autogenerated
		String createQuery = "INSERT INTO burgemeestertable (naam, geboortedatum, gemeente, procentVoorkeursstemmen, termijnen, partij)" +
				" VALUES (?,?,?,?,?,?)";

		// execute through PreparedStatement
		// try with resources
		try (PreparedStatement ps = connection.prepareStatement(createQuery)) {
			// set values
			ps.setString(1, b.getNaam());
			ps.setDate(2, Date.valueOf(b.getGeboortedatum()));
			ps.setString(3, b.getGemeente());
			ps.setDouble(4, b.getProcentVoorkeursstemmen());
			ps.setInt(5, b.getTermijnen());
			ps.setString(6, b.getPartij().name());

			// execute
			return ps.execute();
		} catch (SQLException e) {
			System.err.println("Error inserting Burgemeester into database.");
			return false;
		}
	}

	// retrieve
	@Override
	public Burgemeester zoek(String naam, String gemeente) {
		Burgemeester ret = null;

		try {
			Statement s = connection.createStatement();

			String selectQuery = String.format("SELECT * FROM burgemeestertable " +
					"WHERE naam='%s' AND gemeente='%s'",
					naam, gemeente);

			ResultSet res = s.executeQuery(selectQuery);
			while (res.next()) {
				ret = new Burgemeester(
						res.getInt("id"),
						res.getString("naam"),
						res.getDate("geboortedatum").toLocalDate(),
						res.getString("gemeente"),
						res.getDouble("procentVoorkeursstemmen"),
						res.getInt("termijnen"),
						Partij.valueOf(res.getString("partij"))
				);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving burgemeester " + naam + ", " + gemeente);
		}

		return ret;
	}

	// update
	public boolean update(Burgemeester b) {
		// compose query
		String values = "naam=?, geboortedatum=?, gemeente=?, procentVoorkeursstemmen=?, termijnen=?, partij=?";
		String updateQuery = String.format("UPDATE burgemeestertable SET %s WHERE id=?", values);

		try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
			ps.setString(1, b.getNaam());
			ps.setDate(2, Date.valueOf(b.getGeboortedatum()));
			ps.setString(3, b.getGemeente());
			ps.setDouble(4, b.getProcentVoorkeursstemmen());
			ps.setInt(5, b.getTermijnen());
			ps.setString(6, b.getPartij().name());
			ps.setInt(7, b.getId());

			int rowsAffected = ps.executeUpdate();

			return rowsAffected == 1;
		} catch (SQLException e) {
			System.err.println("Error updating Burgemeester record with id " + b.getId() +  ".");
			return false;
		}
	}

	// delete
	@Override
	public boolean verwijder(String naam, String gemeente) {
		// compose query
		String deleteQuery = String.format("DELETE FROM burgemeestertable WHERE naam='%s' AND gemeente='%s'",
				naam, gemeente);

		try (Statement s = connection.createStatement()) {
			return s.execute(deleteQuery);
		} catch (SQLException e) {
			System.err.println("Error deleting Burgemeester " + naam + ", " + gemeente + ".");
			return false;
		}
	}

	@Override
	public List<Burgemeester> gesorteerdOpNaam() {
		return this.sortedByAttribute("naam");
	}

	@Override
	public List<Burgemeester> gesorteerdOpGeboortedatum() {
		return this.sortedByAttribute("geboortedatum");
	}

	@Override
	public List<Burgemeester> gesorteerdOpTermijnen() {
		return this.sortedByAttribute("termijnen");
	}

	private List<Burgemeester> sortedByAttribute(String attribute) {
		return this.retrieveMultiple(" ORDER BY " + attribute);
	}

	// filters voorkeursstemmen
	public List<Burgemeester> filter(double filtervalue) {
		String extension = String.format(" WHERE procentVoorkeursstemmen > %f", filtervalue);
		return this.retrieveMultiple(extension);
	}

	private List<Burgemeester> retrieveMultiple(String extension) {
		List<Burgemeester> ret = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			String query = "SELECT * FROM burgemeestertable" + extension;

			ResultSet res = s.executeQuery(query);
			while (res.next()) {
				ret.add(new Burgemeester(
						res.getInt("id"),
						res.getString("naam"),
						res.getDate("geboortedatum").toLocalDate(),
						res.getString("gemeente"),
						res.getDouble("procentVoorkeursstemmen"),
						res.getInt("termijnen"),
						Partij.valueOf(res.getString("partij"))
				));
			}
		} catch (SQLException e) {
			System.err.println("Error getting sorted items.");
		}

		return ret;
	}
}
