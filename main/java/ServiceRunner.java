

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceRunner  {

    public JSONObject makeResponseJSONData (String name, Responses responseFunction ) throws JSONException, IOException {
        WeatherRequest weatherRequest = new WeatherRequest();
        String APPID = "e10de45b13d55e166ce06f3cdddb87b8";
        URL forecastUrl = weatherRequest.getForecastRequestURL(name, APPID);
        System.out.println(forecastUrl);
        String weatherStringData = responseFunction.getResponseBodyFromURL(forecastUrl);
        JSONObject weatherJsonData = responseFunction.convertStringDataToJsonData(weatherStringData);
        return weatherJsonData;
    }
    
    public JSONArray  makeForecastResponseJSONArray(String name) throws JSONException, IOException {
    	Responses responseFunction = new Responses();
        JSONArray weatherJsonArray = makeResponseJSONData(name, responseFunction).getJSONArray("list");
        return weatherJsonArray;
    }

    public ArrayList<String> getOnlyDataThatYouNeedFromJSONForecast(ArrayList<String> cityNames) throws IOException, JSONException {
        ArrayList<String> allCitiesData = new ArrayList<>();
        Responses responseFunction = new Responses();
        ServiceRunner serviceRunner = new ServiceRunner();
        ForecastWeatherData forecastWeatherData = new ForecastWeatherData();
        FileController fileController = new FileController();
        for (String name : cityNames) {
        	

            ArrayList<String> content = new ArrayList<>();
            String cityName = responseFunction.getCityNameFromForecastJsonData(serviceRunner.makeResponseJSONData(name, responseFunction));
            Double lat = responseFunction.getLatitudeFromForecastJsonData(serviceRunner.makeResponseJSONData(name, responseFunction));
            Double lon = responseFunction.getLongitudeFromForecastJsonData(serviceRunner.makeResponseJSONData(name, responseFunction));
            Double dayOneMinTemp = Collections.min(forecastWeatherData.ListOfDayOneMaxMinTemp(name));
            Double dayOneMaxTemp = Collections.max(forecastWeatherData.ListOfDayOneMaxMinTemp(name));
            Double dayTwoMinTemp = Collections.min(forecastWeatherData.ListOfDayTwoMaxMinTemp(name));
            Double dayTwoMaxTemp = Collections.max(forecastWeatherData.ListOfDayTwoMaxMinTemp(name));
            Double dayThreeMinTemp = Collections.min(forecastWeatherData.ListOfDayThreeMaxMinTemp(name));
            Double dayThreeMaxTemp = Collections.max(forecastWeatherData.ListOfDayThreeMaxMinTemp(name));
            String date = responseFunction.getDateAsStringFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name), 0);
            content.add("Latitude: " + lat.toString());
            content.add("Longitude: " + lon.toString());
            content.add("I day max temp: " + dayOneMaxTemp.toString() + ", min temp: " + dayOneMinTemp.toString());
            content.add("II day max temp: " + dayTwoMaxTemp.toString() + ", min temp: " + dayTwoMinTemp.toString());
            content.add("III day max temp: " + dayThreeMaxTemp.toString() + ", min temp: " + dayThreeMinTemp.toString());
            content.add("Forecast starting from: " + date);
            allCitiesData.add(cityName + " " + content.toString() + System.lineSeparator());

        }
        fileController.fileWriter(allCitiesData);
        System.out.println("Ennustuse andmed kirjutatud faili(desse)");
        return allCitiesData;

    }

    public ArrayList<String> getOnlyDataThatYouNeedFromJSONCurrentWeather(ArrayList<String> cityNames) throws IOException, JSONException {
        FileController fileController = new FileController();

        ArrayList<String> allCitiesData = new ArrayList<>();
        
        for (String name : cityNames) {
            ArrayList<String> content = new ArrayList<>();
            Responses responseFunction = new Responses();
            ServiceRunner serviceRunner =  new ServiceRunner();
            
            String cityName = responseFunction.getCityNameFromForecastJsonData(serviceRunner.makeResponseJSONData(name, responseFunction));
            Double lat = responseFunction.getLatitudeFromForecastJsonData(serviceRunner.makeResponseJSONData(name, responseFunction));
            Double lon = responseFunction.getLongitudeFromForecastJsonData(serviceRunner.makeResponseJSONData(name, responseFunction));
            Double maxTemp = responseFunction.getMaxTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name), 0);
            Double minTemp = responseFunction.getMinTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name), 0);
            String date = responseFunction.getDateAsStringFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name), 0);
            content.add("Latitude: " + lat.toString());
            content.add("Longitude: " + lon.toString());
            content.add("Max temp: " + maxTemp.toString());
            content.add("Min temp: " + minTemp.toString());
            content.add("Date: " + date);
            allCitiesData.add(cityName + " " + content.toString() + System.lineSeparator());
        }
        fileController.fileWriter(allCitiesData);
        System.out.println("Hetkeilma andmed kirjutatud faili(desse)");
        return allCitiesData;
    }

    public static void main(String[] args) throws IOException, JSONException{

        ServiceRunner serviceRunner = new ServiceRunner();
        Responses responseFunctions = new Responses();

        System.out.println(serviceRunner.makeResponseJSONData("Tartu", responseFunctions));

        ConsoleController consoleReader = new ConsoleController();

        consoleReader.getDataAndPrintToConsoleOrFile();



    }
}
