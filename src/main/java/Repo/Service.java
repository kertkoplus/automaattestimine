package Repo;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service {

    private String city;
    private String apiUrl;

    public Service(String city) {
        this.city = city;
        this.apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + this.city + "&units=metric&appid=472668130148f8ffc32aaba1c58267f6";
    }

    public JSONObject findData() {
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
            return new JSONObject(sb.toString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }


}
