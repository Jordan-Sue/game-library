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
        for (int x = 0; x < 10; x++) {
            assertTrue(testGameList.findGame(Integer.toString(x)));
        }
    }

    @Test
    public void testFindGame() {
        Game g = new Game("Super Mario World", "SNES", "Completed", 10);
        testGameList.addGame(g);
        assertTrue(testGameList.findGame("Super Mario World"));
        assertFalse(testGameList.findGame("Super Mario Bros."));
    }

    @Test
    public void testReturnGame() {
        Game g = new Game("Super Mario World", "SNES", "Completed", 10);
        testGameList.addGame(g);
        assertEquals(g, testGameList.returnGame("Super Mario World"));
    }

    @Test
    public void testReturnGameNull() {
        Game g = new Game("Super Mario World", "SNES", "Completed", 10);
        testGameList.addGame(g);
        assertNull(testGameList.returnGame("Mario Party"));
    }

    @Test
    public void testRemoveGame() {
        Game g = new Game("Super Mario World", "SNES", "Completed", 10);
        testGameList.addGame(g);
        testGameList.removeGame("Super Mario World");
        assertEquals(0, testGameList.size());
    }
}