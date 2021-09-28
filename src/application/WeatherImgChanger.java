package application;

public class WeatherImgChanger {
	public static String getImage(String icon) {
		switch(icon) {
		case "sun-5-128":
			return "pics/sun-5-128.png";
		case "clouds-128.png":
			return "pics/clouds-128.png";
		case "partly-cloudy-day-128.png":
			return "pics/partly-cloudy-day-128.png";
		case "rain-128.png":
			return "pics/rain-128.png";
		case "snow-128.png":
			return "pics/snow-128.png";
		case "storm-128.png":
			return "pics/storm-128.png";
		}
		return "pics/sun-5-128.png";
	}
}
