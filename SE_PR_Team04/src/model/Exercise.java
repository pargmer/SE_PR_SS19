package model;

import java.util.List;


public class Exercise {

	
	private String name;
	private String trains;
	private int reps;
	
	public Exercise(String name, String trains, int reps) {
		this.name = name;
		this.trains = trains;
		this.reps = reps;
	}
		
	public Exercise() {
		super();
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrains() {
		return trains;
	}
	public void setTrains(String trains) {
		this.trains = trains;
	}
	public int getReps() {
		return reps;
	}
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
