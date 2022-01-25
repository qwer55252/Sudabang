package Student;

public class StudentClinicData {
    private String date;
    private String attendance;
    private String name;
    private String unitName;
    private String achivementLevel;
    private String weakUnit;
    private String detailCourse;
    private String month;
    private String week;
    private String month_weekNum;
    private String Count;
    private String name_month_weekNum;
    private String name_month_weekNum_count;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAchivementLevel() {
        return achivementLevel;
    }

    public void setAchivementLevel(String achivementLevel) {
        this.achivementLevel = achivementLevel;
    }

    public String getWeakUnit() {
        return weakUnit;
    }

    public void setWeakUnit(String weakUnit) {
        this.weakUnit = weakUnit;
    }

    public String getDetailCourse() {
        return detailCourse;
    }

    public void setDetailCourse(String detailCourse) {
        this.detailCourse = detailCourse;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth_weekNum() {
        return month_weekNum;
    }

    public void setMonth_weekNum(String month_weekNum) {
        this.month_weekNum = month_weekNum;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getName_month_weekNum() {
        return name_month_weekNum;
    }

    public void setName_month_weekNum(String name_month_weekNum) {
        this.name_month_weekNum = name_month_weekNum;
    }

    public String getName_month_weekNum_count() {
        return name_month_weekNum_count;
    }

    public void setName_month_weekNum_count(String name_month_weekNum_count) {
        this.name_month_weekNum_count = name_month_weekNum_count;
    }


    public StudentClinicData() {

    }

    @Override
    public String toString() {
        return "StudentClinicData{" +
                "date='" + date + '\'' +
                ", attendance='" + attendance + '\'' +
                ", name='" + name + '\'' +
                ", unitName='" + unitName + '\'' +
                ", achivementLevel='" + achivementLevel + '\'' +
                ", weakUnit='" + weakUnit + '\'' +
                ", detailCourse='" + detailCourse + '\'' +
                ", month='" + month + '\'' +
                ", week='" + week + '\'' +
                ", month_weekNum='" + month_weekNum + '\'' +
                ", Count='" + Count + '\'' +
                ", name_month_weekNum='" + name_month_weekNum + '\'' +
                ", name_month_weekNum_count='" + name_month_weekNum_count + '\'' +
                '}';
    }

    public StudentClinicData(String date, String attendance, String name, String unitName, String achivementLevel, String weakUnit, String detailCourse, String month, String week, String month_weekNum, String Count, String name_month_weekNum, String name_month_weekNum_count) {
        this.date = date;
        this.attendance = attendance;
        this.name = name;
        this.unitName = unitName;
        this.achivementLevel = achivementLevel;
        this.weakUnit = weakUnit;
        this.detailCourse = detailCourse;
        this.month = month;
        this.week = week;
        this.month_weekNum = month_weekNum;
        this.Count = Count;
        this.name_month_weekNum = name_month_weekNum;
        this.name_month_weekNum_count = name_month_weekNum_count;
    }

}