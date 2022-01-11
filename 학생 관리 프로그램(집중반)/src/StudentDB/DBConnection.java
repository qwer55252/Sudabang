package StudentDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smp","root","mjymsy9598@");
            st = con.createStatement();
        }catch (Exception e){
            System.out.println("데이터베이스 연결 오류 : " + e.getMessage());
        }
    }


    //학생 리스트 생성 메서드
    public ArrayList<StudentData> getStudentList(){
        try {
            String SQL = "SELECT * FROM vacation_student";
            rs = st.executeQuery(SQL);
            ArrayList<StudentData> studentList = new ArrayList<StudentData>();
            while(rs.next()){
                StudentData sd = new StudentData();
                sd.setName(rs.getString("이름"));
                sd.setAssignment_comment(rs.getString("과제"));
                sd.setAttendance(rs.getString("출결"));
                sd.setConcentration(rs.getString("수업집중도"));
                sd.setAssignment_performance(rs.getString("과제수행도"));
                sd.setNum(rs.getInt("순번"));
                sd.setPlanner_performance(rs.getString("플래너수행도"));
                sd.setProgress(rs.getString("진도"));
                sd.setTextbook(rs.getString("교재"));
                sd.setTest_score(rs.getString("테스트결과"));
                sd.setDate(rs.getString("날짜"));
                sd.setWeek_num(rs.getString("병합"));
                studentList.add(sd);
            }
            return studentList;
        }catch (Exception e){
            System.out.println("학생 인스턴스 생성을 실패하였습니다.");
        }
        return null;
    }

    public ArrayList<String> getStudentNames(){
        try {
            String SQL = "SELECT DISTINCT `이름` FROM vacation_student";
            rs = st.executeQuery(SQL);
            ArrayList<String> nameList = new ArrayList<String>();
            while(rs.next()){
                String name;
                name = rs.getString("이름");
                nameList.add(name);
            }
            return nameList;
        }catch (Exception e){
            System.out.println("학생 이름(유니크) 생성을 실패하였습니다.");
        }
        return null;
    }
    public ArrayList<String> getWeekNum(){
        try {
            String SQL = "SELECT DISTINCT `병합` FROM vacation_student";
            rs = st.executeQuery(SQL);
            ArrayList<String> weekNumList = new ArrayList<String>();
            while(rs.next()){
                String weekNum;
                weekNum = rs.getString("병합");
                weekNumList.add(weekNum);
            }
            return weekNumList;
        }catch (Exception e){
            System.out.println("주차(유니크) 생성을 실패하였습니다.");
        }
        return null;
    }
}
