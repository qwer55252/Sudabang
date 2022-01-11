package Student;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Main {
    public static String filePath = "C:\\Users\\home\\Desktop";
    public static String fileNm = "고1 B 수학집중반 관리표.xlsx";
    public static void main(String[] args) {

        try {
            FileInputStream file = new FileInputStream(new File(filePath, fileNm));

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
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(rowCnt==0){
                    rowCnt++;
                    continue;
                }
                if(rowCnt==5) break; //열 이름과 4명만 출력
                // 각각의 행에 존재하는 모든 열(cell)을 순회한다.
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellCnt = 0; //11번째 셀 까지만
                while (cellIterator.hasNext() && cellCnt!=11) {
                    Cell cell = cellIterator.next();
                    if(cellCnt==0){ //순번 무시
                        cellCnt++;
                        continue;
                    }
                    DataFormatter formatter = new DataFormatter();
//                    // cell의 타입을 확인 하고, 값을 가져온다.
                    switch (cell.getCellType()) {
//                        case NUMERIC :
//                            if (DateUtil.isCellDateFormatted(cell)) {
//                                Date date = cell.getDateCellValue();
//                                String cellString = new SimpleDateFormat("yyyy-MM-dd").format(date);
////                                System.out.println("<"+cell.getDateCellValue()+">");
//                                System.out.print("<"+cellString+">");
//                            } else {
//                                System.out.print("<"+(int)cell.getNumericCellValue() + ">"); //getNumericCellValue 메서드는 기본으로 double형 반환
//                            }
//                            break;
                        case NUMERIC:
                            // 숫자일 경우, String형으로 변경하여 값을 읽는다.
                            String value = formatter.formatCellValue(cell);
                            System.out.print(value);
                            break;
                        case STRING :
                            System.out.print("<"+cell.getStringCellValue() + ">");
                            break;
                    }

                    cellCnt++;
                }
                System.out.println();
                rowCnt++;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
