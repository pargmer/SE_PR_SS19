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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Exercise;
import model.ReadAndWriteCSV;
import model.Workout;



/**
 * The Class CreateWorkoutController.
 */
public class CreateWorkoutController {
	
	List<Exercise> exercises = new LinkedList<Exercise>();

	/** The workoutname. */
	String workoutname = "";
	
	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf workoutname. */
	@FXML
	private TextField tf_workoutname;
	
	 @FXML
	 private ListView<String> lv_exercises;
	 private ObservableList<String> ov_exercises;
	

	@FXML
	private void handleBtn_newExercise(ActionEvent event) throws IOException {

		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Create_Exercise.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		CreateExerciseController create = fxmlLoader.getController();
		create.setData(exercises, tf_workoutname.getText().toString());
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
	private void handleBtn_saveWorkout(ActionEvent event) throws IOException, SQLException  {

		List<Workout> workouts = ReadAndWriteCSV.getInstance().readWorkoutsFromCsv("workouts.csv");
		Workout newworkout = new Workout(tf_workoutname.getText(),new Date(),exercises);
		 
		workouts.add(newworkout);
		ReadAndWriteCSV.getInstance().writeWorkoutsOnCSV(workouts);
		 
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


	/**
	 * Sets the data.
	 *
	 * @param oexercises   the oexercises
	 * @param oworkoutname the oworkoutname
	 */
	public void setData(List<Exercise> oexercises, String oworkoutname) {

		exercises = oexercises;
		workoutname = oworkoutname;

		tf_workoutname.setText(workoutname);
		List<String> sexercise = new LinkedList<String>();
		for(Exercise exercise : exercises) {
			sexercise.add(exercise.getName());
		}
		ov_exercises = FXCollections.observableArrayList(sexercise);
		lv_exercises.setItems(ov_exercises);
	}

	/*@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tf_workoutname.setText(workoutname);
		List<String> sexercise = new LinkedList<String>();
		for(Exercise exercise : exercises) {
			sexercise.add(exercise.getName());
		}
		ov_exercises = FXCollections.observableArrayList(sexercise);
		lv_exercises.setItems(ov_exercises);

	}*/

}
