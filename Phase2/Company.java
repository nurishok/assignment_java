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
            String output = e.getName();
            if(e.getTeamName()!=null){
                output+=" (" + e.getTeamName() + ")";
            }
            System.out.println(output);
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

    public void deletingTeam(Team t){
        allTeams.remove(t);
    }

    public void addingTeam(Team t) {
        allTeams.add(t);
    }

    public int addingProject(Project p){
        allProjects.add(p);
        Collections.sort(allProjects);
        return 1;
    }
    public void deletingProject(Project p){
        allProjects.remove(p);
    }

    public Team creatingTeam(String nT, String nE) {
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

    public void listProjects() {
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");

        for(Project p: allProjects){
            String nameOfTeam = "(Not Assigned)";
            String dayOfStart = "";
            String dayOfEnd = "";
            if(p.getDayOfStart()!=null){
                dayOfStart = p.getDayOfStart().toString();
            }
            if(p.getDayOfEnd()!=null){
                dayOfEnd = p.getDayOfEnd().toString();
            }
            if(p.getT()!=null){
                nameOfTeam = p.getT().getNameOfTeam();
            }
            System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", p.getNameOfProject(), p.getManPower()+ " man-days", nameOfTeam, dayOfStart, dayOfEnd);
        }
    }

    public int checkingForExisting(Project p) {
        for(Project p1 : allProjects){
            if(p1.getNameOfProject().equals(p.getNameOfProject())){
                return 0;
            }
        }
        return 1;
    }
    public int takingProject(String nameOfTeam, String nameOfProject, String dayOfStart){
        Team t = null;
        Project p = null;
        Day d = new Day(dayOfStart);
        for(Team t1: allTeams){
            if(t1.getNameOfTeam().equals(nameOfTeam)){
                t = t1;
            }
        }
        if(t == null){
            return -1;
        }
        for(Project p1: allProjects){
            if(p1.getNameOfProject().equals(nameOfProject)){
                p = p1;
            }
        }
        if(p == null){
            return -2;
        }
        for(Project p1 : t.getTeamProjects()){
            if(p.getDayOfStart()!=null && p.getDayOfEnd()!=null && p1.getDayOfStart()!=null){
                if(p.getDayOfEnd().compareTo(p1.getDayOfStart()) == 0){
                    return -3;
                }
                else if ((p.getDayOfStart().compareTo(p1.getDayOfStart()) >= 0 && p.getDayOfEnd().compareTo(p1.getDayOfEnd()) < 0)){
                    return -3;
                }
                else if ((p.getDayOfStart().compareTo(p1.getDayOfEnd())) < 0) {
                    return -3;
                }
            }

        }


        p.setDayOfStart(d);
        p.setT(t);
        if(t.addingProject(p)==1){
            return 1;
        }else{
            return 0;
        }
    }
    public Project findingProject(String projectCode){
        for(Project p: allProjects){
            if(p.getNameOfProject().equals(projectCode)){
                return p;
            }
        }
        return null;
    }
    public void untakingProject(String teamName, String projectName){
        Team t = null;
        Project p = null;
        for(Team t1: allTeams){
            if(t1.getNameOfTeam().equals(teamName)){
                t = t1;
            }
        }
        for(Project p1: allProjects){
            if(p1.getNameOfProject().equals(projectName)){
                p = p1;
            }
        }


        t.removingProject(p);
        p.setDayOfStart(null);
        p.setDayOfEnd(null);
        p.setT(null);


    }

    public int checkAddingProject(String teamName, String projectName, String startDay) {
        Team t = null;
        Project p = null;
        Day d = new Day(startDay);
        for(Team t1: allTeams){
            if(t1.getNameOfTeam().equals(teamName)){
                t = t1;
            }
        }
        if(t == null){
            return -1;
        }
        for(Project p1: allProjects){
            if(p1.getNameOfProject().equals(projectName)){
                p = p1;
            }
        }
        if(p == null){
            return -2;
        }

        if(p.getT() != null){
            return 0;
        }

        for(Project p1 : t.getTeamProjects()) {
            if (p.getDayOfStart() != null && p.getDayOfEnd() != null && p1.getDayOfStart() != null) {
                if (p.getDayOfEnd().compareTo(p1.getDayOfStart()) == 0) {
                    return -3;
                } else if ((p.getDayOfStart().compareTo(p1.getDayOfStart()) >= 0 && p.getDayOfEnd().compareTo(p1.getDayOfEnd()) <= 0)) {
                    return -3;
                } else if ((p.getDayOfStart().compareTo(p1.getDayOfEnd())) <= 0) {
                    return -3;
                }
            }
        }

        return 1;
    }
}