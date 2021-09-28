module WeatherApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.json;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
