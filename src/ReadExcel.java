import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.commons.math3.*;
import org.apache.commons.collections4.*;
import org.apache.xmlbeans.*;
import org.apache.commons.compress.*;
import org.openxmlformats.schemas.*;

/***
 * Excel文件读取类
 * @author 李老吃
 *
 */
public class ReadExcel {
	
	/***
	 * 读Excel文件测试
	 * @param filePath
	 * @param sheetNum
	 * @param slist
	 * @throws IOException
	 */
	public void ReadXlsOrXlsxTest(String filePath,int sheetNum,String[] slist) throws IOException {
		//按Excel文件类型初始化workbook
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			FileInputStream fis = new FileInputStream(filePath);
			boolean is03Excel = filePath.matches("^.+\\.(?i)(xls)$")?true:false;
			Workbook workbook = is03Excel?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNum-1);
			
			//读取Excel内容
			int max=sheet.getPhysicalNumberOfRows()+5;//设置整行为空时，往下继续读取的行数(此处设为5，大于5行的空行就停止读取了，即对多容忍5个空行）
			for(int i=0;i<max;i++) {
				Row row = sheet.getRow(i);
				if(row!=null) {
					for(int j=0;j<row.getLastCellNum();j++) {
						Cell cell = row.getCell(j,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
						if(cell!=null) {
							System.out.print(row.getCell(j)+"\t");
						}else {
							System.out.print(""+"\t");
						}
					}	
				}else {
					continue;
				}
				System.out.println();
			}
			
			
			workbook.close();
			fis.close();
		}
		
	}

	/***
	 * 给定一个Excel文件，返回所有数据。Excel文件每行对应一个list,全部list组成list<list<E>>，对应Excel文件数据
	 * @param filePath	Excel文件路径
	 * @param sheetNum	Sheet表号
	 * @return	ArrayList<ArrayList<Object>>
	 * @throws IOException
	 */
	public ArrayList ReadXlsOrXlsx(String filePath,int sheetNum) throws IOException {
		MergeLog.setFileName(null);MergeLog.setStatus(false);MergeLog.setInfo(null);MergeLog.setCount(0);	//日志内容初始化
		ArrayList<ArrayList<Object>> result = new ArrayList<>();
		//按Excel文件类型初始化workbook
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			FileInputStream fis = new FileInputStream(filePath);
			boolean is03Excel = filePath.matches("^.+\\.(?i)(xls)$")?true:false;
			Workbook workbook = is03Excel?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNum-1);
			
			int rowCount = 0;	//行计数
			//读取Excel内容
			int max=sheet.getPhysicalNumberOfRows()+5;//设置整行为空时，往下继续读取的行数(此处设为5，大于5行的空行就停止读取了，即对多容忍5个空行）
			for(int i=0;i<max;i++) {
				ArrayList fields = new ArrayList();
				Row row = sheet.getRow(i);
				if(row!=null) {
					for(int j=0;j<row.getLastCellNum();j++) {
						Cell cell = row.getCell(j,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
						if(cell!=null) {
							fields.add(row.getCell(j));
						}else {
							fields.add("");
						}
					}
					result.add(fields);
					fields=null;
				}else {
					continue;
				}
				rowCount++;	//计数行数
			}	
			workbook.close();
			fis.close();
			MergeLog.setCount(rowCount);	//采集行数写入日志
		}	
		MergeLog.setFileName(filePath);MergeLog.setStatus(true);	//文件名，采集状态写入日志
		return result;
	}
	
}
