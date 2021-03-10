package useit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Public.DB_Operation;

public class login_regist {
    public static String Input(int closesc) {
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        if (closesc == 1) sc.close();
        return result;
    }

    public static void regist() throws SQLException, ClassNotFoundException {
        Connection conn = DB_Operation.Connect("registmessage");
        System.out.println("请输入您要注册的用户名：");
        String user = Input(0);
        List<Map<String, Object>> checkuser = DB_Operation.Select(conn,"user", "registmessage", "user="+user);
        if (!checkuser.isEmpty()) {
            System.out.println("用户名已存在！");
            System.out.println("注册失败！");
            Journal.write(user, "尝试注册失败");
        } else {
            System.out.println("请输入您要注册的密码：");
            String password = Input(0);
            String[] Columnname = new String[2];
            Map<String, Object> value = new HashMap<String, Object>();
            Columnname[0] = "user";
            Columnname[1] = "password";
            value.put("user", user);
            value.put("password", password);
            if (DB_Operation.Insert(conn, "registmessage", Columnname, value)) {
                System.out.println("注册成功！");
                Journal.write(user, "尝试注册成功");
            } else {
                System.out.println("注册失败！");
                Journal.write(user, "尝试注册失败");
            }
        }
    }

    public static String login() throws SQLException, ClassNotFoundException {
        Connection conn = DB_Operation.Connect("registmessage");
        System.out.println("请输入您的用户名：");
        String user = Input(0);
        List<Map<String, Object>> checkuser = DB_Operation.Select(conn,"user", "registmessage", "user="+user);
        if (!checkuser.isEmpty()) {
            System.out.println("用户名不存在！");
            System.out.println("登录失败！");
            return null;
        } else {
            System.out.println("请输入您的密码：");
            String password = Input(0);
            List<Map<String, Object>> checkpassword = DB_Operation.Select(conn, "password", "registmessage", "user=" +user);
            if (password.equals(checkpassword.get(0).get("password"))) {
                System.out.println("密码正确，登录成功！");
                Journal.write(user, "尝试登录成功");
                return user;
            } else {
                System.out.println("密码错误，登录失败！");
                Journal.write(user, "尝试登录失败");
                return user;
            }
        }
    }
}
