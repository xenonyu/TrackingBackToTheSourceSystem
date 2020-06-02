package path_localize;
import java.sql.SQLException;
import Public.DB_Operation;
import AreaCheck.IPcheck;;

public class Insert_judgeby_abtype {
	//向本地异常行为数据库中插入数据
	public static boolean AbBehavior_Type00(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "fakeSysIP";
		Columnname[3] = "fakeSysID";
		Columnname[4] = "sysUserID";
		result = DB_Operation.Select("abBehaviorID,eventCode,fakeSysIP,fakeSysID,sysUserID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[2] = "sysAreaName";
		result[2] = IPcheck.IPcheckmain(result[2]);
		if(DB_Operation.Insert("abbehavior.servabtable", Columnname, 5, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type01(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "personalAPPID";
		Columnname[3] = "personalAPPIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,personalAPPID,personalAPPIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "personalAPPArea";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Insert("abbehavior.servabtable", Columnname, 4, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type02(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[6];
		String []result = new String[6];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "authCertiUserID";
		Columnname[3] = "authCertiSysID";
		Columnname[4] = "authCertiSysIP";
		Columnname[5] = "certiOwnerID";
		result = DB_Operation.Select("abBehaviorID,eventCode,authCertiUserID,authCertiSysID,authCertiSysIP,certiOwnerID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 6);
		Columnname[4] = "authCertiSysAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 6, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type03(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[11];
		String []result = new String[11];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		Columnname[7] = "credReimUserID";
		Columnname[8] = "credUseUser";
		Columnname[9] = "credReimEntID";
		Columnname[10] = "credURL";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP,credReimUserID,credUseUser,credReimEntID,credURL", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 11);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 11, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type04(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];	
		String []result = new String[4];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "fakeSysIP";
		Columnname[3] = "fakeSysID";
		result = DB_Operation.Select("abBehaviorID,eventCode,fakeSysIP,fakeSysID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[2] = "sysAreaName";
		result[2] = IPcheck.IPcheckmain(result[2]);
		if(DB_Operation.Insert("abbehavior.servabtable", Columnname, 4, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type05(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[11];
		String []result = new String[11];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		Columnname[7] = "credReimUserID";
		Columnname[8] = "credUseUser";
		Columnname[9] = "credReimEntID";
		Columnname[10] = "credURL";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP,credReimUserID,credUseUser,credReimEntID,credURL", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 11);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 11, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type06(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 7);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 7, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type07(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 7);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 7, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type08(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 7);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 7, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type09(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credCheckUserID";
		Columnname[3] = "appID";
		Columnname[4] = "appIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credCheckUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[4] = "appAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 5, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type10(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credCheckUserID";
		Columnname[3] = "appID";
		Columnname[4] = "appIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credCheckUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[4] = "appAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 5, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type11(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "authSysID";
		Columnname[3] = "authSysIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,authSysID,authSysIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "authSysAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 4, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type12(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "sysUserID";
		Columnname[3] = "appID";
		Columnname[4] = "appIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,sysUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[4] = "appAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 5, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type13(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 7);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 7, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type14(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "credIssueUserID";
		Columnname[3] = "credReceptUser";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "entAppID";
		Columnname[6] = "entAppIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 7);
		Columnname[6] = "entAppAreaName";
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Insert("abbehavior.credabtable", Columnname, 7, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type15(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "appID";
		Columnname[3] = "appIP";
		Columnname[4] = "appUserID";
		result = DB_Operation.Select("abBehaviorID,eventCode,appID,appIP,appUserID", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[3] = "appAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 5, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type16(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[4];
		String []result = new String[4];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "authSysID";
		Columnname[3] = "authSysIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,authSysID,authSysIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 4);
		Columnname[3] = "authSysAreaName";
		result[3] = IPcheck.IPcheckmain(result[3]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 4, result))
			return true;
		else return false;
	}
	public static boolean AbBehavior_Type17(String AbBehaviorID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[5];
		String []result = new String[5];
		Columnname[0] = "abBehaviorID";
		Columnname[1] = "eventCode";
		Columnname[2] = "sysUserID";
		Columnname[3] = "appID";
		Columnname[4] = "appIP";
		result = DB_Operation.Select("abBehaviorID,eventCode,sysUserID,appID,appIP", "inputdata.abbehavior", "abBehaviorID", "'"+AbBehaviorID+"'",Columnname, 5);
		Columnname[4] = "appAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		if(DB_Operation.Insert("abbehavior.authabtable", Columnname, 5, result))
			return true;
		else return false;
	}
}