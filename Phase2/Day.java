public class Day implements Cloneable, Comparable<Day>{

    private int year;
    private int month;
    private int day;

    private static final String MONTH_NAMES = "JanFebMarAprMayJunJulAugSepOctNovDec";

    @Override
    public Day clone() {
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }

    //Constructor
    public Day(int y, int m, int d) {
        this.year=y;
        this.month=m;
        this.day=d;
    }



    public void set(String sDay) { //Set year,month,day based on a string like 01-Mar-2020
        String[] sDayParts = sDay.split("-");
        this.day = Integer.parseInt(sDayParts[0]);
        this.year = Integer.parseInt(sDayParts[2]);
        this.month = MONTH_NAMES.indexOf(sDayParts[1])/3+1;
    }

    public Day(String sDay) {
        set(sDay);
    } //Constructor, simply call set(sDay)

    // check if a given year is a leap year
    static public boolean isLeapYear(int y)
    {
        if (y%400==0)
            return true;
        else if (y%100==0)
            return false;
        else if (y%4==0)
            return true;
        else
            return false;
    }

    // check if y,m,d valid
    static public boolean valid(int y, int m, int d)
    {
        if (m<1 || m>12 || d<1) return false;
        switch(m){
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                return d<=31;
            case 4: case 6: case 9: case 11:
                return d<=30;
            case 2:
                if (isLeapYear(y))
                    return d<=29;
                else
                    return d<=28;
        }
        return false;
    }

    // Return a string for the day like dd MMM yyyy
    @Override
    public String toString() {
        return day+"-"+ MONTH_NAMES.substring((month-1)*3,(month)*3) + "-"+ year; // (month-1)*3,(month)*3
    }


    public Day addingDays(int days){
        switch(month){
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                if(day + days<=31){
                    day += days;
                }else{
                    day = (day + days)-31;
                    if(month!=12){
                        month++;
                    }else{
                        month = 1;
                        year++;
                    }
                }break;
            case 4: case 6: case 9: case 11:
                if(day + days<=30){
                    day += days;
                }else{
                    day = (day + days)-30;
                    if(month!=12){
                        month++;
                    }else{
                        month = 1;
                        year++;
                    }
                }break;
            case 2:
                if (isLeapYear(year)) {
                    if (day + days <= 29) {
                        day += days;
                    } else {
                        day = (day + days) - 29;
                        if (month != 12) {
                            month++;
                        } else {
                            month = 1;
                            year++;
                        }
                    }
                }
                else {
                    if (day + days <= 28) {
                        day += days;
                    } else {
                        day = (day + days) - 28;
                        if (month != 12) {
                            month++;
                        } else {
                            month = 1;
                            year++;
                        }
                    }
                }
        }
        return this;
    }

    @Override
    public int compareTo(Day o) {
        String date = this.day + this.month + this.year + "";
        String date2 = o.day + o.month + o.year + "";
        return date.compareTo(date2);
    }
}