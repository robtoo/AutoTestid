import java.net.URL;
import okhttp3.HttpUrl;

public class WeatherRequest {
	
	
	    public URL getForecastRequestURL(String city, String APPID) {

	        return new HttpUrl.Builder()
	                .scheme("http")
	                .host("api.openweathermap.org")
	                .addPathSegments("/data/2.5/forecast")
	                .addQueryParameter("q", city)
	                .addQueryParameter("APPID", APPID)
	                .addQueryParameter("units", "metric")
	                .build().url();
	    }

	    public URL getCurrentWeatherRequestURL(String city, String APPID) {
	        return new HttpUrl.Builder()
	                .scheme("http")
	                .host("api.openweathermap.org")
	                .addPathSegments("/data/2.5/weather")
	                .addQueryParameter("q", city)
	                .addQueryParameter("APPID", APPID)
	                .addQueryParameter("units", "metric")
	                .build().url();
	    }


	}



