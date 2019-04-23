package app;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.LinkedList;
import java.util.Scanner;

import gui.Home;

public class FitnessManagerMain {
	

	private LinkedList<Workout> workouts;

	public static void main(String[] args) {

		Muscle bizeps = new Muscle("Bizeps");
		Muscle trizeps = new Muscle("Trizeps");
		Muscle brust = new Muscle("Brust");
		Muscle quadrizeps = new Muscle("Beinstrecker");
		Muscle schulter = new Muscle("Schulter");

		Exercise liegestuetze = new Exercise("Liegestütze");
		liegestuetze.addMuscle(brust);
		liegestuetze.addMuscle(trizeps);
		liegestuetze.addMuscle(schulter);

		Exercise dips = new Exercise("Dips");
		dips.addMuscle(trizeps);

		new Home().setVisible(true);
		

	}

	public boolean addWorkout(String name) {
		Workout workout = new Workout(name);
		for (Workout w : workouts) {
			if (w.equals(workout)) {
				return false;
			}
		}
		workouts.add(workout);
		return true;

	}
	
	

	public void setUp() {

	}

}
