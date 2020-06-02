package ExternalFunction;
import java.sql.SQLException;
import Public.DB_Operation;
//�쳣�¼��Ĳ�ѯ��ɾ��
public class AbEvent {
	public static int EventTypeCheck(String EventID) throws SQLException {
		//��ѯ�쳣�¼�������
		String resulttemp;
		if((resulttemp = DB_Operation.Select("eventType", "judgetable", "eventID", "'"+EventID+"'", "eventType"))!=null) {
			int EventType = Integer.valueOf(resulttemp);
			return EventType;
		}
		else {
			System.out.println("No data can be found!");
			return -1;
		}
	}
	public static String AbEventTypejudge(int EventType) {
		//���ز�ͬ���쳣�¼����Ͷ�Ӧ����������
		String AbType;
		if(EventType==0) AbType = "ͬһ�û���ͬһ��ҵ��������쳣��Ϊ";
		else if(EventType==1) AbType = "��ͬ��ҵ��ͬ�û��쳣��Ϊ����";
		else return null;
		return AbType;
	}
	public static void AbEvent_message_get(String EventID, int EventType) throws SQLException {
		//���ݲ�ͬ���쳣�¼�ID�����ͣ������ͬ��ʽ�Ĳ�ѯ��Ϣ
		if(EventType==0) {
			String []information = new String[7];
			information[0] = "eventType";
			information[1] = "eventCode";
			information[2] = "sysUserID"; 
			information[3] = "appID";
			information[4] = "appAreaName";
			information[5] = "entID";
			information[6] = "entAreaName";
			information = DB_Operation.Select("eventType,eventCode,sysUserID,appID,appAreaName,entID,entAreaName", "judgetable", "eventID", "'"+EventID+"'",information, 7);
			if(information==null)
				System.out.println("No data can be found!");
			else {
				System.out.println("eventType:"+information[0]);
				System.out.println("eventCode:"+information[1]);
				System.out.println("sysUserID:"+information[2]);
				System.out.println("appID:"+information[3]);
				System.out.println("appAreaName:"+information[4]);
				System.out.println("entID:"+information[5]);
				System.out.println("entAreaName:"+information[6]);
			}
		}
		else {
			String []information = new String[7];
			information[0] = "eventType";
			information[1] = "eventCode";
			information[2] = "sysUserID";
			information[3] = "credIssueEntID";
			information[4] = "credIssueEntAreaName";
			information[5] = "credChaStaEntID";
			information[6] = "credChaStaEntAreaName";
			information = DB_Operation.Select("eventType,eventCode,sysUserID,credIssueEntID,credIssueEntAreaName,credChaStaEntID,credChaStaEntAreaName", "judgetable", "eventID", "'"+EventID+"'",information, 7);
			if(information==null)
				System.out.println("No data can be found!");
			else {
				System.out.println("eventType:"+information[0]);
				System.out.println("eventCode:"+information[1]);
				System.out.println("sysUserID:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("credIssueEntAreaName:"+information[4]);
				System.out.println("credChaStaEntID:"+information[5]);
				System.out.println("credChaStaEntAreaName:"+information[6]);
			}
		}
	}
	public static boolean Delete_AbEvent(String EventID) throws SQLException {
		//ɾ��ĳID��Ӧ���쳣�¼�
		return DB_Operation.Delete("judgetable", "eventID", EventID);
	}
}
