package Student;

//package sudabang_management;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// 기능 : sName학생 userMonth월 userWeek주차 주간관리표 생성, 캡처 후 저장
public class VacationWeekTable extends JFrame{
    public static String saveFilePath;
    public VacationWeekTable(ArrayList<StudentData> sList, String sName, String userMonth, String userWeek, String saveFilePath) {
        this.saveFilePath = saveFilePath; // 저장 경로 지정

//        int k = 0; //k는 몇 번째 학생인지 -> 테스트용
        ArrayList<StudentData> printList = new ArrayList<StudentData>(); //출력할 학생의 정보들
        for (StudentData studentData : sList) { // 주간관리표 한 줄에 대하여
            if (studentData.getName().equals(sName) && (studentData.getMonth().equals(userMonth) && studentData.getWeek().equals(userWeek))){
                printList.add(studentData);
            }
//            System.out.println("studentData("+k+") : "+studentData);
//            k++;
        }
        int printSize = printList.size();
        //부족한 수만큼 '-'데이터로 채운 학생 인스턴스 추가
        for(int i=0;i<5-printSize;i++){
            StudentData nullData = new StudentData();
            nullData.setName(sName);
            nullData.setAttendance("-");
            nullData.setDate("-");
            nullData.setTextbook("-");
            nullData.setProgress("-");
            nullData.setConcentration("-");
            nullData.setAssignment_performance("-");
            nullData.setPlanner_performance("-");
            nullData.setAssignment_comment("-");
            nullData.setTest_score("-");
            printList.add(nullData);
        }

        setTitle("주간관리표 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // font
        Font title_font = new Font("Dialog",Font.PLAIN, 15);
        Font font1 = new Font("Dialog",Font.BOLD, 13);
        Font font2 = new Font("Dialog", Font.BOLD, 18);
        Font font3 = new Font("Dialog", Font.PLAIN, 15);
        Font plainFont = new Font("Dialog", Font.PLAIN, 11);
        Font bigFont = new Font("Dialog",Font.BOLD, 40);

        // Color
        Color light_yellow_color = new Color(255, 255, 204);
        Color light_red_color = new Color(255, 204, 204);
        Color light_blue_color = new Color(204, 229, 255);
        Color light_green_color = new Color(204, 255, 229);
        Color light_gray_color = new Color(240, 240, 240);
        Color green_color = new Color(102, 204, 0);

        // Border

        LineBorder border1 = new LineBorder(Color.white, 1);
        LineBorder border2 = new LineBorder(Color.white, 2);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());


        // title - NORTH
        JPanel title = new JPanel(); // NORTH
        title.setPreferredSize(new Dimension(this.getWidth(), 30));
        title.setBackground(Color.white);

        JLabel title_label = new JLabel("<"+sName+" 학생 "+userMonth+"월 "+userWeek+"주차"+" 주간관리표"+">");
        title_label.setFont(title_font);
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title.add(title_label);


        JPanel center = new JPanel();
        center.setLayout(null);
        //center.setBackground(Color.red); // 확인차 레드레드



        //------------------------------------------------------------
        // <출석 란>
        JPanel attendence = new JPanel(); // 1X4 패널
        attendence.setLayout(null);
        attendence.setBorder(border2);
        attendence.setBounds(0, 0, 1300, 150);

        JPanel att_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        att_panel1.setBounds(0, 0, 50, 150);
        att_panel1.setBackground(light_yellow_color);
        att_panel1.setBorder(border1);

        JLabel att_label = new JLabel("출석");
        att_label.setFont(font1);
        att_label.setHorizontalAlignment(JLabel.CENTER);
        att_label.setVerticalAlignment(JLabel.CENTER);
        att_panel1.add(att_label, BorderLayout.CENTER);

        JPanel att_panel2 = new JPanel(new GridLayout(6, 1));
        att_panel2.setBounds(50, 0, 300, 150);
        att_panel2.setBorder(border1);

        JLabel att_date_label = new JLabel("날짜");
        att_date_label.setFont(font2);
        att_date_label.setHorizontalAlignment(JLabel.CENTER);
        att_date_label.setVerticalAlignment(JLabel.CENTER);

        JPanel att_panel2_1 = new JPanel(new BorderLayout());
        att_panel2_1.setBackground(light_yellow_color);
        att_panel2_1.setBorder(border1);
        att_panel2_1.add(att_date_label, BorderLayout.CENTER);

        att_panel2.add(att_panel2_1);

        for(int i=0;i<5;i++){
            JPanel att_panel2_2 = new JPanel();
            att_panel2_2.setBorder(border1);
            att_panel2_2.setBackground(light_gray_color);
            JLabel attLabel = new JLabel(printList.get(i).getDate());
            attLabel.setFont(plainFont);
            att_panel2_2.add(attLabel); //정보 저장

            att_panel2.add(att_panel2_2);
        }


        JPanel att_panel3 = new JPanel(new GridLayout(6, 1));
        att_panel3.setBounds(350, 0, 300, 150); // attendence의 350, 0위치에 300X150 삽입
        att_panel3.setBorder(border1);

        JLabel att_time_label = new JLabel("출석시간");
        att_time_label.setFont(font2);
        att_time_label.setHorizontalAlignment(JLabel.CENTER);
        att_time_label.setVerticalAlignment(JLabel.CENTER);

        JPanel att_panel3_1 = new JPanel(new BorderLayout());
        att_panel3_1.setBounds(0, 0, 300, 25);
        att_panel3_1.setBackground(light_yellow_color);
        att_panel3_1.setBorder(border1);
        att_panel3_1.add(att_time_label, BorderLayout.CENTER);
        att_panel3.add(att_panel3_1);


        for(int i=0;i<5;i++) {
            JPanel att_panel3_2 = new JPanel();
            att_panel3_2.setBorder(border1);
            att_panel3_2.setBackground(light_gray_color);
            JLabel attTimeLabel = new JLabel(printList.get(i).getAttendance());
            attTimeLabel.setFont(plainFont);
            att_panel3_2.add(attTimeLabel); //정보 저장

            att_panel3.add(att_panel3_2);
        }



        JPanel att_panel4 = new JPanel(new GridLayout(6, 1));
        att_panel4.setBounds(650, 0, 650, 150);
        att_panel4.setBorder(border1);
        att_panel4.add(new JLabel(" # 출석시간 10분 전에 하루도 빠지지 않고"));
        att_panel4.add(new JLabel(" 출석하는 경우 학생들의 공부를 도와줄"));
        att_panel4.add(new JLabel(" 선물이 준비되어 있습니다."));
        att_panel4.add(new JLabel(""));
        att_panel4.add(new JLabel(" # 무단지각을 5회이상 하게 되면"));
        att_panel4.add(new JLabel(" 퇴원 처리 됩니다."));


        attendence.add(att_panel1);
        attendence.add(att_panel2);
        attendence.add(att_panel3);
        attendence.add(att_panel4);
        attendence.setBackground(Color.blue);



        //------------------------------------------------------------
        // <진도 & 과제 란>
        JPanel progress_and_homework = new JPanel();
        progress_and_homework.setLayout(null);
        progress_and_homework.setBounds(0, 150, 1300, 290); // center의 0,150위치에 1300X200 삽입
        progress_and_homework.setBorder(border2);
        progress_and_homework.setBackground(Color.red);


        JPanel pro_and_hwk_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        pro_and_hwk_panel1.setBounds(0, 0, 50, 290);
        pro_and_hwk_panel1.setBorder(border1);
        pro_and_hwk_panel1.setBackground(light_red_color);


        JLabel pah_label1 = new JLabel("<html>진도<br>&nbsp;&nbsp;&<br>과제</html>");
        pah_label1.setFont(font1);
        pah_label1.setHorizontalAlignment(JLabel.CENTER);
        pah_label1.setVerticalAlignment(JLabel.CENTER);
        pro_and_hwk_panel1.add(pah_label1, BorderLayout.CENTER);



        JPanel pro_and_hwk_panel2 = new JPanel();
        pro_and_hwk_panel2.setLayout(null);
        pro_and_hwk_panel2.setBounds(50, 0, 100, 290); // progress의 50, 0 위치에 100X290 삽입
        pro_and_hwk_panel2.setBorder(border1);

        JLabel pah_date_label = new JLabel("날짜"); // att_date_label이랑 겹쳐주자
        pah_date_label.setFont(font2);
        pah_date_label.setHorizontalAlignment(JLabel.CENTER);
        pah_date_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pah_panel2_1 = new JPanel(new BorderLayout());
        pah_panel2_1.setBounds(0, 0, 100, 25); // pro_panel2의 (0, 0)위치에 100X25 삽입
        pah_panel2_1.setBorder(border1);
        pah_panel2_1.setBackground(light_red_color);
        pah_panel2_1.add(pah_date_label, BorderLayout.CENTER);


        JPanel pah_panel2_grid = new JPanel(new GridLayout(5, 1)); // 진도 날짜 밑 5개의 grid칸
        pah_panel2_grid.setBounds(0, 25, 100, 265); // pro_panel2의 0, 25위치에 100X265 삽입
        pah_panel2_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel pah_panel2_2 = new JPanel();
            pah_panel2_2.setBorder(border1);
            pah_panel2_2.setBackground(light_gray_color);
            JLabel pahDateLabel = new JLabel(printList.get(i).getDate());
            pahDateLabel.setFont(plainFont);
            pah_panel2_2.add(pahDateLabel); //정보 저장

            pah_panel2_grid.add(pah_panel2_2);
        }


