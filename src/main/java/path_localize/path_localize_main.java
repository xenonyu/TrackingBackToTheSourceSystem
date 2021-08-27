package main.java.path_localize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import main.java.AreaCheck.IPcheck;
import main.java.Public.DB_Operation;
import main.java.abnormal_process.AbnormalJson;
import java.util.Map;

//攻击路径定位接口
public class path_localize_main {
    private static final Map<String, Integer> threatType2Code = new HashMap<>();
    static {
        threatType2Code.put("dccskl", 0x01);
        threatType2Code.put("dczsrzsb", 0x02);
        threatType2Code.put("jfpbx", 0x03);
        threatType2Code.put("jxtlj", 0x04);
        threatType2Code.put("cfzfchbx", 0x05);
        threatType2Code.put("ycsjkjde", 0x06);
        threatType2Code.put("ycsjkjdl", 0x07);
        threatType2Code.put("dsjkqykjdlde", 0x08);
        threatType2Code.put("yhtypjdchzsb", 0x09);
        threatType2Code.put("yhbtpjdchzsb", 0x0A);
        threatType2Code.put("yhdccsrzsb", 0x0B);
        threatType2Code.put("yhpfbgpjzt", 0x0C);
        threatType2Code.put("dsjkjdl", 0x0D);
        threatType2Code.put("dsjkjdlde", 0x0E);
        threatType2Code.put("dccyjfp", 0x0F);
        threatType2Code.put("xtdccsrzsb", 0x10);
        threatType2Code.put("pjpfbgzt", 0x11);
    }
    private static final Map<String, Integer> mapType = new HashMap<>();
    static {
        mapType.put("0x01", 0x01);
        mapType.put("0x02", 0x02);
        mapType.put("0x03", 0x03);
        mapType.put("0x04", 0x04);
        mapType.put("0x05", 0x05);
        mapType.put("0x06", 0x06);
        mapType.put("0x07", 0x07);
        mapType.put("0x08", 0x08);
        mapType.put("0x09", 0x09);
        mapType.put("0x0A", 0x0A);
        mapType.put("0x0B", 0x0B);
        mapType.put("0x0C", 0x0C);
        mapType.put("0x0D", 0x0D);
        mapType.put("0x0E", 0x0E);
        mapType.put("0x0F", 0x0F);
        mapType.put("0x10", 0x10);
        mapType.put("0x11", 0x11);
    }
    private static final Map<String, Integer> threatType2StartId = new HashMap<>();
    static {
        threatType2StartId.put("dccskl", 0);
        threatType2StartId.put("dczsrzsb", 0);
        threatType2StartId.put("jfpbx", 0);
        threatType2StartId.put("jxtlj", 0);
        threatType2StartId.put("cfzfchbx", 0);
        threatType2StartId.put("ycsjkjde", 0);
        threatType2StartId.put("ycsjkjdl", 0);
        threatType2StartId.put("dsjkqykjdlde", 0);
        threatType2StartId.put("yhtypjdchzsb", 0);
        threatType2StartId.put("yhbtpjdchzsb", 0);
        threatType2StartId.put("yhdccsrzsb", 0);
        threatType2StartId.put("yhpfbgpjzt", 0);
        threatType2StartId.put("dsjkjdl", 0);
        threatType2StartId.put("dsjkjdlde", 0);
        threatType2StartId.put("dccyjfp", 0);
        threatType2StartId.put("xtdccsrzsb", 0);
        threatType2StartId.put("pjpfbgzt", 0);
    }
    public static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
        HashMap<V,K> rev = new HashMap<>();
        for(Map.Entry<K,V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }
    static final Map<Integer, String> swapped = reverse(((threatType2Code)));

//    private static map
    public static boolean Deal_inputdata(Connection conn) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> information;
        String tableName;
        Integer startId;
        HandleDifferentAbnormal handler = new HandleDifferentAbnormal();
        for (String t : threatType2Code.keySet()){
            if (!t.equals("dccskl")) continue;
            startId = threatType2StartId.get(t);
            information = DB_Operation.Select(conn, "*", t, "id>=" + startId);
            for (Map<String, Object> stringObjectMap : information) {
                if (stringObjectMap.get("id") == null) return false;
                try {
                    Method handle = handler.getClass().getMethod(t, Map.class);
                    AbnormalJson abJson = (AbnormalJson) handle.invoke(handler, stringObjectMap);
                    if (!DB_Operation.Insert(conn, abJson)) return false;
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }

        tableName = "dczsrzsb";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("authCertiUserID"));
            abJson.setThreatEnterpriseID((String) stringObjectMap.get("authCertiSysID"));
            abJson.setThreatCredenID("");
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="jfpbx";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("agent"));
            abJson.setThreatEnterpriseID((String) stringObjectMap.get("paymentCompany"));
            abJson.setThreatCredenID((String)stringObjectMap.get("netticketID"));
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="jxtlj";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID("");
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID("");
            abJson.setThreatIP((String) stringObjectMap.get("fakeSysIP"));
            abJson.setThreatedIP((String) stringObjectMap.get("appIP"));
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="cfzfchbx";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("agent"));
            abJson.setThreatEnterpriseID((String) stringObjectMap.get("paymentCompany"));
            abJson.setThreatCredenID((String) stringObjectMap.get("netticketID"));
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="yhtypjdchzsb";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("agent"));
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID((String) stringObjectMap.get("netticketID"));
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="yhbtpjdchzsb";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("agent"));
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID((String) stringObjectMap.get("netticketID"));
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="yhdccsrzsb";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("authUserID"));
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID("");
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="yhpfbgpjzt";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("Agent"));
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID((String)stringObjectMap.get("netticketID"));
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="dccyjfp";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("appID"));
            abJson.setThreatUserID((String) stringObjectMap.get("appUserID"));
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID("");
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        tableName ="xtdccsrzsb";
        startId = threatType2StartId.get(tableName);
        information = DB_Operation.Select(conn, "*", tableName, "id>=" + startId);
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("authSysID"));
            abJson.setThreatUserID("");
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID("");
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        return true;

    }

    public static void handleAll() throws ClassNotFoundException, SQLException{
        Connection abnormalConn = DB_Operation.GetConnection("dzpj_aqts_202000628");
        DB_Operation.GetConnection("abbehavior");
        if (Deal_inputdata(abnormalConn)) System.out.println("Successful!");
        else System.out.println("falied!");
    }

    public static void main(String[] args)  {
        Date startDate = new Date();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    handleAll();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }, startDate,  1000);
    }
}
