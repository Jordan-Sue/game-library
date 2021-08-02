package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game testGame;

    @BeforeEach
    public void runBefore() {
        testGame = new Game("Super Mario Bros.", "NES", Status.Not_Played, 0);
    }

    @Test
    public void testChangeStatus() {
        testGame.changeStatus(Status.Beaten);
        assertEquals(Status.Beaten, testGame.getStatus());
    }

    @Test
    public void testChangePlayTime() {
        testGame.changePlayTime(2.5);
        assertEquals(2.5, testGame.getPlayTime());
    }

    @Test
    public void testGetName() {
        assertEquals("Super Mario Bros.", testGame.getName());
    }

    @Test
    public void testGetSystem() {
        assertEquals("NES", testGame.getSystem());
    }
}
