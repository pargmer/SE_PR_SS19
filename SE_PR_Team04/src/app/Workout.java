package app;
import java.time.Duration;
import java.util.LinkedList;

public class Workout {

	private String name;
	private LinkedList<Exercise> exercises;

	public Workout(String name, Duration duration) {
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
