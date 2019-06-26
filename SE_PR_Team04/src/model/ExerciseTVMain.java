package model;

public class ExerciseTVMain {

    private String name;
    private int reps;
    private String unit;

    public ExerciseTVMain(String name, int reps, String unit) {
        this.name = name;
        this.reps = reps;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
