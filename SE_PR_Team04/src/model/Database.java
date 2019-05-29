/*
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class Database.
 */
public class Database {

	/** The instance. */
	private static Database instance;

	/** The Constant CONNECTION_STRING. */
	static final String CONNECTION_STRING = "jdbc:mysql://sql2.freesqldatabase.com:3306/sql2291991";

	/** The Constant USER. */
	static final String USER = "sql2291991";

	/** The Constant PASSWORD. */
	static final String PASSWORD = "fitnessmanager2019";

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
	public List<String> getWorkouts() {
		List<String> outputList = new LinkedList<>();
		try {

			String statement = "Select name from WORKOUT";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				outputList.add(rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
	public List<Exercise> getExercisesFromWorkout(String name) {
		List<Exercise> outputList = new LinkedList<>();
		int id = getWorkoutId(name);

		outputList = getExercisesFromWorkout(id);

		return outputList;
	}

	public int getWorkoutId(String name) {
		int id = 0;
		try {

			String statement = "Select id from Workout where '" + name + "'=name";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = Integer.parseInt(rs.getString(1));

			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}

	public int getExerciseId(String name) {
		int id = 0;
		try {

			String statement = "Select id from Exercise where '" + name + "'=name";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = Integer.parseInt(rs.getString(1));

			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}

	public int getMuscleId(String name) {
		int id = 0;
		try {

			String statement = "Select id from Muscle where '" + name + "'=name";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = Integer.parseInt(rs.getString(1));

			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}

	public List<Exercise> getExercisesFromWorkout(int id) {
		List<String> exerciseId = new LinkedList<String>();
		List<Exercise> exercises = new LinkedList<Exercise>();
		try {

			String statement = "Select ExerciseID from WorkoutExercise where '" + id + "'=WorkoutId";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				exerciseId.add(rs.getString(1));

			}

			for (String help : exerciseId) {
				statement = "Select name,reps from Exercise where '" + help + "'=Id";
				pstmt = conn.prepareStatement(statement);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					exercises.add(new Exercise(rs.getString(1), null, Integer.parseInt(rs.getString(2))));
				}
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		return exercises;
	}

	public void deleteWorkout(String name) {
		int id = getWorkoutId(name);
		try {

			String statement = "Delete from WorkoutExercise where '" + id + "'=WorkoutId";

			// ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			pstmt.executeUpdate();

			statement = "Delete from Workout where '" + id + "'=id";
			pstmt = conn.prepareStatement(statement);
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public List<Exercise> getAllExercises() {
		List<Exercise> outputList = new LinkedList<Exercise>();
		try {

			String statement = "Select name,reps from Exercise";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			int mid = 0;
			while (rs.next()) {

				String statementem = "Select MuscleID from ExerciseMuscle where ExerciseID = " + rs.getString(1);

				ResultSet rsem = null;
				PreparedStatement pstmtem = conn.prepareStatement(statementem);
				rsem = pstmtem.executeQuery();
				while (rsem.next()) {
					mid = Integer.parseInt(rsem.getString(1));
				}

				String statementm = "Select name from Muscle where id = " + mid;
				String muscle = "";
				ResultSet rsm = null;
				PreparedStatement pstmtm = conn.prepareStatement(statementm);
				rsm = pstmtm.executeQuery();

				while (rsm.next()) {
					muscle = rsm.getString(1);
				}
				outputList.add(new Exercise(rs.getString(2), muscle, Integer.parseInt(rs.getString(3))));
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

		return outputList;
	}

	public void createWorkout(String name, LocalDate date, List<Exercise> exercises) {

		try {

			String statement = "Insert into Workout (date,name) values (?,?)";

			// ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			pstmt.setString(1, date.toString());
			pstmt.setString(2, name);

			pstmt.executeUpdate();

			int id = getWorkoutId(name);

			int exid = 0;
			for (int i = 0; i < exercises.size(); i++) {
				statement = "Insert into WorkoutExercise (WorkoutId,ExerciseID) values (?,?)";
				pstmt = conn.prepareStatement(statement);
				pstmt.setString(1, id + "");
				exid = getExerciseId(exercises.get(i).getName());
				pstmt.setString(2, exid + "");
				pstmt.executeUpdate();
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void createExercise(String name, String muscle, int reps) {

		try {

			String statement = "Insert into Exercise (name,reps) values (?,?)";

			// ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			pstmt.setString(1, name);
			pstmt.setString(2, reps + "");

			pstmt.executeUpdate();

			int id = getExerciseId(name);
			int muscleid = getMuscleId(muscle);

			if (muscleid == 0) {
				statement = "Insert into Muscle (name) values (?)";
				pstmt = conn.prepareStatement(statement);
				pstmt.setString(1, muscle);

				pstmt.executeUpdate();
			}
			muscleid = getMuscleId(muscle);
			statement = "Insert into ExerciseMuscle (ExerciseID,MuscleId) values (?,?)";
			pstmt = conn.prepareStatement(statement);
			pstmt.setString(1, id + "");

			pstmt.setString(2, muscleid + "");
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}