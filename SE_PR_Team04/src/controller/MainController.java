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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

public class MainController implements Initializable{
	
	private List<Exercise> exercises = new LinkedList<Exercise>();;
	
	 @FXML
	 private AnchorPane root;
	 
	 @FXML
	 private ListView<Exercise> lv_exercises;
	 private ObservableList<Exercise> ov_exercises;
	 
	 @FXML
	 private ComboBox<String> cb_workouts;
	 private ObservableList<String> ov_workouts;
	 
	@FXML
	private Button btn_NewWorkout;
	
	@FXML
	private void handleButton_newWorkout(ActionEvent event) throws IOException {
		
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	
	}
	
}
