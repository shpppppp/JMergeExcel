import java.io.File;

/***
 * 文件夹文件清单类
 * @author 李老吃
 *
 */
public class FolderOP {
	
	/***
	 * 
	 * @param filepath	文件所在目录
	 * @return String[]
	 */
	public static String[] GetFileName(String filepath) {
		File file = new File(filepath);
		String[] fileName = file.list();
		return fileName;
	}
	
}
