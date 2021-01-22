
public class CmdSetupTeam extends RecordedCommand
{
    Team tteam;
    @Override
    public void execute(String[] cmdParts) {
        try {
            tteam = Company.getInstance().creatingTeam(cmdParts[1], cmdParts[2]);
            if (tteam == null) {
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
        Company.getInstance().deletingTeam(tteam);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company.getInstance().addingTeam(tteam);
        addUndoCommand(this);
    }
}