package Individual_judge;

import java.sql.SQLException;
import Public.DB_Operation;

public class Individual_judge_main {
	public static boolean Deal_inputdata()throws SQLException, ClassNotFoundException {
		//�����ݿ�inputdata����ȡ�쳣�¼����ݣ�ִ�ж�λ������������쳣�¼����ݿ���
		String[]result = new String[2];
		String[][]information = new String[100][2];
		result[0] = "eventID";
		result[1] = "eventType";
		information = DB_Operation.SelectMore("*", "inputdata.abjudge", result, 2);
		for(int i=0; i<100; i++) {
			if(information[0][0]==null) return false;
			else if(information[i][0]==null) break;
			String EventID = information[i][0];
			int EventType = Integer.valueOf(information[i][1]);
			if(Check_EventID_From_judgetable(EventID)==1) {
				if(EventType==0) {
					if(!Modify_judgeby_EventType.Update_Type0(EventID)) return false;
				}
				else if(EventType==1) {
					if(!Modify_judgeby_EventType.Update_Type1(EventID)) return false;
				}
				else return false;
			}
			else {
				if(EventType==0) {
					if(!Modify_judgeby_EventType.Insert_Type0(EventID)) return false;
				}
				else if(EventType==1) {
					if(!Modify_judgeby_EventType.Insert_Type1(EventID)) return false;
				}
				else return false;
			}
		}
		return true;
	}
	public static int Check_EventID_From_judgetable(String EventID) throws SQLException {
		//���쳣�¼����в����Ƿ��Ѵ����쳣�¼�ID����ֹ������ͻ����ͬ����£��������򴴽���
		String result = DB_Operation.Select("eventID", "abjudge.judgetable", "eventID", "'"+EventID+"'", "eventID");
		if(result!=null) return 1;
		else return 0;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DB_Operation.Connect("inputdata");
		DB_Operation.Connect("abjudge");
		if(Deal_inputdata()) System.out.println("Successful!");
		else System.out.println("falied!");
		DB_Operation.Close();
	}
}
