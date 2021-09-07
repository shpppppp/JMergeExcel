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
 * Excel�ļ���ȡ��
 * @author ���ϳ�
 *
 */
public class ReadExcel {
	
	/***
	 * ��Excel�ļ�����
	 * @param filePath
	 * @param sheetNum
	 * @param slist
	 * @throws IOException
	 */
	public void ReadXlsOrXlsxTest(String filePath,int sheetNum,String[] slist) throws IOException {
		//��Excel�ļ����ͳ�ʼ��workbook
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			FileInputStream fis = new FileInputStream(filePath);
			boolean is03Excel = filePath.matches("^.+\\.(?i)(xls)$")?true:false;
			Workbook workbook = is03Excel?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNum-1);
			
			//��ȡExcel����
			int max=sheet.getPhysicalNumberOfRows()+5;//��������Ϊ��ʱ�����¼�����ȡ������(�˴���Ϊ5������5�еĿ��о�ֹͣ��ȡ�ˣ����Զ�����5�����У�
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
	 * ����һ��Excel�ļ��������������ݡ�Excel�ļ�ÿ�ж�Ӧһ��list,ȫ��list���list<list<E>>����ӦExcel�ļ�����
	 * @param filePath	Excel�ļ�·��
	 * @param sheetNum	Sheet���
	 * @return	ArrayList<ArrayList<Object>>
	 * @throws IOException
	 */
	public ArrayList ReadXlsOrXlsx(String filePath,int sheetNum) throws IOException {
		MergeLog.setFileName(null);MergeLog.setStatus(false);MergeLog.setInfo(null);MergeLog.setCount(0);	//��־���ݳ�ʼ��
		ArrayList<ArrayList<Object>> result = new ArrayList<>();
		//��Excel�ļ����ͳ�ʼ��workbook
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			FileInputStream fis = new FileInputStream(filePath);
			boolean is03Excel = filePath.matches("^.+\\.(?i)(xls)$")?true:false;
			Workbook workbook = is03Excel?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNum-1);
			
			int rowCount = 0;	//�м���
			//��ȡExcel����
			int max=sheet.getPhysicalNumberOfRows()+5;//��������Ϊ��ʱ�����¼�����ȡ������(�˴���Ϊ5������5�еĿ��о�ֹͣ��ȡ�ˣ����Զ�����5�����У�
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
				rowCount++;	//��������
			}	
			workbook.close();
			fis.close();
			MergeLog.setCount(rowCount);	//�ɼ�����д����־
		}	
		MergeLog.setFileName(filePath);MergeLog.setStatus(true);	//�ļ������ɼ�״̬д����־
		return result;
	}
	
}
