package main.java.Individual_judge;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import main.java.Public.DB_Operation;

public class Modify_judgeby_EventType {
    //向本地异常事件数据库中插入或更新数据
    public static boolean Update_Type0(Connection conn, String EventID) throws SQLException, ClassNotFoundException {
        String[] Columnname = new String[7];
        Columnname[0] = "eventType";
        Columnname[1] = "eventCode";
        Columnname[2] = "sysUserID";
        Columnname[3] = "appID";
        Columnname[4] = "appIP";
        Columnname[5] = "entID";
        Columnname[6] = "entIP";
        List<Map<String, Object>> result = DB_Operation.Select(conn,"eventType,eventCode,sysUserID,appID,appIP,entID,entIP", "inputdata.abjudge", "eventID=" + EventID);
        Columnname[4] = "appAreaName";
        Columnname[6] = "entAreaName";
        return DB_Operation.Update(conn, "abjudge.judgetable", Columnname, result.get(0), "eventID=" + EventID);
    }

    public static boolean Update_Type1(Connection conn, String EventID) throws SQLException, ClassNotFoundException {
        String[] Columnname = new String[7];
        List<Map<String, Object>> result;
        Columnname[0] = "eventType";
        Columnname[1] = "eventCode";
        Columnname[2] = "sysUserID";
        Columnname[3] = "credIssueEntID";
        Columnname[4] = "credIssueEntIP";
        Columnname[5] = "credChaStaEntID";
        Columnname[6] = "credChaStaEntIP";
        result = DB_Operation.Select(conn, "eventType,eventCode,sysUserID,credIssueEntID,credIssueEntIP,credChaStaEntID,credChaStaEntIP", "inputdata.abjudge", "eventID="+ EventID);
        Columnname[4] = "credIssueEntAreaName";
        Columnname[6] = "credChaStaEntAreaName";
//        result[4] = IPcheck.IPcheckmain(result[4]);
//        result[6] = IPcheck.IPcheckmain(result[6]);
        return DB_Operation.Update(conn, "abjudge.judgetable", Columnname, result.get(0), "eventID=" + EventID);
    }

    public static boolean Insert_Type0(Connection conn, String EventID) throws SQLException, ClassNotFoundException {
        String[] Columnname = new String[8];
        List<Map<String, Object>> result;
        Columnname[0] = "eventID";
        Columnname[1] = "eventType";
        Columnname[2] = "eventCode";
        Columnname[3] = "sysUserID";
        Columnname[4] = "appID";
        Columnname[5] = "appIP";
        Columnname[6] = "entID";
        Columnname[7] = "entIP";
        result = DB_Operation.Select(conn, "eventID,eventType,eventCode,sysUserID,appID,appIP,entID,entIP", "inputdata.abjudge", "eventID="+ EventID);
        Columnname[5] = "appAreaName";
        Columnname[7] = "entAreaName";
//        result[5] = IPcheck.IPcheckmain(result[5]);
//        result[7] = IPcheck.IPcheckmain(result[7]);
        return DB_Operation.UpdateAbnormal(conn, "abjudge.judgetable", Columnname, result.get(0));
    }

    public static boolean Insert_Type1(Connection conn, String EventID) throws SQLException, ClassNotFoundException {
        String[] Columnname = new String[8];
        List<Map<String, Object>> result;
        Columnname[0] = "eventID";
        Columnname[1] = "eventType";
        Columnname[2] = "eventCode";
        Columnname[3] = "sysUserID";
        Columnname[4] = "credIssueEntID";
        Columnname[5] = "credIssueEntIP";
        Columnname[6] = "credChaStaEntID";
        Columnname[7] = "credChaStaEntIP";
        result = DB_Operation.Select(conn,"eventID,eventType,eventCode,sysUserID,credIssueEntID,credIssueEntIP,credChaStaEntID,credChaStaEntIP", "inputdata.abjudge", "eventID=" + EventID);
        Columnname[5] = "credIssueEntAreaName";
        Columnname[7] = "credChaStaEntAreaName";
//        result[5] = IPcheck.IPcheckmain(result[5]);
//        result[7] = IPcheck.IPcheckmain(result[7]);
        return DB_Operation.UpdateAbnormal(conn, "abjudge.judgetable", Columnname, result.get(0));
    }
}
