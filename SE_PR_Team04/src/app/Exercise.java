package app;
import java.util.LinkedList;

public class Exercise {
	
	private String name;
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
	
	public boolean addMuscle(Muscle muscle) {
		for(Muscle m : trains) {
			if(m.getName().equals(muscle.getName())){
				return false;
			}
		}
		trains.add(muscle);
		return true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Übung: "+this.name+"\nFolgende Muskeln werden trainiert:\n");
		for(Muscle m : trains) {
			sb.append("-"+m.getName()+"\n");
		}
		return sb.toString();
	}
	

}
