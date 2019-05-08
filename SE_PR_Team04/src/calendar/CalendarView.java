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

public class CalendarView extends Application {

	private final LocalTime firstSlotStart = LocalTime.of(0, 0);
	private final Duration slotLength = Duration.ofMinutes(15);
	private final LocalTime lastSlotStart = LocalTime.of(23, 59);

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private final List<TimeSlot> timeSlots = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane calendarView = new GridPane();

		ObjectProperty<TimeSlot> mouseAnchor = new SimpleObjectProperty<>();

		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
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

	private boolean isBetween(TimeSlot testSlot, TimeSlot startSlot, TimeSlot endSlot) {
		boolean daysBetween = testSlot.getDayOfWeek().compareTo(startSlot.getDayOfWeek())
				* endSlot.getDayOfWeek().compareTo(testSlot.getDayOfWeek()) >= 0;

		boolean timesBetween = testSlot.getTime().compareTo(startSlot.getTime())
				* endSlot.getTime().compareTo(testSlot.getTime()) >= 0;

		return daysBetween && timesBetween;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
