package useit;

import java.sql.SQLException;
import java.util.Scanner;

import Public.DB_Operation;

public class login_regist {
	public static String Input(int closesc) {
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		if(closesc == 1) sc.close();
		return result;
	}
	public static void regist() throws SQLException {
		System.out.println("��������Ҫע����û�����");
		String user = Input(0);
		String checkuser = DB_Operation.Select("user","registmessage","user","'"+user+"'","user");
		if(checkuser != null) {
			System.out.println("�û����Ѵ��ڣ�");
			System.out.println("ע��ʧ�ܣ�");
			Journal.write(user, "����ע��ʧ��");
		}
		else {
			System.out.println("��������Ҫע������룺");
			String password = Input(0);
			String []Columnname = new String[2];
			String []value = new String[2];
			Columnname[0] = "user";
			Columnname[1] = "password";
			value[0] = user;
			value[1] = password;
			if(DB_Operation.Insert("registmessage",Columnname,2,value)) {
				System.out.println("ע��ɹ���");
				Journal.write(user, "����ע��ɹ�");
			}
			else{
				System.out.println("ע��ʧ�ܣ�");
				Journal.write(user, "����ע��ʧ��");
			}
		}
	}
	public static String login() throws SQLException {
		System.out.println("�����������û�����");
		String user = Input(0);
		String checkuser = DB_Operation.Select("user","registmessage","user","'"+user+"'","user");
		if(checkuser == null) {
			System.out.println("�û��������ڣ�");
			System.out.println("��¼ʧ�ܣ�");
			return null;
		}
		else {
			System.out.println("�������������룺");
			String password = Input(0);
			String checkpassword = DB_Operation.Select("password","registmessage","user","'"+user+"'","password");
			if(password.equals(checkpassword)) {
				System.out.println("������ȷ����¼�ɹ���");
				Journal.write(user, "���Ե�¼�ɹ�");
				return user;
			}
			else {
				System.out.println("������󣬵�¼ʧ�ܣ�");
				Journal.write(user, "���Ե�¼ʧ��");
				return user;
			}
		}
	}
}
