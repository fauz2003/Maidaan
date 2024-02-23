package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import SQLDB.*;
import Person.Person;
import javafx.fxml.*;
import javafx.scene.control.*;


public class loginController {

	@FXML
	private Button backButton1;
	@FXML
	private Button loginButton2;
	@FXML
	private TextField username;
	@FXML
	private PasswordField pw;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField uID;
	
	
	public String name = null, pw1 = null;
	int userID;
	

	public void back() throws IOException {
		Main m1 = new Main();
		m1.changeScene("Menu.fxml");
	}
	
	public void login()throws IOException{
		//call authenticate login function

		userID = Integer.parseInt(uID.getText());
		name = username.getText();
		pw1 = pw.getText();
		

		Boolean flag = true;
		
		Person temp = retrievePersonData(userID);
		
		if(temp.getpID() != -1)
		{
			System.out.println(temp.getName());
			System.out.println(temp.getpID());
			System.out.println(temp.getRole());
			
			if(temp.getPass()==pw1 && temp.getName() == name)
			{
				flag=true;
			}
			//which will return boolean value stored in flag
			
			
			if (flag == true) {
				Main m1 = new Main();
				m1.changeScene("dashboard.fxml");
			}

		}
		else {
			wrongLogin.setText("Incorrect username or password!");
		}
		
		
	}
	

	public static Person retrievePersonData(int personId) {
	    String url = "jdbc:mysql://localhost:3306/Maidaan";
	    String username = "root";
	    String passwordDB = "Abd@032003";
    	Person p1 = new Person (-1,"awd","awd");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
	            String retrieveQuery = "SELECT * FROM Person WHERE person_id = ?";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery)) {
	                preparedStatement.setInt(1, personId);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        int retrievedId = resultSet.getInt("person_id");
	                        String retrievedName = resultSet.getString("name");
	                        String retrievedPassword = resultSet.getString("password");
	                        String retrievedRole = resultSet.getString("role");

	                        // Create a Person object with the retrieved data
	                        Person retrievedPerson = new Person(retrievedId, retrievedName, retrievedPassword);
	                        retrievedPerson.setRole(retrievedRole);

	                        return retrievedPerson;
	                    } else {
	                        System.out.println("No person record found with ID " + personId);
	                        return p1;
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
	    
	    return p1;

	}
}
