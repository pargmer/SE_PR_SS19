/*
 * 
 */
package model;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Workout.
 */
public class Workout {

	/** The name. */
	private String name;
	
	/** The date. */
	private Date date;
	
	/** The exercises. */
	private List<Exercise> exercises;
	
	
	
	

	/**
	 * Instantiates a new workout.
	 *
	 * @param name the name
	 * @param date the date
	 * @param exercises the exercises
	 */
	public Workout(String name, Date date, List<Exercise> exercises) {
		
		this.name = name;
		this.date = date;
		this.exercises = exercises;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the exercises.
	 *
	 * @param exercises the new exercises
	 */
	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	
	
	/**
	 * Adds the exercise.
	 *
	 * @param exercise the exercise
	 * @return the boolean
	 */
	public Boolean addExercise(Exercise exercise) {
		
		if(exercises.add(exercise) == true) {
			exercises.add(exercise);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Delete exercise.
	 *
	 * @param name the name
	 * @return the boolean
	 */
	public Boolean deleteExercise(String name) {
		
		for(int i=0; i < exercises.size();i++) {
			
			if(exercises.get(i).getName().toString() == name) {
				 exercises.remove(i);
				 return true;
			}
		}
		
		return false;
	}
	
	
	/*public Exercise getExercise(String name) {
		
		
		for(int i=0; i < exercises.size();i++) {
			
			if(exercises.get(i).getName().toString() == name) {
				return exercises.get(i);
			}
		}
		return null;
	}*/
	
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the exercises.
	 *
	 * @return the exercises
	 */
	public List<Exercise> getExercises() {
		return exercises;
	}
	
}
