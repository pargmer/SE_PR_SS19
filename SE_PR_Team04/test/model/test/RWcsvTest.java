package model.test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;

import model.Exercise;
import model.Workout;

public class RWcsvTest {
	
	private static Exercise ex1 = new Exercise("Liegestütze", "Bauch", 20);
	private static Exercise ex2 = new Exercise("Plank", "Bauch", 10);
	private static List<Exercise> exercises;
	private static List<Workout> workout;
	private static LocalDate date;
	
	
	public void writeWorkoutsOnCSV() throws Exception {
		FileWriter writer = new FileWriter("wTest.csv");
		StringBuilder sb = new StringBuilder();
		
		exercises.add(ex1); 
		Workout w1 = new Workout("Weekend",date, exercises);
		workout.add(w1);
		sb.append(w1.getName());
		writer.write(sb.toString());
		exercises.add(ex2);
		Workout w2 = new Workout("Unterkoerper",date, exercises);
		workout.add(w2);
		sb.append(w1.getName());
		writer.write(sb.toString());
		writer.flush();
		writer.close();
		Assert.assertEquals("Weekend", "-");
	}
	
	public void readWorkoutsFromCsvTest() {
		
		
	}
	
	public void readExerciseFromCsvTest() {
		
	}
	
	

}
