package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class enterTeamController {
	
	@FXML
	public Button back;
	@FXML
	public Button register;
	@FXML
	public TextField Tname, Mname;
	@FXML
	public Text InputResponse;
	
	String teamName = null;
	int managerID = 6;
    @FXML
    public void RegisterTeam() throws IOException {
        teamName = Tname.getText();
        managerID = Integer.parseInt(Mname.getText());
        insertTeamData( teamName, managerID);
        
        InputResponse.setText("Team Registered Successfully!");
        Tname.clear();
        Mname.clear();
       
    }
	
	public void back() throws IOException {
		Main m1 = new Main();
		m1.changeScene("dashboard.fxml");
	}
	
	public static void insertTeamData(String teamName, int managerId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String insertQuery = "INSERT INTO Team (team_name, manager_id) VALUES (?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, teamName);
                    preparedStatement.setInt(2, managerId);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("A new team record was inserted successfully.");
                    } else {
                        System.out.println("Failed to insert a new team record.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
