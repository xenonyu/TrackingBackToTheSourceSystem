package main.java.path_localize;

import main.java.AreaCheck.IPcheck;
import main.java.abnormal_process.AbnormalJson;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HandleDifferentAbnormal {
    protected static final Map<String, Integer> mapType = new HashMap<>();
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

    public AbnormalJson dccskl(Map<String, Object> stringObjectMap) throws SQLException, ClassNotFoundException {
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
        return abJson;
    }
}


