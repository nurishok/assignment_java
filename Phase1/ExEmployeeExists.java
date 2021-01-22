public class ExEmployeeExists extends Exception {
    public ExEmployeeExists() {
        super("Employee name already exists.");
    }
}