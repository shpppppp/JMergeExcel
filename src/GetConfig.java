import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/***
 * ��ȡ�����ļ���
 * @author ���ϳ�
 *
 */
public class GetConfig {
	static String filePath = System.getProperty("user.dir").toString()+File.separator+"config"+File.separator+"config.txt";  //���ݲ���ϵͳʹ����ȷ���ļ��ָ���
	ArrayList tList = new ArrayList();
	
	/***
	 * ��ȡ�����ļ�
	 * @return ArrayList
	 */
	public ArrayList ConfigTxt() {
		try {
			String encoding="GB2312";
			File file = new File(filePath);
			if(file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);	//�����ʽ
				BufferedReader bufReader = new BufferedReader(read);
				String lineTxt = null; //���ض�ȡ���ַ���
				while((lineTxt = bufReader.readLine()) != null) {
					tList.add(lineTxt);
				}
				read.close();	
			}else {
				System.out.println("�Ҳ���ָ���������ļ�");
			}	
		}catch(Exception e) {
			System.out.println("��ȡ�����ļ����ݳ���");
	    	e.printStackTrace();
		}
		return tList;
	}
}

