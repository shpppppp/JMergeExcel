import java.util.*;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;

import java.io.IOException;

/***
 * 主类
 * @author 李老吃
 *
 */
public class MergeExcel {
	
	public static void main(String[] args) {
		//获取参数
		ArrayList conf = new GetConfig().ConfigTxt();	
		//获取字段列表
		String[] confFields = new String[conf.size()-3];		
		for(int i=0;i<confFields.length;i++) {
			confFields[i]=conf.get(i+3).toString();
		}
		/*******************************************************************/
		
		String[] files = FolderOP.GetFileName(conf.get(0).toString());
//		for(int i=0;i<files.length;i++) {
//			String file = conf.get(0).toString()+files[i];
//			try {
//				ArrayList<ArrayList<Object>> al = new ReadExcel().ReadXlsOrXlsx(file,Integer.parseInt(conf.get(2).toString()));
//				for(int a=0;a<al.size();a++) {
//					for(int b=0;b<al.get(a).size();b++) {
//						System.out.print(al.get(a).get(b)+"\t");
//					}
//					System.out.println();
//				}
//			}catch(Exception e) {
//				System.out.println(e);
//			}
//		}
		
		for(int i=0;i<files.length;i++) {
			String file = conf.get(0).toString()+files[i];
			try {
				ArrayList<ArrayList<Object>> all = new ReadExcel().ReadXlsOrXlsx(file,Integer.parseInt(conf.get(2).toString()));
				ArrayList<ArrayList<Object>> list = new FieldsFilter().FF(all, confFields);
				
//				for(int n=0;n<list.size();n++) {
//					ArrayList thislist = list.get(n);
//					for(int t=0;t<list.get(n).size();t++) {
//						System.out.print(list.get(n).get(t)+"\t");
//					}
//					System.out.println();
//				}

				MergeLog.WriteLog();
				System.out.println(MergeLog.getFileName()+":"+MergeLog.getStatus()+":"+MergeLog.getInfo()+"：行数"+MergeLog.getCount());
				WriteExcel.WriteFile(list,conf.get(1).toString(),MergeLog.getStatus()=="成功"?true:false);
				
				
			}catch(Exception e) {
				
			}
		}
		

	}

}
