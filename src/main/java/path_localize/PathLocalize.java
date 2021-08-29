package main.java.path_localize;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;

import main.java.Public.DB_Operation;
import main.java.abnormal_process.AbnormalJson;
import main.java.protocol.LongClient;
import sun.rmi.runtime.Log;

import java.util.Map;

class ThreatTypeUtil {
    public Integer getAnalysisCode() {
        return analysisCode;
    }

    public void setAnalysisCode(Integer analysisCode) {
        this.analysisCode = analysisCode;
    }

    public Integer getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(Integer handleCode) {
        this.handleCode = handleCode;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    private Integer analysisCode;
    private Integer handleCode;
    private Integer startId;

    ThreatTypeUtil(Integer analysisCode, Integer handleCode, Integer startId ) {
        this.analysisCode = analysisCode;
        this.handleCode = handleCode;
        this.startId = startId;
    }
}
//攻击路径定位接口
public class PathLocalize {
    private final Connection conn = DB_Operation.GetConnection("ycxw");
    //DB_Operation.GetConnection("dzpj_aqts_202000628");
    public static final Map<String, ThreatTypeUtil> threatType2Code = new HashMap<String, ThreatTypeUtil>() {
        {
            put("dccskl",       new ThreatTypeUtil(0x01, 0x0200, 0));
            put("jfpbx",        new ThreatTypeUtil(0x03, 0x0302, 0));
            put("jxtlj",        new ThreatTypeUtil(0x04, 0x0102, 0));
            put("cfzfchbx",     new ThreatTypeUtil(0x05, 0x0301, 0));
            put("yhtypjdchzsb", new ThreatTypeUtil(0x09, 0x0C01, 0));
            put("yhbtpjdchzsb", new ThreatTypeUtil(0x0A, 0x0C02, 0));
            put("yhdccsrzsb",   new ThreatTypeUtil(0x0B, 0x0701, 0));
            put("yhpfbgpjzt",   new ThreatTypeUtil(0x0C, 0x0601, 0));
            put("dccyjfp",      new ThreatTypeUtil(0x0F, 0x0401, 0));
            put("xtdccsrzsb",   new ThreatTypeUtil(0x10, 0x0702, 0));
            put("pjpfbgzt",     new ThreatTypeUtil(0x11, 0x0501, 0));
            put("tyyhdccxxz",   new ThreatTypeUtil(0x12, 0x0A01, 0));
            put("tykpcfjp",     new ThreatTypeUtil(0x13, 0x0B01, 0));
            put("ytyxkpcfjp",   new ThreatTypeUtil(0x14, 0x0B01, 0));
            put("cxezlkj",      new ThreatTypeUtil(0x15, 0x0301, 0)); // TODO
        }
    };

//    private static map
    public boolean Deal_inputdata() {
        HandleDifferentAbnormal handler = new HandleDifferentAbnormal();
        Vector<Thread> threadPool = new Vector<>();
        for (String t : threatType2Code.keySet()){
            Integer startId;
            startId = threatType2Code.get(t).getStartId();
            Thread job = new Thread(() -> {
                try
                {
                    List<Map<String, Object>> information;
                    information = DB_Operation.Select(conn, "*", t, "id>" + startId);
                    for (Map<String, Object> stringObjectMap : information) {
                        Method handle = handler.getClass().getMethod(t, Map.class);
                        AbnormalJson abJson = (AbnormalJson) handle.invoke(handler, stringObjectMap);
                        DB_Operation.UpdateAbnormal(conn, abJson);
                        abJson.setThreatType(threatType2Code.get(t).getHandleCode());
                        abJson.analysisOtherMsg();
                        abJson.parseJsonMessage();
                        LongClient.putAbnormal(abJson);
                        if (abJson.getOriginID() > startId) threatType2Code.get(t).setStartId(abJson.getOriginID());
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            });
            job.start();
            threadPool.add(job);
        }

        for (Thread t : threadPool) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("main thread start");
        return true;

    }

    public void handleAll(){
        Date startDate = new Date();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Deal_inputdata();
            }
        }, startDate,  1000);
    }

    public static void main(String[] args)  {
        PathLocalize pathLocalize = new PathLocalize();
        LongClient.main(new String[]{});
        pathLocalize.handleAll();
    }
}
