package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService2 {
	public static String filePath = "C:\\Users\\User\\Documents\\카카오톡 받은 파일";
    public static String fileNm = "4. LTE-M IoT 전송 단말기(IMEI) 2020.08.13.xlsx";
    public static Logger logger = (Logger) LoggerFactory.getLogger(ExcelService2.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SocketDAO dao = new SocketDAO();
		List<List<Object>> list = new ArrayList<>();
		List<Object> Row_value = null;
		try {
			FileInputStream file = new FileInputStream(new File("C:\\Users\\User\\Documents\\카카오톡 받은 파일\\4. LTE-M IoT 전송 단말기(IMEI) 2020.08.13.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(1);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()){
				
				Row row = rowIterator.next();
				logger.info("row : {}",row.getRowNum() );
                System.out.println("테스트 셀 카운트 "+row.getLastCellNum());
                int celllength = (int)row.getLastCellNum(); // 열의 총 개수 
				
                for(int c=1;c<celllength+1;c++){
                	Cell cell = row.getCell(c);
                	if (cell == null || cell.getCellType() == CellType.BLANK) {
                		System.out.println(c + "번, 빈값 들어감.");
                	}
                }
                
            }
			
            file.close();
            
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
		}
	}

}
