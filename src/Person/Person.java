package Person;

public class Person extends User {
    private String role;

    // Constructor
    public Person(int personId, String name, String password) {
        super(personId, name, password);
    }

    
    
    void set_Admin()
    {
    	this.role="Admin";
    }
    
    void set_LeagueOrganizer()
    {
    	this.role="LeagueOrganizer";
    }
    
    void set_Manager()
    {
    	this.role="Manager";
    }
    
    void set_Player()
    {
    	this.role="Player";
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String newRole) {
        this.role = newRole;
    }


  
}
