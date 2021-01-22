public class Project  implements Comparable<Project> {
    private String nameOfProject;
    private String manDaysPower;
    private Day setupDate;
    private Day dayOfStart;
    private Day dayOfEnd;
    private Team t;

    public Project(String n, String m) {
        nameOfProject = n;
        manDaysPower = m;
        setupDate = SystemDate.getInstance().clone();
    }


    public String getNameOfProject() {
        return nameOfProject;
    }


    public String getManPower() {
        return manDaysPower;
    }


    public Team getT() {
        return t;
    }

    public void setT(Team t) {
        this.t = t;
        if(t !=null){
            int pNumber = t.getTeamMembers().size() + 1;
            int manPowerInt = Integer.parseInt(manDaysPower);
            double daysToAdd = (double)manPowerInt/(double)pNumber;
            int number = (int)Math.ceil(daysToAdd);
            Day start = dayOfStart.clone();
            dayOfEnd = start.addingDays(number-1);
        }
    }

    public Day getDayOfStart() {
        return dayOfStart;
    }

    public void setDayOfStart(Day dayOfStart) {
        this.dayOfStart = dayOfStart;
    }

    public Day getDayOfEnd() {
        return dayOfEnd;
    }

    public void setDayOfEnd(Day dayOfEnd) {
        this.dayOfEnd = dayOfEnd;
    }

    @Override
    public int compareTo(Project o) {
        return this.nameOfProject.compareTo(o.getNameOfProject());
    }
    public String getBetweenString(){
        return "(" + this.dayOfStart.toString() + " to " + this.dayOfEnd.toString() + ").";
    }
}