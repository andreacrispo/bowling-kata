package my.playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Game {

    private List<Frame> frames;
    private int currentFrameIndex;

    public Game() {
        this.currentFrameIndex = 0;
        this.frames = new ArrayList<>();
        this.frames.add(new Frame());
    }

    public Frame getCurrentFrame() {
        return frames.get(currentFrameIndex);
    }

    public void roll(int knockedPins) {
        if(knockedPins > 10 || knockedPins < 0)
            throw new RuntimeException("Roll invalid knocked pins number");

       Frame currentFrame = this.getCurrentFrame();

       if(!currentFrame.isFirstRollPerformed())
           currentFrame.performFirstRoll(knockedPins);
       else if(!currentFrame.isSecondRollPerformed())
           currentFrame.performSecondRoll(knockedPins);

       if(currentFrame.isSecondRollPerformed()){
           nextFrame();
       }
    }

    private void nextFrame() {
        this.currentFrameIndex++;
        this.frames.add(new Frame());
    }

    public int score() {
        return this.frames
                .stream()
                .mapToInt(Frame::calculateTotalScore)
                .sum();
    }
}
