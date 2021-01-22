public class ExZeroNegativeRange extends Exception{
    public ExZeroNegativeRange () {
        super("Estimated manpower should not be zero or negative.\n" +
                "Consider changing 0 to a positive non-zero amount.");
    }
}
