public class CmdStartNewDay extends RecordedCommand {
    private String firstDate;
    private String afterDate;
    @Override
    public void execute(String[] cmdParts)
    {
        firstDate = SystemDate.getInstance().toString();
        afterDate =cmdParts[1];
        SystemDate.getInstance().set(cmdParts[1]);
        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe()
    {
        SystemDate.getInstance().set(firstDate);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        SystemDate.getInstance().set(afterDate);
        addUndoCommand(this);
    }
}