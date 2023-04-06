package model;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {
	public static String filePath = "C:\\Users\\User\\Documents\\카카오톡 받은 파일";
    public static String fileNm = "4. LTE-M IoT 전송 단말기(IMEI) 2020.08.13.xlsx";
    public static Logger logger = (Logger) LoggerFactory.getLogger(ExcelService.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SocketDAO dao = new SocketDAO();
		List<List<Object>> list = new ArrayList<>();
		List<Object> Row_value = null;
		int lastcell = 0;
		 int excel_no=dao.last_table()+1;
		 List<Integer> arr = new ArrayList<>();
		 HashMap<String, Object> map = new HashMap<>();
		 HashMap<String, Object> map1 = new HashMap<>();
		 map1.put("table_name", excel_no);
		 List<List<Object>> error_catch = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File("C:\\Users\\User\\Documents\\카카오톡 받은 파일\\4. LTE-M IoT 전송 단말기(IMEI) 2020.08.13 (1).xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(1);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			
            while (rowIterator.hasNext()) {

            	Row row = rowIterator.next();
                
                // 각각의 행에 존재하는 모든 열(cell)을 순회한다.
                Iterator<Cell> cellIterator = row.cellIterator();
                Row_value = new ArrayList<>();
                logger.info("row test : {}", row.getRowNum());
                while (cellIterator.hasNext()) {	
                    Cell cell = cellIterator.next();
//                    System.out.println(cell.getRowIndex());
//                    System.out.println(cell.getColumnIndex());
                    switch (cell.getCellType()) {	// cell의 타입을 하고, 값을 가져온다.
                        case NUMERIC:
                            System.out.print((long) cell.getNumericCellValue() + "\t"); //getNumericCellValue 메서드는 기본으로 double형 반환
                            Row_value.add((long) cell.getNumericCellValue());
                            break;
                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
                            String test = new String(cell.getStringCellValue().getBytes(Charset.forName("UTF-8")));
                            System.out.print(test);
//                            Row_value.add(cell.getStringCellValue());
                            Row_value.add(test);
                            break;
                        case BLANK:
                        	Row_value.add("");
                        	System.out.print("blank");
                        	break;
                        case _NONE:
                        	Row_value.add("");
                        	System.out.print("none");
                        	break;
                        case FORMULA:
                        	Row_value.add("");
                        	System.out.print("formula");
                        	break;
                    }
                }
                System.out.println();
                if(Row_value.toString()=="[]") {
                	logger.info("마지막열 삭제 및 종료");
                	Row_value.remove(Row_value.size()-1);
                	list.add(Row_value);
                	break;
                }
                list.add(Row_value);
                if(lastcell < Row_value.size()) {
            		lastcell = Row_value.size();
            	}
                if(Row_value.size() == 12) {
                	error_catch.add(Row_value);
                }
                
            }
            System.out.println(list.toString());
            System.out.println(lastcell);
            for(int i=0; i<lastcell; i++) {
            	arr.add(i);
            }
            System.out.println(excel_no);
            file.close();
            
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			map1.put("arr_len", arr);
            int num2 = dao.create_table(map1);
            map.put("column", arr);
            map.put("row", list);
            map.put("table_name", excel_no);
            System.out.println(error_catch.toString());
           dao.upload_excel(map);
		}
	}

}
