package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Controller implements Initializable {
	OpenWeatherMap openWeatherMap;
	String citySet;
	
	@FXML
	private ImageView img, img1, img2, img3, img4, img5;
	@FXML
	private Button search;
	@FXML
	private TextField cityName;
	@FXML
	private Label city, temperature, temperature1, temperature2, temperature3, temperature4, temperature5, 
				  day, day1, day2, day3, day4, day5, 
				  desc, errors, windSpeed, cloudiness, pressure, humidity;
	
	
	 public Controller() {
	        this.citySet = "Bucharest".toUpperCase();
	  }
	
	@FXML
    private void handleButtonClicks(javafx.event.ActionEvent ae) {

        if (ae.getSource() == search) {
            setPressed();
        }
     }
	
	// en metod som rensar allt
    private void reset() {
        day.setText("");
        day1.setText("");
        day2.setText("");
        day3.setText("");
        day4.setText("");
        day5.setText("");
        temperature.setText("");
        temperature1.setText("");
        temperature2.setText("");
        temperature3.setText("");
        temperature4.setText("");
        temperature5.setText("");
        desc.setText("");
        windSpeed.setText("");
        cloudiness.setText("");
        pressure.setText("");
        humidity.setText("");
        img.setImage(null);
    }
    
    private void setPressed(){
        //if user enters nothing into cityName field
        if(cityName.getText().equals("")){
        	showToast("City Name cannot be blank");
            return;
        } else {
            try {
                errors.setText("");
                this.citySet = cityName.getText();
                cityName.setText((cityName.getText().trim()).toUpperCase());
                openWeatherMap = new OpenWeatherMap(citySet);
                openWeatherMap.getWeather();
                openWeatherMap.getForecast();
                city.setText(openWeatherMap.getCity().toUpperCase());
                temperature.setText(openWeatherMap.getTemperature()+"°");
                temperature1.setText(openWeatherMap.getTemperature1()+"°");
                temperature2.setText(openWeatherMap.getTemperature2()+"°");
                temperature3.setText(openWeatherMap.getTemperature3()+"°");
                temperature4.setText(openWeatherMap.getTemperature4()+"°");
                temperature5.setText(openWeatherMap.getTemperature5()+"°");
                day1.setText(openWeatherMap.getDay1());
                day2.setText(openWeatherMap.getDay2());
                day3.setText(openWeatherMap.getDay3());
                day4.setText(openWeatherMap.getDay4());
                day5.setText(openWeatherMap.getDay5());
                img.setImage(new Image(WeatherImgChanger.getImage(openWeatherMap.getIcon())));
                desc.setText(openWeatherMap.getDescription().toUpperCase());
                windSpeed.setText(openWeatherMap.getWindSpeed()+" m/s");
                cloudiness.setText(openWeatherMap.getCloudiness()+"%");
                pressure.setText(openWeatherMap.getPressure()+" hpa");
                humidity.setText(openWeatherMap.getHumidity()+"%");
            } catch (Exception e) {
                city.setText("Error");
                showToast("City with the given name was not found.");
                reset();
            }
        }
    }

    // visa error message
    private void showToast(String message) {
        errors.setText(message);
        errors.setTextFill(Color.TOMATO);
        errors.setStyle("-fx-background-color: #fff; -fx-background-radius: 50px;");
    }
    
	public void initialize(URL location, ResourceBundle resources) {
        cityName.setText(citySet);
        errors.setText("");
        openWeatherMap = new OpenWeatherMap(citySet);

        //try catch block to see if there is internet and disabling ecery field
        try {
        	openWeatherMap.getWeather();
        	openWeatherMap.getForecast();
        	city.setText(openWeatherMap.getCity().toUpperCase());
        	temperature.setText(openWeatherMap.getTemperature()+"°");
        	temperature1.setText(openWeatherMap.getTemperature1()+"°");
        	temperature2.setText(openWeatherMap.getTemperature2()+"°");
        	temperature3.setText(openWeatherMap.getTemperature3()+"°");
        	temperature4.setText(openWeatherMap.getTemperature4()+"°");
        	temperature5.setText(openWeatherMap.getTemperature5()+"°");
        	day1.setText(openWeatherMap.getDay1());
        	day2.setText(openWeatherMap.getDay2());
        	day3.setText(openWeatherMap.getDay3());
        	day4.setText(openWeatherMap.getDay4());
        	day5.setText(openWeatherMap.getDay5());
        	desc.setText(openWeatherMap.getDescription().toUpperCase());
        	img.setImage(new Image(WeatherImgChanger.getImage(openWeatherMap.getIcon())));
        	windSpeed.setText(openWeatherMap.getWindSpeed()+" m/s");
        	cloudiness.setText(openWeatherMap.getCloudiness()+"%");
        	pressure.setText(openWeatherMap.getPressure()+" hpa");
        	humidity.setText(openWeatherMap.getHumidity()+"%");
        } catch (Exception e){
            city.setText("Error");
            showToast("this sucks");
            reset();
            cityName.setText("");
        }

        // binda enter till sökfunktionen
        cityName.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                setPressed();
            }
        });
    }
}
