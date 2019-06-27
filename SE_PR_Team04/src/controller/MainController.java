/*
 * 
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
public class MainController implements Initializable {

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The lb test. */
	@FXML
	private Label lb_test;

	/** The lv exercises. */
	@FXML
	TableView<ExerciseTVMain> tvExercise;
	
	/** The olist. */
	ObservableList<ExerciseTVMain> olist;

	/** The name col. */
	@FXML
	TableColumn<ExerciseTVMain, String> nameCol = new TableColumn<ExerciseTVMain, String>("Name");

	/** The reps col. */
	@FXML
	TableColumn<ExerciseTVMain, Integer> repsCol = new TableColumn<ExerciseTVMain, Integer>("Reps");

        /** The unit col. */
        @FXML
        TableColumn<ExerciseTVMain, String> unitCol = new TableColumn<ExerciseTVMain, String>("Unit");
	/** The cb workouts. */
	@FXML
	private ComboBox<String> cb_workouts;

	/** The ov workouts. */
	private ObservableList<String> ov_workouts;

	/** The btn new workout. */
	@FXML
	private Button btn_NewWorkout, btn_deleteWorkout, btn_start;

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

	/**
	 * Handle btn delete workout.
	 *
	 * @param event the event
	 */
	@FXML
	private void handleBtn_deleteWorkout(ActionEvent event) {

		try {
			Database.getInstance().deleteWorkout(cb_workouts.getValue());
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ov_workouts.remove(cb_workouts.getValue());
		cb_workouts.setItems(ov_workouts);
		cb_workouts.setValue("Choose a Workout!");

	}

	/**
	 * Handl btn export workouts.
	 *
	 * @param event the event
	 * @throws SQLException the SQL exception
	 * @throws IOException  Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handlBtn_ExportWorkouts(ActionEvent event) throws SQLException, IOException {

		List<String> workoutsname = new LinkedList<String>();

		workoutsname = Database.getInstance().getWorkouts();
		List<Workout> workouts = new LinkedList<Workout>();
		List<Exercise> exercises = new LinkedList<Exercise>();
		Workout workout;
		for (String s : workoutsname) {

			workout = Database.getInstance().getWorkoutInfo(s);
			exercises = Database.getInstance().getExercisesFromWorkout(s);
			workouts.add(new Workout(s, workout.getDate(), exercises));
		}

		ReadAndWriteCSV.getInstance().writeWorkoutsOnCSV(workouts);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("The workouts were exported to the workouts.csv file!");

		alert.showAndWait();
	}

	/**
	 * Handl btn import workouts.
	 *
	 * @param event the event
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    private void handlBtn_ImportWorkouts(ActionEvent event) throws SQLException, IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        System.out.println(file.getAbsolutePath());
        
        
        List<Workout> newWorkouts = ReadAndWriteCSV.getInstance().readWorkoutsFromCsv(file.getAbsolutePath());
        
        for(Workout workout : newWorkouts){
            Database.getInstance().createWorkoutfromCSV(workout);
        }
        
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("The workouts were imported from the workouts.csv file!");

        alert.showAndWait();
        
          List<String> sworkouts = new LinkedList<String>();
        try {
            sworkouts = Database.getInstance().getWorkouts();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ov_workouts = FXCollections.observableArrayList(sworkouts);
        cb_workouts.setItems(ov_workouts);

    }
	
	/**
	 * Handle btn start workout.
	 *
	 * @param event the event
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleBtn_StartWorkout(ActionEvent event) throws SQLException, IOException {
		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();

		fxmlLoader.setLocation(getClass().getResource("/view/Start_Workout.fxml"));

		Parent root2 = (Parent) fxmlLoader.load();
		StartWorkoutController start = fxmlLoader.getController();
		start.setData(cb_workouts.getValue());
		Stage stage = new Stage();
		stage.setTitle("Create Workout!");
		stage.setScene(new Scene(root2));
		stage.show();
		oldStage.close();

	}

	/**
	 * Handle button get statistics.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    private void handleButton_getStatistics(ActionEvent event) throws IOException {

        Stage oldStage;
        oldStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Statistics.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Create Workout!");
        stage.setScene(new Scene(root2));
        stage.show();
        oldStage.close();
        
	}
	/**
	 * Initialize.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTVMain, String>("name"));
		repsCol.setCellValueFactory(new PropertyValueFactory<ExerciseTVMain, Integer>("reps"));
                unitCol.setCellValueFactory(new PropertyValueFactory<ExerciseTVMain, String>("unit"));
		List<String> sworkouts = new LinkedList<String>();

		try {
			sworkouts = Database.getInstance().getWorkouts();
		} catch (SQLException e) {
			e.printStackTrace();
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
		btn_start.setDisable(false);

		try {

			List<String> lvexercises = new LinkedList<String>();
			List<Exercise> exercises = Database.getInstance().getExercisesFromWorkout(cb_workouts.getValue());
			olist = FXCollections.observableArrayList();
			lvexercises.clear();
			for (Exercise exercise : exercises) {

				lvexercises.add(exercise.getName());
				olist.add(new ExerciseTVMain(exercise.getName(), exercise.getReps(), exercise.getUnit()));
			}

			tvExercise.setItems(olist);
			
		} catch (SQLException ex) {

			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
