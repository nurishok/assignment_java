import java.util.ArrayList;

public class CmdCreateProject extends RecordedCommand {
    Company company = Company.getInstance();
    Project p;
    @Override
    public void execute(String[] cmdParts) {
        try{
            p = new Project(cmdParts[1], cmdParts[2]);
            for(char c: cmdParts[2].toCharArray()){
                if(c=='.' || c == ','){
                    throw new ExNotCorrectNumberFormat();
                }
            }
            int isCreated = company.checkExists(p);
            if(isCreated!=1){
                throw new ExProjectCodeAlreadyExists();
            }
            if(cmdParts[2].charAt(0)==45){
                throw new  ExZeroNegativeRange();
            }
            if(cmdParts[2].charAt(0)>=48 && cmdParts[2].charAt(0)<=57){
                if(cmdParts[2].charAt(0)==48){
                    throw new  ExZeroNegativeRange();
                }
                company.addProject(p);
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }
            else{
                throw new ExNotCorrectNumberFormat();
            }

        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Insufficient command arguments.");
        }
        catch (ExProjectCodeAlreadyExists ex){
            System.out.println(ex.getMessage());
        }
        catch (ExNotCorrectNumberFormat ex){
            System.out.println(ex.getMessage());
        }
        catch ( ExZeroNegativeRange ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void undoMe() {
        company.delProject(p);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        company.addProject(p);
        addUndoCommand(this);
    }
}