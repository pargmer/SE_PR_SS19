/*
 * 
 */
package model.test;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import model.Exercise;
import model.Workout;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkoutTest.
 */
public class WorkoutTest {
	
	/** The ex 1. */
	private static Exercise ex1 = new Exercise("Liegestütze", "Bauch", 20);
	
	/** The ex 2. */
	private static Exercise ex2 = new Exercise("Plank", "Bauch", 10);
	
	/** The exercises. */
	private static List<Exercise> exercises;
	
	/** The date. */
	private static LocalDate date;
	
	/** The workout */
	private static Workout w = new Workout("Weekend",date, exercises);
	
	/**
	 * Adds the exercise test.
	 */
	@Test
	final void addExerciseTest() {
		
		assertEquals(true, w.addExercise(ex1));
	}
	
	/**
	 * Delete exercise test.
	 */
	@Test
	final void deleteExerciseTest() {
		assertEquals(false, w.deleteExercise("Plank"));
		w.addExercise(ex2);
		assertEquals(true, w.deleteExercise("Plank"));
	}

}
