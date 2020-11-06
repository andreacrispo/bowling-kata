package my.playground;

public class Frame {

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

    public boolean isFirstRollPerformed() { return this.firstRollPerformed ; }

    public boolean isSecondRollPerformed() {
        return this.secondRollPerformed ;
    }
}
