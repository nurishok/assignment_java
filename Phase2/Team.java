import java.util.*;
public class Team implements Comparable<Team>{
    private String teamName;
    private Employee head;
    private Day dateSetup;
    private ArrayList<Employee> teamMembers = new ArrayList<>();
    private ArrayList<Project> teamProjects = new ArrayList<>();

    public Team(String n, Employee hd) {
        teamName = n;
        head = hd;
        hd.setTeamName(teamName);
        dateSetup = SystemDate.getInstance().clone();
    }

    public String getNameOfTeam() {
        return teamName;
    }

    public String getHeadName() {
        return head.getName();
    }

    public static void list(ArrayList<Team> list) {
        Collections.sort(list);
        System.out.printf("%-15s%-10s%-14s%-20s\n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team t : list)
            System.out.printf("%-15s%-10s%-13s%-20s\n",t.teamName, t.head.getName(), t.dateSetup, t.getMembersOfTeam()); //display t.teamName, etc..
    }

    private String getMembersOfTeam() {
        if(teamMembers.isEmpty()){
            return "(no member)";
        }
        else {
            String s = "";
            for(Employee e: teamMembers){
                s += " " + e.getName();
            }
            return s;
        }
    }

    @Override
    public int compareTo(Team another) {
        return this.teamName.compareTo(another.teamName);
    }

    public void addTeamMember(Employee employee){
        if(!teamMembers.contains(employee)){
            teamMembers.add(employee);
            employee.setTeamName(this.getNameOfTeam());
        }
        Collections.sort(teamMembers);
    }
    public void delTeamMember(Employee employee){
        teamMembers.remove(employee);
        employee.setTeamName(null);
    }

    public String getTeamName() {
        return teamName;
    }


    public ArrayList<Employee> getTeamMembers() {
        return teamMembers;
    }

    public ArrayList<Project> getTeamProjects() {
        return teamProjects;
    }

    public int addingProject(Project p1){
        if(!teamProjects.contains(p1)){
            this.teamProjects.add(p1);
            return 1;
        }
        return 0;
    }
    public int removingProject(Project p1){
        if(teamProjects.contains(p1)){
            teamProjects.remove(p1);
            return 1;
        }
        return 0;
    }
}