import java.util.*;

public class Project  implements Comparable<Project> {
    private String projectName;
    private String manPower;
    private Day dateSetup;
    private Team team;

    public Project(String n, String m) {
        projectName = n;
        manPower = m;
        dateSetup = SystemDate.getInstance().clone();
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManPower() {
        return manPower;
    }

    public void setManPower(String manPower) {
        this.manPower = manPower;
    }

    public Day getDateSetup() {
        return dateSetup;
    }

    public void setDateSetup(Day dateSetup) {
        this.dateSetup = dateSetup;
    }
    public void joinTeam(Team team){
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int compareTo(Project o) {

        return this.projectName.compareTo(o.getProjectName());
    }
}