public class ExStartingDayTooEarly extends Exception{
    public ExStartingDayTooEarly() {
        super("The earliest start day is tomorrow.");
    }
}