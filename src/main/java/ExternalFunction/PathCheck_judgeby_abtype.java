package ExternalFunction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Public.DB_Operation;

//查询不同异常行为对应的区域信息
public class PathCheck_judgeby_abtype {
    public static List<Map<String, Object>> AbBehavior_Type00(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,sysAreaName,fakeSysID,sysUserID", "servabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type01(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,personalAPPID,personalAPPArea", "servabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type02(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,authCertiUserID,authCertiSysID,authCertiSysAreaName,certiOwnerID", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type03(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName,credReimUserID,credUseUser,credReimEntID,credURL", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type04(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,sysAreaName,fakeSysID", "servabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type05(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName,credReimUserID,credUseUser,credReimEntID,credURL", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type06(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type07(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type08(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type09(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credCheckUserID,appID,appAreaName", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type10(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credCheckUserID,appID,appAreaName", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type11(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,authSysID,authSysAreaName", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type12(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,sysUserID,appID,appAreaName", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type13(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type14(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,credIssueUserID,credReceptUser,credIssueEntID,entAppID,entAppAreaName", "credabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type15(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,appID,appAreaName,appUserID", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type16(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,authSysID,authSysAreaName", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }

    public static List<Map<String, Object>> AbBehavior_Type17(Connection conn, String AbBehaviorID) throws SQLException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "eventCode,sysUserID,appID,appAreaName", "authabtable", "abBehaviorID=" + AbBehaviorID );
        if(information.isEmpty()){
            System.out.println("No data can be found!");
            return null;
        } else
            return information;
    }
}