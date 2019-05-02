/**
 * 
 */
package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.ReadAndWriteCSV;
import model.Workout;

/**
 * @author Pargan
 *
 */
class ReadAndWriteCSV_test {

	
	/**
	 * Test method for {@link model.ReadAndWriteCSV#readWorkoutsFromCsv(java.lang.String)}.
	 */
	@Test
	final void testReadWorkoutsFromCsv() {
		ReadAndWriteCSV rw = new ReadAndWriteCSV();
		List<Workout> workout = new List<Workout> 
		try {
			List<Workout> workouts = rw.readWorkoutsFromCsv("workouts.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.ReadAndWriteCSV#readExercisesFromCsv(java.lang.String)}.
	 */
	@Test
	final void testReadExercisesFromCsv() {
		fail("Not yet implemented"); // TODO
	}

}
