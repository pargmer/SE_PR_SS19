/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Statistic.
 */
public class Statistic {

	/** The name. */
	private String name;
	
	/** The count. */
	private int count;

	/**
	 * Instantiates a new statistic.
	 *
	 * @param name the name
	 * @param count the count
	 */
	public Statistic(String name, int count) {
		this.name = name;
		this.count = count;
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
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
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
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
	}

}
