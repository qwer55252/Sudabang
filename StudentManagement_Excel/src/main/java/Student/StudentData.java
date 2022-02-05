package Student;

public class StudentData {
    private String name;
    private String attendance;
    private String assignment_performance;
    private String planner_performance;
    private String concentration;
    private String test_score;
    private String assignment_comment;
    private String textbook;
    private String progress;
    private String date;
    private String week_num;
    private String month;
    private String week;
    private String opinion;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getAssignment_performance() {
        return assignment_performance;
    }

    public void setAssignment_performance(String assignment_performance) {
        this.assignment_performance = assignment_performance;
    }

    public String getPlanner_performance() {
        return planner_performance;
    }

    public void setPlanner_performance(String planner_performance) {
        this.planner_performance = planner_performance;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getTest_score() {
        return test_score;
    }

    public void setTest_score(String test_score) {
        this.test_score = test_score;
    }

    public String getAssignment_comment() {
        return assignment_comment;
    }

    public void setAssignment_comment(String assignment_comment) {
        this.assignment_comment = assignment_comment;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek_num() {
        return week_num;
    }

    public void setWeek_num(String week_num) {
        this.week_num = week_num;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }



    public StudentData(){

    }

    public StudentData(String week_num, String name, String attendance, String assignment_performance, String planner_performance, String concentration, String test_score, String assignment_comment, String textbook, String progress, String date, String month, String week) {
        this.name = name;
        this.attendance = attendance;
        this.assignment_performance = assignment_performance;
        this.planner_performance = planner_performance;
        this.concentration = concentration;
        this.test_score = test_score;
        this.assignment_comment = assignment_comment;
        this.textbook = textbook;
        this.progress = progress;
        this.date = date;
        this.month = month;
        this.week = week;
        this.week_num = week_num;
    }
}




