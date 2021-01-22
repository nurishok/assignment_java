
public class CmdSetupTeam extends RecordedCommand
{
    Team newT;
    @Override
    public void execute(String[] cmdParts) {
        try {
            newT = Company.getInstance().createTeam(cmdParts[1], cmdParts[2]);
            if (newT == null) {
            }
            else {
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Insufficient command arguments.");
        }
    }

    @Override
    public void undoMe()
    {
        Company.getInstance().delTeam(newT);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company.getInstance().addTeam(newT);
        addUndoCommand(this);
    }
}