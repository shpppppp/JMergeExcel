import java.util.*;
import java.io.IOException;

/***
 * 字段筛选类
 * @author 李老吃
 *
 */
public class FieldsFilter {
	
	/***
	 * 内部类记录符合要求的字段的起始位置
	 * @author 李老吃
	 *
	 */
	class FieldsInfo{
		private int colnume = 0;
		private int row = 0;
		void setCol(int col){
			this.colnume = col;
		}
		int getCol() {
			return this.colnume;
		}
		void setRow(int row) {
			this.row = row;
		}
		int getRow() {
			return this.row;
		}
	}
	
	/***
	 * 在内存中按给定的字段内容整理数据
	 * @param list	从Excel中读取的原始内容
	 * @param fields	给定的字段列表
	 * @return
	 */
	public ArrayList FF(ArrayList<ArrayList<Object>> list,String[] fields) {
		ArrayList<ArrayList<Object>> result = new ArrayList<>();	//返回值
		ArrayList info = new ArrayList(); 	//字段位置信息
		try {
			//字段匹配定位
			for(int i=0;i<fields.length;i++) {
				//按行开始查找
				for(int r=0;r<list.size();r++) {
					for(int c=0;c<list.get(r).size();c++) {
						if(list.get(r).get(c).toString().equals(fields[i])) {
							FieldsInfo finfo = new FieldsInfo();
							finfo.setRow(r);finfo.setCol(c);
							info.add(finfo);
						}
					}
				}
			}	

			if(info.size()==fields.length) {
				//取全部字段（源数据中一列列对应新list的一行）
				for(int i=0;i<info.size();i++) {
					ArrayList thisfields = new ArrayList();
					FieldsInfo finfo = (FieldsInfo)info.get(i); 
					int col = finfo.getCol();
//					System.out.println(col);
					for(int r=0;r<list.size();r++) {
//						System.out.println(list.get(r).get(col));
						try {
							if(list.get(r).get(col)!="")
								thisfields.add(list.get(r).get(col));
							else 
								thisfields.add("null");
						}catch(Exception e) {
							//arraylist越界处理，说明该字段对应该行无内容
							thisfields.add("null");
						}
					}
					result.add(thisfields);
				}
			}else {
				MergeLog.setStatus(false);MergeLog.setInfo("字段缺失"); MergeLog.setCount(0);
			}
		}catch(Exception e) {
			MergeLog.setStatus(false); MergeLog.setInfo("内部错误"); MergeLog.setCount(0);
		}	
		return result;
	}
	
}
