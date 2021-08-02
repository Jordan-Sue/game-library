package persistence;

import model.Game;
import model.GameLibrary;
import model.Status;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Class and implementation based on JsonSerializationDemo

// Represents a reader that reads GameLibrary from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads GameLibrary from file and returns it;
    //          throws IOException if an error occurs when reading data from the file
    public GameLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GameLibrary from JSON object and returns it
    private GameLibrary parseGameLibrary(JSONObject jsonObject) {
        GameLibrary gameLib = new GameLibrary();
        addGameLibrary(gameLib, jsonObject);
        return gameLib;
    }

    // MODIFIES: gameLib
    // EFFECTS: parses games from JSON object and adds them to GameLibrary
    private void addGameLibrary(GameLibrary gameLib, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Game Library");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(gameLib, nextGame);
        }
    }

    // MODIFIES: gameLib
    // EFFECTS: parses game from JSON object and adds it to GameLibrary
    private void addGame(GameLibrary gameLib, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String system = jsonObject.getString("system");
        Status status = Status.valueOf(jsonObject.getString("status"));
        double playTime = jsonObject.getDouble("playTime");
        Game game = new Game(name, system, status, playTime);
        gameLib.addGame(game);
    }
}
