package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLibraryTest {
    private GameLibrary testGameLibrary;

    @BeforeEach
    public void runBefore() {
        testGameLibrary = new GameLibrary();
    }

    @Test
    public void testAddManyGames() {
        for (int x = 0; x < 10; x++) {
            Game g = new Game(Integer.toString(x), "x", Status.Not_Played, x);
            testGameLibrary.addGame(g);
        }
        assertEquals(10, testGameLibrary.size());
        for (int x = 0; x < 10; x++) {
            assertTrue(testGameLibrary.findGame(Integer.toString(x)));
        }
    }

    @Test
    public void testFindGame() {
        Game g = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g);
        assertTrue(testGameLibrary.findGame("Super Mario World"));
        assertFalse(testGameLibrary.findGame("Super Mario Bros."));
    }

    @Test
    public void testReturnGame() {
        Game g = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g);
        assertEquals(g, testGameLibrary.returnGame("Super Mario World"));
    }

    @Test
    public void testReturnGameNull() {
        Game g = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g);
        assertNull(testGameLibrary.returnGame("Mario Party"));
    }

    @Test
    public void testRemoveGame() {
        Game g = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g);
        testGameLibrary.removeGame("Super Mario World");
        assertEquals(0, testGameLibrary.size());
    }
}