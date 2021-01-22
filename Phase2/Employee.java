import java.util.*;
public class Employee implements Comparable<Employee> {
    private String name;
    private String teamName;

    public Employee(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        for (Employee e: list) {
            if (e.getName().equals(nameToSearch))
                return e;
        }
        return null;
    }
    @Override
    public int compareTo(Employee another) {
        return this.name.compareTo(another.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void changeTeam(Team team){
        List<Team> teams= Company.getInstance().getTeamLists();
        Team oldTeam = null;
        for(Team t : teams){
            if(t.getNameOfTeam().equals(teamName)){
                oldTeam = t;
            }
        }
        oldTeam.delTeamMember(this);
        team.addTeamMember(this);
        this.teamName = team.getNameOfTeam();
    }
}