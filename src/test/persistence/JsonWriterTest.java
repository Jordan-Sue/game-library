package persistence;

import model.Game;
import model.GameLibrary;
import model.Status;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Class is based off of JsonSerializationDemo

class JsonWriterTest extends JsonTest{

    @Test
    void testWriteToInvalidFile() {
        try {
            GameLibrary gameLib = new GameLibrary();
            JsonWriter writer = new JsonWriter("./data/i\1egalName.json");
            writer.open();
            fail("Should have thrown exception");
        } catch (IOException e) {
            // Should be thrown
        }
    }

    @Test
    void testWriteToFile() {
        try {
            GameLibrary gameLib = new GameLibrary();
            gameLib.addGame(new Game("Super Metroid", "SNES", Status.Completed, 1.9));
            gameLib.addGame(new Game("Breath of the Wild", "Switch", Status.Beaten, 220));
            JsonWriter writer = new JsonWriter("./data/testWriter.json");
            writer.open();
            writer.write(gameLib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter.json");
            gameLib = reader.read();
            assertEquals(2, gameLib.size());
            checkGame("Super Metroid",
                    "SNES",
                    Status.Completed,
                    1.9,
                    gameLib.returnGame("Super Metroid"));
            checkGame("Breath of the Wild",
                    "Switch",
                    Status.Beaten,
                    220.0,
                    gameLib.returnGame("Breath of the Wild"));
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }
}
