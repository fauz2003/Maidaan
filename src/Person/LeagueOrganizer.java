package Person;

public class LeagueOrganizer extends Person {
    private int leagueID;

    // Constructor
    public LeagueOrganizer(int personId, String name, String password, int leagueID) {
        super(personId, name, password);
        this.setLeagueID(leagueID);
        
        super.set_LeagueOrganizer();
    }

    // Example usage
    public static void main(String[] args) {
        // Create a LeagueOrganizer instance
        LeagueOrganizer organizer1 = new LeagueOrganizer(3, "Organizer User", "organizerPassword", 4);

        // Display organizer details
        System.out.println("ID: " + organizer1.getpID());
        System.out.println("Name: " + organizer1.getName());
        System.out.println("Role: " + organizer1.getRole());
        System.out.println("League ID: " + organizer1.getLeagueID());

        // Change the league name
        organizer1.setLeagueID(8);
        System.out.println("New League ID: " + organizer1.getLeagueID());

        // Authentication example
        String enteredPassword = "organizerPassword";
        if (organizer1.authenticate(enteredPassword)) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed.");
        }
    }

	public int getLeagueID() {
		return leagueID;
	}

	public void setLeagueID(int leagueID) {
		this.leagueID = leagueID;
	}
}
