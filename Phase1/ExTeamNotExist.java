public class ExTeamNotExist extends Exception {
    public ExTeamNotExist() {
        super("Team name already exists.");
    }
}