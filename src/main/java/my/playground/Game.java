package my.playground;

public class Game {

    private int totalScore;

    public void roll(int knockedPins) {
        if(knockedPins > 10 || knockedPins < 0)
            throw new RuntimeException("Roll invalid knocked pins number");

        this.addScore(knockedPins);
    }

    public int score() {
        return totalScore;
    }

    public void addScore(int value){
        totalScore += value;
    }
}
