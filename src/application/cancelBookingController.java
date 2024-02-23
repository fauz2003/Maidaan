package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Ground.Slot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class cancelBookingController {

    @FXML
    private Text InputResponse;

    @FXML
    private TextField SID;

    @FXML
    private Button back;

    @FXML
    private Button cancelBooking;

    @FXML
    void back(ActionEvent event) throws IOException {
    	Main m1 = new Main();
    	m1.changeScene("dashboard.fxml");
    }
    
    public int slotID;
    public int GroundID;
    
    
    @FXML
    public void cancelBooking(ActionEvent event) {
    	slotID = Integer.parseInt(SID.getText());
        
    	List<Slot> slotsList = retrieveSlotData(GroundID,"6:00 AM - 7:00 AM");
    	
    	Slot temp =slotsList.get(slotID);
        
    	if(temp.getStatus()=="Booked")
    	{

    		updateSlotStatusByTime(GroundID,temp.getSlotTimes(),"A");
            InputResponse.setText("Booking Cancelled!");
    	}
    	else
    	{

            InputResponse.setText("Slot Already Available!");
    	}
        SID.clear();
    }
    
    
	public static List<Slot> retrieveSlotData(int groundId, String slotTimes) {
	    String url = "jdbc:mysql://localhost:3306/Maidaan";
	    String username = "root";
	    String passwordDB = "Abd@032003";
	    String slotsTableName = "Slot";
	
	    List<Slot> slotsList = new ArrayList<>();
	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
	            String retrieveQuery = "SELECT * FROM " + slotsTableName + " WHERE ground_id = ? AND slot_times = ?";
	
	            try (PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery)) {
	                preparedStatement.setInt(1, groundId);
	                preparedStatement.setString(2, slotTimes);
	
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        int slotId = resultSet.getInt("slot_id");
	                        String retrievedSlotTimes = resultSet.getString("slot_times");
	                        String status = resultSet.getString("status");
	
	                        // Create a Slot object with the retrieved data
	                        Slot retrievedSlot = new Slot(groundId, retrievedSlotTimes, status);
	                        slotsList.add(retrievedSlot);
	                    }
	                }
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	
	    return slotsList;
	}

	public static void updateSlotStatusByTime(int groundId, String slotTimes, String newStatus) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";
        String slotsTableName = "Slot";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String updateQuery = "UPDATE " + slotsTableName + " SET status = ? WHERE ground_id = ? AND slot_times = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newStatus);
                    preparedStatement.setInt(2, groundId);
                    preparedStatement.setString(3, slotTimes);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Slot status updated to '" + newStatus + "' for Ground ID " + groundId + " and Slot Times " + slotTimes);
                    } else {
                        System.out.println("No slot found with Ground ID " + groundId + " and Slot Times " + slotTimes + ". Update failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
