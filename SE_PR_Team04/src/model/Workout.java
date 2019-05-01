package model;

import java.util.Date;
import java.util.List;

public class Workout {

	private String name;
	private Date date;
	private List<Exercise> exercises;
	
	
	
	

	public Workout(String name, Date date, List<Exercise> exercises) {
		
		this.name = name;
		this.date = date;
		this.exercises = exercises;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	public Boolean addExercise(Exercise exercise) {
		
		if(exercises.add(exercise) == true) {
			exercises.add(exercise);
			return true;
		}
		else {
			return false;
		}
	}
	
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
	
	
	public Date getDate() {
		return date;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}
	
}
