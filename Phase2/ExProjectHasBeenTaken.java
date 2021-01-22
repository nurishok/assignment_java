public class ExProjectHasBeenTaken extends Exception{
    public ExProjectHasBeenTaken() {
        super("Project has already been assigned to a team.");
    }
}