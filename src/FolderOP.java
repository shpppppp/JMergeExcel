import java.io.File;

/***
 * �ļ����ļ��嵥��
 * @author ���ϳ�
 *
 */
public class FolderOP {
	
	/***
	 * 
	 * @param filepath	�ļ�����Ŀ¼
	 * @return String[]
	 */
	public static String[] GetFileName(String filepath) {
		File file = new File(filepath);
		String[] fileName = file.list();
		return fileName;
	}
	
}
