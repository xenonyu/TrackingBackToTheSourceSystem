package abnormal_process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import protocol.MessageProtocol;
import protocol.ProtocolUtil;

public class AbnormalJson {
    private String jsonPath;
    private String jsonMessage;
    private String messageType;

    public AbnormalJson(String messageType){
        this.messageType = messageType;
        setJsonPath(MessageProtocol.class.getClassLoader().getResource("abnormal.json").getPath());
        setJsonMessage();
    }

    private void setJsonPath(String path){
        this.jsonPath = path;
    }

    private void setJsonMessage(){
        String jsonString = ProtocolUtil.readJsonFile(jsonPath);
        JSONObject jsonObj = JSON.parseObject(jsonString);
        JSONArray root = jsonObj.getJSONArray("root");
        JSONObject ycxw = (JSONObject)root.get(0);
        JSONArray ycxwList = ycxw.getJSONArray("ycxw");

        for (int i = 0; i < ycxwList.size(); i++){
            JSONObject yc = ycxwList.getJSONObject(i);
            String ycName = yc.getString("name");
//            System.out.println("ycName: " + ycName + " messageType: " + messageType);
            if(ycName.equals(messageType)){
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
