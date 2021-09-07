import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/***
 * Execl文件写入类
 * @author 李老吃
 *
 */
public class WriteExcel {
	
	/***
	 * 写入Excel文件
	 * @param list
	 * @param filepath
	 * @throws IOException
	 */
	public static void WriteFile(ArrayList<ArrayList<Object>> list,String filepath,boolean flag) throws IOException {
		File file = new File(filepath);
		FileOutputStream out = null;
		FileInputStream in = null;
		if(!file.exists()&&flag) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("合并表");
			//转置list
			ArrayList<ArrayList<Object>> newlist = WriteExcel.Transposition(list);
			//写入
			for(int i=0;i<newlist.size();i++) {
				XSSFRow row = sheet.createRow(i);
				for(int t=0;t<newlist.get(i).size();t++) {
					XSSFCell cell = row.createCell(t);
					cell.setCellValue(newlist.get(i).get(t).toString());
					//System.out.print(newlist.get(i).get(t)+"\t");
				}
				//System.out.println();
			}
			out = new FileOutputStream(filepath);
			workbook.write(out);out.flush();out.close();workbook.close();	
		}
		else {
			if(flag) {
				in = new FileInputStream(filepath);
				XSSFWorkbook workbook = new XSSFWorkbook(in);
				XSSFSheet sheet = workbook.getSheet("合并表");
				//转置list
				ArrayList<ArrayList<Object>> newlist = WriteExcel.Transposition(list);
				//写入
				for(int i=0;i<newlist.size();i++) {
					XSSFRow row = sheet.createRow(sheet.getPhysicalNumberOfRows());
					for(int t=0;t<newlist.get(i).size();t++) {
						XSSFCell cell = row.createCell(t);
						cell.setCellValue(newlist.get(i).get(t).toString());
					}
				}
				out = new FileOutputStream(filepath);
				workbook.write(out);out.flush();out.close();workbook.close();	
			}
		}

	}
	
	/***
	 * 对ArrayList进行转置
	 * @param list
	 * @return
	 */
	private static ArrayList Transposition(ArrayList<ArrayList<Object>> list) {
		ArrayList<ArrayList<Object>> result = new ArrayList<>();
		for(int i=0;i<list.get(0).size();i++) {
			ArrayList temp = new ArrayList();
			for(int t=0;t<list.size();t++) {
				//System.out.print(list.get(t).get(i).toString()+"\t");
				temp.add(list.get(t).get(i));
			}
			//System.out.println();
			result.add(temp);
		}
		return result;
	}
	
}
