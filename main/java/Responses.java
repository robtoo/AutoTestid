


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Responses {
    OkHttpClient client = new OkHttpClient();

    public String getResponseBodyFromURL (URL url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    
    public  JSONObject  makeForcecastResponseJSONData(String name ) throws JSONException, IOException {

        WeatherRequest weatherRequest = new WeatherRequest(); 
        String APPID = "e10de45b13d55e166ce06f3cdddb87b8";
        URL forecastUrl = weatherRequest.getForecastRequestURL(name, APPID);
        Responses responses = new Responses();
        String weatherStringData = responses.getResponseBodyFromURL(forecastUrl);
        JSONObject weatherJsonData = responses.convertStringDataToJsonData(weatherStringData);
        return weatherJsonData;
    }

    public  JSONObject  makeCurrentResponseJSONData(String name ) throws JSONException, IOException {

        WeatherRequest weatherRequest = new WeatherRequest(); 
        String APPID = "e10de45b13d55e166ce06f3cdddb87b8";
        URL forecastUrl = weatherRequest.getCurrentWeatherRequestURL(name, APPID);
        Responses responses = new Responses();
        String weatherStringData = responses.getResponseBodyFromURL(forecastUrl);
        JSONObject weatherJsonData = responses.convertStringDataToJsonData(weatherStringData);
        return weatherJsonData;
    }


    public JSONArray  makeForecastResponseJSONArray(String name) throws JSONException, IOException {
        JSONArray weatherJsonArray = makeForcecastResponseJSONData(name).getJSONArray("list");
        return weatherJsonArray;
    }

    public JSONArray  makeCurrentResponseJSONArray(String name) throws JSONException, IOException {
        JSONArray weatherJsonArray = makeCurrentResponseJSONData(name).getJSONArray("list");
        return weatherJsonArray;
    }
    public JSONObject convertStringDataToJsonData (String stringdata) throws JSONException {
        JSONObject jsondata = new JSONObject(stringdata);
        return jsondata;
    }

    public String getCityNameFromForecastJsonData (JSONObject jsondata) throws JSONException {
        return jsondata.getJSONObject("city").getString("name");
    }

    public Double getMaxTempFromForecastJsonData (JSONArray jsondata, Integer positionInArray) throws JSONException {
        return Double.parseDouble(jsondata.getJSONObject(positionInArray).getJSONObject("main").getString("temp_max"));
    }

    public Double getMinTempFromForecastJsonData (JSONArray jsondata, Integer positionInArray) throws JSONException {
        return Double.parseDouble(jsondata.getJSONObject(positionInArray).getJSONObject("main").getString("temp_min"));
    }

    public Double getLatitudeFromForecastJsonData (JSONObject jsondata) throws JSONException {
        return Double.parseDouble(jsondata.getJSONObject("city").getJSONObject("coord").getString("lat"));
    }

    public Double getLongitudeFromForecastJsonData(JSONObject jsondata) throws JSONException {
        return Double.parseDouble(jsondata.getJSONObject("city").getJSONObject("coord").getString("lon"));
    }

    public String getDateAsStringFromForecastJsonData (JSONArray jsondata, Integer numberInArray) throws JSONException {
        return jsondata.getJSONObject(numberInArray).getString("dt_txt").substring(0, 10);
    }
   
   

}