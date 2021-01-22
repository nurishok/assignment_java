import java.util.ArrayList;
import java.util.Collections;

public class Company {
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;

    private static Company instance = new Company();

    private Company () {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }

    public ArrayList<Employee> getList() {
        return allEmployees;
    }

    public ArrayList<Team> getTeamLists() {
        return allTeams;
    }

    public Employee findEmployee(String name) {
        for (Employee e: allEmployees) {
            if (e.getName().equals(name))
                return e;
        }
        return null;
    }


    public void listEmployees() {
        for (Employee e: allEmployees) {
            System.out.println(e.getName());
        }
    }

    public void addEmployee(Employee e) {
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public void removeEmployee(Employee e) {
        allEmployees.remove(e); //.remove is a method of ArrayList
    }


    public void listTeams() {
        Team.list(allTeams);
    }

    public void delTeam(Team t){
        allTeams.remove(t);
    }

    public void addTeam(Team t) {
        allTeams.add(t);
    }

    public Employee createEmployee(String name) {
        Employee e = new Employee(name);
        allEmployees.add(e);
        Collections.sort(allEmployees);
        return e;
    }

    public Team createTeam(String nT, String nE) {
        try {
            Employee e = Employee.searchEmployee(allEmployees, nE);
            if (e == null) {
                throw new ExEmployeeNameNotExist();
            }
            else{
                for (Team t2 : Company.getInstance().getTeamLists()) {
                    if (e.getName().equals(t2.getHeadName())) {
                        throw new ExEmployeeHasJoined();
                    }
                }
            }

            Team t = new Team(nT, e);
            try {
                for (Team t1 : Company.getInstance().getTeamLists()) {
                    if (t1.getNameOfTeam().equals(t.getNameOfTeam())) {
                        throw new ExTeamNotExist();
                    }
                }
                allTeams.add(t);
                Collections.sort(allTeams);
                return t;
            } catch (ExTeamNotExist e1) {
                System.out.println(e1.getMessage());
            }


        } catch (ExEmployeeNameNotExist e2) {
            System.out.println(e2.getMessage());
        } catch (ExEmployeeHasJoined e3) {
            System.out.println(e3.getMessage());
        }
        return null;
    }
    public int addProject(Project project){
        allProjects.add(project);
        Collections.sort(allProjects);
        return 1;
    }
    public void delProject(Project project){
        allProjects.remove(project);
    }

    public void listProjects() {
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");

        for(Project p: allProjects){
            String teamName = "(Not Assigned)";
            if(p.getTeam()!=null){
                teamName = p.getTeam().getNameOfTeam();
            }
            System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", p.getProjectName(), p.getManPower()+ "man-days", teamName, "", "");
        }
    }

    public int checkExists(Project p) {
        for(Project p1 : allProjects){
            if(p1.getProjectName().equals(p.getProjectName())){
                return 0;
            }
        }
        return 1;
    }
}
