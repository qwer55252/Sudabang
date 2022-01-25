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


public class VacationClinicWeekTable extends JFrame {
    public static String saveFilePath;
    VacationClinicWeekTable(ArrayList<StudentClinicData> sList, String sName, String userMonth, String userWeek, String saveFilePath) {
        this.saveFilePath = saveFilePath;

        //int k = 0; //k는 몇 번째 학생인지 -> 테스트용
        ArrayList<StudentClinicData> printList = new ArrayList<StudentClinicData>(); //출력할 학생의 정보들
        for (StudentClinicData studentClinicData : sList) { // 주간관리표 한 줄에 대하여
            if (studentClinicData.getName().equals(sName) && (studentClinicData.getMonth().equals(userMonth) && studentClinicData.getWeek().equals(userWeek))){
                printList.add(studentClinicData);
            }
            //System.out.println("studentClinicData("+k+") : "+studentClinicData);
            //k++;
        }
        int printSize = printList.size();
        //부족한 수만큼 '-'데이터로 채운 학생 인스턴스 추가
        for(int i=0;i<5-printSize;i++){
            StudentClinicData nullData = new StudentClinicData();
            nullData.setDate("-");
            nullData.setAttendance("-");
            nullData.setName(sName);
            nullData.setUnitName("-");
            nullData.setAchivementLevel("-");
            nullData.setWeakUnit("-");
            nullData.setDetailCourse("-");
            nullData.setMonth("-");
            nullData.setWeek("-");
            nullData.setMonth_weekNum("-");
            nullData.setCount("-");
            nullData.setName_month_weekNum("-");
            nullData.setName_month_weekNum_count("-");
            printList.add(nullData);
        }



        setTitle("클리닉 주간관리표 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // font
        Font title_font = new Font("Dialog",Font.PLAIN, 15);
        Font font1 = new Font("Dialog",Font.BOLD, 13);
        Font font2 = new Font("Dialog", Font.BOLD, 15);
        Font font3 = new Font("Dialog", Font.PLAIN, 15);
        Font plainFont = new Font("Dialog", Font.PLAIN, 11);
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
        center.setLayout(null);





        //------------------------------------------------------------
        // 위 패널
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 900, 280); // center의 0,0 위치에 900X280 삽입
        topPanel.setBorder(border2);

        // <날짜 패널>
        JPanel topDatePanel = new JPanel();
        topDatePanel.setLayout(null);
        topDatePanel.setBounds(0, 0, 100, 280); // topPanel 0, 0 위치에 100X280 삽입
        topDatePanel.setBorder(border1);

        JLabel topDateLabel = new JLabel("날짜"); // att_date_label이랑 겹쳐주자
        topDateLabel.setFont(font2);
        topDateLabel.setHorizontalAlignment(JLabel.CENTER);
        topDateLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel topDatePanel_1 = new JPanel(new BorderLayout());
        topDatePanel_1.setBounds(0, 0, 100, 30); // topPanel_1의 (0, 0)위치에 100X30 삽입
        topDatePanel_1.setBorder(border1);
        topDatePanel_1.setBackground(light_yellow_color);
        topDatePanel_1.add(topDateLabel, BorderLayout.CENTER);


        JPanel topDatePanel_grid = new JPanel(new GridLayout(5, 1)); // 날짜 밑 5개의 grid칸
        topDatePanel_grid.setBounds(0, 30, 100, 250); // topPanel_1의 0, 30위치에 100X250 삽입
        topDatePanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel topDatePanel_grid_elem = new JPanel(new BorderLayout());
            topDatePanel_grid_elem.setBorder(border1);
            topDatePanel_grid_elem.setBackground(light_gray_color);

            JLabel i_th_dateLabel = new JLabel(printList.get(i).getDate());
            i_th_dateLabel.setFont(plainFont);
            i_th_dateLabel.setHorizontalAlignment(JLabel.CENTER);
            i_th_dateLabel.setVerticalAlignment(JLabel.CENTER);
            topDatePanel_grid_elem.add(i_th_dateLabel); //정보 저장


            topDatePanel_grid.add(topDatePanel_grid_elem, BorderLayout.CENTER);
        }

        topDatePanel.add(topDatePanel_1);
        topDatePanel.add(topDatePanel_grid);


        // <출석시간 패널>
        JPanel topAttPanel = new JPanel();
        topAttPanel.setLayout(null);
        topAttPanel.setBounds(100, 0, 100, 280); // center의 (100, 0)위치에 100X280 삽입
        topAttPanel.setBorder(border1);

