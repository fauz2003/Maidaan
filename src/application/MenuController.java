package application;

import java.io.IOException;

import javafx.fxml.*;
import javafx.scene.control.Button;

public class MenuController {
	
	public MenuController() {
		
	}
	
	@FXML
	private Button loginButton;
	@FXML
	private Button signupButton;
	
	
	public void start_login() throws IOException {
		Main m1 = new Main();
		m1.changeScene("login.fxml");
	}
	
	public void start_signup() throws IOException {
		Main m1 = new Main();
		m1.changeScene("signup.fxml");
	}
	
}
