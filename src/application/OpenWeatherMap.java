package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherMap {
	private String city;
	private String day;
    private Integer temperature;
    private String icon;
    private String description;
    private String windSpeed;
    private String cloudiness;
    private String pressure;
    private String humidity;
    
    public OpenWeatherMap(String city) {
    	this.city = city;
    }

    // gör en string från json-filen
    private String readAll(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    
    // läser och returnar json-objektet
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(br);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    
    public void getWeather(){
        int d = 0;

        JSONObject json;
        JSONObject json_specific;

        SimpleDateFormat df2 = new SimpleDateFormat("EEEE", Locale.ENGLISH); //Entire word/day as output
        Calendar c = Calendar.getInstance();

        // länkar api:n och requesta
        try {
            json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid=5e8deca3a96faf1a45021c91cf3c6cc7&lang=eng&units=metric");
            System.out.println(json);
        } catch (IOException e) {
            return;
        }
        //receives the particular data in the read Json File
        json_specific = json.getJSONObject("main");
        this.temperature = json_specific.getInt("temp");
        this.pressure = json_specific.get("pressure").toString();
        this.humidity = json_specific.get("humidity").toString();
        json_specific = json.getJSONObject("wind");
        this.windSpeed = json_specific.get("speed").toString();
        json_specific = json.getJSONObject("clouds");
        this.cloudiness = json_specific.get("all").toString();
        
        c.add(Calendar.DATE, d);
        this.day = df2.format(c.getTime());

        json_specific = json.getJSONArray("weather").getJSONObject(0);
        this.description = json_specific.get("description").toString();
        this.icon = json_specific.get("icon").toString();
        System.out.println(city);
    }
    
    
    public String getCity() {
		return city;
	}

	public String getDay() {
		return day;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public String getIcon() {
		return icon;
	}

	public String getDescription() {
		return description;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public String getCloudiness() {
		return cloudiness;
	}

	public String getPressure() {
		return pressure;
	}

	public String getHumidity() {
		return humidity;
	}
}