        JLabel topAttLabel = new JLabel("출석시간");
        topAttLabel.setFont(font2);
        topAttLabel.setHorizontalAlignment(JLabel.CENTER);
        topAttLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel topAttPanel_1 = new JPanel(new BorderLayout());
        topAttPanel_1.setBounds(0, 0, 100, 30);
        topAttPanel_1.setBorder(border1);
        topAttPanel_1.setBackground(light_yellow_color);
        topAttPanel_1.add(topAttLabel);

        JPanel topAttPanel_grid = new JPanel(new GridLayout(5, 1)); // 출석시간 밑 5개의 grid칸
        topAttPanel_grid.setBounds(0, 30, 100, 250); // topAttPanel의 0, 30위치에 100X250 삽입
        topAttPanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel topAttPanel_grid_elem = new JPanel(new BorderLayout());
            topAttPanel_grid_elem.setBorder(border1);
            topAttPanel_grid_elem.setBackground(light_gray_color);

            JLabel i_th_attLabel = new JLabel(printList.get(i).getAttendance());
            i_th_attLabel.setFont(plainFont);
            i_th_attLabel.setHorizontalAlignment(JLabel.CENTER);
            i_th_attLabel.setVerticalAlignment(JLabel.CENTER);
            topAttPanel_grid_elem.add(i_th_attLabel, BorderLayout.CENTER); //정보 저장

