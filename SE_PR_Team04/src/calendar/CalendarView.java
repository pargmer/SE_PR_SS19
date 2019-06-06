/*
 * 
 */
package calendar;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class CalendarView.
 */
public class CalendarView extends Application {

	/** The first slot start. */
	private final LocalTime firstSlotStart = LocalTime.of(0, 0);
	
	/** The slot length. */
	private final Duration slotLength = Duration.ofMinutes(15);
	
	/** The last slot start. */
	private final LocalTime lastSlotStart = LocalTime.of(23, 59);

	/** The Constant SELECTED_PSEUDO_CLASS. */
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	/** The time slots. */
	private final List<TimeSlot> timeSlots = new ArrayList<>();

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane calendarView = new GridPane();

		ObjectProperty<TimeSlot> mouseAnchor = new SimpleObjectProperty<>();

		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() -1L);
		LocalDate endOfWeek = startOfWeek.plusDays(6);

		for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
			int slotIndex = 1;

			for (LocalDateTime startTime = date.atTime(firstSlotStart); !startTime
					.isAfter(date.atTime(lastSlotStart)); startTime = startTime.plus(slotLength)) {

				TimeSlot timeSlot = new TimeSlot(startTime, slotLength);
				timeSlots.add(timeSlot);

				registerDragHandlers(timeSlot, mouseAnchor);

				calendarView.add(timeSlot.getView(), timeSlot.getDayOfWeek().getValue(), slotIndex);

				slotIndex++;
			}
		}
	}

	/**
	 * Register drag handlers.
	 *
	 * @param timeSlot the time slot
	 * @param mouseAnchor the mouse anchor
	 */
	private void registerDragHandlers(TimeSlot timeSlot, ObjectProperty<TimeSlot> mouseAnchor) {
		timeSlot.getView().setOnDragDetected(event -> {
			mouseAnchor.set(timeSlot);
			timeSlot.getView().startFullDrag();
			timeSlots.forEach(slot -> slot.setSelected(slot == timeSlot));
		});

		timeSlot.getView().setOnMouseDragEntered(event -> {
			TimeSlot startSlot = mouseAnchor.get();
			timeSlots.forEach(slot -> slot.setSelected(isBetween(slot, startSlot, timeSlot)));
		});

		timeSlot.getView().setOnMouseReleased(event -> mouseAnchor.set(null));

	}

	/**
	 * Checks if is between.
	 *
	 * @param testSlot the test slot
	 * @param startSlot the start slot
	 * @param endSlot the end slot
	 * @return true, if is between
	 */
	private boolean isBetween(TimeSlot testSlot, TimeSlot startSlot, TimeSlot endSlot) {
		boolean daysBetween = testSlot.getDayOfWeek().compareTo(startSlot.getDayOfWeek())
				* endSlot.getDayOfWeek().compareTo(testSlot.getDayOfWeek()) >= 0;

		boolean timesBetween = testSlot.getTime().compareTo(startSlot.getTime())
				* endSlot.getTime().compareTo(testSlot.getTime()) >= 0;

		return daysBetween && timesBetween;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
