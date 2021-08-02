package persistence;

import model.GameLibrary;
import org.json.JSONObject;
import java.io.*;

// Class and implementation based on JsonSerializationDemo

// Represents a writer that writes a JSON representation of a GameLibrary to a file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to a destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException
    //          if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of GameLibrary to file
    public void write(GameLibrary gameLib) {
        JSONObject json = gameLib.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
