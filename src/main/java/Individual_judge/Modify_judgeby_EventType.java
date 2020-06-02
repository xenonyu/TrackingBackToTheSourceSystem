package Individual_judge;
import java.sql.SQLException;
import AreaCheck.IPcheck;
import Public.DB_Operation;

public class Modify_judgeby_EventType {
	//向本地异常事件数据库中插入或更新数据
	public static boolean Update_Type0(String EventID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "eventType";
		Columnname[1] = "eventCode";
		Columnname[2] = "sysUserID"; 
		Columnname[3] = "appID";
		Columnname[4] = "appIP";
		Columnname[5] = "entID";
		Columnname[6] = "entIP";
		result = DB_Operation.Select("eventType,eventCode,sysUserID,appID,appIP,entID,entIP", "inputdata.abjudge", "eventID", "'"+EventID+"'",Columnname, 7);
		Columnname[4] = "appAreaName";
		Columnname[6] = "entAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Update("abjudge.judgetable", Columnname, 7, result, "eventID", EventID))
			return true;
		else return false;
	}
	public static boolean Update_Type1(String EventID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[7];
		String []result = new String[7];
		Columnname[0] = "eventType";
		Columnname[1] = "eventCode";
		Columnname[2] = "sysUserID";
		Columnname[3] = "credIssueEntID";
		Columnname[4] = "credIssueEntIP";
		Columnname[5] = "credChaStaEntID";
		Columnname[6] = "credChaStaEntIP";
		result = DB_Operation.Select("eventType,eventCode,sysUserID,credIssueEntID,credIssueEntIP,credChaStaEntID,credChaStaEntIP", "inputdata.abjudge", "eventID", "'"+EventID+"'",Columnname, 7);
		Columnname[4] = "credIssueEntAreaName";
		Columnname[6] = "credChaStaEntAreaName";
		result[4] = IPcheck.IPcheckmain(result[4]);
		result[6] = IPcheck.IPcheckmain(result[6]);
		if(DB_Operation.Update("abjudge.judgetable", Columnname, 7, result, "eventID", EventID))
			return true;
		else return false;
	}
	public static boolean Insert_Type0(String EventID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[8];
		String []result = new String[8];
		Columnname[0] = "eventID";
		Columnname[1] = "eventType";
		Columnname[2] = "eventCode";
		Columnname[3] = "sysUserID"; 
		Columnname[4] = "appID";
		Columnname[5] = "appIP";
		Columnname[6] = "entID";
		Columnname[7] = "entIP";
		result = DB_Operation.Select("eventID,eventType,eventCode,sysUserID,appID,appIP,entID,entIP", "inputdata.abjudge", "eventID", "'"+EventID+"'",Columnname, 8);
		Columnname[5] = "appAreaName";
		Columnname[7] = "entAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		result[7] = IPcheck.IPcheckmain(result[7]);
		if(DB_Operation.Insert("abjudge.judgetable", Columnname, 8, result))
			return true;
		else return false;
	}
	public static boolean Insert_Type1(String EventID) throws SQLException, ClassNotFoundException {
		String []Columnname = new String[8];
		String []result = new String[8];
		Columnname[0] = "eventID";
		Columnname[1] = "eventType";
		Columnname[2] = "eventCode";
		Columnname[3] = "sysUserID";
		Columnname[4] = "credIssueEntID";
		Columnname[5] = "credIssueEntIP";
		Columnname[6] = "credChaStaEntID";
		Columnname[7] = "credChaStaEntIP";
		result = DB_Operation.Select("eventID,eventType,eventCode,sysUserID,credIssueEntID,credIssueEntIP,credChaStaEntID,credChaStaEntIP", "inputdata.abjudge", "eventID", "'"+EventID+"'",Columnname, 8);
		Columnname[5] = "credIssueEntAreaName";
		Columnname[7] = "credChaStaEntAreaName";
		result[5] = IPcheck.IPcheckmain(result[5]);
		result[7] = IPcheck.IPcheckmain(result[7]);
		if(DB_Operation.Insert("abjudge.judgetable", Columnname, 8, result))
			return true;
		else return false;
	}
}
