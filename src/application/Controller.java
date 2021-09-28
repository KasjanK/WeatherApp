package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
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
	private ImageView img;
	@FXML
	private Button change, set, cancel;
	@FXML
	private TextField cityName, invis;
	@FXML
	private Label city, temperature, day, desc, errors, windSpeed, cloudiness, pressure, humidity;
	
	 public Controller() {
	        this.citySet = "London".toUpperCase();
	    }
	
	@FXML
    private void handleButtonClicks(javafx.event.ActionEvent ae) {
        String initialCity = city.getText(); //stores the last searched city-name

        if(ae.getSource() == change){
            cityName.setText("");
            bottomSet(true);
            cityName.requestFocus();
        }else if (ae.getSource() == set) {
            setPressed();
        } else if (ae.getSource() == cancel) {
            cityName.setText(initialCity);
            bottomSet(false);
            invis.requestFocus();
        }
    }
	
	//method to clear all the fields
    private void reset() {
    	bottomSet(false);
        day.setText("");
        temperature.setText("");
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
                this.citySet = cityName.getText().trim();
                cityName.setText((cityName.getText().trim()).toUpperCase());
                openWeatherMap = new OpenWeatherMap(citySet);
                showWeather();
                bottomSet(false);
                invis.requestFocus();
            }catch(Exception e){
                city.setText("Error");
                showToast("City with the given name was not found.");
                reset();
                invis.requestFocus();
            }
        }
    }
    
    //method to handle nodes at botton part of the scene
    private void bottomSet(boolean statement){
        cityName.setDisable(!statement);
        set.setVisible(statement);
        change.setVisible(!statement);
        cancel.setVisible(statement);
    }
    
    private void showToast(String message) {
        errors.setText(message);
        errors.setTextFill(Color.TOMATO);
        errors.setStyle("-fx-background-color: #fff; -fx-background-radius: 50px;");

        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setToValue(1);
        fadeIn.setFromValue(0);
        fadeIn.play();

        fadeIn.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition();
            pause.play();
            pause.setOnFinished(event2 -> {
                FadeTransition fadeOut = new FadeTransition();
                fadeOut.setToValue(0);
                fadeOut.setFromValue(1);
                fadeOut.play();
            });
        });
    }
    
	private void showWeather() {
        openWeatherMap.getWeather();
        city.setText(openWeatherMap.getCity().toUpperCase());
        temperature.setText(openWeatherMap.getTemperature()+"�C");
        day.setText(openWeatherMap.getDay());
        desc.setText(openWeatherMap.getDescription().toUpperCase());
        img.setImage(new Image(WeatherImgChanger.getImage(openWeatherMap.getIcon())));
        windSpeed.setText(openWeatherMap.getWindSpeed()+" m/s");
        cloudiness.setText(openWeatherMap.getCloudiness()+"%");
        pressure.setText(openWeatherMap.getPressure()+" hpa");
        humidity.setText(openWeatherMap.getHumidity()+"%");
    }

	public void initialize(URL location, ResourceBundle resources) {
        cityName.setText(citySet);
        cityName.setDisable(true);
        set.setVisible(false);
        cancel.setVisible(false);
        errors.setText("");
        openWeatherMap = new OpenWeatherMap(citySet);
        // invis.requestFocus();

        //try catch block to see if there is internet and disabling ecery field
        
        openWeatherMap.getWeather();
        city.setText(openWeatherMap.getCity().toUpperCase());
        temperature.setText(openWeatherMap.getTemperature()+"�C");
        day.setText(openWeatherMap.getDay());
        desc.setText(openWeatherMap.getDescription().toUpperCase());
        windSpeed.setText(openWeatherMap.getWindSpeed()+" m/s");
        cloudiness.setText(openWeatherMap.getCloudiness()+"%");
        pressure.setText(openWeatherMap.getPressure()+" hpa");
        humidity.setText(openWeatherMap.getHumidity()+"%");
        /*} catch (Exception e){
            city.setText("Error");
            showToast("this sucks");
            reset();
            change.setDisable(true);
            cityName.setText("");
        } */

        //Set the city entered into textField on pressing enter on Keyboard
        cityName.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                setPressed();
            }
        });
    }
}