package ExternalFunction;
import java.sql.SQLException;
import Public.DB_Operation;
//�쳣��Ϊ�Ĳ�ѯ��ɾ��
public class AbBehavior {
	public static int EventCodeCheck(String AbBehaviorID) throws SQLException {
		//��ѯ�쳣��Ϊ����
		String resulttemp;
		if((resulttemp = DB_Operation.Select("eventCode", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'", "eventCode"))!=null) {
			int EventCode = Integer.valueOf(resulttemp);
			return EventCode;
		}
		else if((resulttemp = DB_Operation.Select("eventCode", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'", "eventCode"))!=null){
			int EventCode = Integer.valueOf(resulttemp);
			return EventCode;
		}
		else if((resulttemp = DB_Operation.Select("eventCode", "servabtable", "abBehaviorID", "'"+AbBehaviorID+"'", "eventCode"))!=null){
			int EventCode = Integer.valueOf(resulttemp);
			return EventCode;
		}
		else {
			System.out.println("No data can be found!");
			return -1;
		}
	}
	public static String AbTypejudge(int Eventcode) {
		//���ز�ͬ���쳣��Ϊ�����Ӧ����������
		String AbType;
		switch(Eventcode){
		case 0: AbType = "��������ð"; break;
		case 1: AbType = "��γ��Կ���"; break;
		case 2: AbType = "���֤����֤ʧ��"; break;
		case 3: AbType = "�ٷ�Ʊ����"; break;
		case 4: AbType = "��ϵͳ����"; break;
		case 5: AbType = "�ظ����ϳ��ƾ�ݱ���"; break;
		case 6: AbType = "�쳣ʱ�俪�ߴ��ƾ��"; break;
		case 7: AbType = "�쳣ʱ�俪�ߴ���ƾ��"; break;
		case 8: AbType = "ͬһ�û���ʱ�����ҵ���ߴ������ƾ��"; break;
		case 9: AbType = "ͬһ�û�ͬһ����ƾ�ݶ�γ��Ժ�׼ʧ��"; break;
		case 10: AbType = "ͬһ�û���ͬ����ƾ�ݶ�γ��Ժ�׼ʧ��"; break;
		case 11: AbType = "ͬһ�û���γ�����֤ʧ��"; break;
		case 12: AbType = "ͬһ�û�Ƶ���������ƾ��״̬"; break;
		case 13: AbType = "ͬһ��ҵ��ʱ�俪�ߴ���ƾ��"; break;
		case 14: AbType = "ͬһ��ҵ��ʱ�俪�ߴ������ƾ��"; break;
		case 15: AbType = "ͬһ�û�/��ҵ��β���ٷ�Ʊ"; break;
		case 16: AbType = "ͬһϵͳ��γ�����֤ʧ��"; break;
		case 17: AbType = "ͬһƾ��Ƶ�����״̬"; break;
		default: AbType = null; break;
		}
		return AbType;
	}
	public static void AbBehavior_message_get(String AbBehaviorID, int EventCode) throws SQLException {
		//���ݲ�ͬ���쳣��ΪID����룬�����ͬ��ʽ�Ĳ�ѯ��Ϣ
		switch(EventCode) {
		case 0: {
			String []information = new String[4];
			information = PathCheck_judgeby_abtype.AbBehavior_Type00(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("sysAreaName:"+information[1]);
				System.out.println("fakeSysID:"+information[2]);
				System.out.println("sysUserID:"+information[3]);
			}
			break;
		}
		case 1: {
			String []information = new String[3];
			information = PathCheck_judgeby_abtype.AbBehavior_Type01(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("personalAPPID:"+information[1]);
				System.out.println("personalAPPArea:"+information[2]);
			}
			break;
		}
		case 2: {
			String []information = new String[5];
			information = PathCheck_judgeby_abtype.AbBehavior_Type02(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("authCertiUserID:"+information[1]);
				System.out.println("authCertiSysID:"+information[2]);
				System.out.println("authCertiSysAreaName:"+information[3]);
				System.out.println("certiOwnerID:"+information[4]);
			}
			break;
		}
		case 3: {
			String []information = new String[10];
			information = PathCheck_judgeby_abtype.AbBehavior_Type03(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credIssueUserID:"+information[1]);
				System.out.println("credReceptUser:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("entAppID:"+information[4]);
				System.out.println("entAppAreaName:"+information[5]);
				System.out.println("credReimUserID:"+information[6]);
				System.out.println("credUseUser:"+information[7]);
				System.out.println("credReimEntID:"+information[8]);
				System.out.println("credURL:"+information[9]);
			}
			break;
		}
		case 4: {
			String []information = new String[3];
			information = PathCheck_judgeby_abtype.AbBehavior_Type04(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("sysAreaName:"+information[1]);
				System.out.println("fakeSysID:"+information[2]);
			}
			break;
		}
		case 5: {
			String []information = new String[10];
			information = PathCheck_judgeby_abtype.AbBehavior_Type05(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credIssueUserID:"+information[1]);
				System.out.println("credReceptUser:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("entAppID:"+information[4]);
				System.out.println("entAppAreaName:"+information[5]);
				System.out.println("credReimUserID:"+information[6]);
				System.out.println("credUseUser:"+information[7]);
				System.out.println("credReimEntID:"+information[8]);
				System.out.println("credURL:"+information[9]);
			}
			break;
		}
		case 6: {
			String []information = new String[6];
			information = PathCheck_judgeby_abtype.AbBehavior_Type06(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credIssueUserID:"+information[1]);
				System.out.println("credReceptUser:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("entAppID:"+information[4]);
				System.out.println("entAppAreaName:"+information[5]);
			}
			break;
		}
		case 7: {
			String []information = new String[6];
			information = PathCheck_judgeby_abtype.AbBehavior_Type07(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credIssueUserID:"+information[1]);
				System.out.println("credReceptUser:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("entAppID:"+information[4]);
				System.out.println("entAppAreaName:"+information[5]);
			}
			break;
		}
		case 8: {
			String []information = new String[1];
			information = PathCheck_judgeby_abtype.AbBehavior_Type08(AbBehaviorID);
			if(information!=null) {
			}
			break;
		}
		case 9: {
			String []information = new String[4];
			information = PathCheck_judgeby_abtype.AbBehavior_Type09(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credCheckUserID:"+information[1]);
				System.out.println("appID:"+information[2]);
				System.out.println("appAreaName:"+information[3]);
			}
			break;
		}
		case 10: {
			String []information = new String[4];
			information = PathCheck_judgeby_abtype.AbBehavior_Type10(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credCheckUserID:"+information[1]);
				System.out.println("appID:"+information[2]);
				System.out.println("appAreaName:"+information[3]);
			}
			break;
		}
		case 11: {
			String []information = new String[3];
			information = PathCheck_judgeby_abtype.AbBehavior_Type11(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("authSysID:"+information[1]);
				System.out.println("authSysAreaName:"+information[2]);
			}
			break;
		}
		case 12: {
			String []information = new String[4];
			information = PathCheck_judgeby_abtype.AbBehavior_Type12(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("sysUserID:"+information[1]);
				System.out.println("appID:"+information[2]);
				System.out.println("appAreaName:"+information[3]);
			}
			break;
		}
		case 13: {
			String []information = new String[6];
			information = PathCheck_judgeby_abtype.AbBehavior_Type13(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credIssueUserID:"+information[1]);
				System.out.println("credReceptUser:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("entAppID:"+information[4]);
				System.out.println("entAppAreaName:"+information[5]);
			}
			break;
		}
		case 14: {
			String []information = new String[6];
			information = PathCheck_judgeby_abtype.AbBehavior_Type14(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("credIssueUserID:"+information[1]);
				System.out.println("credReceptUser:"+information[2]);
				System.out.println("credIssueEntID:"+information[3]);
				System.out.println("entAppID:"+information[4]);
				System.out.println("entAppAreaName:"+information[5]);
			}
			break;
		}
		case 15: {
			String []information = new String[4];
			information = PathCheck_judgeby_abtype.AbBehavior_Type15(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("AppID:"+information[1]);
				System.out.println("AppAreaName:"+information[2]);
				System.out.println("AppUserID:"+information[3]);
			}
			break;
		}
		case 16: {
			String []information = new String[3];
			information = PathCheck_judgeby_abtype.AbBehavior_Type16(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("authSysID:"+information[1]);
				System.out.println("authSysAreaName:"+information[2]);
			}
			break;
		}
		case 17: {
			String []information = new String[4];
			information = PathCheck_judgeby_abtype.AbBehavior_Type17(AbBehaviorID);
			if(information!=null) {
				System.out.println("eventCode:"+information[0]);
				System.out.println("sysUserID:"+information[1]);
				System.out.println("appID:"+information[2]);
				System.out.println("appAreaName:"+information[3]);
			}
			break;
		}
		default: break;
		}
	}
	public static boolean Delete_AbBehavior(String AbBehaviorID) throws SQLException {
		//ɾ��ĳID��Ӧ���쳣��Ϊ
		int EventCode = AbBehavior.EventCodeCheck(AbBehaviorID);
		String tablename;
		if((EventCode==2)||(EventCode>=9)&&(EventCode<=12)||(EventCode>=15))
			tablename = "authabtable";
		else if((EventCode==0)||(EventCode==1)||(EventCode==4))
			tablename = "servabtable";
		else 
			tablename = "credabtable";
		return DB_Operation.Delete(tablename, "abBehaviorID", AbBehaviorID);
	}
}
