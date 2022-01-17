package Student;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


// 기능 : filePath/fileName 엑셀파일을 읽어서 userMonth월 userweek주차 모든 학생의 주간관리표 캡처본 저장
public class ReadExcel {
    public static String loadFilePath;
    public static String loadFileName;
    public static String saveFilePath;
    public static String userWeek;
    public static String userMonth;
    public ArrayList<StudentData> studentList;
    public ArrayList<String> nameList;

    //Row가 비었는지 확인하는 메서드
    public static boolean isRowEmpty(Row row) {
        int c = row.getFirstCellNum()+2;
        Cell cell = row.getCell(c);
        if (cell != null && cell.getCellType() != CellType.BLANK) {
            return false;
        }
        return true;
    }


    public ReadExcel(String loadFilePath, String loadFileName, String saveFilePath, String userMonth, String userWeek) {
        this.loadFilePath = loadFilePath;
        this.loadFileName = loadFileName;
        this.saveFilePath = saveFilePath;
        this.userMonth = userMonth;
        this.userWeek = userWeek;


        //엑셀 데이터 읽어오기 -> studentList(학생 전체), weekNumList(주차 이름), nameList(학생 이름) 생성

        try {
            FileInputStream file = new FileInputStream(new File(loadFilePath, loadFileName));

            // 엑셀 파일로 Workbook instance를 생성한다.
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // workbook의 첫번째 sheet를 가저온다. -> 학습 관리표
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 만약 특정 이름의 시트를 찾는다면 workbook.getSheet("찾는 시트의 이름");
            // 만약 모든 시트를 순회하고 싶으면
            // for(Integer sheetNum : workbook.getNumberOfSheets()) {
            //      XSSFSheet sheet = workbook.getSheetAt(i);
            // }
            // 아니면 Iterator<Sheet> s = workbook.iterator() 를 사용해서 조회해도 좋다.

            // 모든 행(row)들을 조회한다. -> 첫 번째 행은 무시(학생 이름)
            int rowCnt = 0;
            Iterator<Row> rowIterator = sheet.iterator();
            ArrayList<StudentData> studentList = new ArrayList<>(); //전체 학생들 정보
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(rowCnt==0||rowCnt==1){
                    rowCnt++;
                    continue;
                }
                if(isRowEmpty(row)) break; //빈 행이 나오면 정지 -> 실수로 빈 행이 나오는 경우엔 어떻게 해야할까?

                // 각각의 행에 존재하는 모든 열(cell)을 순회한다.
                Iterator<Cell> cellIterator = row.cellIterator();
                StudentData s = new StudentData(); //학생 한 명의 정보를 저장할 객체 -> studentList에 넣어줄거임
                int cellCnt = 0; //11번째 셀 까지만
                while (cellIterator.hasNext() && cellCnt!=13) {
                    Cell cell = cellIterator.next();
                    if(cellCnt==0){ //순번 무시
                        cellCnt++;
                        continue;
                    }
                    String value = null; //저장할 값
                    // cell의 타입을 확인 하고, 값을 가져온다.
                    switch (cell.getCellType()) {
                        case NUMERIC -> {
                            if (DateUtil.isCellDateFormatted(cell)&&cellCnt==1) { //문제 해결: 날짜 포멧인 경우엔 예외로 처리해줘야함
                                Date date = cell.getDateCellValue();
                                value = new SimpleDateFormat("yyyy.MM.dd").format(date); //저장할 날짜 포맷
//                                System.out.print("<" + value + ">");
                            }
                            else if(cellCnt==3){ //출결
                                SimpleDateFormat time = new SimpleDateFormat("a h:mm"); //엑셀 서식에 따라 지정해줘야 함
                                value = time.format(cell.getDateCellValue());
//                                System.out.print("<"+value+">");
                            }
                            else {
                                value = Integer.toString((int)cell.getNumericCellValue()); //Numeric은 기본적으로 정수를 반환하지 않기 때문에 정수로 강제 형 변환 후 문자열로 파싱
//                                System.out.print("<" + (int) cell.getNumericCellValue() + ">"); //getNumericCellValue 메서드는 기본으로 double형 반환
                            }
                        }
                        case STRING -> {
                            value = cell.getStringCellValue();
//                            System.out.print("<" + cell.getStringCellValue() + ">");
                        }
                        case FORMULA -> { //셀에 수식이 있는 경우엔 FormulaEvaluator를 사용하면 결과값을 얻을 수 있다.
                            FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();
                            value = formulaEval.evaluate(cell).formatAsString();
                            value = value.substring(0,value.length()-2);
                        }
                    }
                    switch (cellCnt){ //switch문을 사용하면 if~if else보다는 좀 더 코드가 보기 좋아짐
                        case 1 -> s.setDate(value);
                        case 2 -> s.setName(value);
                        case 3 -> s.setAttendance(value);
                        case 4 -> s.setAssignment_performance(value);
                        case 5 -> s.setPlanner_performance(value);
                        case 6 -> s.setConcentration(value);
                        case 7 -> s.setTest_score(value);
                        case 8 -> s.setAssignment_comment(value);
                        case 9 -> s.setTextbook(value);
                        case 10 -> s.setProgress(value);
                        case 11 -> s.setMonth(value);
                        case 12 -> s.setWeek(value);
                    }
                    s.setWeek_num(s.getMonth()+"월 "+s.getWeek()+"주차");
                    cellCnt++;
                }
//                System.out.println();
                //studentList에 한 명씩 추가
                studentList.add(s);
                rowCnt++;
            }
            System.out.println(rowCnt+"개의 학생 데이터를 저장했습니다!");
            //출력할 학생들 이름 리스트를 만들어 준다.
            ArrayList<String> nameList;

            //이름 리스트
            nameList = new ArrayList<String>();
            for (StudentData s : studentList) {
                if ((s.getWeek().equals(userWeek)&&s.getMonth().equals(userMonth)) && !nameList.contains(s.getName())) {
                    nameList.add(s.getName());
                }
            }

            file.close();

            this.nameList = nameList;
            this.studentList = studentList;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
