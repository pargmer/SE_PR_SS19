package app;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Scanner;

public class FitnessManagerMain {
	
	public static void main(String[] args) {
		
		Muscle biceps = new Muscle("Bizeps");
		Muscle triceps = new Muscle("Trizeps");
		Muscle chest = new Muscle("Brust");
		Muscle quadriceps = new Muscle("Beinstrecker");
		Muscle shoulder = new Muscle("Schulter");
		
		Exercise liegestuetze = new Exercise("Liegestütze", 3);
		liegestuetze.addMuscle(chest);
		liegestuetze.addMuscle(triceps);
		liegestuetze.addMuscle(shoulder);
		
		Exercise dips = new Exercise("Dips", 2);
		dips.addMuscle(triceps);
		
		Scanner keyboard = new Scanner(System.in);
	
		System.out.println("Neues Workout anlegen");
		System.out.println("Name eingeben:");
		String name = keyboard.nextLine();
		System.out.print("Dauer:");
		String duration = keyboard.nextLine();
		System.out.print("min:");
		int min = keyboard.nextInt();
		
		
//		Workout workout1 = new Workout(name, duration);
		
//		System.out.println(liegestuetze);
		
		
	}
	
	/*public void setUp() {
		
	}*/

}
