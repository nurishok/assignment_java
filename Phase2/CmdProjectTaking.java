public class CmdProjectTaking extends RecordedCommand {
    Company company = Company.getInstance();
    String team;
    String codeProject;
    String dateOfProject;
    Project p1;

    @Override
    public void execute(String[] cmdParts) {
        try{
            team = cmdParts[1];
            codeProject = cmdParts[2];
            dateOfProject = cmdParts[3];

            int currentStatus = company.checkAddingProject(cmdParts[1], cmdParts[2], cmdParts[3]);
            p1 = company.findingProject(cmdParts[2]);
            if(SystemDate.getInstance().toString().equals(cmdParts[3])){
                throw new ExStartingDayTooEarly();
            }

            if(currentStatus == -1){
                throw new ExTeamIsNotExisting();
            }else if(currentStatus == -2){
                throw new ExProjectIsNotExisting();
            }
            else if(currentStatus == 0){
                throw new ExProjectHasBeenTaken();
            }
            else if(currentStatus == 1){
                company.takingProject(cmdParts[1], cmdParts[2], cmdParts[3]);
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }
            else if(currentStatus == -3){
                throw new ExTeamIsNotAvailable();
            }
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Insufficient command arguments.");
        }catch (ExStartingDayTooEarly e1){
            System.out.println(e1.getMessage());
        } catch (ExProjectIsNotExisting e1) {
            System.out.println(e1.getMessage());
        } catch (ExProjectHasBeenTaken e1) {
            System.out.println(e1.getMessage());
        } catch (ExTeamIsNotExisting e1) {
            System.out.println(e1.getMessage());
        } catch (ExTeamIsNotAvailable e1) {
            System.out.println(e1.getMessage() + p1.getBetweenString());
        }
    }
    @Override
    public void undoMe() {
        company.untakingProject(team, codeProject);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        company.takingProject(team, codeProject, dateOfProject);
        addUndoCommand(this);
    }
}
