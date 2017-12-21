import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;


public class WeatherAPITest {

    WeatherRequest weatherRequest = new WeatherRequest(); 
    String APPID = "e10de45b13d55e166ce06f3cdddb87b8";
    String name = "Tartu";
    URL correctCurrentWeathertUrl = new URL("http://api.openweathermap.org//data/2.5/weather?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");
    URL correctForecasttUrl = new URL("http://api.openweathermap.org//data/2.5/forecast?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");
   
    private ServiceRunner serviceRunner;
    private ArrayList<String> cityNames;
    private ArrayList<String> cityName;
    
    
    @Before

    public void init() {
    serviceRunner = new ServiceRunner();
	cityName = new ArrayList<>(
        Arrays.asList("Tartu"));
	cityNames = new ArrayList<>(
        Arrays.asList("Tartu","Tapa"));

}

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
    	
    	
    	ServiceRunner serviceRunner = new ServiceRunner();
        ArrayList<String> response = serviceRunner.getOnlyDataThatYouNeedFromJSONCurrentWeather(cityNames);
        assertThat(response, instanceOf(ArrayList.class));
    }

    @Test
    public void doesForecastDataResponseReturnArrayList() throws Exception {

        ArrayList<String> response = serviceRunner.getOnlyDataThatYouNeedFromJSONForecast(cityNames);
        assertThat(response, instanceOf(ArrayList.class));
    }
    

    @Test
    public void doesResponseReturnDataAsJSONObject() throws Exception {
    	
    	ServiceRunner serviceRunner = new ServiceRunner();
    	Responses responseFunctions = new Responses();
        JSONObject objectData = serviceRunner.makeResponseJSONData("Tartu", responseFunctions);
        assertThat(objectData, instanceOf(JSONObject.class));

    }

    @Test
    public void doesResponseReturnDataAsJSONArray() throws Exception {
    	
    	ServiceRunner serviceRunner = new ServiceRunner();
        JSONArray arrayData = serviceRunner.makeForecastResponseJSONArray("Tartu");
        assertThat(arrayData, instanceOf(JSONArray.class));

    }
}