package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

public class StatisticsController implements Initializable {

    List<Statistic> statistics;
    @FXML
    private AnchorPane root;

    @FXML
    private PieChart pieChart;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            statistics = Database.getInstance().getStatistic();
            for (Statistic stat : statistics) {
                pieChart.getData().add(new PieChart.Data(stat.getName(), stat.getCount()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}