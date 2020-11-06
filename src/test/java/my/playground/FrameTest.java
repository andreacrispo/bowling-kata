package my.playground;

import org.junit.Before;
import org.junit.Test;

import javax.management.RuntimeErrorException;

import static org.junit.Assert.*;

public class FrameTest {

    private Frame frame;

    @Before
    public void frame(){
        this.frame = new Frame();
    }

    @Test
    public void isFirstRollPerformed() {
        this.frame.performFirstRoll(0);

        boolean isFirstRollPerformed = this.frame.isFirstRollPerformed();

        assertEquals(true, isFirstRollPerformed);
    }

    @Test
    public void isSecondRollPerformed() {
        this.frame.performFirstRoll(0);
        this.frame.performSecondRoll(0);

        boolean isSecondRollPerformed = this.frame.isSecondRollPerformed();

        assertEquals(true, isSecondRollPerformed);
    }

    @Test(expected = RuntimeException.class)
    public void isFirstRollNotPerformedWhenSecondRollIsStarting(){
        this.frame.performSecondRoll(0);

    }

    @Test(expected = RuntimeException.class)
    public void performSecondRollBeforeFirstRollThrowsException(){
        this.frame.performSecondRoll(0);
        this.frame.performFirstRoll(0);

    }

    @Test
    public void totalScoreIsValid(){
        int totalScore;

        this.frame.performFirstRoll(10);
        this.frame.performSecondRoll(10);

        totalScore = this.frame.calculateTotalScore();

        assertEquals(20, totalScore);
    }
}