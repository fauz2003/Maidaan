package Person;

public abstract class User {
	
    private int ID;
    private String name;
    private String password;

    public User(int ID, String name, String password){
        this.ID = ID;
        this.name = name;
        this.password = password;
    }

    // Getter for personId
    public int getpID() {
        return ID;
    }

    // Getter for name
    public String getName() {
        return name;
    }
    
    public String getPass() {
        return password;
    }


    // Setter for name
    public void setName(String newName) {
        this.name = newName;
    }

    // Method to authenticate
    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

}