        pro_and_hwk_panel2.add(pah_panel2_1);
        pro_and_hwk_panel2.add(pah_panel2_grid);



        JPanel pro_and_hwk_panel3 = new JPanel();
        pro_and_hwk_panel3.setLayout(null);
        pro_and_hwk_panel3.setBounds(150, 0, 100, 290); // progress의 (150, 0)위치에 100X290 삽입
        pro_and_hwk_panel3.setBorder(border1);

        JPanel pah_panel3_grid = new JPanel(new GridLayout(5, 1)); // 진도 날짜 밑 5개의 grid칸
        pah_panel3_grid.setBounds(0, 25, 100, 265); // pro_panel3의 0, 25위치에 100X265 삽입
        pah_panel3_grid.setBorder(border1);

        JLabel pah_book_label = new JLabel("교재");
        pah_book_label.setFont(font2);
        pah_book_label.setHorizontalAlignment(JLabel.CENTER);
        pah_book_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pah_panel3_1 = new JPanel(new BorderLayout());
        pah_panel3_1.setBounds(0, 0, 100, 25);
        pah_panel3_1.setBorder(border1);
        pah_panel3_1.setBackground(light_red_color);
        pah_panel3_1.add(pah_book_label);

        for(int i=0;i<5;i++){
            JPanel pah_panel3_2 = new JPanel();
            pah_panel3_2.setBorder(border1);
            pah_panel3_2.setBackground(light_gray_color);
            JLabel pahBookLabel = new JLabel(printList.get(i).getTextbook());
            pahBookLabel.setFont(plainFont);
            pah_panel3_2.add(pahBookLabel); //정보 저장

            pah_panel3_grid.add(pah_panel3_2);
        }

