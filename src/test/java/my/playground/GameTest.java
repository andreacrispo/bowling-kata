package my.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
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
    public void isFirstRollPerformed() {
        this.game.roll(0);

        Frame frameGame = this.game.getCurrentFrame();

        assertTrue(frameGame.isFirstRollPerformed());
        assertFalse(frameGame.isSecondRollPerformed());
    }

    @Test
    public void isSecondRollPerformed() {
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

    @Test
    public void
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

    @Test
    public void
    whenStrikeAchievedTheFrameIsDone() {
        this.game.roll(10);
        Frame frame = this.game.getFrames().get(0);
        assertTrue(frame.isFirstRollPerformed());
        assertFalse(frame.isSecondRollPerformed());
        assertTrue(frame.isStrikeAchieved());
        assertTrue(frame.isDone());
    }


    @Test
    public void
    whenSpareIsAchievedTheBonusIsFirstRollOfNextFrame() {
        this.game.roll(1);
        this.game.roll(2);

        this.game.roll(5);
        this.game.roll(5);

        this.game.roll(2);
        this.game.roll(2);
        assertEquals(3 + 10 + 2 + 4, this.game.score());
    }

    @Test
    public void
    whenStrikeIsAchievedTheBonusIsFirstAndSecondRollOfNextFrameKnockedPins() {
        this.game.roll(10);

        this.game.roll(1);
        this.game.roll(2);

        assertEquals(10 + 1 + 2 + 3, this.game.score());

    }

    @Test
    public void
    whenTwoSpare() {
        this.game.roll(1);
        this.game.roll(9);

        this.game.roll(9);
        this.game.roll(1);


        this.game.roll(5);
        this.game.roll(3);

        assertEquals(10 + 9 + 10 + 5 + 8, this.game.score());
    }

    @Test
    public void
    whenTwoStrikes() {
        this.game.roll(10);


        this.game.roll(10);

        this.game.roll(5);
        this.game.roll(3);

        assertEquals((10 + 15) + (10 + 8) + (8), this.game.score());
    }

    @Test
    public void
    whenSpareAndStrike() {
        this.game.roll(9);
        this.game.roll(1);

        this.game.roll(10);


        this.game.roll(5);
        this.game.roll(3);

        assertEquals((10 + 10) + (10 + 8) + (8), this.game.score());
    }

    @Test
    public void
    whenStrikeAndSpare() {
        this.game.roll(10);

        this.game.roll(9);
        this.game.roll(1);

        this.game.roll(5);
        this.game.roll(3);

        assertEquals((10 + 10) + (10 + 5) + (8), this.game.score());
    }


    @Test
    @Disabled
    public void
    ensure_that_score_per_frame_does_not_exceed_30_points() {
        this.game.roll(10);
        this.game.roll(10);
        this.game.roll(10);
    }


    @Test
    public void
    throws_exception_if_try_to_roll_over_10_frame_and_last_is_not_strike() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    this.game.roll(1);
                    this.game.roll(1);

                    // Not allowed
                    this.game.roll(1);
                    this.game.roll(1);
                }
        );
    }


    @Test
    public void
    allow_to_roll_over_10_frame_if_last_is_strike() {

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(10);

        // Allowed
        this.game.roll(1);
        this.game.roll(1);
    }

    @Test
    public void
    if_20_rolls_performed_10_frames_are_played() {

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        this.game.roll(1);
        this.game.roll(1);

        assertEquals(10, this.game.getFrames().size());
    }


}



