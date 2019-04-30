package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List; 

public class ReadAndWriteCSV {

	
	 private static ReadAndWriteCSV instance;
		
	  public static ReadAndWriteCSV getInstance() throws SQLException{
	        if(instance == null){
	            instance = new ReadAndWriteCSV();
	        }
	        return instance;
	    }
	
	public List<Workout> readWorkoutsFromCsv(String file) throws IOException { 
		
		List<Workout> workouts = new LinkedList<Workout>();
		List<Exercise> exercises = new LinkedList<Exercise>();
		 String row;
		BufferedReader csvReader = new BufferedReader(new FileReader(file)); 
	
		while ((row = csvReader.readLine()) != null) {  
		    String[] data = row.split(";");
		    String[] dataexercise = data[2].split(",");
		 exercises.clear();
		    for(int i = 0; i < dataexercise.length;i++) {
		    	exercises.add(new Exercise(dataexercise[i],"",0));
		    	
		    }
		    System.out.println();
		    Date date = new Date();
		   Workout helpwork = new Workout(data[1],date,exercises);
		    workouts.add(helpwork);
		   
			
			
		    }
		csvReader.close();  
		
		
		return workouts;
	}
	
	public List<Exercise> readExercisesFromCsv(String file) throws IOException { 
		
		List<Exercise> exercises = new LinkedList<Exercise>();
		//List<Exercise> exercises = new LinkedList<Exercise>();
		 String row;
		BufferedReader csvReader = new BufferedReader(new FileReader(file)); 
	
		while ((row = csvReader.readLine()) != null) {  
		    String[] data = row.split(";");
		
		    exercises.add(new Exercise(data[0],data[1],Integer.parseInt(data[2])));
}
		csvReader.close();  
		
		return exercises;
	}
}

