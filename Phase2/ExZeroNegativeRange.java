public class ExZeroNegativeRange extends Exception{
    public ExZeroNegativeRange (String a) {
        super("Estimated manpower should not be zero or negative.\n" +
                "Consider changing " + a + " to a positive non-zero amount.");
    }
}
