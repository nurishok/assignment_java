public class CmdTeamChanging extends RecordedCommand {
    Company company = Company.getInstance();
    Employee emp;
    Team previousTeam;
    Team nextTeam;

    @Override
    public void execute(String[] cmdParts) {
        try{
            emp = company.findEmployee(cmdParts[1]);
            if(emp ==null){
                throw new ExEmployeeNameNotExist();
            }
            nextTeam = null;
            for(Team t1 : company.getTeamLists()){
                if(t1.getNameOfTeam().equals(cmdParts[2])){
                    nextTeam = t1;
                }
                if(t1.getNameOfTeam().equals(emp.getTeamName())){
                    previousTeam = t1;
                }
            }
            if(nextTeam==null){
                throw new ExTeamIsNotExisting();
            }
            if(nextTeam.equals(previousTeam)){
                throw new ExSameTeams();
            }
            emp.changeTeam(nextTeam);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Insufficient command arguments.");
        }catch (ExSameTeams ex){
            System.out.println(ex.getMessage());
        }catch (ExEmployeeNameNotExist e1){
            System.out.println(e1.getMessage());
        }catch (ExTeamIsNotExisting e1){
            System.out.println(e1.getMessage());
        }
    }
    @Override
    public void undoMe() {
        emp.changeTeam(previousTeam);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        emp.changeTeam(nextTeam);
        addUndoCommand(this);
    }
}