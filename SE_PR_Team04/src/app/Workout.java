package app;
import java.time.Duration;
import java.util.LinkedList;

public class Workout {

	String name;
	Duration duration;
	LinkedList<Exercise> exercises;

	public Workout(String name, Duration duration) {
		this.name = name;
		this.duration = duration;
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
