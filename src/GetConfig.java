import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/***
 * 读取配置文件类
 * @author 李老吃
 *
 */
public class GetConfig {
	static String filePath = System.getProperty("user.dir").toString()+File.separator+"config"+File.separator+"config.txt";  //根据操作系统使用正确的文件分隔符
	ArrayList tList = new ArrayList();
	
	/***
	 * 读取配置文件
	 * @return ArrayList
	 */
	public ArrayList ConfigTxt() {
		try {
			String encoding="GB2312";
			File file = new File(filePath);
			if(file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);	//编码格式
				BufferedReader bufReader = new BufferedReader(read);
				String lineTxt = null; //返回读取的字符串
				while((lineTxt = bufReader.readLine()) != null) {
					tList.add(lineTxt);
				}
				read.close();	
			}else {
				System.out.println("找不到指定的配置文件");
			}	
		}catch(Exception e) {
			System.out.println("读取配置文件内容出错");
	    	e.printStackTrace();
		}
		return tList;
	}
}

