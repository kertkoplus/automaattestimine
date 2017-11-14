package Repo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class WeatherMain {
	
public void readFromInputAndWriteToOutput(String inputFile, String outputFile) {
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile + ".txt"));
			String lineInFile = bufferedReader.readLine();
			System.out.println(inputFile + ".txt " + lineInFile);
			
			WeatherRepo weatherNow2 = new WeatherRepo(lineInFile);
			ForecastRepo forecastNow2 = new ForecastRepo(lineInFile);
			
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile + ".txt"), "utf-8"));
			writer.write("Weather at the moment: " + weatherNow2.getCurrentCity() + ": " + weatherNow2.getTemperatureAtTheMoment() + "\n");
			writer.write("Forecast: " + "1st day minTemp - " + forecastNow2.getMaxAndMinTemp(1, "min") + "and maxTemp - " + forecastNow2.getMaxAndMinTemp(1, "max")
			+ "\n" + "2nd day minTemp - " + forecastNow2.getMaxAndMinTemp(2, "min") + "and maxTemp - " + forecastNow2.getMaxAndMinTemp(2, "max")
			+ "\n" + "3rd day minTemp - " + forecastNow2.getMaxAndMinTemp(3, "min") + "and maxTemp - " + forecastNow2.getMaxAndMinTemp(3, "max"));
			
			writer.close();
			
		} catch (IOException e1) {
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
				
				WeatherRepo weatherNow = new WeatherRepo(input2);
				System.out.println("City:" + weatherNow.getCurrentCity() + "Temp" + weatherNow.getTemperatureAtTheMoment());
		}
			
			
			else if (input.toLowerCase().equals("r")) {
				System.out.println("Input file: ");
				Scanner scan3 = new Scanner(System.in);
				String inputFile = scan3.nextLine();
				
				System.out.println("Output file: ");
				Scanner scan4 = new Scanner(System.in);
				String outputFile = scan3.nextLine();
				
				weather.readFromInputAndWriteToOutput(inputFile, outputFile);
				

			}

		
}
		
	}
	
	

	
}


