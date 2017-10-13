package Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastRepo {
	
	private String city;
	private JSONObject jsonObject;
	private DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("YYYY-MM-DD");
	private Date currentDate = new Date();
	
	public ForecastRepo(String city) {
		
		this.city = city;
		
		String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + this.city + "&units=metric&appid=472668130148f8ffc32aaba1c58267f6";
		

	
	try {

		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		StringBuilder sb = new StringBuilder();

		String output;
		while ((output = br.readLine()) != null) {
			sb.append(output).append("\n");
		}

		conn.disconnect();
		
		jsonObject = new JSONObject(sb.toString());

	  } catch (JSONException e1) {

		e1.printStackTrace();

	  } catch (IOException e1) {

		e1.printStackTrace();

	  }

	}
	

    public Double getTemperatureAtTheMoment() {
        try {
            return jsonObject.getJSONObject("main").getDouble("temp");
        } catch (JSONException e1) {
        	e1.printStackTrace();
        }
        return null;
    }

    public String getCurrentCity() {
        try {
            return jsonObject.getString("name");
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    
    public Double getMaxAndMinTemp(int day, String maxMin) {
    	if (day < 1 || day > 3) {
    		return null;
    	}
    	
    	if (!maxMin.toLowerCase().equals("max") && !maxMin.toLowerCase().equals("min")) {
    		return null;
    	}
    	
    	LocalDateTime localDate = LocalDateTime.from(currentDate.toInstant().atZone(ZoneId.of("UTC+03:00"))).plusDays(1);
    	String formatDate = localDate.format(dateTime);
    	
    	//vaatame listi ja kui kuupaev on sama, mis meie valitud, siis lisame listi
    	JSONArray forecastDate = new JSONArray();
    	try {
    		JSONArray weatherForecast = jsonObject.getJSONArray("list");
    		
    		for (int i=0; i < weatherForecast.length(); i++) {
    			if (weatherForecast.getJSONObject(i).getString("dt_txt").substring(0,10).equals(formatDate)) {
    				forecastDate.put(weatherForecast.getJSONObject(i));
    			};
    		}
    		
    	} catch (JSONException e1) {
    		e1.printStackTrace();
    	}
    	
    	//otsime max temperatuurid
    	
    	Double returnedTemp = null;
    	try {
    		
    		for (int i=0; i < forecastDate.length(); i++) {
    			Double temperature = forecastDate.getJSONObject(i).getJSONObject("main").getDouble("temp_max");
    			if (returnedTemp == null) {
    				returnedTemp = temperature;
    			}
    			
    			if (temperature > returnedTemp && maxMin.toLowerCase().equals("max")) {
    				returnedTemp = temperature;
    			}
    			
    			if (temperature < returnedTemp && maxMin.toLowerCase().equals("min")) {
    				returnedTemp = temperature;
    			}
    		}
    	} catch (JSONException e1) {
    		e1.printStackTrace();
    	}
    	
    	return returnedTemp;
    	

}

}
