package my.playground;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Frame> frames;
    private int currentFrameIndex;

    public Game() {
        this.currentFrameIndex = 0;
        this.frames = new ArrayList<>();
        this.frames.add(new Frame());
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame getCurrentFrame() {
        return frames.get(currentFrameIndex);
    }

    public void roll(int knockedPins) {

        if(knockedPins > 10 || knockedPins < 0)
            throw new RuntimeException("Roll invalid knocked pins number");

        Frame currentFrame = this.getCurrentFrame();

        currentFrame.roll(knockedPins);



        if(currentFrame.isDone()){
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
                .mapToInt(Frame::calculateScoreWithoutBonus)
                .sum();
    }
}
