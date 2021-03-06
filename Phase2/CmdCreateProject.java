public class CmdCreateProject extends RecordedCommand {
    Company company = Company.getInstance();
    Project p1;
    @Override
    public void execute(String[] cmdParts) {
        try{
            p1 = new Project(cmdParts[1], cmdParts[2]);
            for(char c: cmdParts[2].toCharArray()){
                if(c=='.' || c == ','){
                    throw new ExNotCorrectNumberFormat();
                }
            }
            int isItCreated = company.checkingForExisting(p1);
            if(isItCreated != 1){
                throw new ExProjectCodeAlreadyExists();
            }
            if(cmdParts[2].charAt(0)==45){
                String a = cmdParts[2];
                throw new  ExZeroNegativeRange(a);
            }
            if(cmdParts[2].charAt(0)>=48 && cmdParts[2].charAt(0)<=57){
                if(cmdParts[2].charAt(0)==48){
                    String a = cmdParts[2];
                    throw new  ExZeroNegativeRange(a);
                }
                company.addingProject(p1);
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
        catch (ExZeroNegativeRange ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void undoMe() {
        company.deletingProject(p1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        company.addingProject(p1);
        addUndoCommand(this);
    }
}