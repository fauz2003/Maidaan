package SQLDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Maidaan.*;
import Person.Person;
import Ground.Ground;
import Ground.Slot;

public class SQLDatabase {

    public static void main(String[] args) {
    	
    	createDatabaseAndTables();
        
    }
    
    public static void createTableForGround(Connection connection, String groundTableName, String slotsTableName) {
        try (Statement statement = connection.createStatement()) {
            // Create Ground table
            String createGroundTableSQL = "CREATE TABLE IF NOT EXISTS " + groundTableName + " ("
                    + "ground_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "location VARCHAR(255),"
                    + "inventory VARCHAR(255),"
                    + "manager_id INT,"
                    + "slot_table_id INT,"
                    + "FOREIGN KEY (slot_table_id) REFERENCES " + slotsTableName + "(slot_id)"
                    + ")";
            statement.executeUpdate(createGroundTableSQL);

            String createSlotsTableSQL = "CREATE TABLE IF NOT EXISTS " + slotsTableName + " ("
                    + "slot_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "ground_id INT,"
                    + "slot_times VARCHAR(255),"
                    + "status VARCHAR(50),"
                    + "FOREIGN KEY (ground_id) REFERENCES " + groundTableName + "(ground_id)"
                    + ")";
            statement.executeUpdate(createSlotsTableSQL);

            System.out.println("Tables '" + groundTableName + "' and '" + slotsTableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }
       
    public static void createTableForTeam(Connection connection, String tableName) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                    + "team_name VARCHAR(255) PRIMARY KEY,"
                    + "manager_id INT,"
                    + "FOREIGN KEY (manager_id) REFERENCES Person(person_id)"
                    + ")";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createTableForPerson(Connection connection, String tableName) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                    + "person_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "password VARCHAR(255),"
                    + "role VARCHAR(50)"
                    + ")";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createTableForPlayer(Connection connection, String tableName) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                    + "player_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "player_name VARCHAR(255),"
                    + "team_name VARCHAR(255),"
                    + "FOREIGN KEY (team_name) REFERENCES Team(team_name)"
                    + ")";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
 
    public static void createTableForMatch(Connection connection, String tableName) {
        try (Statement statement = connection.createStatement()) {
        	String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ("
        	        + "match_id INT AUTO_INCREMENT PRIMARY KEY,"
        	        + "team1_name VARCHAR(255),"
        	        + "team2_name VARCHAR(255),"
        	        + "ground_id INT,"
        	        + "match_date DATE,"
        	        + "FOREIGN KEY (team1_name) REFERENCES Team(team_name),"
        	        + "FOREIGN KEY (team2_name) REFERENCES Team(team_name),"
        	        + "FOREIGN KEY (ground_id) REFERENCES Ground(ground_id)"
        	        + ")";
        	statement.executeUpdate(createTableSQL);

            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
    
    public static void createTableForLeagueOrganizer(Connection connection, String tableName) {
        try {
        	Statement statement = connection.createStatement();
        	
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                    + "person_id INT PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "password VARCHAR(255),"
                    + "role VARCHAR(50),"
                    + "league_id INT,"
                    + "FOREIGN KEY (person_id) REFERENCES Person(person_id)"
                    + ")";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
    
    public static void createTableForLeague(Connection connection, String tableName) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                    + "league_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "league_name VARCHAR(255),"
                    + "organizer_id INT,"
                    + "FOREIGN KEY (organizer_id) REFERENCES Person(person_id)"
                    + ")";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createDatabaseAndTables() {
        String databaseURL = "jdbc:mysql://localhost:3306/";
        String databaseName = "Maidaan";
        String username = "root";
        String password = "Abd@032003";
        String personTableName = "Person";
        String playerTableName = "Player";
        String groundTableName = "Ground";
        String slotTableName = "Slot";
        String matchTableName = "match";
        String teamTableName = "Team";
        String leagueOrganizerTableName = "LeagueOrganizer";
        String leagueTableName = "League";

        try (Connection connection = DriverManager.getConnection(databaseURL, username, password)) {
            // Create the database if it doesn't exist
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createDatabaseSQL);
            }

            System.out.println("Database '" + databaseName + "' created successfully.");

            // Switch to the new database
            connection.setCatalog(databaseName);

            // Create the Person table
            createTableForPerson(connection, personTableName);
            
            // Create the Player table
            createTableForPlayer(connection, playerTableName);

            // Create the Ground table
            createTableForGround(connection, groundTableName, slotTableName);

            // Create the Team table
            createTableForTeam(connection, teamTableName);

            // Create the Match table
            createTableForMatch(connection, matchTableName);
            
            // Create the LeagueOrganizer table
            createTableForLeagueOrganizer(connection, leagueOrganizerTableName);

            // Create Table For League 
            createTableForLeague(connection, leagueTableName);
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
}

    // ------- Person CRUD Operations ----------
    
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

	public static void deletePersonData(int personId) {
	    String url = "jdbc:mysql://localhost:3306/Maidaan";
	    String username = "root";
	    String passwordDB = "Abd@032003";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
	            String deleteQuery = "DELETE FROM Person WHERE person_id = ?";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
	                preparedStatement.setInt(1, personId);

	                int rowsDeleted = preparedStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                    System.out.println("Person record with ID " + personId + " was deleted successfully.");
	                } else {
	                    System.out.println("No person record found with ID " + personId + ". Deletion failed.");
	                }
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	public static void updatePersonData(int personId, String newName, String newPassword, String newRole) {
	    String url = "jdbc:mysql://localhost:3306/Maidaan";
	    String username = "root";
	    String passwordDB = "Abd@032003";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
	            String updateQuery = "UPDATE Person SET name = ?, password = ?, role = ? WHERE person_id = ?";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	                preparedStatement.setString(1, newName);
	                preparedStatement.setString(2, newPassword);
	                preparedStatement.setString(3, newRole);
	                preparedStatement.setInt(4, personId);

	                int rowsUpdated = preparedStatement.executeUpdate();

	                if (rowsUpdated > 0) {
	                    System.out.println("Person record with ID " + personId + " was updated successfully.");
	                } else {
	                    System.out.println("No person record found with ID " + personId + ". Update failed.");
	                }
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public static Person retrievePersonData(int personId) {
	    String url = "jdbc:mysql://localhost:3306/Maidaan";
	    String username = "root";
	    String passwordDB = "Abd@032003";

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

	    // Return null if no person is found
	    return null;
	}
	
	// ------ Player CRUD Operations ----------
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

    public static void updatePlayerData(int playerId, String newPlayerName, String newTeamName) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String updateQuery = "UPDATE Player SET player_name = ?, team_name = ? WHERE player_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newPlayerName);
                    preparedStatement.setString(2, newTeamName);
                    preparedStatement.setInt(3, playerId);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Player record with ID " + playerId + " was updated successfully.");
                    } else {
                        System.out.println("No player record found with ID " + playerId + ". Update failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    
    public static Player retrievePlayerData(int playerId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String retrieveQuery = "SELECT * FROM Player WHERE player_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery)) {
                    preparedStatement.setInt(1, playerId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String retrievedPlayerName = resultSet.getString("player_name");
                            String retrievedTeamName = resultSet.getString("team_name");

                            // Create a Player object with the retrieved data
                            Player retrievedPlayer = new Player(playerId, retrievedPlayerName, retrievedTeamName);

                            return retrievedPlayer;
                        } else {
                            System.out.println("No player record found with ID " + playerId);
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

        // Return null if no player is found
        return null;
    }

	
    // ------ Team CRUD Operations ---------
    
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

    public static void updateTeamData(String teamName, int newManagerId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String updateQuery = "UPDATE Team SET manager_id = ? WHERE team_name = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setInt(1, newManagerId);
                    preparedStatement.setString(2, teamName);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Team record with name " + teamName + " was updated successfully.");
                    } else {
                        System.out.println("No team record found with name " + teamName + ". Update failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    
    public static Team retrieveTeamData(String teamName) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String retrieveQuery = "SELECT * FROM Team WHERE team_name = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery)) {
                    preparedStatement.setString(1, teamName);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            int managerId = resultSet.getInt("manager_id");

                            // Create a Team object with the retrieved data
                            Team retrievedTeam = new Team(teamName, managerId);

                            return retrievedTeam;
                        } else {
                            System.out.println("No team record found with name " + teamName);
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

        // Return null if no team is found
        return null;
    }

	
    // ------ Ground CRUD Operations --------
    
    public static void insertGroundData(String name, String location, String inventory, int managerId, String slotTimes) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String insertQuery = "INSERT INTO Ground (name, location, inventory, manager_id, slot_times) VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, location);
                    preparedStatement.setString(3, inventory);
                    preparedStatement.setInt(4, managerId);
                    preparedStatement.setString(5, slotTimes);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("A new ground record was inserted successfully.");

                        // Retrieve the auto-generated ground_id
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int groundId = generatedKeys.getInt(1);
                            System.out.println("Generated Ground ID: " + groundId);
                        } else {
                            System.out.println("Failed to retrieve the generated Ground ID.");
                        }
                    } else {
                        System.out.println("Failed to insert a new ground record.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateGroundData(int groundId, String newName, String newLocation, String newInventory, int newManagerId, String newSlotTimes) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String updateQuery = "UPDATE Ground SET name = ?, location = ?, inventory = ?, manager_id = ?, slot_times = ? WHERE ground_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newName);
                    preparedStatement.setString(2, newLocation);
                    preparedStatement.setString(3, newInventory);
                    preparedStatement.setInt(4, newManagerId);
                    preparedStatement.setString(5, newSlotTimes);
                    preparedStatement.setInt(6, groundId);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Ground record with ID " + groundId + " was updated successfully.");
                    } else {
                        System.out.println("No ground record found with ID " + groundId + ". Update failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroundData(int groundId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String deleteQuery = "DELETE FROM Ground WHERE ground_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, groundId);

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Ground record with ID " + groundId + " was deleted successfully.");
                    } else {
                        System.out.println("No ground record found with ID " + groundId + ". Deletion failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public static void insertSlotTimes(int groundId, String slotTimes, String status) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";
        String slotsTableName = "Slot";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String insertQuery = "INSERT INTO " + slotsTableName + " (ground_id, slot_times, status) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, groundId);
                    preparedStatement.setString(2, slotTimes);
                    preparedStatement.setString(3, status);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Slot times with status '" + status + "' inserted successfully for Ground ID " + groundId);
                    } else {
                        System.out.println("Failed to insert slot times for Ground ID " + groundId);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void generateAndInsertDummySlotTimes(int groundId) {
        // Generate dummy values with 1-hour intervals from 6:00 AM to 12:00 PM
        for (int hour = 6; hour <= 12; hour++) {
            String startTime = String.format("%02d:00 AM", hour);
            String endTime = String.format("%02d:00 AM", hour + 1);

            String availableSlotTimes = startTime + " - " + endTime;
            insertSlotTimes(groundId, availableSlotTimes, "Available");
        }
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
    
    // ------ Match CRUD Operations --------
    
    public static void insertMatchData(Team team1, Team team2, Ground ground, String matchDate) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                // Use backticks to escape the reserved keyword "MATCH"
                String insertQuery = "INSERT INTO `Match` (team1_name, team2_name, ground_id, match_date) VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, team1.getTeamName());
                    preparedStatement.setString(2, team2.getTeamName());
                    preparedStatement.setInt(3, ground.getGroundId());
                    preparedStatement.setString(4, matchDate);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("A new match record was inserted successfully.");

                        // Retrieve the auto-generated match_id
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int matchId = generatedKeys.getInt(1);
                            System.out.println("Generated Match ID: " + matchId);
                        } else {
                            System.out.println("Failed to retrieve the generated Match ID.");
                        }
                    } else {
                        System.out.println("Failed to insert a new match record.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void updateMatchData(int matchId, Team newTeam1, Team newTeam2, Ground newGround, String newMatchDate) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String updateQuery = "UPDATE Match SET team1_name = ?, team2_name = ?, ground_id = ?, match_date = ? WHERE match_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newTeam1.getTeamName());
                    preparedStatement.setString(2, newTeam2.getTeamName());
                    preparedStatement.setInt(3, newGround.getGroundId());
                    preparedStatement.setString(4, newMatchDate);
                    preparedStatement.setInt(5, matchId);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Match record with ID " + matchId + " was updated successfully.");
                    } else {
                        System.out.println("No match record found with ID " + matchId + ". Update failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMatchData(int matchId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String deleteQuery = "DELETE FROM Match WHERE match_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, matchId);

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Match record with ID " + matchId + " was deleted successfully.");
                    } else {
                        System.out.println("No match record found with ID " + matchId + ". Deletion failed.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static Team retrieveTeamData(int teamId) {
        String url = "jdbc:mysql://localhost:3306/Maidaan";
        String username = "root";
        String passwordDB = "Abd@032003";

        Team retrievedTeam = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, passwordDB)) {
                String retrieveQuery = "SELECT * FROM Team WHERE team_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery)) {
                    preparedStatement.setInt(1, teamId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String teamName = resultSet.getString("team_name");
                            int managerId = resultSet.getInt("manager_id");
                            

                            retrievedTeam = new Team(teamName, managerId);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retrievedTeam;
    }
	
}


