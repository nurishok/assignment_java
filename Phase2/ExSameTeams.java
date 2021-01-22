public class ExSameTeams extends Exception{
    public ExSameTeams() {
        super("The old and new teams should not be the same.");
    }
}