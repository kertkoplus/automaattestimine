package Repo;

public class WeatherMain {
	
	public static void main(String[] args) {
		
		WeatherRepo weather = new WeatherRepo("Tallinn");
		System.out.println(weather.getTemperatureAtTheMoment());
		System.out.println(weather.getCurrentCity());
	}

}
