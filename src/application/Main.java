package application;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import SQLDB.SQLDatabase;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	
	SQLDatabase temp = new SQLDatabase();
	
	private static Stage stg;
	public void start(Stage primaryStage) {
		stg = primaryStage;
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Maidaan");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void changeScene(String fxml) throws IOException {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(root);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
