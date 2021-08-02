package persistence;

import model.GameLibrary;
import model.Status;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Class is based off of JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nope.json");
        try {
            GameLibrary gameLib = reader.read();
            fail("Should have thrown exception");
        } catch (IOException e) {
            // Should be thrown
        }
    }

    @Test
    void testReader(){
        JsonReader reader = new JsonReader("./data/testReader.json");
        try {
            GameLibrary gameLib = reader.read();
            assertEquals(2, gameLib.size());
            checkGame("Super Mario Bros.",
                    "NES",
                    Status.Not_Played,
                    0.0,
                    gameLib.returnGame("Super Mario Bros."));
            checkGame("Celeste",
                    "Steam",
                    Status.Beaten,
                    276.0,
                    gameLib.returnGame("Celeste"));
        } catch (IOException e){
            fail("Should not have thrown exception");
        }
    }
}
