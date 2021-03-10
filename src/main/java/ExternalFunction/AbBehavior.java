package ExternalFunction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Public.DB_Operation;

//异常行为的查询与删除
public class AbBehavior {
    public static int EventCodeCheck(Connection conn, String AbBehaviorID) throws SQLException {
        //查询异常行为编码
        List<Map<String, Object>> resulttemp;
        resulttemp = DB_Operation.Select(conn, "eventCode", "authabtable", "abBehaviorID="+AbBehaviorID);
        if (!resulttemp.isEmpty()) return (Integer) resulttemp.get(0).get("eventCode");
        resulttemp = DB_Operation.Select(conn, "eventCode", "servertable", "abBehaviorID="+AbBehaviorID);
        if (!resulttemp.isEmpty()) return (Integer) resulttemp.get(0).get("eventCode");
        resulttemp = DB_Operation.Select(conn, "eventCode", "credabtable", "abBehaviorID="+AbBehaviorID);
        if (!resulttemp.isEmpty()) return (Integer) resulttemp.get(0).get("eventCode");
        System.out.println("No data can be found!");
        return -1;
    }

    public static String AbTypejudge(int Eventcode) {
        //返回不同的异常行为编码对应的中文名称
        String AbType;
        switch (Eventcode) {
            case 0:
                AbType = "服务器仿冒";
                break;
            case 1:
                AbType = "多次尝试口令";
                break;
            case 2:
                AbType = "多次证书认证失败";
                break;
            case 3:
                AbType = "假发票报销";
                break;
            case 4:
                AbType = "假系统连接";
                break;
            case 5:
                AbType = "重复作废冲红凭据报销";
                break;
            case 6:
                AbType = "异常时间开具大额凭据";
                break;
            case 7:
                AbType = "异常时间开具大量凭据";
                break;
            case 8:
                AbType = "同一用户短时间跨企业开具大量大额凭据";
                break;
            case 9:
                AbType = "同一用户同一电子凭据多次尝试核准失败";
                break;
            case 10:
                AbType = "同一用户不同电子凭据多次尝试核准失败";
                break;
            case 11:
                AbType = "同一用户多次尝试认证失败";
                break;
            case 12:
                AbType = "同一用户频繁变更电子凭据状态";
                break;
            case 13:
                AbType = "同一企业短时间开具大量凭据";
                break;
            case 14:
                AbType = "同一企业短时间开具大量大额凭据";
                break;
            case 15:
                AbType = "同一用户/企业多次查验假发票";
                break;
            case 16:
                AbType = "同一系统多次尝试认证失败";
                break;
            case 17:
                AbType = "同一凭据频繁变更状态";
                break;
            default:
                AbType = null;
                break;
        }
        return AbType;
    }

    public static void AbBehavior_message_get(Connection conn, String AbBehaviorID, int EventCode) throws SQLException {
        //根据不同的异常行为ID与编码，输出不同格式的查询信息
        switch (EventCode) {
            case 0: {
                List<Map<String, Object>> information;
                information = PathCheck_judgeby_abtype.AbBehavior_Type00(conn, AbBehaviorID);
                if (information != null)
                    for (Map.Entry<String, Object> entry : information.get(0).entrySet()){
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                else System.out.println("data not found");
                break;
            }
            case 1: {
                List<Map<String, Object>> information;
                information = PathCheck_judgeby_abtype.AbBehavior_Type01(conn, AbBehaviorID);
                if (information != null)
                    for (Map.Entry<String, Object> entry : information.get(0).entrySet()){
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                else System.out.println("data not found");
                break;
            }
            case 2: {
                List<Map<String, Object>> information;
                information = PathCheck_judgeby_abtype.AbBehavior_Type02(conn, AbBehaviorID);
                if (information != null)
                    for (Map.Entry<String, Object> entry : information.get(0).entrySet()){
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                else System.out.println("data not found");
                break;
            }
            default:
                break;
        }
    }

    public static boolean Delete_AbBehavior(Connection conn, String AbBehaviorID) throws SQLException {
        //删除某ID对应的异常行为
        int EventCode = AbBehavior.EventCodeCheck(conn, AbBehaviorID);
        String tablename;
        if ((EventCode == 2) || (EventCode >= 9) && (EventCode <= 12) || (EventCode >= 15))
            tablename = "authabtable";
        else if ((EventCode == 0) || (EventCode == 1) || (EventCode == 4))
            tablename = "servabtable";
        else
            tablename = "credabtable";
        return DB_Operation.Delete(tablename, "abBehaviorID", AbBehaviorID);
    }
}
