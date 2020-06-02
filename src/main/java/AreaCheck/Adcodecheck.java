package AreaCheck;
import java.sql.SQLException;
import java.util.Scanner;
import Public.DB_Operation;
//实现基于行政区划编码的查询
public class Adcodecheck {
	public static String Input() {
		//输入方法
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		sc.close();
		return result;
	}
	public static void areacheck(String adcode) throws SQLException {
		//查询区域信息
		if(adcode.length()!=6) 
			System.out.println("Input illegal!");
		else{
			String areaname = "areaname";
			areaname = DB_Operation.Select("areaname", "adcodedatabase.adcodetable", "adcode", "'"+adcode+"'", areaname);
			if(areaname==null) 
				System.out.println("No data can be found!");
			else {
				System.out.println("The results are as follows:");
				System.out.println("areaname:"+areaname);
				System.out.println("The acquisition of data is successful!");
			}		
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DB_Operation.Connect("adcodedatabase");
		System.out.println("Please input the 6bit administrative division code:");
		String adcode = Input();
		//long startTime = System.currentTimeMillis();
		areacheck(adcode);
		//long endTime = System.currentTimeMillis();
		//System.out.println("The time costs:"+(endTime - startTime)+"ms");
		DB_Operation.Close();
	}
}