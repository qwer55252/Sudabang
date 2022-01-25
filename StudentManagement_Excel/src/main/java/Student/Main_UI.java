package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main_UI extends JFrame {

    public static String userMonth;
    public static String userWeek;
    public static String loadedExcelPath;
    public static String loadedExcelName;
    public static String saveExcelPath;
    public static JLabel loadedExcelNameLabel = new JLabel();
    public static JLabel saveExcelPathLabel = new JLabel();

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
    public void setLoadedExcelPath(String loadedExcelPath) {
        this.loadedExcelPath = loadedExcelPath;
    }
    public String getLoadedExcelPath() {
        return this.loadedExcelPath;
    }
    public void setLoadedExcelName(String loadedExcelName) {
        this.loadedExcelName = loadedExcelName;
    }
    public String getLoadedExcelName() {
        return this.loadedExcelName;
    }
    public void setSaveExcelPath(String saveExcelPath) {
        this.saveExcelPath = saveExcelPath;
    }
    public String getSaveExcelPath() {
        return this.saveExcelPath;
    }


    //</editor-fold>


    public Main_UI() {
        setTitle("엑셀 캡처 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(4, 2));

        // 몇월 몇주차 선택
        String[] monthList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        JComboBox<String> monthes_combo = new JComboBox<String>(monthList);
        monthes_combo.setSelectedIndex(0);
        monthes_combo.addActionListener(new monthes_combo_ActionListener(this));

        String[] weekList = {"1","2","3","4","5","6"};
        JComboBox<String> weeks_combo = new JComboBox<String>(weekList);
        weeks_combo.setSelectedIndex(0);
        weeks_combo.addActionListener(new weeks_combo_ActionListener(this));

        JPanel select_month_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        select_month_panel.add(new JLabel("월을 선택하세요"));
        select_month_panel.add(monthes_combo);

        JPanel select_week_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        select_week_panel.add(new JLabel("주차를 선택하세요"));
        select_week_panel.add(weeks_combo);


        // 엑셀 불러오기 버튼
        JButton loadExcelButton = new JButton("엑셀 불러오기");
        loadExcelButton.addActionListener(new loadExcel_ActionListener(this));

        // 엑셀 불러오기 패널
        JPanel loadExcelPanel = new JPanel(new GridLayout(2, 1));
        loadExcelPanel.add(loadExcelButton);
        loadExcelPanel.add(loadedExcelNameLabel); // 불러온 엑셀 파일명 보여주는 Label


        // 저장 경로 선택 버튼
        JButton saveExcelButton = new JButton("저장 위치 설정");
        saveExcelButton.addActionListener(new saveExcel_ActionListener(this));

        // 저장 경로 설정 패널
        JPanel saveExcelPanel = new JPanel(new GridLayout(2, 1));
        saveExcelPanel.add(saveExcelButton);
        saveExcelPanel.add(saveExcelPathLabel); // 선택한 저장 경로 보여주는 Label


        // 캡처 버튼 4개, 버튼 액션리스너 생성
        JButton semester_weektable_capture_btn = new JButton("정규반 주간관리표 캡처하기");
        JButton semester_clinic_weektabel_catture_btn = new JButton("정규반 클리닉 주간관리표 캡처하기");
        JButton vacation_weektable_capture_btn = new JButton("집중반 주간관리표 캡처하기");
        JButton vacation_clinic_weektable_capture_btn = new JButton("집중반 클리닉 주간관리표 캡처하기");

        // filedialog의 parent를 지정해주기 위해 ActionListener에 this(main_ui)를 보내준다.
        semester_weektable_capture_btn.addActionListener(new semester_ActionListener(this));
        semester_clinic_weektabel_catture_btn.addActionListener(new semester_Clinic_ActionListener(this));
        vacation_weektable_capture_btn.addActionListener(new vacation_ActionListener(this));
        vacation_clinic_weektable_capture_btn.addActionListener(new vacation_Clinic_ActionListener(this));



        c.add(select_month_panel);
        c.add(select_week_panel);
        c.add(loadExcelPanel);
        c.add(saveExcelPanel);
        c.add(semester_weektable_capture_btn);
        c.add(semester_clinic_weektabel_catture_btn);
        c.add(vacation_weektable_capture_btn);
        c.add(vacation_clinic_weektable_capture_btn);


        setVisible(true);
        setSize(1000, 300);

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

    // 집중반 주간관리표 버튼 액션 리스너
    class vacation_ActionListener implements ActionListener {
        private Main_UI main_ui;
        public vacation_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String loadFilePath = main_ui.getLoadedExcelPath();
            String loadFileName = main_ui.getLoadedExcelName();
            String saveFilePath = main_ui.getSaveExcelPath();
            String userWeek = main_ui.getUserWeek();
            String userMonth = main_ui.getUserMonth();

            // 읽은 엑셀파일을 가지고 학생별 주간 관리표 캡쳐
            ReadSheet re = new ReadSheet(loadFilePath, loadFileName, saveFilePath, userMonth, userWeek); // filePath, fileName의 userWeek주차 주간관리표 캡처

            // userMonth, userWeek 주차 캡쳐 진행
            System.out.println("파일을 저장 중입니다...");
            for (String name : re.nameList) {
                new VacationWeekTable(re.studentList, name, userMonth, userWeek, saveFilePath);
            }
            System.out.println("모든 파일을 저장했습니다!");
        }
    }

    // 집중반 클리닉 주간관리표 액션 리스너
    class vacation_Clinic_ActionListener implements  ActionListener {
        private Main_UI main_ui;
        public vacation_Clinic_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String loadFilePath = main_ui.getLoadedExcelPath();
            String loadFileName = main_ui.getLoadedExcelName();
            String saveFilePath = main_ui.getSaveExcelPath();
            String userWeek = main_ui.getUserWeek();
            String userMonth = main_ui.getUserMonth();

            // 선택한 엑셀 파일의 학습관리표2 시트 읽기
            ReadClinicSheet re = new ReadClinicSheet(loadFilePath, loadFileName, saveFilePath, userMonth, userWeek); // filePath, fileName의 userWeek주차 주간관리표 캡처

            //userMonth, userWeek 주차 캡쳐 진행
            System.out.println("파일을 저장 중입니다...");
            for (String name : re.nameList){
                new VacationClinicWeekTable(re.studentList, name, userMonth, userWeek, saveFilePath);
            }
            System.out.println("모든 파일을 저장했습니다!");
        }
    }

    // 정규반 주간관리표 버튼 액션 리스너
    class semester_ActionListener implements ActionListener {
        private Main_UI main_ui;
        public semester_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String loadFilePath = main_ui.getLoadedExcelPath();
            String loadFileName = main_ui.getLoadedExcelName();
            String saveFilePath = main_ui.getSaveExcelPath();
            String userWeek = main_ui.getUserWeek();
            String userMonth = main_ui.getUserMonth();

            //읽은 엑셀파일로 데이터 만들기
            ReadSheet re = new ReadSheet(loadFilePath, loadFileName, saveFilePath, userMonth, userWeek); // filePath, fileName의 userWeek주차 주간관리표 캡처


            System.out.println(re.getNameList());


            //userMonth, userWeek 주차 캡쳐 진행
            System.out.println("파일을 저장 중입니다...");
            for (String name : re.nameList){
                new SemesterWeekTable(re.studentList, name, userMonth, userWeek, saveFilePath);
            }
            System.out.println("모든 파일을 저장했습니다!");
        }
    }

    // 정규반 클리닉 주간관리표 액션 리스너
    class semester_Clinic_ActionListener implements ActionListener {
        private Main_UI main_ui;
        public semester_Clinic_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String loadFilePath = main_ui.getLoadedExcelPath();
            String loadFileName = main_ui.getLoadedExcelName();
            String saveFilePath = main_ui.getSaveExcelPath();
            String userWeek = main_ui.getUserWeek();
            String userMonth = main_ui.getUserMonth();

            // 선택한 엑셀 파일의 학습관리표2 시트 읽기
            ReadClinicSheet re = new ReadClinicSheet(loadFilePath, loadFileName, saveFilePath, userMonth, userWeek); // filePath, fileName의 userWeek주차 주간관리표 캡처

            //userMonth, userWeek 주차 캡쳐 진행
            System.out.println("파일을 저장 중입니다...");
            for (String name : re.nameList){
                new SemesterClinicWeekTable(re.studentList, name, userMonth, userWeek, saveFilePath);
            }
            System.out.println("모든 파일을 저장했습니다!");
        }
    }


    // 엑셀 불러오기 버튼 액션 리스너
    class loadExcel_ActionListener implements ActionListener {
        private Main_UI main_ui;
        loadExcel_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("엑셀을 불러오는 중입니다...");

            FileDialog loadFileDialog = new FileDialog(main_ui, "엑셀 불러오기", FileDialog.LOAD);
            loadFileDialog.setVisible(true);
            loadFileDialog.setSize(300, 200);

            main_ui.setLoadedExcelPath(loadFileDialog.getDirectory());
            main_ui.setLoadedExcelName(loadFileDialog.getFile());
            main_ui.loadedExcelNameLabel.setText(loadFileDialog.getFile());

            System.out.println("엑셀을 성공적으로 불러왔습니다!");
        }
    }

    // 엑셀 저장 경로 버튼 액션 리스너
    class saveExcel_ActionListener implements ActionListener {
        private Main_UI main_ui;
        public saveExcel_ActionListener(Main_UI main_ui) {
            this.main_ui = main_ui;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("저장 경로를 설정 중입니다...");

            FileDialog saveFileDialog = new FileDialog(main_ui, "저장 위치 설정", FileDialog.SAVE);
            saveFileDialog.setVisible(true);
            saveFileDialog.setSize(300, 200);

            main_ui.setSaveExcelPath(saveFileDialog.getDirectory());
            main_ui.saveExcelPathLabel.setText(saveFileDialog.getDirectory());

            System.out.println("저장 경로를 성공적으로 설정했습니다!");
        }
    }

    public static void main(String[] args) {
        Main_UI main_ui = new Main_UI();
    }
}