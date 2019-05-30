/*
 * 
 */
package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Exercise;


/**
 * The Class CreateExerciseController.
 */
public class CreateExerciseController {

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf muscle. */
	@FXML
	private TextField tf_name, tf_reps, tf_muscle;

	/** The exercises. */
	List<Exercise> exercises = new LinkedList<Exercise>();

	/** The workoutname. */
	String workoutname;

	/**
	 * Handle btn save exercise.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleBtn_saveExercise(ActionEvent event) throws IOException {

		Exercise newExercise = new Exercise(tf_name.getText().toString(), tf_muscle.getText().toString(),Integer.parseInt(tf_reps.getText().toString()));
		exercises.add(newExercise);	

		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Create_Workout.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		CreateWorkoutController create = fxmlLoader.getController();
		create.setData(exercises, workoutname);
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

	}
}
