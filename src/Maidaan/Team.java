package Maidaan;

import Person.*;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static final int MAX_PLAYERS = 15;

    private String teamName;
    private List<Person> players;
    private int manager;

    // Constructor
    public Team(String teamName, int manager ) {
        this.teamName = teamName;
        this.manager = manager;
        this.players = new ArrayList<Person>();
    }

    // Getter for teamName
    public String getTeamName() {
        return teamName;
    }

    // Getter for players
    public List<Person> getPlayers() {
        return players;
    }

    // Getter for manager
    public int getManager() {
        return manager;
    }

    // Add a player to the team
    public void addPlayer(Person player) {
        if (players.size() < MAX_PLAYERS) {
            players.add(player);
            System.out.println(player.getName() + " added to " + teamName);
        } else {
            System.out.println("Cannot add " + player.getName() + ". Maximum players reached for " + teamName);
        }
    }

    // Remove a player from the team
    public void removePlayer(Person player) {
        if (players.remove(player)) {
            System.out.println(player.getName() + " removed from " + teamName);
        } else {
            System.out.println(player.getName() + " is not in " + teamName);
        }
    }

}

