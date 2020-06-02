package ExternalFunction;
import java.sql.SQLException;
import Public.DB_Operation;
//异常行为的查询与删除
public class AbBehavior {
	public static int EventCodeCheck(String AbBehaviorID) throws SQLException {
		//查询异常行为编码
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
		//返回不同的异常行为编码对应的中文名称
		String AbType;
		switch(Eventcode){
		case 0: AbType = "服务器仿冒"; break;
		case 1: AbType = "多次尝试口令"; break;
		case 2: AbType = "多次证书认证失败"; break;
		case 3: AbType = "假发票报销"; break;
		case 4: AbType = "假系统连接"; break;
		case 5: AbType = "重复作废冲红凭据报销"; break;
		case 6: AbType = "异常时间开具大额凭据"; break;
		case 7: AbType = "异常时间开具大量凭据"; break;
		case 8: AbType = "同一用户短时间跨企业开具大量大额凭据"; break;
		case 9: AbType = "同一用户同一电子凭据多次尝试核准失败"; break;
		case 10: AbType = "同一用户不同电子凭据多次尝试核准失败"; break;
		case 11: AbType = "同一用户多次尝试认证失败"; break;
		case 12: AbType = "同一用户频繁变更电子凭据状态"; break;
		case 13: AbType = "同一企业短时间开具大量凭据"; break;
		case 14: AbType = "同一企业短时间开具大量大额凭据"; break;
		case 15: AbType = "同一用户/企业多次查验假发票"; break;
		case 16: AbType = "同一系统多次尝试认证失败"; break;
		case 17: AbType = "同一凭据频繁变更状态"; break;
		default: AbType = null; break;
		}
		return AbType;
	}
	public static void AbBehavior_message_get(String AbBehaviorID, int EventCode) throws SQLException {
		//根据不同的异常行为ID与编码，输出不同格式的查询信息
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
		//删除某ID对应的异常行为
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
