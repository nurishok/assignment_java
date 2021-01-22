import java.util.ArrayList;

public class CmdHire extends RecordedCommand {
    private Employee e;
    ArrayList<Employee> hiredEmployees = new ArrayList<>();
    ArrayList<Employee> removedEmployees = new ArrayList<>();
    Company company = Company.getInstance();

    @Override
    public void execute(String[] cmdParts) {
        try {
            Employee e = new Employee(cmdParts[1]);
            for (Employee emp: company.getList()) {
                if (emp.compareTo(e)==0) {
                    throw new ExEmployeeExists();
                }
            }
            company.addEmployee(e);
            hiredEmployees.add(e);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Insufficient command arguments.");
        } catch (ExEmployeeExists e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void undoMe() {
        int lastEmployee = (hiredEmployees.size()-1);
        Employee e1 = hiredEmployees.get(lastEmployee);
        company.removeEmployee(e1);
        hiredEmployees.remove(e1);
        removedEmployees.add(e1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        int lastEmployee = removedEmployees.size()-1;
        Employee e2 = removedEmployees.get(lastEmployee);
        company.addEmployee(e2);
        hiredEmployees.add(e2);
        removedEmployees.remove(e2);
        addUndoCommand(this);
    }
}