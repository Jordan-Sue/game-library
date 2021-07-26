package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest {
    private GameList testGameList;

    @BeforeEach
    public void runBefore() {
        testGameList = new GameList();
    }

    @Test
    public void testAddManyGames() {
        for (int x = 0; x < 10; x++) {
            Game g = new Game(Integer.toString(x), "x", "Not Played", x);
            testGameList.addGame(g);
        }
        assertEquals(10, testGameList.size());
    }

    @Test
    public void testFindGame() {
        Game g = new Game("Super Mario World", "SNES", "Completed", 10);
        testGameList.addGame(g);
        assertTrue(testGameList.findGame("Super Mario World"));
        assertFalse(testGameList.findGame("Super Mario Bros."));
    }

}