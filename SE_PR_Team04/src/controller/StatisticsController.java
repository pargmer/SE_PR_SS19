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
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class StatisticsController.
 */
public class StatisticsController implements Initializable {

	/** The statistics. */
	List<Statistic> statistics;
	
	/** The timestatistics. */
	List<Statistic> timestatistics;
	
	/** The root. */
	@FXML
	private AnchorPane root;

	/** The pie chart. */
	@FXML
	private PieChart pieChart;

	/** The x axis. */
	@FXML
	private CategoryAxis xAxis;

	/** The y axis. */
	@FXML
	private NumberAxis yAxis;

	/** The bar chart. */
	@FXML
	private BarChart<String, Number> barChart;

	/** The dpto. */
	@FXML
	private DatePicker dpfrom, dpto;

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
	 * Handle btn search.
	 *
	 * @param event the event
	 */
	@FXML
	private void handleBtn_search(ActionEvent event) {
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();

		try {
			timestatistics = Database.getInstance().getStatisticBetween(dpfrom.getValue(), dpto.getValue());
			for (Statistic stat : timestatistics) {
				dataSeries1.getData().add(new XYChart.Data<>(stat.getName(), stat.getCount()));
			}
			barChart.getData().clear();
			barChart.getData().add(dataSeries1);
		} catch (SQLException ex) {
			Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
		xAxis = new CategoryAxis();
		xAxis.setLabel("Workouts");

		yAxis = new NumberAxis();
		yAxis.setLabel("Anzahl");

		try {
			statistics = Database.getInstance().getStatistic();
			for (Statistic stat : statistics) {
				dataSeries1.getData().add(new XYChart.Data<>(stat.getName(), stat.getCount()));
			}
			barChart.getData().add(dataSeries1);
		} catch (SQLException ex) {
			Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}