import java.util.*;
import java.io.IOException;

/***
 * �ֶ�ɸѡ��
 * @author ���ϳ�
 *
 */
public class FieldsFilter {
	
	/***
	 * �ڲ����¼����Ҫ����ֶε���ʼλ��
	 * @author ���ϳ�
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
	 * ���ڴ��а��������ֶ�������������
	 * @param list	��Excel�ж�ȡ��ԭʼ����
	 * @param fields	�������ֶ��б�
	 * @return
	 */
	public ArrayList FF(ArrayList<ArrayList<Object>> list,String[] fields) {
		ArrayList<ArrayList<Object>> result = new ArrayList<>();	//����ֵ
		ArrayList info = new ArrayList(); 	//�ֶ�λ����Ϣ
		try {
			//�ֶ�ƥ�䶨λ
			for(int i=0;i<fields.length;i++) {
				//���п�ʼ����
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
				//ȡȫ���ֶΣ�Դ������һ���ж�Ӧ��list��һ�У�
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
							//arraylistԽ�紦��˵�����ֶζ�Ӧ����������
							thisfields.add("null");
						}
					}
					result.add(thisfields);
				}
			}else {
				MergeLog.setStatus(false);MergeLog.setInfo("�ֶ�ȱʧ"); MergeLog.setCount(0);
			}
		}catch(Exception e) {
			MergeLog.setStatus(false); MergeLog.setInfo("�ڲ�����"); MergeLog.setCount(0);
		}	
		return result;
	}
	
}
