import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class test {

	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub

		
		Responses responses = new Responses();
		System.out.println(responses.makeForcecastResponseJSONData("Tallinn"));
		JSONArray data = responses.makeCurrentResponseJSONArray("Tallinn");
		System.out.println(data);
		System.out.println(responses.makeCurrentResponseJSONData("Tallinn"));
		System.out.println(responses.getMaxTempFromForecastJsonData(data,0));
		System.out.println(responses.getMinTempFromForecastJsonData(data,0));
		
	}

}
