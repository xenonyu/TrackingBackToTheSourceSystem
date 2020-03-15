package path_localize;
import java.sql.SQLException;
import Public.DB_Operation;
import AreaCheck.IPcheck;;

public class Update_judgeby_abtype {
	//向本地异常行为数据库中更新数据
	public static boolean AbBehavior_Type00(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "eventCode";
		Columnname[1] = "fakeSysIP";
		Columnname[2] = "fakeSysID";
		Columnname[3] = "sysUserID";
		result = DB_Operation.Select("eventCode,fakeSysIP,fakeSysID,sysUserID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[1] = "sysAreaName";
		result[1] = IPcheck.IPcheckmain(result[1]);
		if(DB_Operation.Update("abbehavior.servabtable", Columnname, 4, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type01(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[3];
		String []result = new String[3];
		Columnname[0] = "eventCode";
		Columnname[1] = "personalAPPID";
		Columnname[2] = "personalAPPIP";
		result = DB_Operation.Select("eventCode,personalAPPID,personalAPPIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 3);
		Columnname[2] = "personalAPPArea";
		result[2] = IPcheck.IPcheckmain(result[2]);
		if(DB_Operation.Update("abbehavior.servabtable", Columnname, 3, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type02(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "eventCode";
		Columnname[1] = "authCertiUserID";
		Columnname[2] = "authCertiSysID";
		Columnname[3] = "authCertiSysIP";
		Columnname[4] = "certiOwnerID";
		result = DB_Operation.Select("eventCode,authCertiUserID,authCertiSysID,authCertiSysIP,certiOwnerID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[3] = "authCertiSysAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 5, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type03(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[10];
		String []result = new String[10];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		Columnname[6] = "credReimUserID";
		Columnname[7] = "credUseUser";
		Columnname[8] = "credReimEntID";
		Columnname[9] = "credURL";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP,credReimUserID,credUseUser,credReimEntID,credURL", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 10);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 10, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type04(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[3];	
		String []result = new String[3];
		Columnname[0] = "eventCode";
		Columnname[1] = "fakeSysIP";
		Columnname[2] = "fakeSysID";
		result = DB_Operation.Select("eventCode,fakeSysIP,fakeSysID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 3);
		Columnname[1] = "sysAreaName";
		result[1] = IPcheck.IPcheckmain(result[1]);
		if(DB_Operation.Update("abbehavior.servabtable", Columnname, 3, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type05(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[10];
		String []result = new String[10];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		Columnname[6] = "credReimUserID";
		Columnname[7] = "credUseUser";
		Columnname[8] = "credReimEntID";
		Columnname[9] = "credURL";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP,credReimUserID,credUseUser,credReimEntID,credURL", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 10);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 10, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type06(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[6];
		String []result = new String[6];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 6);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 6, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type07(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[6];
		String []result = new String[6];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 6);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 6, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type08(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[6];
		String []result = new String[6];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 6);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 6, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type09(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "eventCode";
		Columnname[1] = "credCheckUserID";
		Columnname[2] = "appID";
		Columnname[3] = "appIP";
		result = DB_Operation.Select("eventCode,credCheckUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "appAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 4, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type10(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "eventCode";
		Columnname[1] = "credCheckUserID";
		Columnname[2] = "appID";
		Columnname[3] = "appIP";
		result = DB_Operation.Select("eventCode,credCheckUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "appAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 4, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type11(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[3];
		String []result = new String[3];
		Columnname[0] = "eventCode";
		Columnname[1] = "authSysID";
		Columnname[2] = "authSysIP";
		result = DB_Operation.Select("eventCode,authSysID,authSysIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 3);
		Columnname[2] = "authSysAreaName";
		result[2] = IPcheck.IPcheckmain(result[2]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 3, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type12(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "eventCode";
		Columnname[1] = "sysUserID";
		Columnname[2] = "appID";
		Columnname[3] = "appIP";
		result = DB_Operation.Select("eventCode,sysUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "appAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 4, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type13(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[6];
		String []result = new String[6];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 6);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 6, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type14(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[6];
		String []result = new String[6];
		Columnname[0] = "eventCode";
		Columnname[1] = "credIssueUserID";
		Columnname[2] = "credReceptUser";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "entAppID";
		Columnname[5] = "entAppIP";
		result = DB_Operation.Select("eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 6);
		Columnname[5] = "entAppAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		if(DB_Operation.Update("abbehavior.credabtable", Columnname, 6, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type15(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "eventCode";
		Columnname[1] = "appID";
		Columnname[2] = "appIP";
		Columnname[3] = "appUserID";
		result = DB_Operation.Select("eventCode,appID,appIP,appUserID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[2] = "appAreaName";
		result[2] = IPcheck.IPcheckmain(result[2]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 4, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type16(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[3];
		String []result = new String[3];
		Columnname[0] = "eventCode";
		Columnname[1] = "authSysID";
		Columnname[2] = "authSysIP";
		result = DB_Operation.Select("eventCode,authSysID,authSysIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 3);
		Columnname[2] = "authSysAreaName";
		result[2] = IPcheck.IPcheckmain(result[2]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 3, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type17(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "eventCode";
		Columnname[1] = "sysUserID";
		Columnname[2] = "appID";
		Columnname[3] = "appIP";
		result = DB_Operation.Select("eventCode,sysUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "appAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Update("abbehavior.authabtable", Columnname, 4, result, "abBehaviorID", AbBehaviorID))
			return true;
		else return false;
	}
}