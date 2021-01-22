public class ExProjectIsNotExisting extends Exception{
    public ExProjectIsNotExisting() {
        super("Project does not exist.");
    }
}