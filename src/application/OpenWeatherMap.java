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
	private String day1;
	private String day2;
	private String day3;
	private String day4;
	private String day5;
    private Integer temperature;
    private Integer temperature1;
    private Integer temperature2;
    private Integer temperature3;
    private Integer temperature4;
    private Integer temperature5;
    private String icon;
    private String icon1;
    private String icon2;
    private String icon3;
    private String icon4;
    private String icon5;
    private String description;
    private String windSpeed;
    private String cloudiness;
    private String pressure;
    private String humidity;
    private String dt;
    
    
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
    
    public void getWeather() {
        JSONObject json;
        JSONObject json_specific;

        // länkar api:n och requesta
        try {
            json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid=5e8deca3a96faf1a45021c91cf3c6cc7&lang=eng&units=metric");
        } catch (IOException e) {
            return;
        }
        
        // får ut specifik information från json-filen för current weather
        json_specific = json.getJSONObject("main");
        this.temperature = json_specific.getInt("temp");
        this.pressure = json_specific.get("pressure").toString();
        this.humidity = json_specific.get("humidity").toString();
        json_specific = json.getJSONObject("wind");
        this.windSpeed = json_specific.get("speed").toString();
        json_specific = json.getJSONObject("clouds");
        this.cloudiness = json_specific.get("all").toString();

        json_specific = json.getJSONArray("weather").getJSONObject(0);
        this.description = json_specific.get("description").toString();
        this.icon = json_specific.get("icon").toString();
    }
    
    public void getForecast() {	
    	JSONObject json;
        JSONObject json_specific;
        
        SimpleDateFormat df2 = new SimpleDateFormat("EEEE", Locale.ENGLISH); 

        // länkar api:n och requesta info
        try {
            json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/forecast?q="+ city +"&appid=5e8deca3a96faf1a45021c91cf3c6cc7&lang=eng&units=metric");
            System.out.println(json);
        } catch (IOException e) {
            return;
        }
        
        // day 1
        Calendar dayA = Calendar.getInstance();
        dayA.add(Calendar.DATE, 1);
        this.day1 = df2.format(dayA.getTime());
        
        // day 2
        Calendar dayB = Calendar.getInstance();
        dayB.add(Calendar.DATE, 2);
        this.day2 = df2.format(dayB.getTime());
        
        // day 3
        Calendar dayC = Calendar.getInstance();
        dayC.add(Calendar.DATE, 3);
        this.day3 = df2.format(dayC.getTime());
        
        // day 4
        Calendar dayD = Calendar.getInstance();
        dayD.add(Calendar.DATE, 4);
        this.day4 = df2.format(dayD.getTime());
        
        // day 5
        Calendar dayE = Calendar.getInstance();
        dayE.add(Calendar.DATE, 5);
        this.day5 = df2.format(dayE.getTime());
        
        // får ut specifik information från json-filen för forecast
        json_specific = json.getJSONArray("list").getJSONObject(0).getJSONObject("main");
        this.temperature1 = json_specific.getInt("temp");
        
        // får ut temperatur varje dag
        json_specific = json.getJSONArray("list").getJSONObject(7).getJSONObject("main");
        this.temperature2 = json_specific.getInt("temp");
        json_specific = json.getJSONArray("list").getJSONObject(15).getJSONObject("main");
        this.temperature3 = json_specific.getInt("temp");
        json_specific = json.getJSONArray("list").getJSONObject(23).getJSONObject("main");
        this.temperature4 = json_specific.getInt("temp");
        json_specific = json.getJSONArray("list").getJSONObject(31).getJSONObject("main");
        this.temperature5 = json_specific.getInt("temp");

    }
    
    public String getCity() {
		return city;
	}

	public String getDay1() {
		return day1;
	}
	
	public String getDay2() {
		return day2;
	}
	
	public String getDay3() {
		return day3;
	}
	
	public String getDay4() {
		return day4;
	}
	
	public String getDay5() {
		return day5;
	}
	
	public Integer getTemperature() {
		return temperature;
	}
	
	public Integer getTemperature1() {
		return temperature1;
	}
	
	public Integer getTemperature2() {
		return temperature2;
	}
	
	public Integer getTemperature3() {
		return temperature3;
	}
	
	public Integer getTemperature4() {
		return temperature4;
	}
	
	public Integer getTemperature5() {
		return temperature5;
	}


	public String getIcon() {
		return icon;
	}
	
	public String getIcon1() {
		return icon1;
	}

	public String getIcon2() {
		return icon2;
	}
	
	public String getIcon3() {
		return icon3;
	}
	
	public String getIcon4() {
		return icon4;
	}
	
	public String getIcon5() {
		return icon5;
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
	
	public String getDt() {
		return dt;
	}
}

