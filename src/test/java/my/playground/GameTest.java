package my.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void Game() {
        this.game = new Game();
    }

    @ParameterizedTest
    @ValueSource(ints = { 0,1,2,3,4,5,6,7,8,9,10 })
    public void givenNKnockedPinsExpectedValueScoreN(int knockedPins) {
        this.game.roll(knockedPins);
        int valueScore = this.game.score();

        assertEquals(knockedPins, valueScore);
    }

    @Test
    public void given10KnockedPinsExpectedValueScore10() {
        int knockedPins = 10;

        this.game.roll(knockedPins);
        int valueScore = this.game.score();

        assertEquals(10, valueScore);
    }

    @Test()
    public void shouldThrowExceptionIfKnockedPinsNumberIsAbove10() {
        int knockedPins = 11;
        assertThrows(
                RuntimeException.class,
                () -> this.game.roll(knockedPins)
        );
    }

    @Test()
    public void shouldThrowExceptionIfKnockedPinsNumberIsBelow0() {
        int knockedPins = -1;
        assertThrows(
                RuntimeException.class,
                () -> this.game.roll(knockedPins)
        );
    }

    @Test
    public void isFirstRollPerformed(){
        this.game.roll(0);

        Frame frameGame = this.game.getCurrentFrame();

        assertTrue(frameGame.isFirstRollPerformed());
        assertFalse(frameGame.isSecondRollPerformed());
    }

    @Test
    public void isSecondRollPerformed(){
        this.game.roll(0);

        Frame frameGame = this.game.getCurrentFrame();

        assertTrue(frameGame.isFirstRollPerformed());
        assertFalse(frameGame.isSecondRollPerformed());

        this.game.roll(0);

        assertTrue(frameGame.isFirstRollPerformed());
        assertTrue(frameGame.isSecondRollPerformed());
    }

    @Test
    public void
    when_three_rolls_rolled_should_first_frame_completed_and_second_frame_only_first_roll() {
        this.game.roll(0);
        this.game.roll(0);
        this.game.roll(0);
        assertTrue(this.game.getFrames().get(0).isFirstRollPerformed());
        assertTrue(this.game.getFrames().get(0).isSecondRollPerformed());
        assertTrue(this.game.getFrames().get(1).isFirstRollPerformed());
    }

    @Test public void
    whenStrikeAchievedForAFrameShouldMoveToNextFrame() {
        this.game.roll(10); // First frame
        this.game.roll(0);  // Second frame
        Frame firstFrame = this.game.getFrames().get(0);
        assertTrue(firstFrame.isFirstRollPerformed());
        assertFalse(firstFrame.isSecondRollPerformed());
        assertTrue(firstFrame.isStrikeAchieved());

        Frame secondFrame = this.game.getFrames().get(1);
        assertTrue(secondFrame.isFirstRollPerformed());
    }

    @Test public void
    whenStrikeAchievedTheFrameIsDone() {
        this.game.roll(10);
        Frame frame = this.game.getFrames().get(0);
        assertTrue(frame.isFirstRollPerformed());
        assertFalse(frame.isSecondRollPerformed());
        assertTrue(frame.isStrikeAchieved());
        assertTrue(frame.isDone());
    }

    @Test public void TESTX() {
        this.game.roll(5);
        this.game.roll(5);

        Frame firstFrame = this.game.getFrames().get(0);
        Frame secondFrame = this.game.getCurrentFrame();

        this.game.roll(1);

        assertEquals(11, firstFrame.calculateScoreWithBonus());
    }

}



