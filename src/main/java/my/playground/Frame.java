package my.playground;

public class Frame {

    public static final int MAX_KNOCKABLE_PINS_PER_ROLL = 10;
    private int firstRollKnockedPins;
    private int secondRollKnockedPins;
    private boolean firstRollPerformed;
    private boolean secondRollPerformed;

    public void performFirstRoll(int knockedDownPins) {
        this.firstRollPerformed = true;
        this.firstRollKnockedPins = knockedDownPins;
    }

    public void performSecondRoll(int knockedDownPins) {
        if(!this.firstRollPerformed)
           throw  new RuntimeException("First roll not performed");

        this.secondRollPerformed = true;
        this.secondRollKnockedPins = knockedDownPins;
    }

    public boolean isFirstRollPerformed() {
        return this.firstRollPerformed;
    }

    public boolean isSecondRollPerformed() {
        return this.secondRollPerformed ;
    }

    public int calculateTotalScore() {
        return this.firstRollKnockedPins + this.secondRollKnockedPins;
    }

    public void roll(int knockedPins) {

        if(!this.firstRollPerformed)
            performFirstRoll(knockedPins);
        else if(!this.secondRollPerformed)
            performSecondRoll(knockedPins);
    }

    public boolean isSpareAchieved() {
        if(this.firstRollPerformed && this.secondRollPerformed)
            return this.calculateTotalScore() == MAX_KNOCKABLE_PINS_PER_ROLL;

        return  false;
    }

    public boolean isStrikeAchieved() {
        return this.calculateTotalScore() == MAX_KNOCKABLE_PINS_PER_ROLL;
    }

    public boolean isDone() {
        if(isStrikeAchieved())
            return true;

        return firstRollPerformed && secondRollPerformed;
    }
}
