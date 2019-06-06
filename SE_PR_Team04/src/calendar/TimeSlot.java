/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class TimeSlot.
 */
public class TimeSlot {

	/** The start. */
	private final LocalDateTime start;
	
	/** The duration. */
	private final Duration duration;
	
	/** The view. */
	private final Region view;

	/** The selected. */
	private final BooleanProperty selected = new SimpleBooleanProperty();
	
	/** The Constant SELECTED_PSEUDO_CLASS. */
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	/**
	 * Selected property.
	 *
	 * @return the boolean property
	 */
	public final BooleanProperty selectedProperty() {
		return selected;
	}

	/**
	 * Checks if is selected.
	 *
	 * @return true, if is selected
	 */
	public final boolean isSelected() {
		return selectedProperty().get();
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public final void setSelected(boolean selected) {
		selectedProperty().set(selected);
	}

	/**
	 * Instantiates a new time slot.
	 *
	 * @param start the start
	 * @param duration the duration
	 */
	public TimeSlot(LocalDateTime start, Duration duration) {
		this.start = start;
		this.duration = duration;

		view = new Region();
		view.setMinSize(80, 20);
		view.getStyleClass().add("time-slot");

		selectedProperty().addListener(
				(obs, wasSelected, isSelected) -> view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));

	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public LocalDateTime getStart() {
		return start;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public LocalTime getTime() {
		return start.toLocalTime();
	}

	/**
	 * Gets the day of week.
	 *
	 * @return the day of week
	 */
	public DayOfWeek getDayOfWeek() {
		return start.getDayOfWeek();
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public Node getView() {
		return view;
	}

}
