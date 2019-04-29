package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import model.Exercise;
import model.Workout;

class Exercise_test {

	public void setup() throws ParseException {
		Exercise liegestuetze = new Exercise("Liegestütze", "Brust, Trizeps", 15);
		Exercise kniebeugen = new Exercise("Kniebeugen", "Quadrizeps, Gluteus, Unterer Rücken", 12);
		Exercise dips = new Exercise("Dips", "Trizeps", 15);
		Exercise situps = new Exercise("Sit-Ups", "Bauchmuskeln", 20);
		
		
		DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
		Date d1 = df.parse("22.04.2019");
		
		
		Workout w1 = new Workout("Oberkörper", d1, new LinkedList<Exercise>());
		w1.addExercise(liegestuetze);
		w1.addExercise(dips);
		
	}
	@Test
	void test() throws ParseException {
		
		
	}
	
	

}
