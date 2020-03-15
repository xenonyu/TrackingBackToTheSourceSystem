package useit;
import java.sql.SQLException;
import java.util.Scanner;
import Public.DB_Operation;
import abnormal_process.*;
import ExternalFunction.*;
import Individual_judge.*;
import path_localize.*;

public class main_pro {
	public static String Input(int closesc) {
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		if(closesc == 1) sc.close();
		return result;
	}
	public static int select_login_regist() {
		System.out.println("请选择您要做什么：");
		System.out.println("1：登录    2：注册");
		String option = Input(0);
		if(option.equals("1")) return 1;
		else if(option.equals("2")) return 2;
		else return 0;
	}
	public static int select_function() {
		System.out.println("请选择您要使用的功能：");
		System.out.println("1：调用攻击路径定位接口");
		System.out.println("2：调用异常个体/区域判定接口");
		System.out.println("3：调用应急联动处置接口");
		System.out.println("4：调用其他功能");
		String option = Input(0);
		if(option.equals("1")) return 1;
		else if(option.equals("2")) return 2;
		else if(option.equals("3")) return 3;
		else if(option.equals("4")) return 4;
		else return 0;
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		DB_Operation.Connect("register");
		int option = select_login_regist();
		if(option==1) {
			String user = login_regist.login();
			if(user != null) {
				option = select_function();
				switch(option) {
				case 1:{
					Journal.write(user, "调用攻击路径定位接口");
					path_localize_main.main(args);
					break;
				}
				case 2:{
					Journal.write(user, "调用异常个体/区域判定接口");
					Individual_judge_main.main(args);
					break;
				}
				case 3:{
					Journal.write(user, "调用应急联动处置接口");
					abnormal_process_main.main(args);
					break;
				}
				case 4:{
					Journal.write(user, "调用其他功能");
					ExternalFunction_main.main(args);
					break;
				}
				default:{
					System.out.println("输入非法！");
					break;
				}
				}
			}
		}
		else if(option==2) login_regist.regist();
		else System.out.println("输入非法！");
		DB_Operation.Close();
	}
}
