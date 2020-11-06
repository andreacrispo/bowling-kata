package my.playground;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void Game() {
        this.game = new Game();
    }

    @Test
    public void given0KnockedPinsExpectedValueScore0() {
        int knockedPins = 0;

        this.game.roll(knockedPins);
        int valueScore = this.game.score();

        assertEquals(0, valueScore);
    }

    @Test
    public void given1KnockedPinsExpectedValueScore1() {
        int knockedPins = 1;

        this.game.roll(knockedPins);
        int valueScore = this.game.score();

        assertEquals(1, valueScore);
    }

    @Test
    public void given10KnockedPinsExpectedValueScore10() {
        int knockedPins = 10;

        this.game.roll(knockedPins);
        int valueScore = this.game.score();

        assertEquals(10, valueScore);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfKnockedPinsNumberIsAbove10() {
        int knockedPins = 11;
        this.game.roll(knockedPins);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfKnockedPinsNumberIsBelow0() {
        int knockedPins = -1;
        this.game.roll(knockedPins);
    }
}



