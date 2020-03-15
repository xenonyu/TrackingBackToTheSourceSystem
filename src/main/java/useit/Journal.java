package useit;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import Public.DB_Operation;
public class Journal {
	public static String gettime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	public static void write(String user, String event) throws SQLException {
		String []Columnname = new String[3];
		String []value = new String[3];
		Columnname[0] = "user";
		Columnname[1] = "time";
		Columnname[2] = "event";
		value[0] = user;
		value[1] = gettime();
		value[2] = event;
		DB_Operation.Insert("journal", Columnname, 3, value);
	}
}