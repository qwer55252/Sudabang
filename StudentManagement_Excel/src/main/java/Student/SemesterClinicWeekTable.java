package Student;

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

public class SemesterClinicWeekTable extends JFrame {
    public SemesterClinicWeekTable(ArrayList<StudentClinicData> sList, String sName, String userMonth, String userWeek) {

        ArrayList<StudentClinicData> printList = new ArrayList<StudentClinicData>(); //출력할 학생의 정보들
        for (StudentClinicData studentClinicData : sList) { // 주간관리표 한 줄에 대하여
            if (studentClinicData.getName().equals(sName) && (studentClinicData.getMonth().equals(userMonth) && studentClinicData.getWeek().equals(userWeek))){
                printList.add(studentClinicData);
            }
        }

        // 정규반 클리닉은 일주일에 한 번 밖에 없음
        // printList(0)만 사용

        setTitle("정규반 클리닉 주간관리표 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // font
        Font title_font = new Font("Dialog",Font.PLAIN, 15);
        Font font1 = new Font("Dialog",Font.BOLD, 13);
        Font font2 = new Font("Dialog", Font.BOLD, 15);
        Font font3 = new Font("Dialog", Font.PLAIN, 15);
        Font plainFont = new Font("Dialog", Font.PLAIN, 13);
        Font bigFont = new Font("Dialog",Font.BOLD, 25);

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
        title.setPreferredSize(new Dimension(this.getWidth(), 40));
        title.setBackground(Color.white);

        JLabel title_label = new JLabel("<"+sName+" 학생 "+userMonth+"월 "+userWeek+"주차"+" 클리닉 보고서>");
        title_label.setFont(title_font);
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title.add(title_label);

        JPanel center = new JPanel();
        center.setBackground(Color.white);
        center.setLayout(null);


        // 패널 작업 시작
        JPanel attendance = new JPanel();
        attendance.setBounds(0, 0, 450, 100); // center의 0,0에 500X100 삽입
        attendance.setLayout(null);
        attendance.setBorder(border2);


        JPanel att_panel1 = new JPanel(new BorderLayout()); // default : FlowLayout -> 맨 위에 가운데에 설정
        att_panel1.setBounds(0, 0, 50, 100);
        att_panel1.setBackground(light_yellow_color);
        att_panel1.setBorder(border1);

        JLabel att_label = new JLabel("출결");
        att_label.setFont(font1);
        att_label.setHorizontalAlignment(JLabel.CENTER);
        att_label.setVerticalAlignment(JLabel.CENTER);
        att_panel1.add(att_label, BorderLayout.CENTER);


        JPanel att_panel2 = new JPanel();
        att_panel2.setLayout(null);
        att_panel2.setBounds(50, 0, 200, 100);
        att_panel2.setBorder(border1);

        JLabel att_panel2_1_label = new JLabel("날짜");
        att_panel2_1_label.setFont(font2);
        att_panel2_1_label.setHorizontalAlignment(JLabel.CENTER);
        att_panel2_1_label.setVerticalAlignment(JLabel.CENTER);

        JPanel att_panel2_1 = new JPanel(new BorderLayout());
        att_panel2_1.setBounds(0, 0, 200, 30);
        att_panel2_1.setBackground(light_yellow_color);
        att_panel2_1.setBorder(border1);
        att_panel2_1.add(att_panel2_1_label, BorderLayout.CENTER);

        JPanel att_panel2_2 = new JPanel();
        att_panel2_2.setBounds(0, 30, 200, 70);
        att_panel2_2.setBorder(border1);
        att_panel2_2.setBackground(light_gray_color);
        JLabel attLabel = new JLabel(printList.get(0).getDate());
        attLabel.setFont(plainFont);
        att_panel2_2.add(attLabel); //정보 저장

        att_panel2.add(att_panel2_1);
        att_panel2.add(att_panel2_2);


        JPanel att_panel3 = new JPanel();
        att_panel3.setLayout(null);
        att_panel3.setBounds(250, 0, 200, 100); // attendance의 250,0에 200X100 삽입
        att_panel3.setBorder(border1);

        JLabel att_time_label = new JLabel("출석시간");
        att_time_label.setFont(font2);
        att_time_label.setHorizontalAlignment(JLabel.CENTER);
        att_time_label.setVerticalAlignment(JLabel.CENTER);

        JPanel att_panel3_1 = new JPanel();
        att_panel3_1.setBounds(0, 0, 200, 30); // att_panel3의 0,0에 200X30 삽입
        att_panel3_1.setBackground(light_yellow_color);
        att_panel3_1.setBorder(border1);
        att_panel3_1.add(att_time_label);

        JPanel att_panel3_2 = new JPanel();
        att_panel3_2.setBounds(0, 30, 200, 70); //att_panel3의 0,30에 200X70 삽입
        att_panel3_2.setBorder(border1);
        att_panel3_2.setBackground(light_gray_color);
        JLabel attTimeLabel = new JLabel(printList.get(0).getAttendance());
        attTimeLabel.setFont(plainFont);
        att_panel3_2.add(attTimeLabel); //정보 저장


        att_panel3.add(att_panel3_1);
        att_panel3.add(att_panel3_2);

        attendance.add(att_panel1);
        attendance.add(att_panel2);
        attendance.add(att_panel3);


        // <클리닉 진행형황> 패널
        JPanel clinic_progress = new JPanel();
        clinic_progress.setLayout(null);
        clinic_progress.setBorder(border2);
        clinic_progress.setBounds(0, 110, 650, 250); // center의 0,110에 450X200 삽입


        JLabel clinic_progress_label = new JLabel("<html>&nbsp;클리닉<br>진행현황</html>");
        clinic_progress_label.setFont(font1);
        clinic_progress_label.setHorizontalAlignment(JLabel.CENTER);
        clinic_progress_label.setVerticalAlignment(JLabel.CENTER);

        JPanel cln_prg_panel1 = new JPanel(new BorderLayout());
        cln_prg_panel1.setBounds(0,0,50,250);
        cln_prg_panel1.setBackground(light_blue_color);
        cln_prg_panel1.setBorder(border1);
        cln_prg_panel1.add(clinic_progress_label, BorderLayout.CENTER);


        JPanel cln_prg_panel2 = new JPanel();
        cln_prg_panel2.setLayout(null);
        cln_prg_panel2.setBounds(50, 0, 200, 250); // cln_prg_panel의 50,0에 200X200 삽입
        cln_prg_panel2.setBorder(border1);

        JPanel cln_prg_panel2_1 = new JPanel(new BorderLayout());
        cln_prg_panel2_1.setBounds(0,0,200,30);
        cln_prg_panel2_1.setBackground(light_blue_color);
        cln_prg_panel2_1.setBorder(border1);

        JLabel cln_prg_panel2_1_label = new JLabel("단원명");
        cln_prg_panel2_1_label.setFont(font2);
        cln_prg_panel2_1_label.setHorizontalAlignment(JLabel.CENTER);
        cln_prg_panel2_1_label.setVerticalAlignment(JLabel.CENTER);

        cln_prg_panel2_1.add(cln_prg_panel2_1_label, BorderLayout.CENTER);


        JPanel cln_prg_panel2_2 = new JPanel(new BorderLayout());
        cln_prg_panel2_2.setBounds(0, 30, 200, 70);
        cln_prg_panel2_2.setBackground(light_gray_color);
        cln_prg_panel2_2.setBorder(border1);

        // 단원명 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
        JTextPane textPane_UnitName = new JTextPane();
        textPane_UnitName.setEditable(false);
        textPane_UnitName.setBackground(light_gray_color);
        textPane_UnitName.setText(printList.get(0).getUnitName());
        //tpName의 styleDocument를 가져와 가운데 정렬 설정
        StyledDocument doc_UnitName = textPane_UnitName.getStyledDocument();
        SimpleAttributeSet ce_UnitName = new SimpleAttributeSet();
        StyleConstants.setAlignment(ce_UnitName, StyleConstants.ALIGN_CENTER);
        doc_UnitName.setParagraphAttributes(0, doc_UnitName.getLength(), ce_UnitName, false);

        cln_prg_panel2_2.add(textPane_UnitName);


        JPanel cln_prg_panel2_3 = new JPanel(new BorderLayout());
        cln_prg_panel2_3.setBounds(0,100,200,30);
        cln_prg_panel2_3.setBackground(light_blue_color);
        cln_prg_panel2_3.setBorder(border1);

        JLabel cln_prg_panel2_3_label = new JLabel("성취단계");
        cln_prg_panel2_3_label.setFont(font2);
        cln_prg_panel2_3_label.setHorizontalAlignment(JLabel.CENTER);
        cln_prg_panel2_3_label.setVerticalAlignment(JLabel.CENTER);

        cln_prg_panel2_3.add(cln_prg_panel2_3_label, BorderLayout.CENTER);


        JPanel cln_prg_panel2_4 = new JPanel(new BorderLayout());
        cln_prg_panel2_4.setBounds(0, 130, 200, 150);
        cln_prg_panel2_4.setBorder(border1);
        cln_prg_panel2_4.setBackground(light_gray_color);

        JLabel cln_prg_panel2_4_label = new JLabel(printList.get(0).getAchivementLevel());
        cln_prg_panel2_4_label.setFont(bigFont);
        cln_prg_panel2_4_label.setHorizontalAlignment(JLabel.CENTER);
        cln_prg_panel2_4_label.setVerticalAlignment(JLabel.CENTER);
        cln_prg_panel2_4.add(cln_prg_panel2_4_label, BorderLayout.CENTER); //정보 저장


        cln_prg_panel2.add(cln_prg_panel2_1);
        cln_prg_panel2.add(cln_prg_panel2_2);
        cln_prg_panel2.add(cln_prg_panel2_3);
        cln_prg_panel2.add(cln_prg_panel2_4);


        JPanel cln_prg_panel3 = new JPanel();
        cln_prg_panel3.setLayout(null);
        cln_prg_panel3.setBounds(250, 0, 400, 250); // clinc_progress의 250,0에 400X200 삽입
        cln_prg_panel3.setBorder(border1);


        JPanel cln_prg_panel3_1 = new JPanel(new BorderLayout());
        cln_prg_panel3_1.setBounds(0,0,400,30);
        cln_prg_panel3_1.setBackground(light_blue_color);
        cln_prg_panel3_1.setBorder(border1);

        JLabel cln_prg_panel3_1_label = new JLabel("취약유형");
        cln_prg_panel3_1_label.setFont(font2);
        cln_prg_panel3_1_label.setHorizontalAlignment(JLabel.CENTER);
        cln_prg_panel3_1_label.setVerticalAlignment(JLabel.CENTER);

        cln_prg_panel3_1.add(cln_prg_panel3_1_label, BorderLayout.CENTER);


        JPanel cln_prg_panel3_2 = new JPanel(new BorderLayout());
        cln_prg_panel3_2.setBounds(0, 30, 400, 70);
        cln_prg_panel3_2.setBorder(border1);
        cln_prg_panel3_2.setBackground(light_gray_color);

        // 취약유형 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
        JTextPane textPane_WeakUnit = new JTextPane();
        textPane_WeakUnit.setEditable(false);
        textPane_WeakUnit.setBackground(light_gray_color);
        textPane_WeakUnit.setText(printList.get(0).getWeakUnit());

        //tpName의 styleDocument를 가져와 가운데 정렬 설정
        StyledDocument doc_WeakUnit = textPane_WeakUnit.getStyledDocument();
        SimpleAttributeSet ce_WeakUnit = new SimpleAttributeSet();
        StyleConstants.setAlignment(ce_WeakUnit, StyleConstants.ALIGN_CENTER);
        doc_WeakUnit.setParagraphAttributes(0, doc_WeakUnit.getLength(), ce_WeakUnit, false);

        cln_prg_panel3_2.add(textPane_WeakUnit);


        JPanel cln_prg_panel3_3 = new JPanel();
        cln_prg_panel3_3.setBounds(0, 100, 400, 150);
        cln_prg_panel3_3.setBorder(border1);
        JLabel cln_prg_panel3_3_label = new JLabel("<html>" +
                "* 단계별 성취사항<br>" +
                "(오답유형을 전부 해결하지 못할 시 추가 클리닉)<br><br>" +
                "1단계 : 클리닉 참여하지 않은 경우<br><br>" +
                "2단계 : 오답유형 해결<br><br>" +
                "3단계 : 오답유형 + 유사문항 해결<br><br></html>");
        cln_prg_panel3_3_label.setFont(plainFont);
        cln_prg_panel3_3_label.setHorizontalAlignment(JLabel.CENTER);
        cln_prg_panel3_3_label.setVerticalAlignment(JLabel.CENTER);

        cln_prg_panel3_3.add(cln_prg_panel3_3_label);


        cln_prg_panel3.add(cln_prg_panel3_1);
        cln_prg_panel3.add(cln_prg_panel3_2);
        cln_prg_panel3.add(cln_prg_panel3_3);

        clinic_progress.add(cln_prg_panel1);
        clinic_progress.add(cln_prg_panel2);
        clinic_progress.add(cln_prg_panel3);



        JPanel detail_course_panel = new JPanel();
        detail_course_panel.setBounds(0, 370, 650, 200);
        detail_course_panel.setLayout(null);
        detail_course_panel.setBorder(border2);


        JPanel detail_course_panel_1 = new JPanel(new BorderLayout());
        detail_course_panel_1.setBounds(0, 0, 650, 30);
        detail_course_panel_1.setBorder(border1);
        detail_course_panel_1.setBackground(light_red_color);

        JLabel detail_course_panel_1_label = new JLabel("특이사항 및 조치사항");
        detail_course_panel_1_label.setFont(font2);
        detail_course_panel_1_label.setHorizontalAlignment(JLabel.CENTER);
        detail_course_panel_1_label.setVerticalAlignment(JLabel.CENTER);

        detail_course_panel_1.add(detail_course_panel_1_label, BorderLayout.CENTER);


        JPanel detail_course_panel_2 = new JPanel(new BorderLayout());
        detail_course_panel_2.setBounds(0, 30, 650, 170);
        detail_course_panel_2.setBorder(border1);
        detail_course_panel_2.setBackground(light_gray_color);

        // 특이사항 및 조치사항 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
        JTextPane textPane_DetailCourse = new JTextPane();
        textPane_DetailCourse.setEditable(false);
        textPane_DetailCourse.setBackground(light_gray_color);
        textPane_DetailCourse.setText(printList.get(0).getDetailCourse());

        //tpName의 styleDocument를 가져와 가운데 정렬 설정
        StyledDocument doc_DetailCourse = textPane_DetailCourse.getStyledDocument();
        SimpleAttributeSet ce_DetailCourse = new SimpleAttributeSet();
        StyleConstants.setAlignment(ce_DetailCourse, StyleConstants.ALIGN_CENTER);
        doc_DetailCourse.setParagraphAttributes(0, doc_DetailCourse.getLength(), ce_DetailCourse, false);

        detail_course_panel_2.add(textPane_DetailCourse);

        detail_course_panel.add(detail_course_panel_1);
        detail_course_panel.add(detail_course_panel_2);


        // <여백>
        JPanel west_panel = new JPanel();
        west_panel.setPreferredSize(new Dimension(10, this.getHeight()));
        west_panel.setBackground(Color.white);

        JPanel east_panel = new JPanel();
        east_panel.setPreferredSize(new Dimension(10, this.getHeight()));
        east_panel.setBackground(Color.white);


        center.add(attendance);
        center.add(clinic_progress);
        center.add(detail_course_panel);
        c.add(title, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        c.add(west_panel, BorderLayout.WEST);
        c.add(east_panel, BorderLayout.EAST);



        setSize(670, 650);
        setVisible(true);
        dispose();

    }

}