        pro_and_hwk_panel3.add(pah_panel3_1);
        pro_and_hwk_panel3.add(pah_panel3_grid);


        JPanel pro_and_hwk_panel4 = new JPanel();
        pro_and_hwk_panel4.setLayout(null);
        pro_and_hwk_panel4.setBounds(250, 0, 525, 290); // progress의 (250, 0)위치에 1050X290 삽입
        pro_and_hwk_panel4.setBorder(border1);

        JPanel pah_panel4_grid = new JPanel(new GridLayout(5, 1));
        pah_panel4_grid.setBounds(0, 25, 525, 265);
        pah_panel4_grid.setBorder(border1);

        JLabel pah_pro_label = new JLabel("진도");
        pah_pro_label.setFont(font2);
        pah_pro_label.setHorizontalAlignment(JLabel.CENTER);
        pah_pro_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pah_panel4_1 = new JPanel(new BorderLayout());
        pah_panel4_1.setBounds(0, 0, 525, 25);
        pah_panel4_1.setBorder(border1);
        pah_panel4_1.setBackground(light_red_color);
        pah_panel4_1.add(pah_pro_label, BorderLayout.CENTER);

        for(int i=0;i<5;i++){
            JPanel pah_panel4_grid_i = new JPanel();
            pah_panel4_grid_i.setBorder(border1);
            pah_panel4_grid_i.setBackground(light_gray_color);

            //진도 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
            JTextPane tpName = new JTextPane();
            tpName.setEditable(false);
            tpName.setBackground(light_gray_color);
            tpName.setText(printList.get(i).getProgress());

            //tpName의 styleDocument를 가져와 가운데 정렬 설정
            StyledDocument doc = tpName.getStyledDocument();
            SimpleAttributeSet ce = new SimpleAttributeSet();
            StyleConstants.setAlignment(ce, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), ce, false);

