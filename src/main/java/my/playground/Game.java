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

        if(currentFrameIndex >= 10) {
            throw new RuntimeException("Exceed roll");
        }

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
        int totalScore = 0;

        for(int frameIndex=0; frameIndex< this.frames.size(); frameIndex++){
            Frame current = this.frames.get(frameIndex);

            if(current.isSpareAchieved()){
                Frame next = this.frames.get(frameIndex+1);
                current.setBonus(next.getFirstRollKnockedPins());
            }
            else if(current.isStrikeAchieved()) {
                Frame next = this.frames.get(frameIndex+1);
                int strikeBonus = next.getFirstRollKnockedPins();
                if(next.isStrikeAchieved()) {
                    Frame nextOfNext = this.frames.get(frameIndex+2);
                    strikeBonus += nextOfNext.getFirstRollKnockedPins();
                }
                else{

                    strikeBonus += next.getSecondRollKnockedPins();
                }
                current.setBonus(strikeBonus);
            }

            totalScore += current.calculateTotalScore();
        }

        return totalScore;
    }
}
