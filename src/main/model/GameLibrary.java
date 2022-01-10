package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Comparator;

// JSON related methods based on JsonSerializationDemo

// A list of game objects
public class GameLibrary implements Writable {
    private ArrayList<Game> gameLib;
    // private String owner; !!!

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

    // EFFECTS: returns the game at the given index
    public Game getGame(int index) {
        return gameLib.get(index);
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

    // EFFECTS: returns an object array of object arrays of the 4 game parameters: name, system, status, and play time
    public Object[][] getGamesInfo() {
        Object[][] games = new Object[gameLib.size()][];
        int counter = 0;
        for (Game g : gameLib) {
            Object[] stats = new Object[4];
            stats[0] = g.getName();
            stats[1] = g.getSystem();
            stats[2] = String.valueOf(g.getStatus());
            stats[3] = String.valueOf(g.getPlayTime());
            games[counter] = stats;
            counter += 1;
        }
        return games;
    }

    // EFFECTS: returns true if there is a completed game in the GameLibrary
    public boolean isComplete() {
        for (Game g : gameLib) {
            if (g.getStatus() == Status.Completed) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: sorts the GameLibrary alphabetical by name
    public void sortName() {
        gameLib.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    // EFFECTS: sorts the GameLibrary alphabetical by system, then alphabetically by name
    public void sortSystem() {
        gameLib.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                int systemEqual = o1.getSystem().compareToIgnoreCase(o2.getSystem());
                if (systemEqual == 0) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                } else {
                    return systemEqual;
                }
            }
        });
    }

    // EFFECTS: sorts the GameLibrary by completion status from the least completion (Not Played)
    //          to most completion (Completed), then alphabetically by name
    public void sortStatus() {
        gameLib.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                int statusEqual = o1.getStatus().compareTo(o2.getStatus());
                if (statusEqual == 0) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                } else {
                    return statusEqual;
                }
            }
        });
    }

    // EFFECTS: sorts the GameLibrary from the lowest play time to highest, then alphabetically by name
    public void sortTime() {
        gameLib.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                int playTimeEqual = Double.compare(o1.getPlayTime(), o2.getPlayTime());
                if (playTimeEqual == 0) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                } else {
                    return playTimeEqual;
                }
            }
        });
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
