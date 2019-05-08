package calendar;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class TimeSlot {

	private final LocalDateTime start;
	private final Duration duration;
	private final Region view;

	private final BooleanProperty selected = new SimpleBooleanProperty();
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	public final BooleanProperty selectedProperty() {
		return selected;
	}

	public final boolean isSelected() {
		return selectedProperty().get();
	}

	public final void setSelected(boolean selected) {
		selectedProperty().set(selected);
	}

	public TimeSlot(LocalDateTime start, Duration duration) {
		this.start = start;
		this.duration = duration;

		view = new Region();
		view.setMinSize(80, 20);
		view.getStyleClass().add("time-slot");

		selectedProperty().addListener(
				(obs, wasSelected, isSelected) -> view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));

	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalTime getTime() {
		return start.toLocalTime();
	}

	public DayOfWeek getDayOfWeek() {
		return start.getDayOfWeek();
	}

	public Duration getDuration() {
		return duration;
	}

	public Node getView() {
		return view;
	}

}
