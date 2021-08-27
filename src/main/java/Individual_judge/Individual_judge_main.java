package main.java.Individual_judge;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import main.java.Public.DB_Operation;

public class Individual_judge_main {
    public static boolean Deal_inputdata(Connection inputDB) throws SQLException, ClassNotFoundException {
        //从数据库inputdata中提取异常事件数据，执行定位后输出到本地异常事件数据库中
//        result[0] = "eventID";
//        result[1] = "eventType";
        List<Map<String, Object>> information = DB_Operation.Select(inputDB, "*", "fwqfm", "id>=0");
        for (int i = 0; i < information.size(); i++) {
            if (information.get(i).get("eventID") == null) return false;
            else if (information.get(i).get("eventID") == null) break;
            String EventID = information.get(i).get("eventID").toString();
            int EventType = Integer.parseInt(information.get(i).get("eventType").toString());
            if (Check_EventID_From_judgetable(inputDB, EventID)) {
                if (EventType == 0) {
                    if (!Modify_judgeby_EventType.Update_Type0(inputDB, EventID)) return false;
                } else if (EventType == 1) {
                    if (!Modify_judgeby_EventType.Update_Type1(inputDB, EventID)) return false;
                } else return false;
            } else {
                if (EventType == 0) {
                    if (!Modify_judgeby_EventType.Insert_Type0(inputDB, EventID)) return false;
                } else if (EventType == 1) {
                    if (!Modify_judgeby_EventType.Insert_Type1(inputDB, EventID)) return false;
                } else return false;
            }
        }
        return true;
    }

    public static boolean Check_EventID_From_judgetable(Connection conn, String EventID) throws SQLException {
        //从异常事件表中查找是否已存在异常事件ID（防止主键冲突，相同则更新，不存在则创建）
        List<Map<String,Object>> result = DB_Operation.Select(conn, "eventID", "abjudge.judgetable", "eventID="+ EventID);
        return result.isEmpty();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection inputDataConn = DB_Operation.GetConnection("dzpj_aqts_202000628");
        Connection abjudge = DB_Operation.GetConnection("abjudge");
        if (Deal_inputdata(inputDataConn)) System.out.println("Successful!");
        else System.out.println("falied!");
    }
}
