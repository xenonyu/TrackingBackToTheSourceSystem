package main.java.AreaCheck;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import main.java.Public.DB_Operation;
//实现基于IP地址的查询
public class IPcheck {
	public static String Input() {
		//输入方法
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		sc.close();
		return result;
	}
	public static int iptranslate(String strip) {
		//实现IP地址点分到整数的转换
		if(strip == null) return -1;
		String regex =
			"((\\d)|(\\d\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))"+"\\."
			+"((\\d)|(\\d\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))"+"\\."
			+"((\\d)|(\\d\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))"+"\\."
			+"((\\d)|(\\d\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))";
		Boolean judge = strip.matches(regex);
		if(judge == false) return -1;
		else{
			String []striptmp = strip.split("\\.");
			int []intiptmp = new int[4]; int intip = 0;
			for(int i=0;i<4;i++) {
				intiptmp[i] = Integer.valueOf(striptmp[3-i]);
				intip = (int)(intip+intiptmp[i]*Math.pow(256,i));
			}
			return intip;
		}
	}
	public static String tablecheck(Connection conn, int intip) throws SQLException {
		//查询目标IP地址的所在表
		List<Map<String, Object>> information = DB_Operation.Select(conn, "tableid", "tableindex", intip+" BETWEEN minip AND maxip");
		Integer tableid = Integer.parseInt(information.get(0).get("tableid").toString());
		DecimalFormat df = new DecimalFormat("0000");
		return df.format(tableid);
	}
	//
	public static List<Map<String, Object>> informationcheck(Connection conn, String tableid, int intip) throws SQLException {
		//查询目标表中的IP地址信息
		List<Map<String, Object>> information;
		information = DB_Operation.Select(conn, "*", "table"+tableid, intip+" BETWEEN minip AND maxip");
		if (information.get(0).get("multiarea") != null)
			information.get(0).put((String) information.get(0).get("multiarea"), chartran((String) information.get(0).get("multiarea")));
		return information;
	}
	public static String chartran(String str) {
		//multiarea中的转义字符处理
		int index = str.indexOf("'");
    	StringBuffer sb = new StringBuffer();
        while (index!=-1) {
        	str = (sb.append(str).insert(index,"'")).toString();
            index = str.indexOf("'", index+2);
        }
		return str;
	}
	public static String IPcheckmain(String strip)  {
		//IP地址查询主方法
		Connection conn = DB_Operation.GetConnection("ipdatabase");
		int intip = iptranslate(strip);
		if(intip == -1)
			return null;
		else {
			String tableid;
			try {
				tableid = tablecheck(conn, intip);
				List<Map<String, Object>> information = informationcheck(conn, tableid, intip);
				return "continent:"+information.get(0).get("continent")+" country:"+information.get(0).get("country")
						+" multiarea:"+information.get(0).get("multiarea")+" user:"+information.get(0).get("user");
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return " ";
	}
}