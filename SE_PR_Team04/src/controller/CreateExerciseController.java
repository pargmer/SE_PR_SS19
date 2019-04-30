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

public class CreateExerciseController {

	@FXML
	private AnchorPane root;
	
	@FXML
	private TextField tf_name,tf_reps,tf_muscle;
	
	List<Exercise> exercises = new LinkedList<Exercise>();
	String workoutname;
	
	@FXML
	private void handleBtn_saveExercise(ActionEvent event) throws IOException {
		
		
		
		Exercise newExercise = new Exercise(tf_name.getText().toString(), tf_muscle.getText().toString(),Integer.parseInt(tf_reps.getText().toString()));
		exercises.add(newExercise);		
		
		  Stage oldStage;
          oldStage = (Stage)root.getScene().getWindow();

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
	
	public void setData(List<Exercise> oexercises, String oworkoutname) {
		exercises = oexercises;
		workoutname = oworkoutname;
		
	}
}
