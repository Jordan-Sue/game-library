package model;

// Represents a game that has a name, a system, a completion status, and a play time
public class Game {
    private String name;
    private String system;
    private String status;
    private double playTime;

    // REQUIRES: name and system are not an empty string; status is one of the strings: Not Played, Played, Beaten,
    //           or Completed; playTime must be >= 0.
    // EFFECTS: sets the given parameters to the appropriate variables
    public Game(String name, String system, String status, double playTime) {
        this.name = name;
        this.system = system;
        this.status = status;
        this.playTime = playTime;
    }

    // REQUIRES: newStatus must be one of the strings: Not Played, Played, Beaten, Completed
    // MODIFIES: this
    // EFFECTS: changes the current status of the game to the given status
    public void changeStatus(String newStatus) {
        status = newStatus;
    }

    // REQUIRES: newPlayTime must be >= 0
    // MODIFIES: this
    // EFFECTS: changes the current playTime to the newPlayTime
    public void changePlayTime(double newPlayTime) {
        playTime = newPlayTime;
    }

    // EFFECTS: returns the name of the game
    public String getName() {
        return name;
    }

    // EFFECTS: returns the system the game is on
    public String getSystem() {
        return system;
    }

    // EFFECTS: returns the completion status of the game
    public String getStatus() {
        return status;
    }

    // EFFECTS: returns the play time of the game
    public double getPlayTime() {
        return playTime;
    }
}
