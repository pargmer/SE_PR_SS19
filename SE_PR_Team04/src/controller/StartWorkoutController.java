package controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Label;
import java.awt.TextField;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Workout;

public class StartWorkoutController implements Initializable {

	String workoutname;
	Workout workout = new Workout();
	List<Exercise> wexercises;
	List<Exercise> allexercises;
	Boolean allchecked;

	@FXML
	private Label lb_workoutname;

	@FXML
	private AnchorPane root;

	@FXML
	private TextField tf_date;

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
			alert.setContentText("Es wurden nicht alle Aufgaben erledigt");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Gratulation du hast alle Übungen erledigt und somit das Workout bestanden!!");

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
						olist.add(new ExerciseTV(ahelp.getName(), ahelp.getTrains(), ahelp.getReps(), true));
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("name"));
		muscleCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("trains"));
		repsCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Integer>("reps"));
		activeCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Boolean>("active"));

	}
}
