/*
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
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

		List<Workout> workouts = new LinkedList<>();
		
		String row;
		try(BufferedReader csvReader = new BufferedReader(new FileReader(file))){

		while ((row = csvReader.readLine()) != null) {
			List<Exercise> exercises = new LinkedList<>();
			String[] data = row.split(";");
			String[] dataexercise = data[2].split(",");
			exercises.clear();
			 LocalDate date = LocalDate.now();
			
			for (int i = 0; i < dataexercise.length; i++) {
				exercises.add(new Exercise(dataexercise[i], "", 0,null));

			}
			Workout helpwork = new Workout();
			helpwork = new Workout(data[1], LocalDate.parse(data[0]), exercises);
			
			workouts.add(helpwork);

		}
		}
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

		List<Exercise> exercises = new LinkedList<>();
		String row;
		try(BufferedReader csvReader = new BufferedReader(new FileReader(file))){

		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(";");

			 exercises.add(new Exercise(data[0],data[1],Integer.parseInt(data[2]),null));
		}
		}
		return exercises;
	}

	/**
	 * Write workouts on CSV.
	 *
	 * @param workouts the workouts
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeWorkoutsOnCSV(List<Workout> workouts) throws IOException {

		String help = "";
		try(FileWriter writer = new FileWriter("workouts.csv")){
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
	}
	}
}
