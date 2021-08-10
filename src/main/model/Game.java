package model;

import org.json.JSONObject;
import persistence.Writable;

// JSON related methods based on JsonSerializationDemo

// Represents a game that has a name, a system, a completion status, and a play time
public class Game implements Writable, Comparable<Game> {
    private String name;
    private String system;
    private Status status;
    private double playTime;

    // REQUIRES: status is one of the enum: Not_Played, Played, Beaten,
    //           or Completed; playTime must be >= 0.
    // EFFECTS: sets the given parameters to the appropriate variables
    public Game(String name, String system, Status status, double playTime) {
        this.name = name;
        this.system = system;
        this.status = status;
        this.playTime = playTime;
    }

    // MODIFIES: this
    // EFFECTS: changes the current status of the game to the given status
    public void changeStatus(Status status) {
        this.status = status;
    }

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
    public Status getStatus() {
        return status;
    }

    // EFFECTS: returns the play time of the game
    public double getPlayTime() {
        return playTime;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("system", system);
        json.put("status", status);
        json.put("playTime", playTime);

        return json;
    }

    @Override
    public int compareTo(Game o) {
        return this.getName().compareTo(o.getName());
    }
}
