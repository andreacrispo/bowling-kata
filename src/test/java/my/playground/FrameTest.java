package my.playground;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {

    private Frame frame;

    @Before
    public void frame(){
        this.frame = new Frame();
    }

    @Test
    public void isFirstRollPerformed() {
        this.frame.performFirstRoll();

        boolean isFirstRollPerformed = this.frame.isFirstRollPerformed();

        assertEquals(true, isFirstRollPerformed);
    }

    @Test
    public void isSecondRollPendingWhenFirstRollIsPending() {
        boolean isSecondRollPerformed = this.frame.isSecondRollPerformed();

        assertEquals(false, isSecondRollPerformed);
    }

    @Test
    public void isSecondRollPendingWhenFirstRollIsRunning() {
        boolean isSecondRollPerformed = this.frame.isSecondRollPerformed();

        assertEquals(false, isSecondRollPerformed);
    }

    @Test
    public void isFirstRollPerformedWhenSecondRollIsRunning() {
        this.frame.performFirstRoll(0);

        boolean isSecondRollPerformed = this.frame.isSecondRollPerformed();

        assertEquals(false, isSecondRollPerformed);
    }

}