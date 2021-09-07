import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/***
 * ��־��
 * @author ���ϳ�
 *
 */
public class MergeLog {
	
	private static String fileName = null;	//�ļ�����¼
	public static void setFileName(String name) {
		fileName = name;
	}
	public static String getFileName() {
		return fileName;
	}
	
	private static String status = null;	//״̬��¼��true=�ɹ�,false=ʧ��
	public static void setStatus(boolean stat) {
		status = stat?"�ɹ�":"ʧ��";
	}
	public static String getStatus() {
		return status;
	}
	
	private static String logInfo = null;	//��־��Ϣ��¼
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
//		sdf.applyPattern("yyyy-MM-dd HH��mm��ss");
		Date date = new Date();
		String logfile = System.getProperty("user.dir").toString()+"\\log\\mergelog.txt";
		File file = new File(logfile);
		if(!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file,true);	//׷��д��
		fw.write(fileName+":"+status+":���ɼ�"+countNum+"��:"+logInfo+System.getProperty("line.separator"));	//д����Ϣ������ϵͳ����ȷ�����з�
		fw.close();
	}
}
