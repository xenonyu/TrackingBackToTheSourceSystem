package AreaCheck;
import java.sql.SQLException;
import java.util.Scanner;
import Public.DB_Operation;
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
	public static String tablecheck(int intip) throws SQLException {
		//查询目标IP地址的所在表
		String tableid = DB_Operation.Select("tableid", "ipdatabase.tableindex", intip+" BETWEEN minip AND maxip", "tableid");
		if(tableid.length()==1) tableid = "000"+tableid;
		else if(tableid.length()==2) tableid = "00"+tableid;
		else if(tableid.length()==3) tableid = "0"+tableid;
		return tableid;
	}
	//
	public static String[] informationcheck(String tableid, int intip) throws SQLException {
		//查询目标表中的IP地址信息
		String []information = new String[4];
		information[0] = "continent";
		information[1] = "country";
		information[2] = "multiarea";
		information[3] = "user";
		information = DB_Operation.Select("*", "table"+tableid, String.valueOf(intip)+" BETWEEN minip AND maxip LIMIT 1", information, 4);
		information[2] = chartran(information[2]);
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
	public static String IPcheckmain(String strip) throws ClassNotFoundException, SQLException {
		//IP地址查询主方法
		DB_Operation.Connect("ipdatabase");
		int intip = iptranslate(strip);
		if(intip == -1) 
			return null;
		else {
			String tableid = tablecheck(intip);
			String[]information = informationcheck(tableid, intip);
			String Areamessage = "continent:"+information[0]+" country:"+information[1]
					+" multiarea:"+information[2]+" user:"+information[3];
			return Areamessage;
		}
	}
}