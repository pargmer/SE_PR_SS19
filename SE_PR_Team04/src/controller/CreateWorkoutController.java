
/*
 * 
 */
package controller;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Database;
import model.Exercise;
import model.ExerciseTV;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateWorkoutController.
 */
public class CreateWorkoutController implements Initializable {

	/** The hexercises. */
	List<Exercise> hexercises = new LinkedList<Exercise>();

	/** The exercises. */
	List<Exercise> exercises = new LinkedList<Exercise>();

	/** The workoutname. */
	String workoutname = "";

	/** The tv exercise. */
	@FXML
	TableView<ExerciseTV> tvExercise;

	/** The olist. */
	ObservableList<ExerciseTV> olist;

	/** The name col. */
	@FXML
	TableColumn<ExerciseTV, String> nameCol = new TableColumn<ExerciseTV, String>("Name");

	/** The muscle col. */
	@FXML
	TableColumn<ExerciseTV, String> muscleCol = new TableColumn<ExerciseTV, String>("Muscle");

	/** The reps col. */
	@FXML
	TableColumn<ExerciseTV, Integer> repsCol = new TableColumn<ExerciseTV, Integer>("Reps");

        /** The unit col. */
        @FXML
        TableColumn<ExerciseTV, String> unitCol = new TableColumn<ExerciseTV, String>("Unit");
	/** The active col. */
	@FXML
	TableColumn<ExerciseTV, Boolean> activeCol = new TableColumn<ExerciseTV, Boolean>("Select");

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf workoutname. */
	@FXML
	private TextField tf_workoutname;

	/** The date picker. */
	@FXML
	private DatePicker datePicker;

	/**
	 * Handle btn new exercise.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	 */
	@FXML
	private void handleBtn_saveWorkout(ActionEvent event) {

		for (int i = 0; i < olist.size(); i++) {
			if (olist.get(i).getActive().isSelected() == true) {
				 exercises.add(new Exercise(olist.get(i).getName(), olist.get(i).getTrains(), olist.get(i).getReps(), olist.get(i).getUnit()));
			}
		}

		try {

			if (exercises.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("No exercises were selected!");

				alert.showAndWait();
			} else {

				Database.getInstance().createWorkout(tf_workoutname.getText(), datePicker.getValue(), exercises);

				Stage oldStage;
				oldStage = (Stage) root.getScene().getWindow();

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/Main.fxml"));
				Parent root2 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setTitle("Workout!");
				stage.setScene(new Scene(root2));
				stage.show();
				oldStage.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handle btn delete exercise.
	 *
	 * @param event the event
	 * @throws SQLException the SQL exception
	 */
	@FXML
	private void handleBtn_deleteExercise(ActionEvent event) throws SQLException {

		for (int i = 0; i < olist.size(); i++) {
			if (olist.get(i).getActive().isSelected() == true) {
				 Database.getInstance().deleteExercise(new Exercise(olist.get(i).getName(), olist.get(i).getTrains(), olist.get(i).getReps(), olist.get(i).getUnit()));
			}
		}

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Selected exercises were deleted!");
		alert.showAndWait();
		hexercises.clear();

		try {

			hexercises = Database.getInstance().getAllExercises();

		} catch (SQLException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}
		getExerciseList();
		tvExercise.setItems(olist);

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

		hexercises = oexercises;
		workoutname = oworkoutname;

		tf_workoutname.setText(workoutname);
	}

	/**
	 * Initialize.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	public void initialize(URL location, ResourceBundle resources) {
		try {
			hexercises = Database.getInstance().getAllExercises();

		} catch (SQLException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}

		nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("name"));
		muscleCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("trains"));
		repsCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Integer>("reps"));
                unitCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("unit"));
		activeCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Boolean>("active"));

		getExerciseList();
		tvExercise.setItems(olist);

	}

	/**
	 * Gets the exercise list.
	 *
	 * @return the exercise list
	 */
	private void getExerciseList() {

		olist = FXCollections.observableArrayList();
		for (int i = 0; i < hexercises.size(); i++) {

			olist.add(new ExerciseTV(hexercises.get(i).getName(), hexercises.get(i).getTrains(), hexercises.get(i).getReps(), hexercises.get(i).getUnit()));

		}
	}

}
