package my.playground;

import java.util.ArrayList;
import java.util.List;

public class Game {

    //Max number of frames in a standard game
    public static final int STANDARD_FRAMES_MAX_NUMBER = 10;
    //Max number of frames in strikes game
    public static final int STRIKE_MAX_FRAMES_NUMBER = 12;

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
        if (gameOver)
            throw new RuntimeException("Game over");

        if (knockedPins > 10 || knockedPins < 0)
            throw new RuntimeException("Roll invalid knocked pins number");

        Frame currentFrame = this.getCurrentFrame();

        currentFrame.roll(knockedPins);

        if (currentFrame.isDone()) {
            if (this.currentFrameIndex + 1 < STANDARD_FRAMES_MAX_NUMBER
                    || currentFrame.isStrikeAchieved()
                    || currentFrame.isSpareAchieved())
                goToNextFrame();
            else
                gameOver = true;
        }
    }

    private void goToNextFrame() {

        this.currentFrameIndex++;
        this.frames.add(new Frame());
    }

    public int score() {
        int totalScore = 0;

        for (int frameIndex = 0; frameIndex < this.frames.size(); frameIndex++) {
            Frame current = this.frames.get(frameIndex);
            int bonus = calculateBonus(frameIndex, current);
            current.setBonus(bonus);
            totalScore += current.calculateTotalScore();

            if (frameIndex == 9) {
                break;
            }
        }

        return totalScore;
    }

    private int calculateBonus(int frameIndex, Frame current) {
        if (current.isSpareAchieved()) {
            return handleSpareScore(frameIndex);
        } else if (current.isStrikeAchieved()) {
            return handleStrikeScore(frameIndex);
        }

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
        } else {
            return next.totalPinsKnockedDown();
        }
    }

}
