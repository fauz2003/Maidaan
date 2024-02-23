package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class removePlayerController {

    @FXML
    private Text InputResponse;

    @FXML
    private TextField PID;

    @FXML
    private TextField TID;
    
    @FXML
    public Button removePlayer;

    @FXML
    private Button back;

    public int playerID, teamID;
    
    @FXML
    public void removePlayer() {
    	
        playerID = Integer.parseInt(PID.getText());
        teamID = Integer.parseInt(TID.getText());
        
        deletePlayerData( playerID); 
        
       
        TID.clear();
        PID.clear();
        InputResponse.setText("Player Removed Successfully!");
    }
    
    public static void deletePlayerData(int playerId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String deleteQuery = "DELETE FROM Player WHERE player_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, playerId);

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Player record with ID " + playerId + " was deleted successfully.");
                    } else {
                        System.out.println("No player record found with ID " + playerId + ". Deletion failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
     public void back(ActionEvent event) throws IOException {
    	Main m1 = new Main();
		m1.changeScene("dashboard.fxml");
    }

}
