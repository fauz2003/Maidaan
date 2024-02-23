package Maidaan; // Replace YourPackage with the actual package name

import Ground.Ground;
import Person.*;

public class Match {
    private Team team1;
    private Team team2;
    private Ground ground;
    private String matchDate;

    // Constructor
    public Match(Team team1, Team team2, Ground ground, String matchDate) {
        this.team1 = team1;
        this.team2 = team2;
        this.ground = ground;
        this.matchDate = matchDate;
    }

    // Getter for team1
    public Team getTeam1() {
        return team1;
    }

    // Getter for team2
    public Team getTeam2() {
        return team2;
    }

    // Getter for ground
    public Ground getGround() {
        return ground;
    }

    // Getter for matchDate
    public String getMatchDate() {
        return matchDate;
    }

    // Setter for matchDate
    public void setMatchDate(String newMatchDate) {
        this.matchDate = newMatchDate;
    }

    // Display match details
    public void displayMatchDetails() {
        System.out.println("Match Details:");
        System.out.println("Match Date: " + matchDate);
        System.out.println("Team 1: " + team1.getTeamName());
        System.out.println("Team 2: " + team2.getTeamName());
        System.out.println("Ground: " + ground.getGroundId());
        System.out.println("Location: " + ground.getLocation());
        System.out.println("Slot Times: " + ground.getSlotTimes());
    }

    // Example usage
    public static void main(String[] args) {
        // Create Teams
        Person manager1 = new Person(101, "Manager 1", "manager1Password");
        Team team1 = new Team("Team A", manager1.getpID());

        Person manager2 = new Person(102, "Manager 2", "manager2Password");
        Team team2 = new Team("Team B", manager2.getpID());

        // Create Ground
        Ground ground = new Ground(1, "Sports Ground", "City Park", "Football, Basketball", 201, "9 AM - 5 PM");

        // Create a Match instance
        Match footballMatch = new Match(team1, team2, ground, "2023-11-15");

        // Display match details
        footballMatch.displayMatchDetails();
    }
}
