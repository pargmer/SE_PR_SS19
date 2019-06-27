/*
 * 
 */
package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Exercise;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.Database;

// TODO: Auto-generated Javadoc
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

        
        /** The cb unit. */
        @FXML
        private ComboBox<String> cb_unit;
        
        /** The ov unit. */
        private ObservableList<String> ov_unit;
	/**
	 * Handle btn save exercise.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleBtn_saveExercise(ActionEvent event) throws IOException {

		try {
			  Database.getInstance().createExercise(tf_name.getText(), tf_muscle.getText(), Integer.parseInt(tf_reps.getText()),cb_unit.getValue());
		} catch (SQLException ex) {
			Logger.getLogger(CreateExerciseController.class.getName()).log(Level.SEVERE, null, ex);
		}

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
	 * Handle btn back to main.
	 *
	 * @param event the event
	 */
	@FXML
	private void handleBtn_backToMain(ActionEvent event) {
		try {
			Stage oldStage;
			oldStage = (Stage) root.getScene().getWindow();

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/view/Main.fxml"));
			Parent root2 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Workouts");
			stage.setScene(new Scene(root2));
			stage.show();
			oldStage.close();
		} catch (IOException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}
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

                 ov_unit = FXCollections.observableArrayList("Wh","min","sek");
                 cb_unit.setItems(ov_unit);
	}
}
