import java.util.List;

public class CmdTeamJoining extends RecordedCommand {
    Company company = Company.getInstance();
    Team t;
    Employee e;

    @Override
    public void execute(String[] cmdParts) {
        try{
            t = null;
            List<Team> teams = company.getTeamLists();
            for(Team t1: teams){
                if(t1.getNameOfTeam().equals(cmdParts[2])){
                    t = t1;
                }
            }
            if(t!=null){
                e = company.findEmployee(cmdParts[1]);
                if(e==null){
                    throw new ExEmployeeNameNotExist();
                }
                if(e.getTeamName()!=null){
                    throw new ExEmployeeJoinedAlready();
                }
                t.addTeamMember(e);
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }
            else{
                throw new ExTeamIsNotExisting();
            }

        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Insufficient command arguments.");
        }catch (ExTeamIsNotExisting e1){
            System.out.println(e1.getMessage());
        }catch (ExEmployeeNameNotExist e1){
            System.out.println(e1.getMessage());
        }catch (ExEmployeeJoinedAlready e1){
            System.out.println(e1.getMessage());
        }
    }


    @Override
    public void undoMe() {
        t.delTeamMember(e);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        t.addTeamMember(e);
        addUndoCommand(this);
    }
}