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
		System.out.println("��ѡ����Ҫ��ʲô��");
		System.out.println("1����¼    2��ע��");
		String option = Input(0);
		if(option.equals("1")) return 1;
		else if(option.equals("2")) return 2;
		else return 0;
	}
	public static int select_function() {
		System.out.println("��ѡ����Ҫʹ�õĹ��ܣ�");
		System.out.println("1�����ù���·����λ�ӿ�");
		System.out.println("2�������쳣����/�����ж��ӿ�");
		System.out.println("3������Ӧ���������ýӿ�");
		System.out.println("4��������������");
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
					Journal.write(user, "���ù���·����λ�ӿ�");
					path_localize_main.main(args);
					break;
				}
				case 2:{
					Journal.write(user, "�����쳣����/�����ж��ӿ�");
					Individual_judge_main.main(args);
					break;
				}
				case 3:{
					Journal.write(user, "����Ӧ���������ýӿ�");
					abnormal_process_main.main(args);
					break;
				}
				case 4:{
					Journal.write(user, "������������");
					ExternalFunction_main.main(args);
					break;
				}
				default:{
					System.out.println("����Ƿ���");
					break;
				}
				}
			}
		}
		else if(option==2) login_regist.regist();
		else System.out.println("����Ƿ���");
		DB_Operation.Close();
	}
}
