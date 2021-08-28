package main.java.useit;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import main.java.Public.DB_Operation;

public class Journal {
    public static String gettime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static void write(String user, String event) throws SQLException, ClassNotFoundException {
        String[] Columnname = new String[3];
        Map<String, Object> value = new HashMap<String, Object>();
        Columnname[0] = "user";
        Columnname[1] = "time";
        Columnname[2] = "event";
        value.put(Columnname[0], user);
        value.put(Columnname[1], gettime());
        value.put(Columnname[2], event);
        Connection conn = DB_Operation.GetConnection("journal");
        DB_Operation.UpdateAbnormal(conn, "journal", Columnname, value);
    }
}