package model.test;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import model.Exercise;
import model.Workout;

public class WorkoutTest {
	
	private static Exercise ex1 = new Exercise("Liegestütze", "Bauch", 20);
	private static Exercise ex2 = new Exercise("Plank", "Bauch", 10);
	private static List<Exercise> exercises;
	private static LocalDate date;
	private static Workout w = new Workout("Weekend",date, exercises);
	
	@Test
	final void addExerciseTest() {
		
		assertEquals(true, w.addExercise(ex1));
	}
	
	@Test
	final void deleteExerciseTest() {
		assertEquals(false, w.deleteExercise("Plank"));
		w.addExercise(ex2);
		assertEquals(true, w.deleteExercise("Plank"));
	}

}
