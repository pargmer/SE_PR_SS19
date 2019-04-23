package app;
import java.util.LinkedList;

public class Exercise implements Comparable<Exercise> {
	
	private String name;
	int i;
	private int reps;
	private LinkedList<Muscle> trains;
	
	public Exercise(String name) {
		this.name = name;
		trains = new LinkedList<Muscle>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public LinkedList<Muscle> getMuscle() {
		return trains;
	}
	
	public boolean addMuscle(Muscle m) {
		for(Muscle muscle : trains) {
			if(muscle.equals(m)) {
				return false;
			}
		}
		trains.add(m);
		return true;
	}

	@Override
	public int compareTo(Exercise arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
