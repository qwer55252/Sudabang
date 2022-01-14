package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main_UI extends JFrame {

    public static String filePath;
    public static String fileName;
    public static String userMonth;
    public static String userWeek;


    //<editor-fold desc="get, set 기능">
    public void setUserMonth(String userMonth) {
        this.userMonth = userMonth;
    }
    public void setUserWeek(String userWeek) {
        this.userWeek = userWeek;
    }
    public String getUserMonth() {
        return userMonth;
    }
    public String getUserWeek() {
        return userWeek;
    }
    //</editor-fold>


    public Main_UI() {
        setTitle("엑셀 캡처 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(3, 2));

        // 몇월 몇주차 선택
        String[] monthList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        JComboBox<String> monthes_combo = new JComboBox<String>(monthList);
        monthes_combo.setSelectedIndex(5);
        monthes_combo.addActionListener(new monthes_combo_ActionListener(this));

        String[] weekList = {"1","2","3","4","5","6"};
        JComboBox<String> weeks_combo = new JComboBox<String>(weekList);
        weeks_combo.addActionListener(new weeks_combo_ActionListener(this));


        // 캡처 버튼 4개, 버튼 액션리스너 생성
        JButton semester_weektable_capture_btn = new JButton("정규반 주간관리표 캡처하기");
        JButton semester_clinic_weektabel_catture_btn = new JButton("정규반 클리닉 주간관리표 캡처하기");
        JButton vacation_weektable_capture_btn = new JButton("집중반 주간관리표 캡처하기");
        JButton vacation_clinic_weektable_capture_btn = new JButton("집중반 클리닉 주간관리표 캡처하기");

        // filedialog의 parent를 지정해주기 위해 ActionListener에 this(main_ui)를 보내준다.
        semester_weektable_capture_btn.addActionListener(new semester_ActionListener(this));
        //semester_clinic_weektabel_catture_btn.addActionListener(new semester_clinic_ActionListener(this));
        vacation_weektable_capture_btn.addActionListener(new vacation_ActionListener(this));
        //vacation_clinic_weektable_capture_btn.addActionListener(new vacation_clinic_ActionListener(this));



        c.add(monthes_combo);
        c.add(weeks_combo);
        c.add(semester_weektable_capture_btn);
        c.add(semester_clinic_weektabel_catture_btn);
        c.add(vacation_weektable_capture_btn);
        c.add(vacation_clinic_weektable_capture_btn);


        setVisible(true);
        setSize(600, 300);

    }

    // monthes_combo 액션 리스너
    class monthes_combo_ActionListener implements ActionListener {
        private Main_UI main_ui;
        monthes_combo_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> monthes_combo = (JComboBox<String>)e.getSource();
            main_ui.setUserMonth((String)monthes_combo.getSelectedItem());
        }
    }

    // weeks_combo 액션 리스너
    class weeks_combo_ActionListener implements ActionListener {
        private Main_UI main_ui;
        weeks_combo_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> weeks_combo = (JComboBox<String>)e.getSource();
            main_ui.setUserWeek((String)weeks_combo.getSelectedItem());
        }
    }

    // 집중반 버튼 액션 리스너
    class vacation_ActionListener implements ActionListener {
        private Main_UI main_ui;
        public vacation_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog loadFileDialog = new FileDialog(main_ui, "엑셀 불러오기", FileDialog.LOAD);
            loadFileDialog.setVisible(true);
            loadFileDialog.setSize(300, 200);

            FileDialog saveFileDialog = new FileDialog(main_ui, "저장 위치 설정", FileDialog.SAVE);
            saveFileDialog.setVisible(true);
            loadFileDialog.setSize(300, 200);


            //선택한 엑셀 파일의 Directory와 Filename를 받아서 읽어보자
            System.out.println(loadFileDialog.getDirectory());
            System.out.println(loadFileDialog.getFile());
            System.out.println(saveFileDialog.getDirectory());
            System.out.println(saveFileDialog.getFile());


//            new ReadAndPrintExcel(filedialog.getDirectory(), filedialog.getFile());
//            // 엑셀 읽기 성공

            String loadFilePath = loadFileDialog.getDirectory();
            String loadFileName = loadFileDialog.getFile();
            String saveFilePath = saveFileDialog.getDirectory();
            String userWeek = main_ui.getUserWeek();
            String userMonth = main_ui.getUserMonth();

            new SaveVacationWeektable(loadFilePath, loadFileName, saveFilePath, userMonth, userWeek); // filePath, fileName의 userWeek주차 주간관리표 캡처


        }
    }

    // 정규반 버튼 액션 리스너
    class semester_ActionListener implements ActionListener {
        private Main_UI main_ui;
        public semester_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog filedialog = new FileDialog(main_ui, "엑셀 불러오기", FileDialog.LOAD);
            filedialog.setVisible(true);
            filedialog.setSize(300, 200);

            //선택한 엑셀 파일의 Directory와 Filename를 받아서 읽어보자
            System.out.println(filedialog.getDirectory());
            System.out.println(filedialog.getFile());

//            new ReadAndPrintExcel(filedialog.getDirectory(), filedialog.getFile());
            // 엑셀 읽기 성공
        }
    }



    public static void main(String[] args) {
        Main_UI main_ui = new Main_UI();

    }
}
