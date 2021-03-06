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

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database {

	/** The instance. */
	private static Database instance;
	
	/** The Constant CONNECTION_STRING. */
	static final String CONNECTION_STRING = "jdbc:mysql://db4free.net:3306/fitnessjdk";
	
	/** The Constant USER. */
	static final String USER = "fitnessmanagjku";
	
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
	public List<String> getWorkouts() throws SQLException {
		List<String> outputList = new LinkedList<String>();
		String statement = "Select name from Workout";
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(statement)) {

			rs = pstmt.executeQuery();
			while (rs.next()) {
				outputList.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return outputList;
	}

	/**
	 * Gets the workout info.
	 *
	 * @param name the name
	 * @return the workout info
	 * @throws SQLException the SQL exception
	 */
	public Workout getWorkoutInfo(String name) throws SQLException {
		Workout outputList = new Workout();
		List<Exercise> exercises = new LinkedList<Exercise>();
		String statement = "Select name, date from Workout where '" + name + "'=name";
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(statement)) {

			rs = pstmt.executeQuery();
			while (rs.next()) {
				outputList = new Workout(rs.getString(1), LocalDate.parse(rs.getString(2)), exercises);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rs != null) {
				rs.close();
			}
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
		List<Exercise> outputList = new LinkedList<Exercise>();
		int id = getWorkoutId(name);

		outputList = getExercisesFromWorkout(id);

		return outputList;
	}

	/**
	 * Gets the workout id.
	 *
	 * @param name the name
	 * @return the workout id
	 * @throws SQLException the SQL exception
	 */
	public int getWorkoutId(String name) throws SQLException {
		int id = 0;
		String statement = "Select id from Workout where '" + name + "'=name";
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(statement)) {

			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = Integer.parseInt(rs.getString(1));

			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if(rs!=null)rs.close();
		}
		return id;
	}

	/**
	 * Gets the exercise id.
	 *
	 * @param name the name
	 * @return the exercise id
	 */
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

	/**
	 * Gets the muscle id.
	 *
	 * @param name the name
	 * @return the muscle id
	 */
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

	/**
	 * Gets the exercises from workout.
	 *
	 * @param id the id
	 * @return the exercises from workout
	 */
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
				statement = "Select name,reps,unit from Exercise where '" + help + "'=Id";
				pstmt = conn.prepareStatement(statement);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					exercises.add(new Exercise(rs.getString(1), null, Integer.parseInt(rs.getString(2)),rs.getString(3)));
				}
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		return exercises;
	}

	/**
	 * Delete workout.
	 *
	 * @param name the name
	 * @throws SQLException the SQL exception
	 */
	public void deleteWorkout(String name) throws SQLException {
		int id = getWorkoutId(name);
		try {
			
			String statement = "Delete from Workout where '" + id + "'=id";
			PreparedStatement pstmt = conn.prepareStatement(statement);
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Gets the all exercises.
	 *
	 * @return the all exercises
	 */
	public List<Exercise> getAllExercises() {
		List<Exercise> outputList = new LinkedList<Exercise>();
		try {

			String statement = "Select id,name,reps,unit from Exercise";

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
				outputList.add(new Exercise(rs.getString(2), muscle, Integer.parseInt(rs.getString(3)),rs.getString(4)));
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

		return outputList;
	}

	/**
	 * Creates the workout.
	 *
	 * @param name the name
	 * @param date the date
	 * @param exercises the exercises
	 */
	public void createWorkout(String name, LocalDate date, List<Exercise> exercises) {

		try {

			String statement = "Insert into Workout (date,name) values (?,?)";

			PreparedStatement pstmt = conn.prepareStatement(statement);
			pstmt.setString(1, date.toString());
			pstmt.setString(2, name);

			pstmt.executeUpdate();

			int id = getWorkoutId(name);

			int exid = 0;
			for (int i = 0; i < exercises.size(); i++) {
				exid = 0;
	            exid = getExerciseId(exercises.get(i).getName());
	            
				if (exid != 0) {
					statement = "Insert into WorkoutExercise (WorkoutId,ExerciseID) values (?,?)";
					pstmt = conn.prepareStatement(statement);
					pstmt.setString(1, id + "");
					pstmt.setString(2, exid + "");
					pstmt.executeUpdate();
				}
			}

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Creates the exercise.
	 *
	 * @param name the name
	 * @param muscle the muscle
	 * @param reps the reps
	 * @param unit the unit
	 */
	public void createExercise(String name, String muscle, int reps, String unit) {

		try {

			String statement = "Insert into Exercise (name,reps,unit) values (?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(statement);
			pstmt.setString(1, name);
			pstmt.setString(2, reps + "");
                        pstmt.setString(3, unit);

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

	/**
	 * Workout done.
	 *
	 * @param workout the workout
	 */
	public void WorkoutDone(Workout workout) {

		try {
			int id = getWorkoutId(workout.getName());
			String statement = "Select id from Statistic where '" + id + "'=WorkoutId";

			String help = "";

			ResultSet rs = null;
			PreparedStatement pstmt = conn.prepareStatement(statement);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				help = rs.getString(1);

			}

			if (help.equals("")) {
				statement = "Insert into Statistic (workoutid,workoutdone) values (?,?)";
				pstmt = conn.prepareStatement(statement);
				pstmt.setString(1, "" + id);
				pstmt.setString(2, "1");

			} else {
				statement = "Update Statistic set workoutdone = workoutdone + 1 where id = " + help;
				pstmt = conn.prepareStatement(statement);
			}

			pstmt.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Gets the statistic.
	 *
	 * @return the statistic
	 * @throws SQLException the SQL exception
	 */
	public List<Statistic> getStatistic() throws SQLException {
		List<Statistic> statistics = new LinkedList<Statistic>();
		String statement = "Select workoutid, workoutdone from Statistic";

		String help = "";

		ResultSet rs = null;
		PreparedStatement pstmt = conn.prepareStatement(statement);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String statement1 = "Select name from Workout where id =" + rs.getString(1);
			ResultSet rs1 = null;
			PreparedStatement pstmt1 = conn.prepareStatement(statement1);
			rs1 = pstmt1.executeQuery();
			String name = "";
			while (rs1.next()) {

				name = rs1.getString(1);

			}
			if (!name.equals("")) {
				statistics.add(new Statistic(name, Integer.parseInt(rs.getString(2))));
			}

		}
		return statistics;
	}

	/**
	 * Import workout.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void importWorkout() throws SQLException {

	}

	/**
	 * Delete exercise.
	 *
	 * @param exercise the exercise
	 * @throws SQLException the SQL exception
	 */
	public void deleteExercise(Exercise exercise) throws SQLException {
		int id = getExerciseId(exercise.getName());
		String statement = "Delete from Exercise where '" + id + "'=id";
		PreparedStatement pstmt = conn.prepareStatement(statement);
		pstmt.executeUpdate();
	}

	/**
	 * Creates the workoutfrom CSV.
	 *
	 * @param workout the workout
	 * @throws SQLException the SQL exception
	 */
	public void createWorkoutfromCSV(Workout workout) throws SQLException {
		String statement = "Select id from Workout where '" + workout.getName() + "'=name and '" + workout.getDate()
				+ "'=date";
		String help = "";

		ResultSet rs = null;
		PreparedStatement pstmt = conn.prepareStatement(statement);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			help = rs.getString(1);
		}

		if (help == "") {
			createWorkout(workout.getName(), workout.getDate(), workout.getExercises());
		}
	}
	
	/**
	 * Gets the statistic between.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the statistic between
	 * @throws SQLException the SQL exception
	 */
	public List<Statistic> getStatisticBetween(LocalDate from, LocalDate to) throws SQLException {
        List<String> workouts = new LinkedList<String>();
        List<Statistic> statistics = new LinkedList<Statistic>();
       String sstatement = "Select id from Workout where date between '" + from + "' and '"+to+ "'";

      

       ResultSet rss = null;
       PreparedStatement pstmts = conn.prepareStatement(sstatement);
       rss = pstmts.executeQuery();
       while (rss.next()) {
           workouts.add(rss.getString(1));
       }
       
       for (int i = 0; i < workouts.size(); i++) {
            String statement = "Select workoutid, workoutdone from Statistic where '" + workouts.get(i) + "'=workoutid";

       String help = "";

       ResultSet rs = null;
       PreparedStatement pstmt = conn.prepareStatement(statement);
       rs = pstmt.executeQuery();
       while (rs.next()) {
           String statement1 = "Select name from Workout where id =" + rs.getString(1);
           ResultSet rs1 = null;
           PreparedStatement pstmt1 = conn.prepareStatement(statement1);
           rs1 = pstmt1.executeQuery();
           String name = "";
           while (rs1.next()) {

               name = rs1.getString(1);

           }
           if (!name.equals("")) {
               statistics.add(new Statistic(name, Integer.parseInt(rs.getString(2))));
           }
           
       }
     
           

       }
       return statistics;
   
   
   }

}