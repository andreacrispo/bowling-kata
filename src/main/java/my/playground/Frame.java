package my.playground;

public class Frame {

    public static final int MAX_KNOCKABLE_PINS_PER_ROLL = 10;
    private int bonus;
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

    public int totalPinsKnockedDown() {
        return this.firstRollKnockedPins + this.secondRollKnockedPins;
    }

    public int calculateTotalScore(){
        return this.totalPinsKnockedDown() + this.bonus;
    }

    public void roll(int knockedPins) {

        if(!this.firstRollPerformed)
            performFirstRoll(knockedPins);
        else if(!this.secondRollPerformed)
            performSecondRoll(knockedPins);
    }

    public boolean isSpareAchieved() {
        return this.firstRollPerformed
                && this.secondRollPerformed
                && this.totalPinsKnockedDown() == MAX_KNOCKABLE_PINS_PER_ROLL;
    }

    public boolean isStrikeAchieved() {
       return this.firstRollPerformed
               && !this.secondRollPerformed
               && this.totalPinsKnockedDown() == MAX_KNOCKABLE_PINS_PER_ROLL;
    }

    public boolean isDone() {
        if(isStrikeAchieved())
            return true;

        return firstRollPerformed && secondRollPerformed;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getFirstRollKnockedPins() {
        return this.firstRollKnockedPins;
    }

    public int getSecondRollKnockedPins() {
        return this.secondRollKnockedPins;
    }
}
