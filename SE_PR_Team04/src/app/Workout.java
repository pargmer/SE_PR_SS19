package app;

import java.util.LinkedList;

public class Workout {

	String name;
	LinkedList<Exercise> exercises;

	public Workout(String name) {
		this.name = name;
		exercises = new LinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Exercise> getExercises() {
		return exercises;
	}

}
