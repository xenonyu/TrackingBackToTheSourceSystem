package main.java.AreaCheck;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import main.java.Public.DB_Operation;
//实现基于行政区划编码的查询
public class Adcodecheck {
	public static String Input() {
		//输入方法
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		sc.close();
		return result;
	}
	public static void areacheck(Connection conn, String adcode) throws SQLException {
		//查询区域信息
		if(adcode.length()!=6) 
			System.out.println("Input illegal!");
		else{
			List<Map<String, Object>> areaname = DB_Operation.Select(conn, "areaname", "adcodedatabase.adcodetable", "adcode="+adcode);
			if(areaname.isEmpty())
				System.out.println("No data can be found!");
			else {
				System.out.println("The results are as follows:");
				System.out.println("areaname: " + areaname.get(0).get("areaname"));
				System.out.println("The acquisition of data is successful!");
			}		
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = DB_Operation.GetConnection("adcodedatabase");
		System.out.println("Please input the 6bit administrative division code:");
		String adcode = Input();
		//long startTime = System.currentTimeMillis();
		areacheck(conn, adcode);
		//long endTime = System.currentTimeMillis();
		//System.out.println("The time costs:"+(endTime - startTime)+"ms");
	}
}