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
    public void testRemoveGameName() {
        Game g = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g);
        testGameLibrary.removeGame("Super Mario World");
        assertEquals(0, testGameLibrary.size());
    }
    @Test
    public void testRemoveGameGame() {
        Game g = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g);
        testGameLibrary.removeGame(g);
        assertEquals(0, testGameLibrary.size());
    }

    @Test
    public void testGetGamesInfo() {
        Game g1 = new Game("Super Mario Bros.", "NES", Status.Not_Played, 0);
        Game g2 = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g1);
        testGameLibrary.addGame(g2);
        Object[][] testObject = testGameLibrary.getGamesInfo();
        assertEquals(testObject[0][0], "Super Mario Bros.");
        assertEquals(testObject[0][1], "NES");
        assertEquals(testObject[0][2], "Not_Played");
        assertEquals(testObject[0][3], "0.0");
        assertEquals(testObject[1][0], "Super Mario World");
        assertEquals(testObject[1][1], "SNES");
        assertEquals(testObject[1][2], "Completed");
        assertEquals(testObject[1][3], "10.0");
    }

    @Test
    public void testIsComplete() {
        Game g1 = new Game("Super Mario Bros.", "NES", Status.Not_Played, 0);
        Game g2 = new Game("Super Mario World", "SNES", Status.Completed, 10);
        testGameLibrary.addGame(g1);
        assertFalse(testGameLibrary.isComplete());

        testGameLibrary.addGame(g2);
        assertTrue(testGameLibrary.isComplete());
    }

    @Test
    public void testSort() {
        Game g1 = new Game("Super Mario World", "SNES", Status.Completed, 10);
        Game g2 = new Game("Super Mario Bros.", "NES", Status.Not_Played, 0);
        testGameLibrary.addGame(g1);
        testGameLibrary.addGame(g2);
        testGameLibrary.sort();
        assertEquals(testGameLibrary.getGame(0).getName(), "Super Mario Bros.");
        assertEquals(testGameLibrary.getGame(1).getName(), "Super Mario World");
    }
}