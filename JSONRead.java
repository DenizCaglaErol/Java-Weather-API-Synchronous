import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;



public class JSONRead {
	
	public static void parseObject(JSONObject jo) 
    {
        
        JSONObject location = (JSONObject) jo.get("current");
        double temp = (double) location.get("temp_c");    
        System.out.println(temp);
        JSONObject condition = (JSONObject) location.get("condition");   
        String type = (String) condition.get("text");    
        System.out.println(type);
		
    }


	public static String sendGetRequest(String hostAddress, String data,String city) {
		String result = null;
		if (hostAddress.startsWith("https://")) {
		try {
		String urlAsString = createGetUrl(hostAddress, data,city);
		URL url = new URL(urlAsString);
		URLConnection urlConnection = url.openConnection();
		result = parseGetResponse(urlConnection);
		} catch (IOException e) {
		System.out.println("Ba�lant� Hatas�...M: " + e.getMessage());
		}
		}
		//System.out.println(result);
		return result;
		}
		 
		private static String createGetUrl(String hostAddress, String args,String city) {
		String arg=args+city;
		String urlAsString = hostAddress;
		if (arg != null && arg.length() > 0) {
		urlAsString += "?" + arg;
		}
		return urlAsString;
		}
		
		 
		private static String parseGetResponse(URLConnection urlConnection) {
		String result = "";
		BufferedReader bufferedReader;
		try {
		bufferedReader = new BufferedReader(new InputStreamReader(
		urlConnection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
		stringBuffer.append(line);
		}
		bufferedReader.close();
		result = stringBuffer.toString();
		}
		catch (IOException e) {
		System.out.println("Error"+ e.getMessage());
		}
		return result;
		}

}