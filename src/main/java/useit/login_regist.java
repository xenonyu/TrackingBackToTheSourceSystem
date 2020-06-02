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
		System.out.println("ÇëÊäÈëÄúÒª×¢²áµÄÓÃ»§Ãû£º");
		String user = Input(0);
		String checkuser = DB_Operation.Select("user","registmessage","user","'"+user+"'","user");
		if(checkuser != null) {
			System.out.println("ÓÃ»§ÃûÒÑ´æÔÚ£¡");
			System.out.println("×¢²áÊ§°Ü£¡");
			Journal.write(user, "³¢ÊÔ×¢²áÊ§°Ü");
		}
		else {
			System.out.println("ÇëÊäÈëÄúÒª×¢²áµÄÃÜÂë£º");
			String password = Input(0);
			String []Columnname = new String[2];
			String []value = new String[2];
			Columnname[0] = "user";
			Columnname[1] = "password";
			value[0] = user;
			value[1] = password;
			if(DB_Operation.Insert("registmessage",Columnname,2,value)) {
				System.out.println("×¢²á³É¹¦£¡");
				Journal.write(user, "³¢ÊÔ×¢²á³É¹¦");
			}
			else{
				System.out.println("×¢²áÊ§°Ü£¡");
				Journal.write(user, "³¢ÊÔ×¢²áÊ§°Ü");
			}
		}
	}
	public static String login() throws SQLException {
		System.out.println("ÇëÊäÈëÄúµÄÓÃ»§Ãû£º");
		String user = Input(0);
		String checkuser = DB_Operation.Select("user","registmessage","user","'"+user+"'","user");
		if(checkuser == null) {
			System.out.println("ÓÃ»§Ãû²»´æÔÚ£¡");
			System.out.println("µÇÂ¼Ê§°Ü£¡");
			return null;
		}
		else {
			System.out.println("ÇëÊäÈëÄúµÄÃÜÂë£º");
			String password = Input(0);
			String checkpassword = DB_Operation.Select("password","registmessage","user","'"+user+"'","password");
			if(password.equals(checkpassword)) {
				System.out.println("ÃÜÂëÕıÈ·£¬µÇÂ¼³É¹¦£¡");
				Journal.write(user, "³¢ÊÔµÇÂ¼³É¹¦");
				return user;
			}
			else {
				System.out.println("ÃÜÂë´íÎó£¬µÇÂ¼Ê§°Ü£¡");
				Journal.write(user, "³¢ÊÔµÇÂ¼Ê§°Ü");
				return user;
			}
		}
	}
}
