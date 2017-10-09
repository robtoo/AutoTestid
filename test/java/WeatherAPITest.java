import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Test;

public class WeatherAPITest {

    WeatherRequest weatherRequest = new WeatherRequest(); 
    String APPID = "e10de45b13d55e166ce06f3cdddb87b8";
    String name = "Tartu";
    URL correctCurrentWeathertUrl = new URL("http://api.openweathermap.org//data/2.5/weather?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");
    URL correctForecasttUrl = new URL("http://api.openweathermap.org//data/2.5/forecast?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");

    

    @Test
    public void doesWeatherRequestReturnCurrentWeatherRequestURL() throws Exception {
        URL getCurrentRequestURL = weatherRequest.getCurrentWeatherRequestURL(name, APPID);
        assertEquals(getCurrentRequestURL, correctCurrentWeathertUrl);
    }

    @Test
    public void doesWeatherRequestReturnWeatherForecastRequestURL() throws Exception{
        URL forecastRequestURL = weatherRequest.getForecastRequestURL(name, APPID);
        assertEquals(forecastRequestURL, correctForecasttUrl);
    }

    @Test
    public void doesWeatherRequestReturnURL() throws Exception{
        URL forecastRequestURL = weatherRequest.getForecastRequestURL(name, APPID);
        assertThat(forecastRequestURL, instanceOf(URL.class));
    }
    @Test
    public void doesCurrentWeatherDataResponseReturnArrayList() throws Exception {

        ArrayList<String> response = serviceRunner.getOnlyDataThatYouNeedFromJSONCurrentWeather(cityNames);
        assertThat(response, instanceOf(ArrayList.class));
    }

    @Test
    public void doesForecastDataResponseReturnArrayList() throws Exception {

        ArrayList<String> response = serviceRunner.getOnlyDataThatYouNeedFromJSONForecast(cityNames);
        assertThat(response, instanceOf(ArrayList.class));
    }
}