package ExternalFunction;
import java.sql.SQLException;
import Public.DB_Operation;
//查询不同异常行为对应的区域信息
public class PathCheck_judgeby_abtype {
	public static String[] AbBehavior_Type00(String AbBehaviorID) throws SQLException {
		String []information = new String[4];
		information[0] = "eventCode";
		information[1] = "sysAreaName";
		information[2] = "fakeSysID";
		information[3] = "sysUserID";
		information = DB_Operation.Select("eventCode,sysAreaName,fakeSysID,sysUserID", "servabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 4);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type01(String AbBehaviorID) throws SQLException {
		String []information = new String[3];
		information[0] = "eventCode";
		information[1] = "personalAPPID";
		information[2] = "personalAPPArea";
		information = DB_Operation.Select("eventCode,personalAPPID,personalAPPArea", "servabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 3);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type02(String AbBehaviorID) throws SQLException {
		String []information = new String[5];
		information[0] = "eventCode";
		information[1] = "authCertiUserID";
		information[2] = "authCertiSysID";
		information[3] = "authCertiSysAreaName";
		information[4] = "certiOwnerID";
		information = DB_Operation.Select("eventCode,authCertiUserID,authCertiSysID,authCertiSysAreaName,certiOwnerID", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 5);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type03(String AbBehaviorID) throws SQLException {
		String []information = new String[10];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information[6] = "credReimUserID";
		information[7] = "credUseUser";
		information[8] = "credReimEntID";
		information[9] = "credURL";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName,credReimUserID,credUseUser,credReimEntID,credURL", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 10);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type04(String AbBehaviorID) throws SQLException {
		String []information = new String[3];
		information[0] = "eventCode";
		information[1] = "sysAreaName";
		information[2] = "fakeSysID";
		information = DB_Operation.Select("eventCode,sysAreaName,fakeSysID", "servabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 3);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type05(String AbBehaviorID) throws SQLException {
		String []information = new String[10];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information[6] = "credReimUserID";
		information[7] = "credUseUser";
		information[8] = "credReimEntID";
		information[9] = "credURL";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName,credReimUserID,credUseUser,credReimEntID,credURL", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 10);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type06(String AbBehaviorID) throws SQLException {
		String []information = new String[6];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 6);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type07(String AbBehaviorID) throws SQLException {
		String []information = new String[6];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 6);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type08(String AbBehaviorID) throws SQLException {
		String []information = new String[6];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 6);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type09(String AbBehaviorID) throws SQLException {
		String []information = new String[4];
		information[0] = "eventCode";
		information[1] = "credCheckUserID";
		information[2] = "appID";
		information[3] = "appAreaName";
		information = DB_Operation.Select("eventCode,credCheckUserID,appID,appAreaName", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 4);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type10(String AbBehaviorID) throws SQLException {
		String []information = new String[4];
		information[0] = "eventCode";
		information[1] = "credCheckUserID";
		information[2] = "appID";
		information[3] = "appAreaName";
		information = DB_Operation.Select("eventCode,credCheckUserID,appID,appAreaName", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 4);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type11(String AbBehaviorID) throws SQLException {
		String []information = new String[3];
		information[0] = "eventCode";
		information[1] = "authSysID";
		information[2] = "authSysAreaName";
		information = DB_Operation.Select("eventCode,authSysID,authSysAreaName", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 3);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type12(String AbBehaviorID) throws SQLException {
		String []information = new String[4];
		information[0] = "eventCode";
		information[1] = "sysUserID";
		information[2] = "appID";
		information[3] = "appAreaName";
		information = DB_Operation.Select("eventCode,sysUserID,appID,appAreaName", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 4);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type13(String AbBehaviorID) throws SQLException {
		String []information = new String[6];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 6);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type14(String AbBehaviorID) throws SQLException {
		String []information = new String[6];
		information[0] = "eventCode";
		information[1] = "credIssueUserID";
		information[2] = "credReceptUser";
		information[3] = "credIssueEntID";
		information[4] = "entAppID";
		information[5] = "entAppAreaName";
		information = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 6);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type15(String AbBehaviorID) throws SQLException {
		String []information = new String[4];
		information[0] = "eventCode";
		information[1] = "appID";
		information[2] = "appAreaName";
		information[3] = "appUserID";
		information = DB_Operation.Select("eventCode,appID,appAreaName,appUserID", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 4);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type16(String AbBehaviorID) throws SQLException {
		String []information = new String[3];
		information[0] = "eventCode";
		information[1] = "authSysID";
		information[2] = "authSysAreaName";
		information = DB_Operation.Select("eventCode,authSysID,authSysAreaName", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 3);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
	public static String[] AbBehavior_Type17(String AbBehaviorID) throws SQLException {
		String []information = new String[4];
		information[0] = "eventCode";
		information[1] = "sysUserID";
		information[2] = "appID";
		information[3] = "appAreaName";
		information = DB_Operation.Select("eventCode,sysUserID,appID,appAreaName", "authabtable", "abBehaviorID", "'"+AbBehaviorID+"'",information, 4);
		if(information==null) {
			System.out.println("No data can be found!");
			return null;
		}
		else 
			return information;
	}
}