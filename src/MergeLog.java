import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/***
 * 日志类
 * @author 李老吃
 *
 */
public class MergeLog {
	
	private static String fileName = null;	//文件名记录
	public static void setFileName(String name) {
		fileName = name;
	}
	public static String getFileName() {
		return fileName;
	}
	
	private static String status = null;	//状态记录，true=成功,false=失败
	public static void setStatus(boolean stat) {
		status = stat?"成功":"失败";
	}
	public static String getStatus() {
		return status;
	}
	
	private static String logInfo = null;	//日志信息记录
	public static void setInfo(String info) {
		logInfo = info;
	}
	public static String getInfo() {
		return logInfo;
	}
	
	private static int countNum = 0;
	public static void setCount(int c) {
		countNum = c;
	}
	public static int getCount() {
		return countNum;
	}
	
	public static void WriteLog() throws IOException {
//		SimpleDateFormat sdf = new SimpleDateFormat();
//		sdf.applyPattern("yyyy-MM-dd HH：mm：ss");
		Date date = new Date();
		String logfile = System.getProperty("user.dir").toString()+"\\log\\mergelog.txt";
		File file = new File(logfile);
		if(!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file,true);	//追加写入
		fw.write(fileName+":"+status+":共采集"+countNum+"行:"+logInfo+System.getProperty("line.separator"));	//写入信息，根据系统类型确定换行符
		fw.close();
	}
}
