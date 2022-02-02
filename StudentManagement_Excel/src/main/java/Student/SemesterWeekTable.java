package Student;


//package sudabang_management;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class SemesterWeekTable extends JFrame {
    // JFrame 에 swing을 붙이는게 좋을까?
    // JFrmae 위에 JPanel에 swing을 붙이는게 좋을까?

    public SemesterWeekTable(ArrayList<StudentData> sList, String sName, String userMonth, String userWeek, String saveFilePath) {

        //int k = 0; //k는 몇 번째 학생인지 -> 테스트용
        ArrayList<StudentData> printList = new ArrayList<StudentData>(); //출력할 학생의 정보들
        for (StudentData studentData : sList) { // 주간관리표 한 줄에 대하여
            if (studentData.getName().equals(sName) && (studentData.getMonth().equals(userMonth) && studentData.getWeek().equals(userWeek))){
                printList.add(studentData);
            }
        }
        if(printList.size() == 1){
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
        Font title_font = new Font("Dialog",Font.BOLD, 25);
        Font font1 = new Font("Dialog",Font.BOLD, 20);
        Font font2 = new Font("Dialog", Font.BOLD, 15);
        Font font3 = new Font("Dialog", Font.BOLD, 15);
        Font plainFont = new Font("Dialog", Font.PLAIN, 11);
        Font bigFont = new Font("Dialog",Font.BOLD, 50);


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
        title.setPreferredSize(new Dimension(this.getWidth(), 60));
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
        JPanel attendance = new JPanel(); // 1X4 패널
        attendance.setLayout(null);
        attendance.setBorder(border2);
        attendance.setBounds(0, 0, 840, 90);

        JPanel att_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        att_panel1.setBounds(0, 0, 90, 90);
        att_panel1.setBackground(light_yellow_color);
        att_panel1.setBorder(border1);

        JLabel att_label = new JLabel("출석");
        att_label.setFont(font1);
        att_label.setHorizontalAlignment(JLabel.CENTER);
        att_label.setVerticalAlignment(JLabel.CENTER);
        att_panel1.add(att_label, BorderLayout.CENTER);

        //날짜 칼럼 생성
        JPanel att_panel2 = new JPanel(new GridLayout(3, 1));
        att_panel2.setBounds(90, 0, 250, 90);
        att_panel2.setBorder(border1);

        JLabel att_date_label = new JLabel("날짜");
        att_date_label.setFont(font2);
        att_date_label.setHorizontalAlignment(JLabel.CENTER);
        att_date_label.setVerticalAlignment(JLabel.CENTER);

        JPanel att_panel2_1 = new JPanel();
        att_panel2_1.setPreferredSize(new Dimension(250, 30));
        att_panel2_1.setBackground(light_yellow_color);
        att_panel2_1.setBorder(border1);
        att_panel2_1.add(att_date_label);

        JPanel att_panel2_2 = new JPanel();
        att_panel2_2.setPreferredSize(new Dimension(250, 30));
        att_panel2_2.setBorder(border1);
        att_panel2_2.setBackground(light_gray_color);
        JLabel attLabel = new JLabel(printList.get(0).getDate());
        attLabel.setFont(plainFont);
        att_panel2_2.add(attLabel); //정보 저장

        JPanel att_panel2_3 = new JPanel();
        att_panel2_3.setPreferredSize(new Dimension(250, 30));
        att_panel2_3.setBorder(border1);
        att_panel2_3.setBackground(light_gray_color);
        JLabel attLabel2 = new JLabel(printList.get(1).getDate());
        attLabel2.setFont(plainFont);
        att_panel2_3.add(attLabel2); //정보 저장

        att_panel2.add(att_panel2_1);
        att_panel2.add(att_panel2_2);
        att_panel2.add(att_panel2_3);

        //출석시간 칼럼 생성
        JPanel att_panel3 = new JPanel(new GridLayout(3, 1));
        att_panel3.setBounds(340, 0, 250, 90);
        att_panel3.setBorder(border1);

        JLabel att_time_label = new JLabel("출석시간");
        att_time_label.setFont(font2);
        att_time_label.setHorizontalAlignment(JLabel.CENTER);
        att_time_label.setVerticalAlignment(JLabel.CENTER);

        JPanel att_panel3_1 = new JPanel();
        att_panel3_1.setPreferredSize(new Dimension(250, 30));
        att_panel3_1.setBackground(light_yellow_color);
        att_panel3_1.setBorder(border1);
        att_panel3_1.add(att_time_label);

        JPanel att_panel3_2 = new JPanel();
        att_panel3_2.setPreferredSize(new Dimension(250, 30));
        att_panel3_2.setBorder(border1);
        att_panel3_2.setBackground(light_gray_color);
        JLabel attTimeLabel = new JLabel(printList.get(0).getAttendance());
        attTimeLabel.setFont(plainFont);
        att_panel3_2.add(attTimeLabel); //정보 저장

        JPanel att_panel3_3 = new JPanel();
        att_panel3_3.setPreferredSize(new Dimension(250, 30));
        att_panel3_3.setBorder(border1);
        att_panel3_3.setBackground(light_gray_color);
        JLabel attTimeLabel2 = new JLabel(printList.get(1).getAttendance());
        attTimeLabel2.setFont(plainFont);
        att_panel3_3.add(attTimeLabel2); //정보 저장

        att_panel3.add(att_panel3_1);
        att_panel3.add(att_panel3_2);
        att_panel3.add(att_panel3_3);


        JPanel att_panel4 = new JPanel(new GridLayout(6, 1));
        att_panel4.setBounds(590, 0, 350, 90);
        att_panel4.setBorder(border1);
        att_panel4.add(new JLabel(" # 출석시간 10분 전에 하루도 빠지지 않고"));
        att_panel4.add(new JLabel(" 출석하는 경우 학생들의 공부를 도와줄"));
        att_panel4.add(new JLabel(" 선물이 준비되어 있습니다."));
        att_panel4.add(new JLabel(""));
        att_panel4.add(new JLabel(" # 무단지각을 5회이상 하게 되면"));
        att_panel4.add(new JLabel(" 퇴원 처리 됩니다."));


        attendance.add(att_panel1);
        attendance.add(att_panel2);
        attendance.add(att_panel3);
        attendance.add(att_panel4);
        attendance.setBackground(Color.blue);


        //------------------------------------------------------------
        // <진도 란>
        JPanel progress = new JPanel();
        progress.setLayout(null);
        progress.setBounds(0, 90, 840, 125);
        progress.setBorder(border2);



        JPanel pro_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        pro_panel1.setBounds(0, 0, 90, 125);
        pro_panel1.setBorder(border1);
        pro_panel1.setBackground(light_red_color);

        JLabel pro_label = new JLabel("진도");
        pro_label.setFont(font1);
        pro_label.setHorizontalAlignment(JLabel.CENTER);
        pro_label.setVerticalAlignment(JLabel.CENTER);
        pro_panel1.add(pro_label, BorderLayout.CENTER);



        JPanel pro_panel2 = new JPanel();
        pro_panel2.setLayout(null);
        pro_panel2.setBounds(90, 0, 250, 125);
        pro_panel2.setBorder(border1);

        JLabel pro_date_label = new JLabel("날짜"); // att_date_label이랑 겹쳐주자
        pro_date_label.setFont(font2);
        pro_date_label.setHorizontalAlignment(JLabel.CENTER);
        pro_date_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pro_panel2_1 = new JPanel(new BorderLayout());
        pro_panel2_1.setBounds(0, 0, 250, 25);
        pro_panel2_1.setBorder(border1);
        pro_panel2_1.setBackground(light_red_color);
        pro_panel2_1.add(pro_date_label, BorderLayout.CENTER);

        JPanel pro_panel2_2 = new JPanel();
        pro_panel2_2.setBounds(0, 25, 250, 50);
        pro_panel2_2.setBorder(border1);
        pro_panel2_2.setBackground(light_gray_color);
        JLabel proDateLabel = new JLabel(printList.get(0).getDate());
        proDateLabel.setFont(plainFont);
        pro_panel2_2.add(proDateLabel); //정보 저장

        JPanel pro_panel2_3 = new JPanel();
        pro_panel2_3.setBounds(0, 75, 250, 50);
        pro_panel2_3.setBorder(border1);
        pro_panel2_3.setBackground(light_gray_color);
        JLabel proDateLabel2 = new JLabel(printList.get(1).getDate());
        proDateLabel2.setFont(plainFont);
        pro_panel2_3.add(proDateLabel2); //정보 저장

        pro_panel2.add(pro_panel2_1);
        pro_panel2.add(pro_panel2_2);
        pro_panel2.add(pro_panel2_3);



        JPanel pro_panel3 = new JPanel();
        pro_panel3.setLayout(null);
        pro_panel3.setBounds(340, 0, 250, 125);
        pro_panel3.setBorder(border1);

        JLabel pro_book_label = new JLabel("교재");
        pro_book_label.setFont(font2);
        pro_book_label.setHorizontalAlignment(JLabel.CENTER);
        pro_book_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pro_panel3_1 = new JPanel(new BorderLayout());
        pro_panel3_1.setBounds(0, 0, 250, 25);
        pro_panel3_1.setBorder(border1);
        pro_panel3_1.setBackground(light_red_color);
        pro_panel3_1.add(pro_book_label);


        JPanel pro_panel3_2 = new JPanel();
        pro_panel3_2.setBounds(0, 25, 250, 50);
        pro_panel3_2.setBorder(border1);
        pro_panel3_2.setBackground(light_gray_color);
        JLabel proBookLabel = new JLabel(printList.get(0).getTextbook());
        proBookLabel.setFont(plainFont);
        pro_panel3_2.add(proBookLabel); //정보 저장

        JPanel pro_panel3_3 = new JPanel();
        pro_panel3_3.setBounds(0, 75, 250, 50);
        pro_panel3_3.setBorder(border1);
        pro_panel3_3.setBackground(light_gray_color);
        JLabel proBookLabel2 = new JLabel(printList.get(1).getTextbook());
        proBookLabel2.setFont(plainFont);
        pro_panel3_3.add(proBookLabel2); //정보 저장

        pro_panel3.add(pro_panel3_1);
        pro_panel3.add(pro_panel3_2);
        pro_panel3.add(pro_panel3_3);




        JPanel pro_panel4 = new JPanel();
        pro_panel4.setLayout(null);
        pro_panel4.setBounds(590, 0, 250, 125);
        pro_panel4.setBorder(border1);

        JLabel pro_pro_label = new JLabel("진도");
        pro_pro_label.setFont(font2);
        pro_pro_label.setHorizontalAlignment(JLabel.CENTER);
        pro_pro_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pro_panel4_1 = new JPanel(new BorderLayout());
        pro_panel4_1.setBounds(0, 0, 250, 25);
        pro_panel4_1.setBorder(border1);
        pro_panel4_1.setBackground(light_red_color);
        pro_panel4_1.add(pro_pro_label);


        JPanel pro_panel4_2 = new JPanel();
        pro_panel4_2.setBounds(0, 25, 250, 50);
        pro_panel4_2.setBorder(border1);
        pro_panel4_2.setBackground(light_gray_color);
//        JTextPane tpName3 = new JTextPane();
//        tpName3.setEditable(false);
//        tpName3.setBackground(light_gray_color);
//        tpName3.setText(printList.get(0).getProgress());
//
//        //tpName의 styleDocument를 가져와 가운데 정렬 설정
//        StyledDocument doc3 = tpName3.getStyledDocument();
//        SimpleAttributeSet ce3 = new SimpleAttributeSet();
//        StyleConstants.setAlignment(ce3, StyleConstants.ALIGN_CENTER);
//        doc3.setParagraphAttributes(0, doc3.getLength(), ce3, false);
//        pro_panel4_2.add(tpName3);
        JLabel proLabel = new JLabel("<html>"+printList.get(0).getProgress().replace("\n","<br>")+"</html>");
        proLabel.setHorizontalAlignment(JLabel.LEFT);
        proLabel.setFont(plainFont);
        pro_panel4_2.add(proLabel);

        JPanel pro_panel4_3 = new JPanel();
        pro_panel4_3.setBounds(0, 75, 250, 50);
        pro_panel4_3.setBorder(border1);
        pro_panel4_3.setBackground(light_gray_color);
//        JTextPane tpName4 = new JTextPane();
//        tpName4.setEditable(false);
//        tpName4.setText(printList.get(1).getProgress());
//        tpName4.setBackground(light_gray_color);
//
//        //tpName의 styleDocument를 가져와 가운데 정렬 설정
//        StyledDocument doc4 = tpName4.getStyledDocument();
//        SimpleAttributeSet ce4 = new SimpleAttributeSet();
//        StyleConstants.setAlignment(ce4, StyleConstants.ALIGN_CENTER);
//        doc4.setParagraphAttributes(0, doc4.getLength(), ce4, false);
//        pro_panel4_3.add(tpName4);
        JLabel proLabel2 = new JLabel("<html>"+printList.get(1).getProgress().replace("\n","<br>")+"</html>");
        proLabel2.setHorizontalAlignment(JLabel.CENTER);
        proLabel2.setFont(plainFont);
        pro_panel4_3.add(proLabel2);


        pro_panel4.add(pro_panel4_1);
        pro_panel4.add(pro_panel4_2);
        pro_panel4.add(pro_panel4_3);



        progress.add(pro_panel1);
        progress.add(pro_panel2);
        progress.add(pro_panel3);
        progress.add(pro_panel4);


        //------------------------------------------------------------
        // <수업집중도 란>
        JPanel concentration = new JPanel();
        concentration.setLayout(null);
        concentration.setBounds(0, 215, 860, 120);
        concentration.setBorder(border2);


        JPanel con_panel1 = new JPanel(new GridLayout(2, 1)); // default : FlowLayout -> 맨 위에 가운데에 설정
        con_panel1.setBounds(0, 0, 90, 120);
        con_panel1.setBorder(border1);
        con_panel1.setBackground(light_blue_color);

        JLabel con_label1 = new JLabel("수업");
        JLabel con_label2 = new JLabel("집중도");
        con_label1.setFont(font1);
        con_label1.setHorizontalAlignment(JLabel.CENTER);
        con_label1.setVerticalAlignment(JLabel.CENTER);
        con_panel1.add(con_label1, BOTTOM_ALIGNMENT);
        con_label2.setFont(font1);
        con_label2.setHorizontalAlignment(JLabel.CENTER);
        con_label2.setVerticalAlignment(JLabel.CENTER);
        con_panel1.add(con_label2, TOP_ALIGNMENT);


        JPanel con_panel2 = new JPanel();
        con_panel2.setLayout(null);
        con_panel2.setBounds(90, 0, 160, 120);
        con_panel2.setBorder(border1);

        JLabel con_date1_label = new JLabel(printList.get(0).getDate());
        con_date1_label.setFont(font3);
        con_date1_label.setHorizontalAlignment(JLabel.CENTER);
        con_date1_label.setVerticalAlignment(JLabel.CENTER);

        JPanel con_panel2_1 = new JPanel(new BorderLayout());
        con_panel2_1.setBounds(0, 0, 160, 30);
        con_panel2_1.setBorder(border1);
        con_panel2_1.setBackground(light_blue_color);
        con_panel2_1.add(con_date1_label, BorderLayout.CENTER);

        JPanel con_panel2_2 = new JPanel();
        con_panel2_2.setBounds(0, 30, 160, 90);
        con_panel2_2.setBorder(border1);
        con_panel2_2.setBackground(light_gray_color);
        JLabel conLabel2_2  = new JLabel(printList.get(0).getConcentration());
        conLabel2_2.setFont(bigFont);
        con_panel2_2.add(conLabel2_2);


        con_panel2.add(con_panel2_1);
        con_panel2.add(con_panel2_2);




        JPanel con_panel3 = new JPanel();
        con_panel3.setLayout(null);
        con_panel3.setBounds(250, 0, 160, 120);
        con_panel3.setBorder(border1);

        JLabel con_date2_label = new JLabel(printList.get(1).getDate());
        con_date2_label.setFont(font3);
        con_date2_label.setHorizontalAlignment(JLabel.CENTER);
        con_date2_label.setVerticalAlignment(JLabel.CENTER);

        JPanel con_panel3_1 = new JPanel(new BorderLayout());
        con_panel3_1.setBounds(0, 0, 160, 30);
        con_panel3_1.setBorder(border1);
        con_panel3_1.setBackground(light_blue_color);
        con_panel3_1.add(con_date2_label, BorderLayout.CENTER);


        JPanel con_panel3_2 = new JPanel();
        con_panel3_2.setBounds(0, 30, 160, 90);
        con_panel3_2.setBorder(border1);
        con_panel3_2.setBackground(light_gray_color);
        JLabel conLabel3_2  = new JLabel(printList.get(1).getConcentration());
        conLabel3_2.setFont(bigFont);
        con_panel3_2.add(conLabel3_2);


        con_panel3.add(con_panel3_1);
        con_panel3.add(con_panel3_2);



        JPanel con_panel4 = new JPanel(new GridLayout(9, 1));
        con_panel4.setBounds(410, 0, 470, 120);
        con_panel4.setBorder(border1);
        con_panel4.add(new JLabel(""));
        con_panel4.add(new JLabel(" # 수업집중도 1/2/3/4/5"));
        con_panel4.add(new JLabel(""));
        con_panel4.add(new JLabel(" 1 - 수업시간 전반에 걸쳐 집중 못함"));
        con_panel4.add(new JLabel(" 2 - 수업시간 동안 집중한 시간보다 집중하지 못한 시간이 더 많음"));
        con_panel4.add(new JLabel(" 3 - 어느 정도 선생님과의 의사소통을 하며 교감함"));
        con_panel4.add(new JLabel(" 4 - 선생님과의 의사소통이 원활히 되고, 수업에 완전히 집중함"));
        con_panel4.add(new JLabel(" 5 - 완벽 집중도"));
        con_panel4.add(new JLabel(""));


        concentration.add(con_panel1);
        concentration.add(con_panel2);
        concentration.add(con_panel3);
        concentration.add(con_panel4);



        //------------------------------------------------------------
        //<과제수행도 란>
        JPanel homework_state = new JPanel();
        homework_state.setLayout(null);
        homework_state.setBounds(0, 335, 860, 120);
        homework_state.setBorder(border2);


        JPanel hws_panel1 = new JPanel(new GridLayout(2, 1)); // default : FlowLayout -> 맨 위에 가운데에 설정
        hws_panel1.setBounds(0, 0, 90, 120);
        hws_panel1.setBorder(border1);
        hws_panel1.setBackground(light_green_color);

        JLabel hws_label1 = new JLabel("과제");
        JLabel hws_label2 = new JLabel("수행도");
        hws_label1.setFont(font1);
        hws_label1.setHorizontalAlignment(JLabel.CENTER);
        hws_label1.setVerticalAlignment(JLabel.CENTER);
        hws_panel1.add(hws_label1, BOTTOM_ALIGNMENT);
        hws_label2.setFont(font1);
        hws_label2.setHorizontalAlignment(JLabel.CENTER);
        hws_label2.setVerticalAlignment(JLabel.CENTER);
        hws_panel1.add(hws_label2, TOP_ALIGNMENT);


        JPanel hws_panel2 = new JPanel();
        hws_panel2.setLayout(null);
        hws_panel2.setBounds(90, 0, 160, 120);
        hws_panel2.setBorder(border1);

        JLabel hws_date1_label = new JLabel(printList.get(0).getDate());
        hws_date1_label.setFont(font3);
        hws_date1_label.setHorizontalAlignment(JLabel.CENTER);
        hws_date1_label.setVerticalAlignment(JLabel.CENTER);

        JPanel hws_panel2_1 = new JPanel(new BorderLayout());
        hws_panel2_1.setBounds(0, 0, 160, 30);
        hws_panel2_1.setBorder(border1);
        hws_panel2_1.setBackground(light_green_color);
        hws_panel2_1.add(hws_date1_label, BorderLayout.CENTER);

        JPanel hws_panel2_2 = new JPanel();
        hws_panel2_2.setBounds(0, 30, 160, 90);
        hws_panel2_2.setBorder(border1);
        hws_panel2_2.setBackground(light_gray_color);
        JLabel hwsLabel2_2  = new JLabel(printList.get(0).getAssignment_performance());
        hwsLabel2_2.setFont(bigFont);
        hws_panel2_2.add(hwsLabel2_2);


        hws_panel2.add(hws_panel2_1);
        hws_panel2.add(hws_panel2_2);



        JPanel hws_panel3 = new JPanel();
        hws_panel3.setLayout(null);
        hws_panel3.setBounds(250, 0, 160, 120);
        hws_panel3.setBorder(border1);

        JLabel hws_date2_label = new JLabel(printList.get(1).getDate());
        hws_date2_label.setFont(font3);
        hws_date2_label.setHorizontalAlignment(JLabel.CENTER);
        hws_date2_label.setVerticalAlignment(JLabel.CENTER);

        JPanel hws_panel3_1 = new JPanel(new BorderLayout());
        hws_panel3_1.setBounds(0, 0, 160, 30);
        hws_panel3_1.setBorder(border1);
        hws_panel3_1.setBackground(light_green_color);
        hws_panel3_1.add(hws_date2_label, BorderLayout.CENTER);


        JPanel hws_panel3_2 = new JPanel();
        hws_panel3_2.setBounds(0, 30, 160, 90);
        hws_panel3_2.setBorder(border1);
        hws_panel3_2.setBackground(light_gray_color);
        JLabel hwsLabel3_2  = new JLabel(printList.get(1).getAssignment_performance());
        hwsLabel3_2.setFont(bigFont);
        hws_panel3_2.add(hwsLabel3_2);

        hws_panel3.add(hws_panel3_1);
        hws_panel3.add(hws_panel3_2);



        JPanel hws_panel4 = new JPanel(new GridLayout(8, 1));
        hws_panel4.setBounds(410, 0, 470, 120);
        hws_panel4.setBorder(border1);
        hws_panel4.add(new JLabel(""));
        hws_panel4.add(new JLabel(" # 과제 수행도 1/2/3/P"));
        hws_panel4.add(new JLabel(""));
        hws_panel4.add(new JLabel(" 1 - 숙제를 50%미만으로 이행해 옴"));
        hws_panel4.add(new JLabel(" 2 - 숙제 달성률 50~70%"));
        hws_panel4.add(new JLabel(" 3 - 숙제 달성률 70~90%"));
        hws_panel4.add(new JLabel(" P(Perfect) - 숙제 달성률 90~100% + 오답 완벽 처리"));
        hws_panel4.add(new JLabel(""));



        homework_state.add(hws_panel1);
        homework_state.add(hws_panel2);
        homework_state.add(hws_panel3);
        homework_state.add(hws_panel4);

        //------------------------------------------------------------
        // <플래너 수행도 란>
        JPanel planner = new JPanel();
        planner.setLayout(null);
        planner.setBounds(0, 455, 860, 120);
        planner.setBorder(border2);


        JPanel pln_panel1 = new JPanel(new GridLayout(2, 1)); // default : FlowLayout -> 맨 위에 가운데에 설정
        pln_panel1.setBounds(0, 0, 90, 120);
        pln_panel1.setBorder(border1);
        pln_panel1.setBackground(green_color);

        JLabel pln_label1 = new JLabel("플래너");
        JLabel pln_label2 = new JLabel("수행도");
        pln_label1.setFont(font1);
        pln_label1.setHorizontalAlignment(JLabel.CENTER);
        pln_label1.setVerticalAlignment(JLabel.CENTER);
        pln_panel1.add(pln_label1, BOTTOM_ALIGNMENT);
        pln_label2.setFont(font1);
        pln_label2.setHorizontalAlignment(JLabel.CENTER);
        pln_label2.setVerticalAlignment(JLabel.CENTER);
        pln_panel1.add(pln_label2, TOP_ALIGNMENT);


        JPanel pln_panel2 = new JPanel();
        pln_panel2.setLayout(null);
        pln_panel2.setBounds(90, 0, 160, 120);
        pln_panel2.setBorder(border1);

        JLabel pln_date1_label = new JLabel(printList.get(0).getDate());
        pln_date1_label.setFont(font3);
        pln_date1_label.setHorizontalAlignment(JLabel.CENTER);
        pln_date1_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pln_panel2_1 = new JPanel(new BorderLayout());
        pln_panel2_1.setBounds(0, 0, 160, 30);
        pln_panel2_1.setBorder(border1);
        pln_panel2_1.setBackground(green_color);
        pln_panel2_1.add(pln_date1_label, BorderLayout.CENTER);

        JPanel pln_panel2_2 = new JPanel();
        pln_panel2_2.setBounds(0, 30, 160, 90);
        pln_panel2_2.setBorder(border1);
        pln_panel2_2.setBackground(light_gray_color);
        JLabel plnLabel2_2  = new JLabel(printList.get(0).getPlanner_performance());
        plnLabel2_2.setFont(bigFont);
        pln_panel2_2.add(plnLabel2_2);


        pln_panel2.add(pln_panel2_1);
        pln_panel2.add(pln_panel2_2);



        JPanel pln_panel3 = new JPanel();
        pln_panel3.setLayout(null);
        pln_panel3.setBounds(250, 0, 160, 120);
        pln_panel3.setBorder(border1);

        JLabel pln_date2_label = new JLabel(printList.get(1).getDate());
        pln_date2_label.setFont(font3);
        pln_date2_label.setHorizontalAlignment(JLabel.CENTER);
        pln_date2_label.setVerticalAlignment(JLabel.CENTER);

        JPanel pln_panel3_1 = new JPanel(new BorderLayout());
        pln_panel3_1.setBounds(0, 0, 160, 30);
        pln_panel3_1.setBorder(border1);
        pln_panel3_1.setBackground(green_color);
        pln_panel3_1.add(pln_date2_label, BorderLayout.CENTER);


        JPanel pln_panel3_2 = new JPanel();
        pln_panel3_2.setBounds(0, 30, 160, 90);
        pln_panel3_2.setBorder(border1);
        pln_panel3_2.setBackground(light_gray_color);
        JLabel plnLabel3_2  = new JLabel(printList.get(1).getPlanner_performance());
        plnLabel3_2.setFont(bigFont);
        pln_panel3_2.add(plnLabel3_2);


        pln_panel3.add(pln_panel3_1);
        pln_panel3.add(pln_panel3_2);



        JPanel pln_panel4 = new JPanel(new GridLayout(9, 1));
        pln_panel4.setBounds(410, 0, 470, 120);
        pln_panel4.setBorder(border1);
        pln_panel4.add(new JLabel(""));
        pln_panel4.add(new JLabel(" # 플래너 수행도 A/B/C"));
        pln_panel4.add(new JLabel(""));
        pln_panel4.add(new JLabel(" A - 자세한 계획을 세우고 이행 정도를"));
        pln_panel4.add(new JLabel(" 정확히 기입함"));
        pln_panel4.add(new JLabel(" B - 계획을 세웠으나, 자세하지 않거나"));
        pln_panel4.add(new JLabel(" 이행정도를 기입하지 않음"));
        pln_panel4.add(new JLabel(" C - 플래너를 작성하지 않음"));
        pln_panel4.add(new JLabel(""));


        planner.add(pln_panel1);
        planner.add(pln_panel2);
        planner.add(pln_panel3);
        planner.add(pln_panel4);



        //------------------------------------------------------------
        // <과제 란>
        JPanel homework = new JPanel();
        homework.setLayout(null);
        homework.setBounds(0, 575, 860, 120);
        homework.setBorder(border2);



        JPanel hwk_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        hwk_panel1.setBounds(0, 0, 90, 120);
        hwk_panel1.setBorder(border1);
        hwk_panel1.setBackground(light_red_color);

        JLabel hwk_label1 = new JLabel("과제");


        hwk_label1.setFont(font1);
        hwk_label1.setHorizontalAlignment(JLabel.CENTER);
        hwk_label1.setVerticalAlignment(JLabel.CENTER);
        hwk_panel1.add(hwk_label1, BorderLayout.CENTER);


        JPanel hwk_panel2 = new JPanel();
        hwk_panel2.setLayout(null);
        hwk_panel2.setBounds(90, 0, 250, 120);
        hwk_panel2.setBorder(border1);

        JLabel hwk_date1_label = new JLabel(printList.get(0).getDate());
        hwk_date1_label.setFont(font3);
        hwk_date1_label.setHorizontalAlignment(JLabel.CENTER);
        hwk_date1_label.setVerticalAlignment(JLabel.CENTER);

        JPanel hwk_panel2_1 = new JPanel(new BorderLayout());
        hwk_panel2_1.setBounds(0, 0, 250, 30);
        hwk_panel2_1.setBorder(border1);
        hwk_panel2_1.setBackground(light_red_color);
        hwk_panel2_1.add(hwk_date1_label, BorderLayout.CENTER);

        JPanel hwk_panel2_2 = new JPanel();
        hwk_panel2_2.setBounds(0, 30, 250, 90);
        hwk_panel2_2.setBorder(border1);
        hwk_panel2_2.setBackground(light_gray_color);
//        JTextPane tpName = new JTextPane();
//        tpName.setEditable(false);
//        tpName.setText(printList.get(0).getAssignment_comment());
//        tpName.setBackground(light_gray_color);
//
//        //tpName의 styleDocument를 가져와 가운데 정렬 설정
//        StyledDocument doc = tpName.getStyledDocument();
//        SimpleAttributeSet ce = new SimpleAttributeSet();
//        StyleConstants.setAlignment(ce, StyleConstants.ALIGN_CENTER);
//        doc.setParagraphAttributes(0, doc.getLength(), ce, false);
//        hwk_panel2_2.add(tpName);
        JLabel hwkLabel = new JLabel("<html>"+printList.get(0).getAssignment_comment().replace("\n","<br>")+"</html>");
        hwkLabel.setHorizontalAlignment(JLabel.CENTER);
        hwkLabel.setFont(plainFont);
        hwk_panel2_2.add(hwkLabel);


        hwk_panel2.add(hwk_panel2_1);
        hwk_panel2.add(hwk_panel2_2);



        JPanel hwk_panel3 = new JPanel();
        hwk_panel3.setLayout(null);
        hwk_panel3.setBounds(340, 0, 250, 120);
        hwk_panel3.setBorder(border1);

        JLabel hwk_date2_label = new JLabel(printList.get(1).getDate());
        hwk_date2_label.setFont(font3);
        hwk_date2_label.setHorizontalAlignment(JLabel.CENTER);
        hwk_date2_label.setVerticalAlignment(JLabel.CENTER);

        JPanel hwk_panel3_1 = new JPanel(new BorderLayout());
        hwk_panel3_1.setBounds(0, 0, 250, 30);
        hwk_panel3_1.setBorder(border1);
        hwk_panel3_1.setBackground(light_red_color);
        hwk_panel3_1.add(hwk_date2_label, BorderLayout.CENTER);


        JPanel hwk_panel3_2 = new JPanel();
        hwk_panel3_2.setBounds(0, 30, 250, 90);
        hwk_panel3_2.setBorder(border1);
        hwk_panel3_2.setBackground(light_gray_color);
//        JTextPane tpName2 = new JTextPane();
//        tpName2.setEditable(false);
//        tpName2.setText("<html>"+printList.get(1).getAssignment_comment()+"sdfsfsdfsasfasdfasdfasdfsdafsd"+"</html>");
//        tpName2.setFont(plainFont);
//        tpName2.setBackground(light_gray_color);
//
//        //tpName의 styleDocument를 가져와 가운데 정렬 설정
//        StyledDocument doc2 = tpName2.getStyledDocument();
//        SimpleAttributeSet ce2 = new SimpleAttributeSet();
//        StyleConstants.setAlignment(ce2, StyleConstants.ALIGN_CENTER);
//        doc2.setParagraphAttributes(0, doc2.getLength(), ce2, false);
//        hwk_panel3_2.add(tpName2);
        JLabel hwkLabel2 = new JLabel("<html>"+printList.get(1).getAssignment_comment().replace("\n","<br>")+"</html>");
        hwkLabel2.setHorizontalAlignment(JLabel.CENTER);
        hwkLabel2.setFont(plainFont);
        hwk_panel3_2.add(hwkLabel2);

        hwk_panel3.add(hwk_panel3_1);
        hwk_panel3.add(hwk_panel3_2);



        JPanel hwk_panel4 = new JPanel(new GridLayout(9, 1));
        hwk_panel4.setBounds(590, 0, 250, 120);
        hwk_panel4.setBorder(border1);
        hwk_panel4.setBackground(light_gray_color);


        homework.add(hwk_panel1);
        homework.add(hwk_panel2);
        homework.add(hwk_panel3);
        homework.add(hwk_panel4);



        // <TEST 란>
        JPanel test = new JPanel();
        test.setLayout(null);
        test.setBounds(0, 695, 860, 120);
        test.setBorder(border2);


        JPanel tst_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        tst_panel1.setBounds(0, 0, 90, 120);
        tst_panel1.setBorder(border1);
        tst_panel1.setBackground(light_yellow_color);

        JLabel tst_label1 = new JLabel("TEST");
        tst_label1.setFont(font1);
        tst_label1.setHorizontalAlignment(JLabel.CENTER);
        tst_label1.setVerticalAlignment(JLabel.CENTER);
        tst_panel1.add(tst_label1, BorderLayout.CENTER);


        JPanel tst_panel2 = new JPanel();
        tst_panel2.setLayout(null);
        tst_panel2.setBounds(90, 0, 250, 120);
        tst_panel2.setBorder(border1);

        JLabel tst_date1_label = new JLabel(printList.get(0).getDate());
        tst_date1_label.setFont(font3);
        tst_date1_label.setHorizontalAlignment(JLabel.CENTER);
        tst_date1_label.setVerticalAlignment(JLabel.CENTER);

        JPanel tst_panel2_1 = new JPanel(new BorderLayout());
        tst_panel2_1.setBounds(0, 0, 250, 30);
        tst_panel2_1.setBorder(border1);
        tst_panel2_1.setBackground(light_yellow_color);
        tst_panel2_1.add(tst_date1_label, BorderLayout.CENTER);

        JPanel tst_panel2_2 = new JPanel();
        tst_panel2_2.setBounds(0, 30, 250, 90);
        tst_panel2_2.setBorder(border1);
        tst_panel2_2.setBackground(light_gray_color);
        JLabel testLabel2_2  = new JLabel(printList.get(0).getTest_score());
        testLabel2_2.setFont(bigFont);
        tst_panel2_2.add(testLabel2_2);

        tst_panel2.add(tst_panel2_1);
        tst_panel2.add(tst_panel2_2);



        JPanel tst_panel3 = new JPanel();
        tst_panel3.setLayout(null);
        tst_panel3.setBounds(340, 0, 250, 120);
        tst_panel3.setBorder(border1);

        JLabel tst_date2_label = new JLabel(printList.get(1).getDate());
        tst_date2_label.setFont(font3);
        tst_date2_label.setHorizontalAlignment(JLabel.CENTER);
        tst_date2_label.setVerticalAlignment(JLabel.CENTER);

        JPanel tst_panel3_1 = new JPanel(new BorderLayout());
        tst_panel3_1.setBounds(0, 0, 250, 30);
        tst_panel3_1.setBorder(border1);
        tst_panel3_1.setBackground(light_yellow_color);
        tst_panel3_1.add(tst_date2_label, BorderLayout.CENTER);


        JPanel tst_panel3_2 = new JPanel();
        tst_panel3_2.setBounds(0, 30, 250, 90);
        tst_panel3_2.setBorder(border1);
        tst_panel3_2.setBackground(light_gray_color);
        JLabel testLabel3_2  = new JLabel(printList.get(1).getTest_score());
        testLabel3_2.setFont(bigFont);
        tst_panel3_2.add(testLabel3_2);


        tst_panel3.add(tst_panel3_1);
        tst_panel3.add(tst_panel3_2);



        JPanel tst_panel4 = new JPanel(new GridLayout(9, 1));
        tst_panel4.setBounds(590, 0, 250, 120);
        tst_panel4.setBorder(border1);
        tst_panel4.setBackground(light_gray_color);


        test.add(tst_panel1);
        test.add(tst_panel2);
        test.add(tst_panel3);
        test.add(tst_panel4);


        // 패널 작업 끝
        // -------------------------------------------------------


        // <여백>
        JPanel west_panel = new JPanel();
        west_panel.setPreferredSize(new Dimension(20, this.getHeight()));
        west_panel.setBackground(Color.white);

        JPanel east_panel = new JPanel();
        east_panel.setPreferredSize(new Dimension(20, this.getHeight()));
        east_panel.setBackground(Color.white);



        // 각 패널들 추가

        c.add(title, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        c.add(east_panel, BorderLayout.EAST);
        c.add(west_panel, BorderLayout.WEST);
        center.add(attendance);
        center.add(progress);
        center.add(concentration);
        center.add(homework_state);
        center.add(planner);
        center.add(homework);
        center.add(test);

        setSize(880, 900);
        setVisible(true);
        dispose();


        // Create test file
        File test1 = new File(saveFilePath + sName + " " +userMonth+"월 "+userWeek+"주차 주간관리표"+".png");

        // Use the ImageIO API to write the bufferedImage to a temporary file
        try {
            BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = im.createGraphics();
            c.printAll(g2d);

            g2d.dispose();
            ImageIO.write(im, "png", test1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
