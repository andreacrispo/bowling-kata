package my.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    private Frame frame;

    @BeforeEach
    public void frame(){
        this.frame = new Frame();
    }

    @Test
    public void isFirstRollPerformed() {
        this.frame.performFirstRoll(0);

        boolean isFirstRollPerformed = this.frame.isFirstRollPerformed();

        assertTrue(isFirstRollPerformed);
    }

    @Test
    public void isSecondRollPerformed() {
        this.frame.performFirstRoll(0);
        this.frame.performSecondRoll(0);

        boolean isSecondRollPerformed = this.frame.isSecondRollPerformed();

        assertTrue(isSecondRollPerformed);
    }

    @Test()
    public void isFirstRollNotPerformedWhenSecondRollIsStarting(){
        assertThrows(
                RuntimeException.class,
                () -> this.frame.performSecondRoll(0)
        );
    }

    @Test()
    public void performSecondRollBeforeFirstRollThrowsException(){
        assertThrows(
                RuntimeException.class,
                () -> {
                    this.frame.performSecondRoll(0);
                    this.frame.performFirstRoll(0);
                }
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { 0,1,2,3,4,5,6,7,8,9,10 })
    public void totalScoreIsValid(int pins){
        int totalScore;

        this.frame.performFirstRoll(pins);
        this.frame.performSecondRoll(pins);

        totalScore = this.frame.getFirstRollKnockedPins() + this.frame.getSecondRollKnockedPins();

        assertEquals(pins + pins, totalScore);
    }

    @Test public void
    whenOnlyOneRollsKnock10PinsSpareShouldNotBeAchieved(){
        this.frame.roll(10);
        assertFalse(this.frame.isSpareAchieved());
    }

    @Test public void
    whenTwoRollsKnock10PinsSpareIsAchieved(){
        this.frame.roll(7);
        this.frame.roll(3);
        assertTrue(this.frame.isSpareAchieved());
    }


    @Test public void
    whenOneRollKnock10PinsStrikeShouldBeAchieved() {
        this.frame.roll(10);
        assertTrue(this.frame.isStrikeAchieved());
        assertTrue(this.frame.isFirstRollPerformed());
        assertFalse(this.frame.isSecondRollPerformed());
    }
}