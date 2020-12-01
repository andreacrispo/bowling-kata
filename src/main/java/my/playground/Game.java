package my.playground;

import java.util.ArrayList;
import java.util.List;

public class Game {

    //Max number of frames in a standard game
    public static final int STANDARD_FRAMES_MAX_NUMBER = 10;
    private final List<Frame> frames;
    private int currentFrameIndex;

    private boolean gameOver = false;

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
        if (this.gameOver)
            throw new RuntimeException("Game over");

        if (knockedPins > 10 || knockedPins < 0)
            throw new RuntimeException("Roll invalid knocked pins number");

        Frame currentFrame = this.getCurrentFrame();

        currentFrame.roll(knockedPins);

        if (currentFrame.isDone()) {
            if (this.isGameOver(currentFrame))
                this.gameOver = true;
            else
                this.goToNextFrame();
        }
    }

    private boolean isGameOver(Frame currentFrame) {
        return this.currentFrameIndex + 1 >= STANDARD_FRAMES_MAX_NUMBER
                && !currentFrame.isStrikeAchieved()
                && !currentFrame.isSpareAchieved();
    }

    private void goToNextFrame() {

        this.currentFrameIndex++;
        this.frames.add(new Frame());
    }

    public int score() {
        int totalScore = 0;
        int lastOrMaxFrameIndex = Math.min(this.frames.size(), STANDARD_FRAMES_MAX_NUMBER);

        for (int frameIndex = 0; frameIndex < lastOrMaxFrameIndex; frameIndex++) {
            Frame current = this.frames.get(frameIndex);
            int bonus = calculateBonus(frameIndex);
            current.setBonus(bonus);
            totalScore += current.calculateTotalScore();

        }

        return totalScore;
    }

    private int calculateBonus(int frameIndex) {
        Frame frame = this.frames.get(frameIndex);
        if (frame.isSpareAchieved())
            return handleSpareScore(frameIndex);

        if (frame.isStrikeAchieved())
            return handleStrikeScore(frameIndex);

        return 0;
    }

    private int handleSpareScore(int frameIndex) {
        Frame next = this.frames.get(frameIndex + 1);
        return next.getFirstRollKnockedPins();
    }

    private int handleStrikeScore(int frameIndex) {
        Frame next = this.frames.get(frameIndex + 1);
        if (next.isStrikeAchieved()) {
            Frame nextOfNext = this.frames.get(frameIndex + 2);
            return next.getFirstRollKnockedPins() + nextOfNext.getFirstRollKnockedPins();
        }

        return next.totalPinsKnockedDown();
    }
}
