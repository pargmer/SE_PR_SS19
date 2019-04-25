/*
 * 
 */
package model;

import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class Exercise.
 */
public class Exercise {

	
	/** The name. */
	private String name;
	
	/** The trains. */
	private String trains;
	
	/** The reps. */
	private int reps;
	
	/**
	 * Instantiates a new exercise.
	 *
	 * @param name the name
	 * @param trains the trains
	 * @param reps the reps
	 */
	public Exercise(String name, String trains, int reps) {
		this.name = name;
		this.trains = trains;
		this.reps = reps;
	}
		
	/**
	 * Instantiates a new exercise.
	 */
	public Exercise() {
		super();
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
	 * Gets the trains.
	 *
	 * @return the trains
	 */
	public String getTrains() {
		return trains;
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
	 * Gets the reps.
	 *
	 * @return the reps
	 */
	public int getReps() {
		return reps;
	}
	
	/**
	 * Sets the reps.
	 *
	 * @param reps the new reps
	 */
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	/*public Muscle getMuscle(String name) {
		for(int i=0; i < trains.size();i++) {
			if(trains.get(i).getName() == name) {
				return trains.get(i);		
			}
		}	
		return null;
	}
	
	public Boolean addMuscle(String name) {
		if(trains.add(new Muscle(name)) == true) {
			trains.add(new Muscle(name));
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public Boolean deleteMuscle(String name) {
		for(int i=0; i < trains.size();i++) {
			if(trains.get(i).getName() == name) {
				trains.remove(i);
				return true;
			}
		}
		
		return false;
	}*/
	
}
