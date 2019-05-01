/*
 * 
 */
package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database {

	/** The instance. */
	private static Database instance;

	/** The Constant CONNECTION_STRING. */
	static final String CONNECTION_STRING = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11156987";

	/** The Constant USER. */
	static final String USER = "sql11156987";

	/** The Constant PASSWORD. */
	static final String PASSWORD = "Cl568sav1j";

	/** The conn. */
	private Connection conn;

	/**
	 * Instantiates a new database.
	 *
	 * @throws SQLException the SQL exception
	 */
	private Database() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Gets the single instance of Database.
	 *
	 * @return single instance of Database
	 * @throws SQLException the SQL exception
	 */
	public static Database getInstance() throws SQLException {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	/**
	 * Gets the workouts.
	 *
	 * @return the workouts
	 * @throws SQLException the SQL exception
	 */
	public List<String> getWorkouts() throws SQLException {
		List<String> outputList = new LinkedList<>();
		String statement = "Select name from WORKOUT";

		ResultSet rs = null;
		PreparedStatement pstmt = conn.prepareStatement(statement);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			outputList.add(rs.getString(1));
		}
		return outputList;
	}

	/**
	 * Gets the exercises from workout.
	 *
	 * @param name the name
	 * @return the exercises from workout
	 * @throws SQLException the SQL exception
	 */
	public List<Exercise> getExercisesFromWorkout(String name) throws SQLException {
		List<Exercise> outputList = new LinkedList<>();
		String statement = "Select name, muscles, reps from EXERCISES";

		ResultSet rs = null;
		PreparedStatement pstmt = conn.prepareStatement(statement);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			// outputList.add(new
			// Exercise(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3))));
		}
		return outputList;
	}

}