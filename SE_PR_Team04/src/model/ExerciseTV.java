/*
 * 
 */
package model;

import javafx.scene.control.CheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ExerciseTV.
 */
public class ExerciseTV {

	/** The name. */
	private String name;
	
	/** The trains. */
	private String trains;
	
	/** The reps. */
	private int reps;
	
	/** The active. */
	private CheckBox active;

	/**
	 * Instantiates a new exercise TV.
	 *
	 * @param sname the sname
	 * @param strains the strains
	 * @param sreps the sreps
	 * @param active the active
	 */
	public ExerciseTV(String sname, String strains, int sreps, boolean active) {
		this.name = sname;
		this.trains = strains;
		this.reps = sreps;
		this.active = new CheckBox();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the trains.
	 *
	 * @return the trains
	 */
	public String getTrains() {
		return trains;
	}

	/**
	 * Gets the reps.
	 *
	 * @return the reps
	 */
	public int getReps() {
		return reps;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the trains.
	 *
	 * @param trains the new trains
	 */
	public void setTrains(String trains) {
		this.trains = trains;
	}

	/**
	 * Sets the reps.
	 *
	 * @param reps the new reps
	 */
	public void setReps(int reps) {
		this.reps = reps;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public CheckBox getActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(CheckBox active) {
		this.active = active;
	}

}