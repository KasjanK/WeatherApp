package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
			
			primaryStage.setTitle("Weather");
			primaryStage.setScene(new Scene(root, 1600, 900));
			primaryStage.getScene().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
