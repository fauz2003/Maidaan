package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.*;
import javafx.scene.control.*;

import SQLDB.SQLDatabase;

public class signupController {
	
	@FXML
	private Button backButton1;
	@FXML
	private Button signupButton2;
	@FXML
	private TextField uname;
	@FXML
	private PasswordField pass1;
	@FXML
	private PasswordField pass2;
	@FXML
	private TextField mail;
	
	
	public String username = null, pw1 = null, pw2 = null, email = null;
	int userID;
	
	public void back() throws IOException {
		Main m1 = new Main();
		m1.changeScene("Menu.fxml");
	}
	
	public void signup() throws IOException {
		//Call function to authenticate signup
		username = uname.getText();
		pw1 = pass1.getText();
		pw2 = pass2.getText();
		email = mail.getText();
		
		insertPersonData( username,  pw1, "User");
		
		Main m1 = new Main();
		m1.changeScene("login.fxml");
	}
	
	public static void insertPersonData(String name, String password, String role) {
	    String url = "jdbc:mysql://localhost:3306/Maidaan";
	    String username = "root";
	    String passwordDB = "Abd@032003";
	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
	            String insertQuery = "INSERT INTO Person (name, password, role) VALUES (?, ?, ?)";
	
	            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
	                preparedStatement.setString(1, name);
	                preparedStatement.setString(2, password);
	                preparedStatement.setString(3, role);
	
	                int rowsInserted = preparedStatement.executeUpdate();
	
	                if (rowsInserted > 0) {
	                    System.out.println("A new person record was inserted successfully.");
	
	                    // Retrieve the auto-generated person_id
	                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	                    if (generatedKeys.next()) {
	                        int personId = generatedKeys.getInt(1);
	                        System.out.println("Generated Person ID: " + personId);
	                    } else {
	                        System.out.println("Failed to retrieve the generated Person ID.");
	                    }
	                } else {
	                    System.out.println("Failed to insert a new person record.");
	                }
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}
