package Repo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherMain {
	
public void readCityAndWriteWeatherToFile(String inputFile) {
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile + ".txt"));
			
			ArrayList<String> cities = new ArrayList<String>();
			String lineInFile;
			while ((lineInFile = bufferedReader.readLine()) != null) {
				cities.add(lineInFile);
			}
			
			//linnale oma fail ja ilmaennustus
			for (String city : cities) {
			
			WeatherRepo weatherNow2 = new WeatherRepo(new Service(city));
			ForecastRepo forecastNow2 = new ForecastRepo(city);
			
			
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(city + ".txt"), "utf-8"));
			writer.write(city + "\n");
			writer.write("Cordinates: " + weatherNow2.getCoordinates() + "\n");
			writer.write("Weather at the moment: " + weatherNow2.getCurrentCity() + ": " + weatherNow2.getTemperatureAtTheMoment() + "\n");
			writer.write("Forecast: " + "1st day minTemp - " + forecastNow2.getMaxAndMinTemp(1, "min") + "and maxTemp - " + forecastNow2.getMaxAndMinTemp(1, "max")
			+ "\n" + "2nd day minTemp - " + forecastNow2.getMaxAndMinTemp(2, "min") + "and maxTemp - " + forecastNow2.getMaxAndMinTemp(2, "max")
			+ "\n" + "3rd day minTemp - " + forecastNow2.getMaxAndMinTemp(3, "min") + "and maxTemp - " + forecastNow2.getMaxAndMinTemp(3, "max"));
			
			writer.close();
			
		}
	}
		catch (FileNotFoundException e1) {
			 System.out.println("File not found");
		}
		catch (IOException e1) {
			e1.printStackTrace();
		} 
				
}
	
	
	
	public static void main(String[] args) {
		
		WeatherMain weather = new WeatherMain();
		
		while (true) {
			System.out.println("Choose your option: 1)w - insert city from console \n2)r - get city from file ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			
			if (input.toLowerCase().equals("w")) {
				System.out.println("City: ");
				Scanner scan2 = new Scanner(System.in);
				String input2 = scan2.nextLine();
				
				WeatherRepo weatherNow = new WeatherRepo(new Service(input2));
				System.out.println("City:" + weatherNow.getCurrentCity() + "Temp" + weatherNow.getTemperatureAtTheMoment());
		}
			
			
			else if (input.toLowerCase().equals("r")) {
				System.out.println("Input file: ");
				Scanner scan3 = new Scanner(System.in);
				String inputFile = scan3.nextLine();
				
				weather.readCityAndWriteWeatherToFile(inputFile);
				

			}

		
}
		
	}
	
	

	
}


