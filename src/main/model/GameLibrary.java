package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// JSON related methods based on JsonSerializationDemo

// A list of game objects
public class GameLibrary implements Writable {
    private ArrayList<Game> gameLib;
    // private String owner;

    // EFFECTS: creates an empty list of games
    public GameLibrary() {
        gameLib = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a game to games
    public void addGame(Game g) {
        gameLib.add(g);
    }

    // EFFECTS: returns true if the given game's name is in the list, false otherwise
    public boolean findGame(String name) {
        for (Game x : gameLib) {
            if (x.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the game with the given name
    public Game returnGame(String name) {
        for (Game x : gameLib) {
            if (x.getName().equalsIgnoreCase(name)) {
                return x;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: removes the game with the give name
    public void removeGame(String name) {
        gameLib.remove(returnGame(name));
    }

    // MODIFIES: this
    // EFFECTS: removes the game from the GameLibrary
    public void removeGame(Game game) {
        gameLib.remove(game);
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return gameLib.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Game Library", libToJson());
        return json;
    }

    // EFFECTS: returns games in this GameLibrary as a JSON array
    public JSONArray libToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : gameLib) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }
}
