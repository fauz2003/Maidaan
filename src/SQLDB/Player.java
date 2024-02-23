package SQLDB;

public class Player {
    private int playerId;
    private String playerName;
    private String teamName;

    // Constructor
    public Player(int playerId, String playerName, String teamName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.teamName = teamName;
    }

    // Getter for playerId
    public int getPlayerId() {
        return playerId;
    }

    // Setter for playerId
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    // Getter for playerName
    public String getPlayerName() {
        return playerName;
    }

    // Setter for playerName
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Getter for teamName
    public String getTeamName() {
        return teamName;
    }

    // Setter for teamName
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void Display() {
        System.out.println( "Player{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", teamName='" + teamName + '\'' +
                '}');
    }
}
