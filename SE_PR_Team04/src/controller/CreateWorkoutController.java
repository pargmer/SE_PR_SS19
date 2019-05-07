/*
 * 
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Exercise;
import model.ReadAndWriteCSV;
import model.Workout;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateWorkoutController.
 */
public class CreateWorkoutController {

	/** The exercises. */
	List<Exercise> exercises = new LinkedList<>();

	/** The workoutname. */
	String workoutname = "";

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf workoutname. */
	@FXML
	private TextField tfWorkoutname;

	/** The lv exercises. */
	@FXML
	private ListView<String> lvExercises;
	
	//@FXML
	//private CheckBoxListCell<String> clv_exercises;

	/** The ov exercises. */
	private ObservableList<String> ovExercises;

	/**
	 * Handle btn new exercise.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleBtnNewExercise(ActionEvent event) throws IOException {

		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Create_Exercise.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		CreateExerciseController create = fxmlLoader.getController();
		create.setData(exercises, tfWorkoutname.getText().toString());
		Stage stage = new Stage();
		stage.setTitle("Create Workout!");
		stage.setScene(new Scene(root2));
		stage.show();
		oldStage.close();

	}

	/**
	 * Handle btn save workout.
	 *
	 * @param event the event
	 * @throws IOException  Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@FXML
	private void handleBtnSaveWorkout(ActionEvent event) throws IOException, SQLException {

		List<Workout> workouts = ReadAndWriteCSV.getInstance().readWorkoutsFromCsv("workouts.csv");
		Workout newworkout = new Workout(tfWorkoutname.getText(), new Date(), exercises);

		workouts.add(newworkout);
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

		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Main.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Create Workout!");
		stage.setScene(new Scene(root2));
		stage.show();
		oldStage.close();

	}

	/*
	 * @Override public void initialize(URL location, ResourceBundle resources) {
	 * 
	 * tf_workoutname.setText(workoutname); List<String> sexercise = new
	 * LinkedList<String>(); for(Exercise exercise : exercises) {
	 * sexercise.add(exercise.getName()); } ov_exercises =
	 * FXCollections.observableArrayList(sexercise);
	 * lv_exercises.setItems(ov_exercises); }
	 */

	/**
	 * Sets the data.
	 *
	 * @param oexercises   the oexercises
	 * @param oworkoutname the oworkoutname
	 */
	public void setData(List<Exercise> oexercises, String oworkoutname) {

		exercises = oexercises;
		workoutname = oworkoutname;

		tfWorkoutname.setText(workoutname);
		List<String> sexercise = new LinkedList<>();
		for (Exercise exercise : exercises) {
			sexercise.add(exercise.getName());
		}
		ovExercises = FXCollections.observableArrayList(sexercise);
		lvExercises.setItems(ovExercises);
		//clv_exercises.setItem(ov_exercises.toString());
	}

}
