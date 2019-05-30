
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

/**
 * The Class CreateWorkoutController.
 */
public class CreateWorkoutController implements Initializable {

	List<Exercise> hexercises = new LinkedList<Exercise>();

	/** The exercises. */
	List<Exercise> exercises = new LinkedList<Exercise>();

	/** The workoutname. */
	String workoutname = "";

	@FXML
	TableView<ExerciseTV> tvExercise;
	ObservableList<ExerciseTV> olist;

	@FXML
	TableColumn<ExerciseTV, String> nameCol = new TableColumn<ExerciseTV, String>("Name");

	@FXML
	TableColumn<ExerciseTV, String> muscleCol = new TableColumn<ExerciseTV, String>("Muscle");

	@FXML
	TableColumn<ExerciseTV, Integer> repsCol = new TableColumn<ExerciseTV, Integer>("Reps");

	@FXML
	TableColumn<ExerciseTV, Boolean> activeCol = new TableColumn<ExerciseTV, Boolean>("Select");

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf workoutname. */
	@FXML
	private TextField tf_workoutname;

	@FXML
	private DatePicker datePicker;

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
	private void handleBtn_saveWorkout(ActionEvent event) {

		for (int i = 0; i < olist.size(); i++) {
			if (olist.get(i).getActive().isSelected() == true) {
				exercises.add(new Exercise(olist.get(i).getName(), olist.get(i).getTrains(), olist.get(i).getReps()));
			}
		}

		try {

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
		} catch (SQLException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

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

	public void initialize(URL location, ResourceBundle resources) {
		try {
			hexercises = Database.getInstance().getAllExercises();

		} catch (SQLException ex) {
			Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}

		nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("name"));
		muscleCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("trains"));
		repsCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Integer>("reps"));

		activeCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Boolean>("active"));

		getExerciseList();
		tvExercise.setItems(olist);

	}

	private void getExerciseList() {

		olist = FXCollections.observableArrayList();
		for (int i = 0; i < hexercises.size(); i++) {

			olist.add(new ExerciseTV(hexercises.get(i).getName(), hexercises.get(i).getTrains(),
					hexercises.get(i).getReps(), true));

		}
	}

}