            pah_panel4_grid_i.add(tpName);
            pah_panel4_grid.add(pah_panel4_grid_i);

        }


        pro_and_hwk_panel4.add(pah_panel4_1);
        pro_and_hwk_panel4.add(pah_panel4_grid);



        JPanel pro_and_hwk_panel5 = new JPanel();
        pro_and_hwk_panel5.setLayout(null);
        pro_and_hwk_panel5.setBounds(775, 0, 525, 290); // progress의 (250, 0)위치에 1050X290 삽입
        pro_and_hwk_panel5.setBorder(border1);

        JPanel pah_panel5_grid = new JPanel(new GridLayout(5, 1));
        pah_panel5_grid.setBounds(0, 25, 525, 265);
        pah_panel5_grid.setBorder(border1);

        JLabel pah_hwk_label = new JLabel("과제");
        pah_hwk_label.setFont(font2);
        pah_hwk_label.setHorizontalAlignment(JLabel.CENTER);
        pah_hwk_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pah_panel5_1 = new JPanel(new BorderLayout());
        pah_panel5_1.setBounds(0, 0, 525, 25);
        pah_panel5_1.setBorder(border1);
        pah_panel5_1.setBackground(light_red_color);
        pah_panel5_1.add(pah_hwk_label, BorderLayout.CENTER);

        for(int i=0;i<5;i++){
            JPanel pah_panel5_grid_i = new JPanel();
            pah_panel5_grid_i.setBorder(border1);
            pah_panel5_grid_i.setBackground(light_gray_color);

            //과제 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
            JTextPane tpName = new JTextPane();
            tpName.setEditable(false);
            tpName.setBackground(light_gray_color);
            tpName.setText(printList.get(i).getAssignment_comment());

            //tpName의 styleDocument를 가져와 가운데 정렬 설정
            StyledDocument doc = tpName.getStyledDocument();
            SimpleAttributeSet ce = new SimpleAttributeSet();
            StyleConstants.setAlignment(ce, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), ce, false);

            pah_panel5_grid_i.add(tpName);
            pah_panel5_grid.add(pah_panel5_grid_i);

        }




        pro_and_hwk_panel5.add(pah_panel5_1);
        pro_and_hwk_panel5.add(pah_panel5_grid);


        progress_and_homework.add(pro_and_hwk_panel1);
        progress_and_homework.add(pro_and_hwk_panel2);
        progress_and_homework.add(pro_and_hwk_panel3);
        progress_and_homework.add(pro_and_hwk_panel4);
        progress_and_homework.add(pro_and_hwk_panel5);



        //------------------------------------------------------------
        // <수업집중도 란>
        JPanel concentration = new JPanel();
        concentration.setLayout(null);
        concentration.setSize(1300, 90);
        concentration.setBorder(border2);


        JPanel con_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        con_panel1.setBounds(0, 0, 50, 90); // concentration의 (0, 0)위치에 50X90 삽입
        con_panel1.setBorder(border1);
        con_panel1.setBackground(light_blue_color);



        JLabel con_label = new JLabel("<html>&nbsp;수업<br>집중도</html>");
        con_label.setFont(font1);
        con_label.setHorizontalAlignment(JLabel.CENTER);
        con_label.setVerticalAlignment(JLabel.CENTER);
        con_panel1.add(con_label);



        JPanel con_date_grid = new JPanel(new GridLayout(1, 5)); // 월~금 수업집중도 날짜 5X1 그리드
        con_date_grid.setBounds(50, 0, 750, 25); // concentration의 (50, 0)위치에 750X25 삽입
        con_date_grid.setBorder(border1);

        JPanel con_state_grid = new JPanel(new GridLayout(1, 5)); // 월~금 수업집중도 집중도 5X1 그리드
        con_state_grid.setBounds(50, 25, 750, 65); // concentration의 (50, 25)위치에 750X65 삽입
        con_state_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JLabel con_date_label = new JLabel(printList.get(i).getDate());
            con_date_label.setFont(font3);
            con_date_label.setHorizontalAlignment(JLabel.CENTER);
            con_date_label.setVerticalAlignment(JLabel.CENTER);

            JPanel con_panel2_1 = new JPanel(new BorderLayout());
            con_panel2_1.setBorder(border1);
            con_panel2_1.setBackground(light_blue_color);
            con_panel2_1.add(con_date_label, BorderLayout.CENTER);

            con_date_grid.add(con_panel2_1);
        }


        for(int i=0;i<5;i++){
            JPanel con_panel2 = new JPanel();
            con_panel2.setBorder(border1);
            con_panel2.setBackground(light_gray_color);
            JLabel conLabel2  = new JLabel(printList.get(i).getConcentration());
            conLabel2.setFont(bigFont);
            con_panel2.add(conLabel2);

            con_state_grid.add(con_panel2);
        }



        JPanel con_panel7 = new JPanel(new GridLayout(6, 1));
        con_panel7.setBounds(800, 0, 500, 90); // concentration의 (800, 0)위치에 500X90 삽입
        con_panel7.setBorder(border1);
        //con_panel7.add(new JLabel(""));
        con_panel7.add(new JLabel(" # 수업집중도 1/2/3/4/5"));
        //con_panel7.add(new JLabel(""));
        con_panel7.add(new JLabel(" 1 - 수업시간 전반에 걸쳐 집중 못함"));
        con_panel7.add(new JLabel(" 2 - 수업시간 동안 집중한 시간보다 집중하지 못한 시간이 더 많음"));
        con_panel7.add(new JLabel(" 3 - 어느 정도 선생님과의 의사소통을 하며 교감함"));
        con_panel7.add(new JLabel(" 4 - 선생님과의 의사소통이 원활히 되고, 수업에 완전히 집중함"));
        con_panel7.add(new JLabel(" 5 - 완벽 집중도"));
        //con_panel7.add(new JLabel(""));

        concentration.add(con_panel1);
        concentration.add(con_date_grid);
        concentration.add(con_state_grid);
        concentration.add(con_panel7);



        //------------------------------------------------------------
        //<과제수행도 란>
        JPanel homework_state = new JPanel();
        homework_state.setLayout(null);
        homework_state.setSize(1300, 90);
        homework_state.setBorder(border2);


        JPanel hws_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        hws_panel1.setBounds(0, 0, 50, 90); // concentration의 (0, 0)위치에 50X90 삽입
        hws_panel1.setBorder(border1);
        hws_panel1.setBackground(light_green_color);


        JLabel hws_label = new JLabel("<html>&nbsp;과제<br>수행도</html>");
        hws_label.setFont(font1);
        hws_label.setHorizontalAlignment(JLabel.CENTER);
        hws_label.setVerticalAlignment(JLabel.CENTER);
        hws_panel1.add(hws_label);


        JPanel hws_date_grid = new JPanel(new GridLayout(1, 5)); // 월~금 과제수행도 날짜 5X1 그리드
        hws_date_grid.setBounds(50, 0, 750, 25); // homework_state의 (50, 0)위치에 750X25 삽입
        hws_date_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JLabel hws_date_label = new JLabel(printList.get(i).getDate());
            hws_date_label.setFont(font3);
            hws_date_label.setHorizontalAlignment(JLabel.CENTER);
            hws_date_label.setVerticalAlignment(JLabel.CENTER);

            JPanel hws_panel2_1 = new JPanel(new BorderLayout());
            hws_panel2_1.setBorder(border1);
            hws_panel2_1.setBackground(light_green_color);
            hws_panel2_1.add(hws_date_label, BorderLayout.CENTER);

            hws_date_grid.add(hws_panel2_1);
        }


        // 월~금 과제 수행도 칸
        JPanel hws_state_grid = new JPanel(new GridLayout(1, 5)); // 월~금 과제수행도 수행도 5X1 그리드
        hws_state_grid.setBounds(50, 25, 750, 65); // homework_state의 (50, 25)위치에 750X65 삽입
        hws_state_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel hws_panel2 = new JPanel();
            hws_panel2.setBorder(border1);
            hws_panel2.setBackground(light_gray_color);
            JLabel hwsLabel2  = new JLabel(printList.get(i).getAssignment_performance());
            hwsLabel2.setFont(bigFont);
            hws_panel2.add(hwsLabel2);

            hws_state_grid.add(hws_panel2);
        }


        JPanel hws_panel7 = new JPanel(new GridLayout(5, 1));
        hws_panel7.setBounds(800, 0, 500, 90); // homework_state의 (800, 0)위치에 500X90 삽입
        hws_panel7.setBorder(border1);
        //hws_panel7.add(new JLabel(""));
        hws_panel7.add(new JLabel(" # 과제수행도 1/2/3/P"));
        //hws_panel7.add(new JLabel(""));
        hws_panel7.add(new JLabel(" 1 - 숙제를 50%미만으로 이행해 옴"));
        hws_panel7.add(new JLabel(" 2 - 숙제 달성률 50 ~ 70%"));
        hws_panel7.add(new JLabel(" 3 - 숙제 달성률 70 ~ 90%"));
        hws_panel7.add(new JLabel(" P - 숙제 달성률 90 ~ 100% + 오답 완벽 처리"));
        //hws_panel7.add(new JLabel(""));

        homework_state.add(hws_panel1);
        homework_state.add(hws_date_grid);
        homework_state.add(hws_state_grid);
        homework_state.add(hws_panel7);

        //------------------------------------------------------------
        // <플래너 수행도 란>
        JPanel planner = new JPanel();
        planner.setLayout(null);
        planner.setSize(1300, 90);
        planner.setBorder(border2);


        JPanel pln_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        pln_panel1.setBounds(0, 0, 50, 90); // planner의 (0, 0)위치에 50X90 삽입
        pln_panel1.setBorder(border1);
        pln_panel1.setBackground(green_color);


        JLabel pln_label = new JLabel("<html>플래너<br>수행도</html>");
        pln_label.setFont(font1);
        pln_label.setHorizontalAlignment(JLabel.CENTER);
        pln_label.setVerticalAlignment(JLabel.CENTER);
        pln_panel1.add(pln_label);

        // 월~금 날짜 칸


        JPanel pln_date_grid = new JPanel(new GridLayout(1, 5)); // 월~금 플래너수행도 날짜 5X1 그리드
        pln_date_grid.setBounds(50, 0, 750, 25); // planner의 (50, 0)위치에 750X25 삽입
        pln_date_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JLabel pln_date_label = new JLabel(printList.get(i).getDate());
            pln_date_label.setFont(font3);
            pln_date_label.setHorizontalAlignment(JLabel.CENTER);
            pln_date_label.setVerticalAlignment(JLabel.CENTER);

            JPanel pln_panel2_1 = new JPanel(new BorderLayout());
            pln_panel2_1.setBorder(border1);
            pln_panel2_1.setBackground(green_color);
            pln_panel2_1.add(pln_date_label, BorderLayout.CENTER);

            pln_date_grid.add(pln_panel2_1);
        }


        // 월~금 플래너 수행도 칸
        JPanel pln_state_grid = new JPanel(new GridLayout(1, 5)); // 월~금 플래너수행도 수행도 5X1 그리드
        pln_state_grid.setBounds(50, 25, 750, 65); // planner의 (50, 25)위치에 750X65 삽입
        pln_state_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel pln_panel2_2 = new JPanel();
            pln_panel2_2.setBorder(border1);
            pln_panel2_2.setBackground(light_gray_color);
            JLabel plnLabel2_2  = new JLabel(printList.get(i).getPlanner_performance());
            plnLabel2_2.setFont(bigFont);
            pln_panel2_2.add(plnLabel2_2);

            pln_state_grid.add(pln_panel2_2);
        }


        JPanel pln_panel7 = new JPanel(new GridLayout(4, 1));
        pln_panel7.setBounds(800, 0, 500, 90); // planner의 (800, 0)위치에 500X90 삽입
        pln_panel7.setBorder(border1);
        //pln_panel7.add(new JLabel(""));
        pln_panel7.add(new JLabel(" # 플래너 수행도 A/B/C"));
        //pln_panel7.add(new JLabel(""));
        pln_panel7.add(new JLabel(" A - 자세한 계획을 세우고 이행 정도를 정확히 기입함"));
        pln_panel7.add(new JLabel(" B - 계획을 세웠으나, 자세하지 않거나 이행 정도를 기입하지 않음"));
        pln_panel7.add(new JLabel(" C - 플래너를 작성하지 않음"));
        //pln_panel7.add(new JLabel(""));

        planner.add(pln_panel1);
        planner.add(pln_date_grid);
        planner.add(pln_state_grid);
        planner.add(pln_panel7);



        // <TEST 란>
        JPanel test = new JPanel();
        test.setLayout(null);
        test.setSize(1300, 90);
        test.setBorder(border2);


        JPanel tst_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        tst_panel1.setBounds(0, 0, 50, 90); // test의 (0, 0)위치에 50X90 삽입
        tst_panel1.setBorder(border1);
        tst_panel1.setBackground(light_yellow_color);

        JLabel tst_label1 = new JLabel("TEST");
        tst_label1.setFont(font1);
        tst_label1.setHorizontalAlignment(JLabel.CENTER);
        tst_label1.setVerticalAlignment(JLabel.CENTER);
        tst_panel1.add(tst_label1, BorderLayout.CENTER);


        JPanel tst_date_grid = new JPanel(new GridLayout(1, 5)); // 월~금 TEST 날짜 5X1 그리드
        tst_date_grid.setBounds(50, 0, 750, 25); // test의 (50, 0)위치에 750X25 삽입
        tst_date_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JLabel tst_date1_label = new JLabel(printList.get(i).getDate());
            tst_date1_label.setFont(font3);
            tst_date1_label.setHorizontalAlignment(JLabel.CENTER);
            tst_date1_label.setVerticalAlignment(JLabel.CENTER);

            JPanel tst_panel2_1 = new JPanel(new BorderLayout());
            tst_panel2_1.setBorder(border1);
            tst_panel2_1.setBackground(light_yellow_color);
            tst_panel2_1.add(tst_date1_label, BorderLayout.CENTER);

            tst_date_grid.add(tst_panel2_1);
        }



        // 월~금 TEST 점수 칸
        JPanel tst_state_grid = new JPanel(new GridLayout(1, 5)); // 월~금 과제 5X1 그리드
        tst_state_grid.setBounds(50, 25, 750, 65); // test의 (50, 25)위치에 750X65 삽입
        tst_state_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel tst_panel2_2 = new JPanel();
            tst_panel2_2.setBorder(border1);
            tst_panel2_2.setBackground(light_gray_color);

            JLabel testLabel2_2  = new JLabel("<html>"+printList.get(i).getTest_score().replace("\n","<br>")+"</html>");
            testLabel2_2.setFont(new Font("Dialog",Font.BOLD,20));
            tst_panel2_2.add(testLabel2_2);

            tst_state_grid.add(tst_panel2_2);
        }


        JPanel tst_panel7 = new JPanel();
        tst_panel7.setBounds(800, 0, 500, 90); // planner의 (800, 0)위치에 500X90 삽입
        tst_panel7.setBorder(border1);


        test.add(tst_panel1);
        test.add(tst_date_grid);
        test.add(tst_state_grid);
        test.add(tst_panel7);


        // 패널 작업 끝
        // -------------------------------------------------------




        // <여백>
        JPanel west_panel = new JPanel();
        west_panel.setPreferredSize(new Dimension(20, this.getHeight()));
        west_panel.setBackground(Color.white);

        JPanel east_panel = new JPanel();
        east_panel.setPreferredSize(new Dimension(20, this.getHeight()));
        east_panel.setBackground(Color.white);


        // 수업집중도~TEST란 이 들어갈 패널 (크기 지정하는 데에 있어서 반복적인 잡업을 줄이기 위해)
        JPanel center_grid = new JPanel(new GridLayout(4, 1));
        center_grid.setBounds(0, 440, 1300, 360); // center의 0, 440위치(패널의 왼쪽 위 좌표)에 1300X360의 패널
        center_grid.setBorder(border2);

        center_grid.add(concentration);
        center_grid.add(homework_state);
        center_grid.add(planner);
        // center_grid.add(homework);
        center_grid.add(test);


        // 각 패널들 추가
        c.add(title, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        c.add(east_panel, BorderLayout.EAST);
        c.add(west_panel, BorderLayout.WEST);
        center.add(attendence);
        center.add(progress_and_homework);
        center.add(center_grid);



        setSize(1300, 860);
        setVisible(true);
//        dispose();






        // Create test file
        File saveFile = new File(saveFilePath + sName + " " +userMonth+"월 "+userWeek+"주차 주간관리표"+".png");


        // Use the ImageIO API to write the bufferedImage to a temporary file
        try {
            BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = im.createGraphics();
            c.printAll(g2d);

            g2d.dispose();
            ImageIO.write(im, "png", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}