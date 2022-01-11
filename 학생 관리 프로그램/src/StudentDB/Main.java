package StudentDB;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        DBConnection connection = new DBConnection();
        ArrayList<String> nameList;
        ArrayList<String> weekNumList;
        nameList = connection.getStudentNames();
        weekNumList = connection.getWeekNum();
        int cnt = 0; //연습용
        for (String weekNum : weekNumList) { //한 주차에 대해서
            for (String name : nameList) {
                if(cnt==1) break;
                new Week_table(connection.getStudentList(), name, weekNumList.get(2));
                cnt++;
            }
            break;
        }
        System.out.println("작업이 끝났습니다");
//            ScreenShot sc = new ScreenShot(week_table, i);
    }
}
