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
	private Label lbTest;

	/** The lv exercises. */
	@FXML
	private ListView<String> lvExercises;

	/** The ov exercises. */
	private ObservableList<String> ovExercises;

	/** The cb workouts. */
	@FXML
	private ComboBox<String> cbWorkouts;

	/** The ov workouts. */
	private ObservableList<String> ovWorkouts;

	/** The btn new workout. */
	@FXML
	private Button btnNewWorkout;
	private Button btnDeleteWorkout;

	/**
	 * Handle button new workout.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleButtonNewWorkout(ActionEvent event) throws IOException {

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
	private void handleBtnDeleteWorkout(ActionEvent event) throws IOException, SQLException {

		for (Workout workout : workouts) {
			if (workout.getName().equals(cbWorkouts.getValue()) == true) {
				workouts.remove(workout);
			}
		}

		ReadAndWriteCSV.getInstance().writeWorkoutsOnCSV(workouts);
		ovWorkouts.remove(cbWorkouts.getValue());
		cbWorkouts.setItems(ovWorkouts);
		cbWorkouts.setValue("Wähle ein Workout aus!");

		ovExercises.removeAll();
		lvExercises.setItems(ovExercises);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<String> sworkouts = new LinkedList<>();
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
		ovWorkouts = FXCollections.observableArrayList(sworkouts);
		cbWorkouts.setItems(ovWorkouts);

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

		List<String> lvexercises = new LinkedList<>();

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
			if (cbWorkouts.getValue().equals(workout.getName())) {

				for (int i = 0; i < workout.getExercises().size(); i++) {
					lvexercises.add(workout.getExercises().get(i).getName());
				}

			}

		}
		System.out.println(lvexercises.size());
		ovExercises = FXCollections.observableArrayList(lvexercises);
		lvExercises.setItems(ovExercises);
	}

}
