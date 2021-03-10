package path_localize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AreaCheck.IPcheck;
import Public.DB_Operation;
import abnormal_process.AbnormalJson;

//攻击路径定位接口
public class path_localize_main {
    private static final Map<String, Integer> threatTypeArray = new HashMap<>();
    static {
        threatTypeArray.put("dccskl", 0x01);
        threatTypeArray.put("dczsrzsb", 0x02);
        threatTypeArray.put("jfpbx", 0x03);
        threatTypeArray.put("jxtlj", 0x04);
        threatTypeArray.put("cfzfchbx", 0x05);
        threatTypeArray.put("ycsjkjde", 0x06);
        threatTypeArray.put("ycsjkjdl", 0x07);
        threatTypeArray.put("dsjkqykjdlde", 0x08);
        threatTypeArray.put("yhtypjdchzsb", 0x09);
        threatTypeArray.put("yhbtpjdchzsb", 0x0A);
        threatTypeArray.put("yhdccsrzsb", 0x0B);
        threatTypeArray.put("yhpfbgpjzt", 0x0C);
        threatTypeArray.put("dsjkjdl", 0x0D);
        threatTypeArray.put("dsjkjdlde", 0x0E);
        threatTypeArray.put("dccyjfp", 0x0F);
        threatTypeArray.put("xtdccsrzsb", 0x10);
        threatTypeArray.put("pjpfbgzt", 0x11);
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
    public static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
        HashMap<V,K> rev = new HashMap<>();
        for(Map.Entry<K,V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }
    static Map<Integer, String> swapped;
    public path_localize_main(){
        swapped = reverse(threatTypeArray);
    }
    public static boolean Deal_inputdata(Connection conn) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> information;
        information = DB_Operation.Select(conn, "*", "dccskl", "id>=0");
        for (Map<String, Object> stringObjectMap : information) {
            if (stringObjectMap.get("id") == null) return false;
            stringObjectMap.put("sysAreaName", IPcheck.IPcheckmain((String) stringObjectMap.get("appIP")));
            AbnormalJson abJson = new AbnormalJson();
            abJson.setThreatType(
                    mapType.get(stringObjectMap.get("eventCode"))
            );
            abJson.setOriginID((Integer) stringObjectMap.get("id"));
            abJson.setUploadSysID((String) stringObjectMap.get("pccCode"));
            abJson.setThreatUserID((String) stringObjectMap.get("userID"));
            abJson.setThreatEnterpriseID("");
            abJson.setThreatCredenID("");
            abJson.setThreatIP((String) stringObjectMap.get("appIP"));
            abJson.setThreatedIP("");
            if (!DB_Operation.Insert(conn, abJson)) return false;
        }
        information = DB_Operation.Select(conn, "*", "dczsrzsb", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "jfpbx", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "jxtlj", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "cfzfchbx", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "yhtypjdchzsb", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "yhbtpjdchzsb", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "yhdccsrzsb", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "yhpfbgpjzt", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "dccyjfp", "id>=0");
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
        information = DB_Operation.Select(conn, "*", "xtdccsrzsb", "id>=0");
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection abnormalConn = DB_Operation.Connect("dzpj_aqts_202000628");
        DB_Operation.Connect("abbehavior");
        if (Deal_inputdata(abnormalConn)) System.out.println("Successful!");
        else System.out.println("falied!");
        DB_Operation.Close();
    }
}
