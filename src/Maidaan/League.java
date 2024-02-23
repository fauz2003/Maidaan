package Maidaan;

import Ground.Ground;
import Person.*;

import java.util.ArrayList;
import java.util.List;

public class League {
	private LeagueOrganizer Organizer;
    private String leagueName;
    private List<Team> teams;
    private List<Ground> grounds;

    // Constructor
    public League(String leagueName) {
        this.leagueName = leagueName;
        this.teams = new ArrayList<Team>();
        this.grounds = new ArrayList<Ground>();
    }
    
    
    public String getOrganizer()
    {
    	return Organizer.getName();
    }
    
    public void setOrganizer(String newname)
    {
    	Organizer.setName(newname);
    }
    // Getter for leagueName
    public String getLeagueName() {
        return leagueName;
    }

    // Getter for teams
    public List<Team> getTeams() {
        return teams;
    }

    // Getter for grounds
    public List<Ground> getGrounds() {
        return grounds;
    }

    // Add a team to the league
    public void addTeam(Team team) {
        teams.add(team);
        System.out.println("Team " + team.getTeamName() + " added to " + leagueName);
    }

    // Add a ground to the league
    public void addGround(Ground ground) {
        grounds.add(ground);
        System.out.println("Ground " + ground.getGroundId() + " added to " + leagueName);
    }

    // Display league details
    public void displayLeagueDetails() {
        System.out.println("League Details:");
        System.out.println("League Name: " + leagueName);
        System.out.println("Teams:");
        for (Team team : teams) {
            System.out.println("  - " + team.getTeamName());
        }
        System.out.println("Grounds:");
        for (Ground ground : grounds) {
            System.out.println("  - Ground " + ground.getGroundId() + " at " + ground.getLocation());
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create Teams
        Person manager1 = new Person(101, "Manager 1", "manager1Password");
        Team team1 = new Team("Team A", manager1.getpID());

        Person manager2 = new Person(102, "Manager 2", "manager2Password");
        Team team2 = new Team("Team B", manager2.getpID());

        // Create Grounds
        Ground ground1 = new Ground(1, "Sports Ground 1", "City Park", "Football, Basketball", 201, "9 AM - 5 PM");
        Ground ground2 = new Ground(2, "Sports Ground 2", "Central Park", "Soccer, Tennis", 202, "10 AM - 6 PM");

        // Create a League instance
        League footballLeague = new League("Football League");
      
        // Add teams and grounds to the league
        footballLeague.addTeam(team1);
        footballLeague.addTeam(team2);
        footballLeague.addGround(ground1);
        footballLeague.addGround(ground2);

        // Display league details
        footballLeague.displayLeagueDetails();
    }
}
