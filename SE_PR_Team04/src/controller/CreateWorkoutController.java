package controller;

import java.io.IOException;
import java.net.URL;
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
import model.Workout;

public class CreateWorkoutController{

	
	List<Exercise> exercises = new LinkedList<Exercise>();
	
	String workoutname ="";
	@FXML
	private AnchorPane root;
	
	@FXML
	private TextField tf_workoutname;
	
	 
	 @FXML
	 private ListView<String> lv_exercises;
	 private ObservableList<String> ov_exercises;
	
	@FXML
	private void handleBtn_newExercise(ActionEvent event) throws IOException {
		
		  Stage oldStage;
          oldStage = (Stage)root.getScene().getWindow();

          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(getClass().getResource("/view/Create_Exercise.fxml"));
          Parent root2 = (Parent) fxmlLoader.load();
          CreateExerciseController create = fxmlLoader.getController();
          create.setData(exercises, tf_workoutname.getText().toString() );
          Stage stage = new Stage();
          stage.setTitle("Create Workout!");
          stage.setScene(new Scene(root2));  
          stage.show();
          oldStage.close();
		
	}
	
	@FXML
	private void handleBtn_saveWorkout(ActionEvent event) throws IOException {
		
		  Stage oldStage;
          oldStage = (Stage)root.getScene().getWindow();

          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(getClass().getResource("/view/Create_Workout.fxml"));
          Parent root2 = (Parent) fxmlLoader.load();
          Stage stage = new Stage();
          stage.setTitle("Create Workout!");
          stage.setScene(new Scene(root2));  
          stage.show();
          oldStage.close();
		
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
	
	
}
