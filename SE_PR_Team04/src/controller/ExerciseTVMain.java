package controller;

public class ExerciseTVMain {
	
	 private String name;
	    private int reps;

	    public ExerciseTVMain(String name, int reps) {
	        this.name = name;
	        this.reps = reps;
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public int getReps() {
	        return reps;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setReps(int reps) {
	        this.reps = reps;
	    }
	    

}
