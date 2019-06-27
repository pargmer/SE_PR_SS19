/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Exercise.
 */
public class Exercise {

    /**
     * The name.
     */
    private String name;

    /**
     * The trains.
     */
    private String trains;

    /**
     * The reps.
     */
    private int reps;

    /** The unit. */
    private String unit;

    /**
     * Instantiates a new exercise.
     *
     * @param name the name
     * @param trains the trains
     * @param reps the reps
     * @param unit the unit
     */
    public Exercise(String name, String trains, int reps, String unit) {
        this.name = name;
        this.trains = trains;
        this.reps = reps;
        this.unit = unit;
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

    /**
     * Sets the unit.
     *
     * @param unit the new unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets the unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

}
