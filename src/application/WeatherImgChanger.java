package application;

public class WeatherImgChanger {
	public static String getImage(String icon) {
		switch(icon) {
		case "01d": case "01n":
            return "01d.png";
        case "02d": case "02n": case "50d": case "50n":
            return "02d.png";
        case "03d": case "03n": case "04d": case "04n":
            return "03.png";
        case "09d": case "09n": case "10d": case "10n":
            return "09.png";
        case "11n": case "11d":
            return "11.png";
        case "13d": case "13n":
            return "13.png";
		}
		return "01d.png";
	}
}
