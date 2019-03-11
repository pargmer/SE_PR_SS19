package app;
import java.util.LinkedList;

public class Exercise implements Comparable<Exercise> {
	
	private String name;
	private int difficulty;
	private LinkedList<Muscle> trains;
	
	public Exercise(String name, int difficulty) {
		this.name = name;
		this.difficulty = difficulty;
		trains = new LinkedList<Muscle>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
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

	@Override
	public int compareTo(Exercise e) {
		int i = this.difficulty - e.getDifficulty();
		if(i == 0) {
			return this.getName().compareTo(e.getName());
		}
		return i;
	}

	

}
