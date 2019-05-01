/*
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
public class MainController implements Initializable {

	/** The exercises. */
	private List<Exercise> exercises;

	/** The workouts. */
	private List<Workout> workouts;

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The lb test. */
	@FXML
	private Label lb_test;

	/** The lv exercises. */
	@FXML
	private ListView<String> lv_exercises;

	/** The ov exercises. */
	private ObservableList<String> ov_exercises;

	/** The cb workouts. */
	@FXML
	private ComboBox<String> cb_workouts;

	/** The ov workouts. */
	private ObservableList<String> ov_workouts;

	/** The btn new workout. */
	@FXML
	private Button btn_NewWorkout, btn_deleteWorkout;

	/**
	 * Handle button new workout.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleButton_newWorkout(ActionEvent event) throws IOException {

		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Create_Workout.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Create Workout!");
		stage.setScene(new Scene(root2));
		stage.show();
		oldStage.close();

	}
	
	//JAVADOC
	
	@FXML
	private void handleBtn_deleteWorkout(ActionEvent event) throws IOException, SQLException {

		for (Workout workout : workouts) {
			if (workout.getName().equals(cb_workouts.getValue()) == true) {
				workouts.remove(workout);
			}
		}

		ReadAndWriteCSV.getInstance().writeWorkoutsOnCSV(workouts);
		ov_workouts.remove(cb_workouts.getValue());
		cb_workouts.setItems(ov_workouts);
		cb_workouts.setValue("Wähle ein Workout aus!");

		ov_exercises.removeAll();
		lv_exercises.setItems(ov_exercises);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<String> sworkouts = new LinkedList<String>();
		try {
			workouts = ReadAndWriteCSV.getInstance().readWorkoutsFromCsv("workouts.csv");
			exercises = ReadAndWriteCSV.getInstance().readExercisesFromCsv("exercises.csv");

		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Workout workout : workouts) {
			sworkouts.add(workout.getName());
		}
		ov_workouts = FXCollections.observableArrayList(sworkouts);
		cb_workouts.setItems(ov_workouts);

	}

	/**
	 * Combo changed.
	 *
	 * @param event the event
	 */
	@FXML
	public void comboChanged(ActionEvent event) {

		try {
			workouts = ReadAndWriteCSV.getInstance().readWorkoutsFromCsv("workouts.csv");
			exercises = ReadAndWriteCSV.getInstance().readExercisesFromCsv("exercises.csv");

		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		List<String> lvexercises = new LinkedList<String>();

		/*
		 * for(Workout workout : workouts) { System.out.println(workout.getName());
		 * System.out.println(workout.getDate()); System.out.println(); for(int i = 0; i
		 * < workout.getExercises().size();i++) {
		 * System.out.println(workout.getExercises().get(i).getName());
		 * System.out.println(workout.getExercises().get(i).getReps());
		 * System.out.println(workout.getExercises().get(i).getTrains()); } }
		 */

		lvexercises.clear();
		for (Workout workout : workouts) {
			if (cb_workouts.getValue().equals(workout.getName()) == true) {

				for (int i = 0; i < workout.getExercises().size(); i++) {
					lvexercises.add(workout.getExercises().get(i).getName());
				}

			}

		}
		ov_exercises = FXCollections.observableArrayList(lvexercises);
		lv_exercises.setItems(ov_exercises);
	}

}
