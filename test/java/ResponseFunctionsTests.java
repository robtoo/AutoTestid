

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;


public class ResponseFunctionsTests {
    private Responses responseFunctions;
    private String testString = "{\"coord\":{\"lon\":26.72,\"lat\":58.37},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":-2,\"pressure\":1025,\"humidity\":86,\"temp_min\":-2,\"temp_max\":-2},\"visibility\":10000,\"wind\":{\"speed\":4.6,\"deg\":200},\"clouds\":{\"all\":75},\"dt\":1513803000,\"sys\":{\"type\":1,\"id\":5015,\"message\":0.0033,\"country\":\"EE\",\"sunrise\":1513753230,\"sunset\":1513776114},\"id\":588335,\"name\":\"Tartu\",\"cod\":200}";
    URL currentWeathertUrl = new URL("http://api.openweathermap.org//data/2.5/forecast?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");

    public ResponseFunctionsTests() throws MalformedURLException {
    }


    @Test
    public void serviceRunnerTest() throws IOException, JSONException {
        ServiceRunner serviceRunner = new ServiceRunner();
        Responses responseFunctions = new Responses();
        String testAnswer = "{\"dt\":1513849800,\"coord\":{\"lon\":26.72,\"lat\":58.37},\"visibility\":2100,\"weather\":[{\"icon\":\"13d\",\"description\":\"light rain and snow\",\"main\":\"Snow\",\"id\":615},{\"icon\":\"50d\",\"description\":\"mist\",\"main\":\"Mist\",\"id\":701}],\"name\":\"Tartu\",\"cod\":200,\"main\":{\"temp\":-1,\"temp_min\":-1,\"humidity\":100,\"pressure\":1014,\"temp_max\":-1},\"clouds\":{\"all\":90},\"id\":588335,\"sys\":{\"country\":\"EE\",\"sunrise\":1513839647,\"sunset\":1513862531,\"id\":5015,\"type\":1,\"message\":0.0026},\"base\":\"stations\",\"wind\":{\"deg\":200,\"speed\":6.7}}";
        URL url = new  URL("http://api.openweathermap.org//data/2.5/weather?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");
        //Mockito.when(responseFunctions.getResponseBodyFromURL(url)).thenReturn(testAnswer);
        //System.out.println(serviceRunner.makeResponseJSONDataTest("Tartu",responseFunctions));
        assertEquals(serviceRunner.makeResponseJSONData("Tartu",responseFunctions).toString(),testAnswer );
    }


    @Test
    public void serviceRunner() throws IOException, JSONException {
        ServiceRunner serviceRunner = new ServiceRunner();
        Responses responseFunctions = Mockito.mock(Responses.class);
        String testAnswer = "{\"dt\":1513849800,\"coord\":{\"lon\":26.72,\"lat\":58.37},\"visibility\":2100,\"weather\":[{\"icon\":\"13d\",\"description\":\"light rain and snow\",\"main\":\"Snow\",\"id\":615},{\"icon\":\"50d\",\"description\":\"mist\",\"main\":\"Mist\",\"id\":701}],\"name\":\"Tartu\",\"cod\":200,\"main\":{\"temp\":-1,\"temp_min\":-1,\"humidity\":100,\"pressure\":1014,\"temp_max\":-1},\"clouds\":{\"all\":90},\"id\":588335,\"sys\":{\"country\":\"EE\",\"sunrise\":1513839647,\"sunset\":1513862531,\"id\":5015,\"type\":1,\"message\":0.0032},\"base\":\"stations\",\"wind\":{\"deg\":200,\"speed\":6.7}}";
        URL url = new  URL("http://api.openweathermap.org//data/2.5/weather?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");
        Mockito.when(responseFunctions.getResponseBodyFromURL(url)).thenReturn(testAnswer);
        System.out.println(serviceRunner.makeResponseJSONData("Tartu",responseFunctions));
        assertEquals(serviceRunner.makeResponseJSONData("Tartu",responseFunctions).toString(),testAnswer.toString() );
    }

    @Test
    public void ifDataReturnCorrectAmountOfTimes() throws IOException, JSONException{


        String name = "Tartu";
        String testAnswer = "{\"dt\":1513849800,\"coord\":{\"lon\":26.72,\"lat\":58.37},\"visibility\":2100,\"weather\":[{\"icon\":\"13d\",\"description\":\"light rain and snow\",\"main\":\"Snow\",\"id\":615},{\"icon\":\"50d\",\"description\":\"mist\",\"main\":\"Mist\",\"id\":701}],\"name\":\"Tartu\",\"cod\":200,\"main\":{\"temp\":-1,\"temp_min\":-1,\"humidity\":100,\"pressure\":1014,\"temp_max\":-1},\"clouds\":{\"all\":90},\"id\":588335,\"sys\":{\"country\":\"EE\",\"sunrise\":1513839647,\"sunset\":1513862531,\"id\":5015,\"type\":1,\"message\":0.0032},\"base\":\"stations\",\"wind\":{\"deg\":200,\"speed\":6.7}}";
        URL currentWeathertUrl = new URL("http://api.openweathermap.org//data/2.5/weather?q=Tartu&APPID=e10de45b13d55e166ce06f3cdddb87b8&units=metric");
        ResponseFunctions responseFunctions = Mockito.mock(ResponseFunctions.class);
        Mockito.when(responseFunctions.getResponseBodyFromURL(currentWeathertUrl)).thenReturn(testString);
        ServiceRunner serviceRunner = new ServiceRunner();
        serviceRunner.makeResponseJSONDataTest(name, responseFunctions);

        Mockito.verify(responseFunctions, times(1)).getResponseBodyFromURL(currentWeathertUrl);


    }

  



}
