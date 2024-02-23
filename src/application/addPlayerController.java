package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class addPlayerController {

    @FXML
    private Text InputResponse;

    @FXML
    private TextField PID;

    @FXML
    private TextField Pname;

    @FXML
    private TextField TID;

    @FXML
    private TextField Tname;

    @FXML
    public Button addPlayer;

    @FXML
    private Button back;

    public String teamName = null, playerName = null;
    public int playerID, teamID;
    
    @FXML
    public void addplayer() {
    	teamName = Tname.getText();
        playerName = Pname.getText();
        playerID = Integer.parseInt(PID.getText());
        teamID = Integer.parseInt(TID.getText());
        
        insertPlayerData( playerName,  teamName);
        
        Tname.clear();
        Pname.clear();
        TID.clear();
        PID.clear();
        InputResponse.setText("Player Added Successfully!");
    }

    @FXML
     public void back(ActionEvent event) throws IOException {
    	Main m1 = new Main();
		m1.changeScene("dashboard.fxml");
    }
    
	public static void insertPlayerData(String playerName, String teamName) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String insertQuery = "INSERT INTO Player (player_name, team_name) VALUES (?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, playerName);
                    preparedStatement.setString(2, teamName);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("A new player record was inserted successfully.");

                        // Retrieve the auto-generated player_id
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int playerId = generatedKeys.getInt(1);
                            System.out.println("Generated Player ID: " + playerId);
                        } else {
                            System.out.println("Failed to retrieve the generated Player ID.");
                        }
                    } else {
                        System.out.println("Failed to insert a new player record.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

