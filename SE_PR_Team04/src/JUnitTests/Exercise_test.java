package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Exercise;
import model.Workout;

class Exercise_test {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	void setup() {
		Exercise liegestuetze = new Exercise("Liegestütze", "Brust, Trizeps", 15);
		Exercise kniebeugen = new Exercise("Kniebeugen", "Quadrizeps, Gluteus, Unterer Rücken", 12);
		Exercise dips = new Exercise("Dips", "Trizeps", 15);
		Exercise situps = new Exercise("Sit-Ups", "Bauchmuskeln", 20);
		
		
		DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
		try {
			Date d1 = df.parse("22.04.2019");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Workout w1 = new Workout("Oberkörper", d1, )
	}

}
