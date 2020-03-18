package abnormal_process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import protocol.MessageProtocol;
import protocol.ProtocolUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class AbnormalJson {
    private String jsonPath;
    private String jsonMessage;
    private String messageType = "null";

    public AbnormalJson(){
        setJsonPath(MessageProtocol.class.getClassLoader().getResource("abnormal.json").getPath());
        parseJsonMessage();
    }
    public AbnormalJson(String messageType){
        this.messageType = messageType;
        setJsonPath(MessageProtocol.class.getClassLoader().getResource("abnormal.json").getPath());
        parseJsonMessage();
    }

    private void setJsonPath(String path){
        this.jsonPath = path;
    }

    private void setJsonMessage(String message){
        this.jsonMessage = message;
    }
    private void parseJsonMessage(){
        String jsonString = ProtocolUtil.readJsonFile(jsonPath);
        JSONArray ycxwList = JSON.parseArray(jsonString);

        for (int i = 0; i < ycxwList.size(); i++){
            JSONObject yc = ycxwList.getJSONObject(i);
            String ycName = yc.getString("name");
//            System.out.println("ycName: " + ycName + " messageType: " + messageType);
            if(ycName.equals(messageType)){
                //s是String类型的时间戳
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String time= simpleDateFormat.format(date);
                System.out.println(time);
                JSONObject targetJson = yc.getJSONObject("ycxwData");

                targetJson.remove("timestamp");
                targetJson.put("timestamp", time);
                this.jsonMessage =yc.getString("ycxwData");
            }
        }
    }

    public String getMessage(){
        return this.jsonMessage;
    }

    public static void main(String args[]){
        AbnormalJson a = new AbnormalJson("dccskl");
        System.out.println(a.getMessage());

    }

}
