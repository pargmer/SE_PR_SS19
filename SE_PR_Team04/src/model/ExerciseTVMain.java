/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * The Class ExerciseTVMain.
 */
public class ExerciseTVMain {

    /** The name. */
    private String name;
    
    /** The reps. */
    private int reps;
    
    /** The unit. */
    private String unit;

    /**
     * Instantiates a new exercise TV main.
     *
     * @param name the name
     * @param reps the reps
     * @param unit the unit
     */
    public ExerciseTVMain(String name, int reps, String unit) {
        this.name = name;
        this.reps = reps;
        this.unit = unit;
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
     * Gets the reps.
     *
     * @return the reps
     */
    public int getReps() {
        return reps;
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
     * Sets the reps.
     *
     * @param reps the new reps
     */
    public void setReps(int reps) {
        this.reps = reps;
    }

    /**
     * Gets the unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit.
     *
     * @param unit the new unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}