            topAttPanel_grid.add(topAttPanel_grid_elem);
        }

        topAttPanel.add(topAttPanel_1);
        topAttPanel.add(topAttPanel_grid);



        // <단원명 패널>
        JPanel topUnitPanel = new JPanel();
        topUnitPanel.setLayout(null);
        topUnitPanel.setBounds(200, 0, 150, 280); // center의 (200, 0)위치에 100X280 삽입
        topUnitPanel.setBorder(border1);

        JLabel topUnitLabel = new JLabel("단원명");
        topUnitLabel.setFont(font2);
        topUnitLabel.setHorizontalAlignment(JLabel.CENTER);
        topUnitLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel topUnitPanel_1 = new JPanel(new BorderLayout());
        topUnitPanel_1.setBounds(0, 0, 150, 30);
        topUnitPanel_1.setBorder(border1);
        topUnitPanel_1.setBackground(light_blue_color);
        topUnitPanel_1.add(topUnitLabel);

        JPanel topUnitPanel_grid = new JPanel(new GridLayout(5, 1)); // 출석시간 밑 5개의 grid칸
        topUnitPanel_grid.setBounds(0, 30, 150, 250); // topUnitPanel의 0, 30위치에 100X250 삽입
        topUnitPanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel topUnitPanel_grid_elem = new JPanel(new BorderLayout());
            topUnitPanel_grid_elem.setBorder(border1);
            topUnitPanel_grid_elem.setBackground(light_gray_color);

            // 특이사항 및 조치사항 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
            JTextPane textPane_UnitName = new JTextPane();
            textPane_UnitName.setEditable(false);
            textPane_UnitName.setBackground(light_gray_color);
            textPane_UnitName.setText(printList.get(i).getUnitName());

            //tpName의 styleDocument를 가져와 가운데 정렬 설정
            StyledDocument doc = textPane_UnitName.getStyledDocument();
            SimpleAttributeSet ce = new SimpleAttributeSet();
            StyleConstants.setAlignment(ce, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), ce, false);

            topUnitPanel_grid_elem.add(textPane_UnitName);
            topUnitPanel_grid.add(topUnitPanel_grid_elem);
        }

        topUnitPanel.add(topUnitPanel_1);
        topUnitPanel.add(topUnitPanel_grid);


        // <취약유형 패널>
        JPanel topWeakPanel = new JPanel();
        topWeakPanel.setLayout(null);
        topWeakPanel.setBounds(350, 0, 450, 280); // center의 (300, 0)위치에 500X280 삽입
        topWeakPanel.setBorder(border1);

        JLabel topWeakLabel = new JLabel("취약유형");
        topWeakLabel.setFont(font2);
        topWeakLabel.setHorizontalAlignment(JLabel.CENTER);
        topWeakLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel topWeakPanel_1 = new JPanel(new BorderLayout());
        topWeakPanel_1.setBounds(0, 0, 450, 30);
        topWeakPanel_1.setBorder(border1);
        topWeakPanel_1.setBackground(light_blue_color);
        topWeakPanel_1.add(topWeakLabel);

        JPanel topWeakPanel_grid = new JPanel(new GridLayout(5, 1)); // 출석시간 밑 5개의 grid칸
        topWeakPanel_grid.setBounds(0, 30, 450, 250); // topAttPanel의 0, 30위치에 100X250 삽입
        topWeakPanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel topWeakPanel_grid_elem = new JPanel(new BorderLayout());
            topWeakPanel_grid_elem.setBorder(border1);
            topWeakPanel_grid_elem.setBackground(light_gray_color);

            // 특이사항 및 조치사항 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
            JTextPane textPane_WeakUnit = new JTextPane();
            textPane_WeakUnit.setEditable(false);
            textPane_WeakUnit.setBackground(light_gray_color);
            textPane_WeakUnit.setText(printList.get(i).getWeakUnit());

            //tpName의 styleDocument를 가져와 가운데 정렬 설정
            StyledDocument doc = textPane_WeakUnit.getStyledDocument();
            SimpleAttributeSet ce = new SimpleAttributeSet();
            StyleConstants.setAlignment(ce, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), ce, false);

            topWeakPanel_grid_elem.add(textPane_WeakUnit);
            topWeakPanel_grid.add(topWeakPanel_grid_elem);
        }

        topWeakPanel.add(topWeakPanel_1);
        topWeakPanel.add(topWeakPanel_grid);


        // <성취단계 패널>
        JPanel topAchPanel = new JPanel();
        topAchPanel.setLayout(null);
        topAchPanel.setBounds(800, 0, 100, 280); // center의 (800, 0)위치에 100X280 삽입
        topAchPanel.setBorder(border1);

        JLabel topAchLabel = new JLabel("성취단계");
        topAchLabel.setFont(font2);
        topAchLabel.setHorizontalAlignment(JLabel.CENTER);
        topAchLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel topAchPanel_1 = new JPanel(new BorderLayout());
        topAchPanel_1.setBounds(0, 0, 100, 30);
        topAchPanel_1.setBorder(border1);
        topAchPanel_1.setBackground(light_blue_color);
        topAchPanel_1.add(topAchLabel);

        JPanel topAchPanel_grid = new JPanel(new GridLayout(5, 1)); // 출석시간 밑 5개의 grid칸
        topAchPanel_grid.setBounds(0, 30, 100, 250); // topAttPanel의 0, 30위치에 100X250 삽입
        topAchPanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel topAchPanel_grid_elem = new JPanel(new BorderLayout());
            topAchPanel_grid_elem.setBorder(border1);
            topAchPanel_grid_elem.setBackground(light_gray_color);

            JLabel i_th_achLabel = new JLabel(printList.get(i).getAchivementLevel());
            i_th_achLabel.setFont(bigFont);
            i_th_achLabel.setHorizontalAlignment(JLabel.CENTER);
            i_th_achLabel.setVerticalAlignment(JLabel.CENTER);
            topAchPanel_grid_elem.add(i_th_achLabel, BorderLayout.CENTER); //정보 저장

            topAchPanel_grid.add(topAchPanel_grid_elem);
        }

        topAchPanel.add(topAchPanel_1);
        topAchPanel.add(topAchPanel_grid);



        topPanel.add(topDatePanel);
        topPanel.add(topAttPanel);
        topPanel.add(topUnitPanel);
        topPanel.add(topWeakPanel);
        topPanel.add(topAchPanel);
        // <topPanel 작업 끝>


        // <bottomPanel 작업 시작>
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(0, 280, 900, 280); // center의 0,280 위치에 900X280 삽입
        bottomPanel.setBorder(border2);



        JPanel bottomDatePanel = new JPanel();
        bottomDatePanel.setLayout(null);
        bottomDatePanel.setBounds(0, 0, 100, 280); // bottomPanel 0, 0 위치에 100X280 삽입
        bottomDatePanel.setBorder(border1);

        JLabel bottomDateLabel = new JLabel("날짜"); // att_date_label이랑 겹쳐주자
        bottomDateLabel.setFont(font2);
        bottomDateLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomDateLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel bottomDatePanel_1 = new JPanel(new BorderLayout());
        bottomDatePanel_1.setBounds(0, 0, 100, 30); // bottomPanel_1의 (0, 0)위치에 100X30 삽입
        bottomDatePanel_1.setBorder(border1);
        bottomDatePanel_1.setBackground(light_red_color);
        bottomDatePanel_1.add(bottomDateLabel, BorderLayout.CENTER);



        JPanel bottomDatePanel_grid = new JPanel(new GridLayout(5, 1)); // 날짜 밑 5개의 grid칸
        bottomDatePanel_grid.setBounds(0, 30, 100, 250); // bottomPanel_1의 0, 30위치에 100X250 삽입
        bottomDatePanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel bottomDatePanel_grid_elem = new JPanel(new BorderLayout());
            bottomDatePanel_grid_elem.setBorder(border1);
            bottomDatePanel_grid_elem.setBackground(light_gray_color);

            JLabel i_th_dateLabel = new JLabel(printList.get(i).getDate());
            i_th_dateLabel.setFont(plainFont);
            i_th_dateLabel.setHorizontalAlignment(JLabel.CENTER);
            i_th_dateLabel.setVerticalAlignment(JLabel.CENTER);
            bottomDatePanel_grid_elem.add(i_th_dateLabel, BorderLayout.CENTER); //정보 저장

            bottomDatePanel_grid.add(bottomDatePanel_grid_elem);
        }

        bottomDatePanel.add(bottomDatePanel_1);
        bottomDatePanel.add(bottomDatePanel_grid);



        // <특이사항 및 조치사항 패널>
        JPanel bottomDCPanel = new JPanel(); // DC - Detail Course
        bottomDCPanel.setLayout(null);
        bottomDCPanel.setBounds(100, 0, 500, 280); // bottom의 (100, 0)위치에 500X280 삽입
        bottomDCPanel.setBorder(border1);

        JLabel bottomDCLabel = new JLabel("특이사항 및 조치사항");
        bottomDCLabel.setFont(font2);
        bottomDCLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomDCLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel bottomDCPanel_1 = new JPanel(new BorderLayout());
        bottomDCPanel_1.setBounds(0, 0, 500, 30);
        bottomDCPanel_1.setBorder(border1);
        bottomDCPanel_1.setBackground(light_red_color);
        bottomDCPanel_1.add(bottomDCLabel);

        JPanel bottomDCPanel_grid = new JPanel(new GridLayout(5, 1)); // 출석시간 밑 5개의 grid칸
        bottomDCPanel_grid.setBounds(0, 30, 500, 250); // bottomDCPanel의 0, 30위치에 500X250 삽입
        bottomDCPanel_grid.setBorder(border1);

        for(int i=0;i<5;i++){
            JPanel bottomDCPanel_grid_elem = new JPanel(new BorderLayout());
            bottomDCPanel_grid_elem.setBorder(border1);
            bottomDCPanel_grid_elem.setBackground(light_gray_color);

            // 특이사항 및 조치사항 란이 작동기 때문에 자동 줄바꿈 처리가 되는 JTextPane사용
            JTextPane textpane_DetailCourse = new JTextPane();
            textpane_DetailCourse.setEditable(false);
            textpane_DetailCourse.setBackground(light_gray_color);
            textpane_DetailCourse.setText(printList.get(i).getDetailCourse());

            //tpName의 styleDocument를 가져와 가운데 정렬 설정
            StyledDocument doc = textpane_DetailCourse.getStyledDocument();
            SimpleAttributeSet ce = new SimpleAttributeSet();
            StyleConstants.setAlignment(ce, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), ce, false);

            bottomDCPanel_grid_elem.add(textpane_DetailCourse);
            bottomDCPanel_grid.add(bottomDCPanel_grid_elem);

        }

        bottomDCPanel.add(bottomDCPanel_1);
        bottomDCPanel.add(bottomDCPanel_grid);


        // <설명 패널>
        JPanel bottom_third_Panel = new JPanel();
        bottom_third_Panel.setBounds(600, 0, 300, 280);
        bottom_third_Panel.setBorder(border1);
        JLabel bottom_third_Panel_label = new JLabel("<html><br><br>" +
                "* 단계별 성취사항<br>" +
                "(오답유형을 전부 해결하지 못할 시 추가 클리닉)<br><br>" +
                "1단계 : 클리닉 참여하지 않은 경우<br><br>" +
                "2단계 : 오답유형 해결<br><br>" +
                "3단계 : 오답유형 + 유사문항 해결<br><br></html>");
        bottom_third_Panel_label.setFont(font1);
        bottom_third_Panel_label.setHorizontalAlignment(JLabel.CENTER);
        bottom_third_Panel_label.setVerticalAlignment(JLabel.CENTER);

        bottom_third_Panel.add(bottom_third_Panel_label);



        bottomPanel.add(bottomDatePanel);
        bottomPanel.add(bottomDCPanel);
        bottomPanel.add(bottom_third_Panel);
        // <bottomPanel 작업 끝>


        // <여백>
        JPanel west_panel = new JPanel();
        west_panel.setPreferredSize(new Dimension(20, 560));

        west_panel.setBackground(Color.white);

        JPanel east_panel = new JPanel();
        east_panel.setPreferredSize(new Dimension(20, 560));
        east_panel.setBackground(Color.white);


        // 각 패널들 추가
        center.add(topPanel);
        center.add(bottomPanel);
        c.add(title, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        c.add(east_panel, BorderLayout.EAST);
        c.add(west_panel, BorderLayout.WEST);




        setSize(940, 620);
        setVisible(true);
        //dispose();




        // Create test file
        File saveFile = new File(saveFilePath + sName + " " +userMonth+"월 "+userWeek+"주차 클리닉 주간관리표.png");


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