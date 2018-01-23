package Test;

import Repo.ForecastRepo;
import Repo.Service;
import Repo.WeatherRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class WeatherTest {

    WeatherRepo weather;
    WeatherRepo weatherAtTheMoment;

    ForecastRepo forecast;
    ForecastRepo forecastAtTheMoment;

    @Before
    public void startBeforeEeachTest() {
        weather = new WeatherRepo(new Service("Tallinn"));
        forecast = new ForecastRepo("Tallinn");
    }

    @Test
    public void testIfWeatherRepoRequestCityEqualsResponseCity() {
        String requestCity = weather.getCurrentCity();

        weatherAtTheMoment = new WeatherRepo(new Service(requestCity));
        String responseCity = weatherAtTheMoment.getCurrentCity();

        assertEquals(requestCity, responseCity);
    }

    @Test
    public void testIfForecastRepoRequestCityEqualsResponseCity() {
        String requestCity = forecast.getCurrentCity();

        forecastAtTheMoment = new ForecastRepo(requestCity);
        String responseCity = forecastAtTheMoment.getCurrentCity();

        assertEquals(requestCity, responseCity);
    }

    @Test
    public void testIfTemperatureAreExpectedInGivenCity() {
        Double expectedTemperature = weather.getTemperatureAtTheMoment();

        weatherAtTheMoment = new WeatherRepo(new Service(weather.getCurrentCity()));
        Double responseTemperature = weatherAtTheMoment.getTemperatureAtTheMoment();

        assertEquals(expectedTemperature, responseTemperature);
    }

    @Test
    public void testIfNextDayMaxTempIsExpected() {
        Double expectedMaxTempNextDay = forecast.getMaxAndMinTemp(1, "max");

        forecastAtTheMoment = new ForecastRepo(forecast.getCurrentCity());
        Double responseMaxTempNextDay = forecastAtTheMoment.getMaxAndMinTemp(1, "max");

        assertEquals(expectedMaxTempNextDay, responseMaxTempNextDay);
    }

    @Test
    public void testIfNextDayMinTempIsExpected() {
        Double expectedMinTempNextDay = forecast.getMaxAndMinTemp(1, "min");

        forecastAtTheMoment = new ForecastRepo(forecast.getCurrentCity());
        Double responseMinTempNextDay = forecastAtTheMoment.getMaxAndMinTemp(1, "min");

        assertEquals(expectedMinTempNextDay, responseMinTempNextDay);
    }

    @Test
    public void testWeatherWithoutOpenWeatherApi() throws JSONException {
        Service mockedService = Mockito.mock(Service.class);

        Mockito.when(mockedService.findData()).thenReturn(new JSONObject("{\"coord\":{\"lon\":24.75,\"lat\":59.44},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":-1,\"pressure\":1026,\"humidity\":92,\"temp_min\":-1,\"temp_max\":-1},\"visibility\":10000,\"wind\":{\"speed\":3.6,\"deg\":240},\"clouds\":{\"all\":90},\"dt\":1515567000,\"sys\":{\"type\":1,\"id\":5014,\"message\":0.0022,\"country\":\"EE\",\"sunrise\":1515568244,\"sunset\":1515592014},\"id\":588409,\"name\":\"Tallinn\",\"cod\":200}"));
        WeatherRepo weatherRepoMocked = new WeatherRepo(mockedService);

        assertEquals("Tallinn", weatherRepoMocked.getCurrentCity());
        assertEquals(new Double (-1), weatherRepoMocked.getTemperatureAtTheMoment());
        assertEquals("lon: 24.75, lat: 59.44", weatherRepoMocked.getCoordinates());
    }

}
