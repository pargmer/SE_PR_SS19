/*
 * 
 */
package controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import model.Exercise;
import model.Database;
import model.ExerciseTV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Workout;

// TODO: Auto-generated Javadoc
/**
 * The Class StartWorkoutController.
 */
public class StartWorkoutController implements Initializable {

	/** The workoutname. */
	String workoutname;
	
	/** The workout. */
	Workout workout = new Workout();
	
	/** The wexercises. */
	List<Exercise> wexercises;
	
	/** The allexercises. */
	List<Exercise> allexercises;
	
	/** The allchecked. */
	Boolean allchecked;

	/** The lb workoutname. */
	@FXML
	private Label lb_workoutname;

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf date. */
	@FXML
	private TextField tf_date;

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

        @FXML
        TableColumn<ExerciseTV, String> unitCol = new TableColumn<ExerciseTV, String>("Unit");
        
	/** The active col. */
	@FXML
	TableColumn<ExerciseTV, Boolean> activeCol = new TableColumn<ExerciseTV, Boolean>("Select");

	/**
	 * Handle btn done.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@FXML
	private void handleBtn_Done(ActionEvent event) throws IOException, SQLException {
		allchecked = true;
		for (int i = 0; i < olist.size(); i++) {
			if (olist.get(i).getActive().isSelected() != true) {
				allchecked = false;
			}
		}

		if (allchecked == false) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Not all Exercise are done!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Congratulations you have done all exercises and passed the workout!");

			alert.showAndWait();

			Database.getInstance().WorkoutDone(workout);

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
	 * @param oworkoutname the new data
	 */
	public void setData(String oworkoutname) {
		this.workoutname = oworkoutname;
		try {
			workout = Database.getInstance().getWorkoutInfo(workoutname);
			wexercises = Database.getInstance().getExercisesFromWorkout(workoutname);
			allexercises = Database.getInstance().getAllExercises();
			olist = FXCollections.observableArrayList();
			for (Exercise help : wexercises) {
				for (Exercise ahelp : allexercises) {
					if (help.getName().equals(ahelp.getName())) {
						olist.add(new ExerciseTV(ahelp.getName(), ahelp.getTrains(), ahelp.getReps(), ahelp.getUnit()));
					}
				}
			}

		} catch (SQLException ex) {
			Logger.getLogger(StartWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
		}
		lb_workoutname.setText(workoutname);
		tf_date.setText("Starting from " + workout.getDate().getDayOfMonth() + "." + workout.getDate().getMonthValue()
				+ "." + workout.getDate().getYear());
		tvExercise.setItems(olist);
	}

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("name"));
		muscleCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("trains"));
		repsCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Integer>("reps"));
                unitCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("unit"));
		activeCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Boolean>("active"));

	}
}
