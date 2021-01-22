public class ExEmployeeJoinedAlready extends Exception{
    public ExEmployeeJoinedAlready() {
        super("Employee has joined a team already.");
    }
}