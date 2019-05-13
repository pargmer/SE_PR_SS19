/*
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class ReadAndWriteCSV.
 */
public class ReadAndWriteCSV {

	/** The instance. */
	private static ReadAndWriteCSV instance;

	/**
	 * Gets the single instance of ReadAndWriteCSV.
	 *
	 * @return single instance of ReadAndWriteCSV
	 * @throws SQLException the SQL exception
	 */
	public static ReadAndWriteCSV getInstance() throws SQLException {
		if (instance == null) {
			instance = new ReadAndWriteCSV();
		}
		return instance;
	}

	/**
	 * Read workouts from csv.
	 *
	 * @param file the file
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Workout> readWorkoutsFromCsv(String file) throws IOException {

		List<Workout> workouts = new LinkedList<Workout>();
		List<Exercise> exercises = new LinkedList<Exercise>();
		String row;
		BufferedReader csvReader = new BufferedReader(new FileReader(file));

		while ((row = csvReader.readLine()) != null) {
			
			String[] data = row.split(";");
			String[] dataexercise = data[2].split(",");
			exercises.clear();
			
			
			for (int i = 0; i < dataexercise.length; i++) {
				exercises.add(new Exercise(dataexercise[i], "", 0));

			}
			System.out.println();
			Date date = new Date();
			Workout helpwork = new Workout(data[1], date, exercises);
			workouts.add(helpwork);

		}
		csvReader.close();

		return workouts;
	}

	/**
	 * Read exercises from csv.
	 *
	 * @param file the file
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Exercise> readExercisesFromCsv(String file) throws IOException {

		List<Exercise> exercises = new LinkedList<Exercise>();
		// List<Exercise> exercises = new LinkedList<Exercise>();
		String row;
		BufferedReader csvReader = new BufferedReader(new FileReader(file));

		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(";");

			exercises.add(new Exercise(data[0], data[1], Integer.parseInt(data[2])));
		}
		csvReader.close();

		return exercises;
	}

	public void writeWorkoutsOnCSV(List<Workout> workouts) throws IOException {

		String help = "";
		FileWriter writer = new FileWriter("workouts.csv");
		StringBuilder sb = new StringBuilder();
		for (Workout workout : workouts) {
			help = "";

			for (int i = 0; i < workout.getExercises().size(); i++) {
				if (help.equals("")) {
					help = help + workout.getExercises().get(i).getName();
				} else {
					help = help + "," + workout.getExercises().get(i).getName();
				}
			}

			sb.append(workout.getDate().toString());
			sb.append(";");
			sb.append(workout.getName());
			sb.append(";");
			sb.append(help);
			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
		writer.close();
	}
}
