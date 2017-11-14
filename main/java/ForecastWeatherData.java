

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;


public class ForecastWeatherData {


    public ArrayList<Double> ListOfDayOneMaxMinTemp(String name) throws IOException, JSONException {

        Responses responseFunction = new Responses();
        ServiceRunner serviceRunner = new ServiceRunner();

        ArrayList<Double> dayOneMaxMinTemps= new ArrayList<>();

        for (int i = 0; i <= 7; i++)
        {
            Double maxTemp = responseFunction.getMaxTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name),i );
            Double minTemp = responseFunction.getMinTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name),i );
            dayOneMaxMinTemps.add(maxTemp);
            dayOneMaxMinTemps.add(minTemp);
        }

        return dayOneMaxMinTemps;
    }

    public ArrayList<Double> ListOfDayTwoMaxMinTemp(String name) throws IOException, JSONException {
        Responses responseFunction = new Responses();
        ServiceRunner serviceRunner = new ServiceRunner();
        ArrayList<Double> dayTwoMaxMinTemps= new ArrayList<>();

        for (int i = 8; i <= 15; i++)
        {
            Double maxTemp = responseFunction.getMaxTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name),i );
            Double minTemp = responseFunction.getMinTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name),i );

            dayTwoMaxMinTemps.add(maxTemp);
            dayTwoMaxMinTemps.add(minTemp);
        }

        return dayTwoMaxMinTemps;
    }

    public ArrayList<Double> ListOfDayThreeMaxMinTemp(String name) throws IOException, JSONException {
        Responses responseFunction = new Responses();
        ServiceRunner serviceRunner = new ServiceRunner();
        
        ArrayList<Double> dayThreeMaxMinTemps= new ArrayList<>();

        for (int i = 16; i <= 23; i++)
        {
            Double maxTemp = responseFunction.getMaxTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name),i );
            Double minTemp = responseFunction.getMinTempFromForecastJsonData(serviceRunner.makeForecastResponseJSONArray(name),i );
            dayThreeMaxMinTemps.add(maxTemp);
            dayThreeMaxMinTemps.add(minTemp);
        }

        return dayThreeMaxMinTemps;
    }




}
