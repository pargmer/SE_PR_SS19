package controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Label;
import java.awt.TextField;
import java.net.URL;
import java.sql.SQLException;
import model.Exercise;
import model.Database;
import model.ExerciseTV;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Workout;

public class StartWorkoutController implements Initializable {
    
    String workoutname;
     Workout workout = new Workout();
    
    @FXML
    private Label lb_workoutname;
    
    @FXML
    private TextField tf_date;
    
    @FXML
    TableView<ExerciseTV> tvExercise;
    ObservableList<ExerciseTV> olist;

    @FXML
    TableColumn<ExerciseTV, String> nameCol = new TableColumn<ExerciseTV,String>("Name");

    @FXML
    TableColumn<ExerciseTV, String> muscleCol = new TableColumn<ExerciseTV,String>("Muscle");

    @FXML
    TableColumn<ExerciseTV, Integer> repsCol = new TableColumn<ExerciseTV,Integer>("Reps");

    @FXML
    TableColumn<ExerciseTV, Boolean> activeCol = new TableColumn<ExerciseTV,Boolean>("Select");
    
    
    
    public void setData(String oworkoutname) {
        this.workoutname = oworkoutname;
        try {
             workout = Database.getInstance().getWorkoutInfo(workoutname);
            List<Exercise> exercises = Database.getInstance().getExercisesFromWorkout(workoutname);
        } catch (SQLException ex) {
            Logger.getLogger(StartWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb_workoutname.setText(workoutname);
        tf_date.setText("Starting from "+ workout.getDate().getDayOfMonth() + "."+ workout.getDate().getMonthValue()+"."+workout.getDate().getYear());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        
        nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("name"));
        muscleCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("trains"));
        repsCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Integer>("reps"));

        activeCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, Boolean>("active"));
       
    }
}


