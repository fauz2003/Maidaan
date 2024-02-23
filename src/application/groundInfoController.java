package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Ground.Ground;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class groundInfoController {

    @FXML
    private TextField GID;

    @FXML
    private Button back;

    @FXML
    private Button groundInfo;

    @FXML
    public Label loc1;
    
    @FXML
    public Label managerID;

    @FXML
    public Label name;

    int groundID, mID;
    String name1= null, location1 = null;
    
    Ground temp ;
    
    @FXML
    void ShowGroundInfo(ActionEvent event) {
    	groundID = Integer.parseInt(GID.getText());
    	
    	temp=retrieveGroundData(groundID);
    	
    	name1=temp.getName();
    	location1=temp.getLocation();
    	mID=temp.getManagerId();
    	
    	
    	name.setText(name1);
    	loc1.setText(location1);
    	managerID.setText(Integer.toString(mID));
    	
    }

    public static Ground retrieveGroundData(int groundId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String retrieveQuery = "SELECT * FROM Ground WHERE ground_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery)) {
                    preparedStatement.setInt(1, groundId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String retrievedName = resultSet.getString("name");
                            String retrievedLocation = resultSet.getString("location");
                            String retrievedInventory = resultSet.getString("inventory");
                            int retrievedManagerId = resultSet.getInt("manager_id");
                            String retrievedSlotTimes = resultSet.getString("slot_times");

                            // Create a Ground object with the retrieved data
                            Ground retrievedGround = new Ground(groundId, retrievedName, retrievedLocation, retrievedInventory, retrievedManagerId, retrievedSlotTimes);

                            return retrievedGround;
                        } else {
                            System.out.println("No ground record found with ID " + groundId);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions (log or throw custom exception)
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle class not found exception (log or throw custom exception)
            e.printStackTrace();
        }

        // Return null if no ground is found
        return null;
    }
    
    @FXML
    void back(ActionEvent event) throws IOException {
    	Main m1 = new Main();
    	m1.changeScene("dashboard.fxml");
    }

}
