package abnormal_process;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AbnormalJson {
    private String jsonMessage;
    private String threatID;
    private String timeStamp;
    private Integer threatType;
    private String uploadSysID;
    private String threatUserID;
    private String threatEnterpriseID;
    private String threatCredenID;
    private String threatIP;
    private String threatedIP; // 仅存在数据库
    private String threatIPSegment;
    private String otherMsg;
    private int originID; // 仅存在数据库

    private static final Map<String, Integer> threatTypeArray = new HashMap<>();

    static {
        threatTypeArray.put("服务器仿冒", 0x0101);
        threatTypeArray.put("假系统连接", 0x0102);
        threatTypeArray.put("用户多次尝试口令", 0x0201);
        threatTypeArray.put("重复报销、作废/冲红凭据报销", 0x0301);
        threatTypeArray.put("假发票报销", 0x0302);
        threatTypeArray.put("同一企业短时间开具大量凭据", 0x0303);
        threatTypeArray.put("同一企业短时间开具大量大额凭据", 0x0304);
        threatTypeArray.put("同一企业异常时间开具大量凭据", 0x0305);
        threatTypeArray.put("同一企业异常时间开具大额凭据", 0x0306);
        threatTypeArray.put("同一用户跨企业异常时间开具大量凭据", 0x0307);
        threatTypeArray.put("同一用户跨企业异常时间开具大量大额凭据", 0x0308);
        threatTypeArray.put("同一用户短时间跨企业开具大量大额凭据", 0x0309);
        threatTypeArray.put("同一用户/企业多次查验假发票", 0x0401);
        threatTypeArray.put("同一凭据频繁变更状态", 0x0501);
        threatTypeArray.put("同一用户频繁变更电子凭据状态", 0x0601);
        threatTypeArray.put("同一用户多次尝试认证失败", 0x0701);
        threatTypeArray.put("同一系统多次尝试认证失败", 0x0702);
        threatTypeArray.put("受票企业发生多次异常行为/开票企业发生多次异常行为", 0x0801);
        threatTypeArray.put("不同企业或不同用户异常行为关联", 0x0901);
        threatTypeArray.put("多次查询/下载", 0x0A01);
        threatTypeArray.put("客票信息多次查验状态", 0x0B01);

    }

    public AbnormalJson(Integer threatType,
                        String upLoadSysID,
                        String threatUserID,
                        String EnterpriseID,
                        String CredenID,
                        String threatIP,
                        String threatedIP) {
/**
 *timeStamp是String类型的时间戳
 * 这里设置发送消息
 * */
        setThreatID(Long.toString(getMicTime()));
        setTimeStamp(Long.toString(getMicTime()));
        setThreatType(threatType);
        setUploadSysID(upLoadSysID);
        setThreatUserID(threatUserID);
        setThreatEnterpriseID(EnterpriseID);
        setThreatCredenID(CredenID);
        setThreatIP(threatIP);
        setThreatedIP(threatedIP);
        setThreatIPSegment("");
        setOtherMsg("");
        /**
         * if threat type is 0x0800 - 0x0900 add other msg
         */
        if (getThreatType() != null) {
            if (getThreatType() > 2048 && getThreatType() < 2304) {
                setOtherMsg(String.valueOf(getRandom(1, 4)));
            }
            if (getThreatType() > 1536 && getThreatType() < 1792) {
                setOtherMsg(String.valueOf(getRandom(1, 3)));
            }
        }
    }

    public void analysisOtherMsg(){
        if (getThreatType() != null) {
            if (getThreatType() > 2048 && getThreatType() < 2304) {
                setOtherMsg(String.valueOf(getRandom(1, 4)));
            }
            else if (getThreatType() > 1536 && getThreatType() < 1792) {
                setOtherMsg(String.valueOf(getRandom(1, 3)));
            }
            else{
                setOtherMsg("");
            }
        }
    }

    public AbnormalJson(String threatType) {
        /**
         *timeStamp是String类型的时间戳
         * 这里设置发送消息
         * */
        setThreatID(Long.toString(getMicTime()));
        setTimeStamp(Long.toString(getMicTime()));
        setThreatType(threatTypeArray.get(threatType));
        setUploadSysID("100002");
        setThreatUserID("49509946");
        setThreatEnterpriseID("34433691");
        setThreatCredenID("4134312");
        String[] threatIPArray = {"123.46.78.234", "123.46.78.242", "123.45.78.234"};
        setThreatIP(threatIPArray[getRandom(0, threatIPArray.length)]);
        setThreatIPSegment(threatIPArray[getRandom(0, threatIPArray.length)] + "/" + getRandom(0, 24));
        setOtherMsg("");
        /**
         * if threat type is 0x0800 - 0x0900 add other msg
         */
        if (getThreatType() != null) {
            if (getThreatType() > 2048 && getThreatType() < 2304) {
                setOtherMsg(String.valueOf(getRandom(1, 4)));
            }
            if (getThreatType() > 1536 && getThreatType() < 1792) {
                setOtherMsg(String.valueOf(getRandom(1, 3)));
            }
            parseJsonMessage();
        }

    }

    public AbnormalJson() {
        setThreatID(Long.toString(getMicTime()));
        setTimeStamp(Long.toString(getMicTime()));
    }

    public static void main(String[] args) {
        AbnormalJson a = new AbnormalJson("受票企业发生多次异常行为/开票企业发生多次异常行为");

    }

    private void parseJsonMessage() {
        JSONObject targetJson = new JSONObject();

        targetJson.put("threatID", getThreatID());
        targetJson.put("timeStamp", getTimeStamp());
        targetJson.put("threatType", getThreatType());
        targetJson.put("uploadSysID", getUploadSysID());
        targetJson.put("threatUserID", getThreatUserID());
        targetJson.put("threatEnterpriseID", getThreatEnterpriseID());
        targetJson.put("threatCredenID", getThreatCredenID());
        targetJson.put("threatIP", getThreatIP());
        targetJson.put("threatIPSegment", getThreatIPSegment());
        targetJson.put("otherMsg", getOtherMsg());
//        System.out.println("output json: " + targetJson.toString());
//        System.out.println("other msg: " + targetJson.getString("otherMsg"));

        setJsonMessage(targetJson.toString());
    }

    public static Long getMicTime() {
        long currTime = System.currentTimeMillis() * 1000; // 微秒
        long nanoTime = System.nanoTime(); // 纳秒
        return currTime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
    }

    public static int getRandom(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public String getMessage() {
        return this.jsonMessage;
    }

    public String getJsonMessage() {
        return jsonMessage;
    }

    public String getThreatIP() {
        return threatIP;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getThreatID() {
        return threatID;
    }

    public Integer getThreatType() {
        return threatType;
    }

    public String getUploadSysID() {
        return uploadSysID;
    }

    public String getThreatUserID() {
        return threatUserID;
    }

    public String getThreatEnterpriseID() {
        return threatEnterpriseID;
    }

    public String getThreatCredenID() {
        return threatCredenID;
    }

    public String getThreatIPSegment() {
        return threatIPSegment;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setThreatID(String threatID) {
        this.threatID = threatID;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setThreatType(Integer threatType) {
        this.threatType = threatType;
    }

    public void setUploadSysID(String uploadSysID) {
        this.uploadSysID = uploadSysID;
    }

    public void setThreatUserID(String threatUserID) {
        this.threatUserID = threatUserID;
    }

    public void setThreatEnterpriseID(String threatEnterpriseID) {
        this.threatEnterpriseID = threatEnterpriseID;
    }

    public void setThreatCredenID(String threatCredenID) {
        this.threatCredenID = threatCredenID;
    }

    public void setThreatIP(String threatIP) {
        this.threatIP = threatIP;
    }

    public void setThreatIPSegment(String threatIPSegment) {
        this.threatIPSegment = threatIPSegment;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    private void setJsonMessage(String message) {
        this.jsonMessage = message;
    }


    public int getOriginID() {return originID; }

    public void setOriginID(int originID) { this.originID = originID; }

    public String getThreatedIP() {
        return threatedIP;
    }

    public void setThreatedIP(String threatedIP) {
        this.threatedIP = threatedIP;
    }

}
