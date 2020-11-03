package my.playground;

public class Game {

    private int totalScore;

    public void roll(int knockedPins) {
        this.addScore(knockedPins);
    }

    public int score() {
        return totalScore;
    }

    public void addScore(int value){
        totalScore += value;
    }
}
