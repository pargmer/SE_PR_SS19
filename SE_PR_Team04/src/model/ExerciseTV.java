package model;

import javafx.scene.control.CheckBox;

public class ExerciseTV {

	private String name;
	private String trains;
	private int reps;
	private CheckBox active;

	public ExerciseTV(String sname, String strains, int sreps, boolean active) {
		this.name = sname;
		this.trains = strains;
		this.reps = sreps;
		this.active = new CheckBox();
	}

	public String getName() {
		return name;
	}

	public String getTrains() {
		return trains;
	}

	public int getReps() {
		return reps;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTrains(String trains) {
		this.trains = trains;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public CheckBox getActive() {
		return active;
	}

	public void setActive(CheckBox active) {
		this.active = active;
	}

}