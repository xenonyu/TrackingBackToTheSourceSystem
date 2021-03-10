package ExternalFunction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Public.DB_Operation;

//异常事件的查询与删除
public class AbEvent {
    public static int EventTypeCheck(Connection conn, String EventID) throws SQLException {
        //查询异常事件的类型
        List<Map<String, Object>> resulttemp = DB_Operation.Select(conn,"eventType", "judgetable", "eventID="+EventID );
        if (!resulttemp.isEmpty()) return (int) (Integer) resulttemp.get(0).get("eventType");
        System.out.println("No data can be found!");
        return -1;
    }

    public static String AbEventTypejudge(int EventType) {
        //返回不同的异常事件类型对应的中文名称
        String AbType;
        if (EventType == 0) AbType = "同一用户或同一企业发生多次异常行为";
        else if (EventType == 1) AbType = "不同企业或不同用户异常行为关联";
        else return null;
        return AbType;
    }

    public static void AbEvent_message_get(Connection conn, String EventID, int EventType) throws SQLException {
        //根据不同的异常事件ID与类型，输出不同格式的查询信息
        if (EventType == 0) {
            List<Map<String, Object>> information;
            information = DB_Operation.Select(conn, "eventType,eventCode,sysUserID,appID,appAreaName,entID,entAreaName", "judgetable", "eventID="+EventID + "'");
            if (information == null)
                System.out.println("No data can be found!");
            else {
                System.out.println("eventType:" + information.get(0).get("eventType"));
                System.out.println("eventCode:" + information.get(0).get("eventCode"));
                System.out.println("sysUserID:" + information.get(0).get("sysUserID"));
                System.out.println("appID:" + information.get(0).get("appID"));
                System.out.println("appAreaName:" + information.get(0).get("appAreaName"));
                System.out.println("entID:" + information.get(0).get("entID"));
                System.out.println("entAreaName:" + information.get(0).get("entAreaName"));
            }
        } else {
            List<Map<String, Object>> information;
            information = DB_Operation.Select(conn, "eventType,eventCode,sysUserID,credIssueEntID,credIssueEntAreaName,credChaStaEntID,credChaStaEntAreaName", "judgetable", "eventID=" + EventID);
            if (information == null)
                System.out.println("No data can be found!");
            else {
                System.out.println("eventType:" + information.get(0).get("eventType"));
                System.out.println("eventCode:" + information.get(0).get("eventCode"));
                System.out.println("sysUserID:" + information.get(0).get("sysUserID"));
                System.out.println("credIssueEntID:" + information.get(0).get("credIssueEntID"));
                System.out.println("credIssueEntAreaName:" + information.get(0).get("credIssueEntAreaName"));
                System.out.println("credChaStaEntID:" + information.get(0).get("credChaStaEntID"));
                System.out.println("credChaStaEntAreaName:" + information.get(0).get("credChaStaEntAreaName"));
            }
        }
    }

    public static boolean Delete_AbEvent(String EventID) throws SQLException {
        //删除某ID对应的异常事件
        return DB_Operation.Delete("judgetable", "eventID", EventID);
    }
}
