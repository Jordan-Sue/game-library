package persistence;

import model.Game;
import model.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Class is based off of JsonSerializationDemo

public class JsonTest {
    protected void checkGame(String name, String system, Status status, Double playTime, Game game) {
        assertEquals(name, game.getName());
        assertEquals(system, game.getSystem());
        assertEquals(status, game.getStatus());
        assertEquals(playTime, game.getPlayTime());
    }
}
