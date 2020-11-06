package my.playground;

public class Game {

    private int totalScore;

    private Frame frame;

    public Frame getFrame() {
        return frame;
    }

    public void roll(int knockedPins) {
        if(knockedPins > 10 || knockedPins < 0)
            throw new RuntimeException("Roll invalid knocked pins number");

        if(frame == null)
            frame = new Frame();

       if(!frame.isFirstRollPerformed())
            frame.performFirstRoll(knockedPins);
        else if(!frame.isSecondRollPerformed())
            frame.performSecondRoll(knockedPins);

        this.addScore(knockedPins);
    }

    public int score() {
        return totalScore;
    }

    public void addScore(int value){
        totalScore += value;
    }
}
