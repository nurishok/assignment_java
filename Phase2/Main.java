import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input the file pathname: ");
        String filepathname = in.nextLine();


        try {
            Scanner inFile = new Scanner(new File(filepathname));
            String cmdLine1 = inFile.nextLine();
            String[] cmdLine1Parts = cmdLine1.split("\\|");
            System.out.println("\n> "+cmdLine1);
            SystemDate.createTheInstance(cmdLine1Parts[1]);
            while (inFile.hasNext()) {
                String cmdLine = inFile.nextLine().trim();
                if (cmdLine.equals(""))
                    continue;
                System.out.println("\n> "+cmdLine);
                String[] cmdParts = cmdLine.split("\\|");

                if (cmdParts[0].equals("hire"))
                    (new CmdHire()).execute(cmdParts);
                if (cmdParts[0].equals("listEmployees"))
                    (new CmdListEmployees()).execute(cmdParts);
                if (cmdParts[0].equals("undo"))
                    RecordedCommand.undoOneCommand();
                if (cmdParts[0].equals("redo"))
                    RecordedCommand.redoOneCommand();
                if (cmdParts[0].equals("startNewDay"))
                    (new CmdStartNewDay()).execute(cmdParts);
                if (cmdParts[0].equals("setupTeam"))
                    (new CmdSetupTeam()).execute(cmdParts);
                if (cmdParts[0].equals("listTeams"))
                    (new CmdListTeams()).execute(cmdParts);
                if(cmdParts[0].equals("createProject"))
                    (new CmdCreateProject()).execute(cmdParts);
                if(cmdParts[0].equals("listProjects"))
                    (new CmdListProjects()).execute(cmdParts);
                if(cmdParts[0].equals("joinTeam"))
                    (new CmdTeamJoining()).execute(cmdParts);
                if(cmdParts[0].equals("changeTeam"))
                    (new CmdTeamChanging()).execute(cmdParts);
                if(cmdParts[0].equals("takeProject"))
                    (new CmdProjectTaking()).execute(cmdParts);

            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such a file");
        }
        in.close();
    }

}