package model;

import java.util.ArrayList;

// A list of game objects
public class GameList {
    private ArrayList<Game> games;

    // EFFECTS: creates an empty list of games
    public GameList() {
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a game to games
    public void addGame(Game g) {
        games.add(g);
    }

    // EFFECTS: returns true if the given game's name is in the list, false otherwise
    public boolean findGame(String name) {
        for (Game x : games) {
            if (x.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the game with the given name
    public Game returnGame(String name) {
        for (Game x : games) {
            if (x.getName().equalsIgnoreCase(name)) {
                return x;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: removes the game at the given index
    public void removeGame(String name) {
        games.remove(returnGame(name));
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return games.size();
    }
}
