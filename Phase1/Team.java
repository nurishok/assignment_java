import java.util.*;
public class Team implements Comparable<Team>{
    private String teamName;
    private Employee head;
    private Day dateSetup;
    private ArrayList<Employee> membersOfTeam = new ArrayList<>();

    public Team(String n, Employee hd) {
        teamName = n;
        head = hd;
        dateSetup = SystemDate.getInstance().clone();
    }

    public String getNameOfTeam() {
        return teamName;
    }

    public String getHeadName() {
        return head.getName();
    }

    public static void list(ArrayList<Team> list) {
        System.out.printf("%-15s%-10s%-14s%-20s\n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team t : list)
            System.out.printf("%-15s%-10s%-13s%-20s\n",t.teamName, t.head.getName(), t.dateSetup, t.getMembers()); //display t.teamName, etc..
    }

    private String getMembers() {
        if(membersOfTeam.isEmpty()){
            return "(no member)";
        }
        else {
            String s = "";
            for(Employee e: membersOfTeam){
                s += " " + e.getName();
            }
            return s;
        }
    }

    @Override
    public int compareTo(Team another) {
        return this.teamName.compareTo(another.teamName);
    }
}