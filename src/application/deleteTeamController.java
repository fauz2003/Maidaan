package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class deleteTeamController {
	
	@FXML
	public Button back;
	@FXML
	public Button deleteTeam;
	@FXML
	public TextField Tname, TID;
	@FXML
	public Text InputResponse;
	
	String teamName = null;
	int teamID;
    @FXML
    public void deleteTeam() throws IOException {
        teamName = Tname.getText();
        teamID = Integer.parseInt(TID.getText());
        
        deleteTeamData( teamName);
        
        InputResponse.setText("Team Deleted Successfully!");
        Tname.clear();
        TID.clear();
       
    }
    
    public static void deleteTeamData(String teamName) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String deleteQuery = "DELETE FROM Team WHERE team_name = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setString(1, teamName);

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Team record with name " + teamName + " was deleted successfully.");
                    } else {
                        System.out.println("No team record found with name " + teamName + ". Deletion failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public void back() throws IOException {
		Main m1 = new Main();
		m1.changeScene("dashboard.fxml");
	}
}

