package application;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;

public class dashboardController {
	
	@FXML
	public Button browseSlotButton;
	@FXML
	public Button enterTeamButton;
	@FXML
	public Button deleteTeam;
	@FXML
	public Button logoutButton;
	@FXML
	public Button GroundInfoButton;
	 @FXML
	 public Button addPlayerButton;
	 @FXML
	 public Button removePlayerButton;
	@FXML
	public Button bookSlotButton;
	@FXML
	public Button cancelBookingButton;
	@FXML
	public Label nameDisplay;
	

	public void logout() throws IOException {
		Main m1 = new Main();
		m1.changeScene("login.fxml");
	}
	
	public void showSlots() throws IOException {
		Main m1 = new Main();
		m1.changeScene("slots.fxml");
	}
	
	public void enterTeam() throws IOException {
		Main m1 = new Main();
		m1.changeScene("enterTeam.fxml");
	}
	
	 
	public void addPlayer() throws IOException {
		Main m1 = new Main();
		m1.changeScene("addPlayer.fxml");
	}
	
	public void removePlayer() throws IOException {
		Main m1 = new Main();
		m1.changeScene("removePlayer.fxml");
	}
	public void GroundInfo() throws IOException {
        Main m1 = new Main();
        m1.changeScene("GroundInfo.fxml");
    }
	
	public void cancelBooking() throws IOException {
		Main m1 = new Main();
		m1.changeScene("cancelBooking.fxml");
	}
	
	public void bookSlot() throws IOException {
		Main m1 = new Main();
		m1.changeScene("bookSlot.fxml");
	}
	
	public void deleteTeam() throws IOException {
		Main m1 = new Main();
		m1.changeScene("deleteTeam.fxml");
	}
	
	
}
