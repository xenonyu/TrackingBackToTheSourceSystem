package Public;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//对数据库的公共操作
public class DB_Operation {
	public static Connection con;
	static String driver = "com.mysql.jdbc.Driver";
	static String databaseuser = "root";
	static String password = "ccflab";
	public static void Connect(String DB_name) throws ClassNotFoundException, SQLException {
		//数据库连接
		Class.forName(driver);
		String url = "jdbc:mysql://202.120.39.22:33060/"+DB_name+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		con = DriverManager.getConnection(url,databaseuser,password);
	}
	public static void Close() throws SQLException {
		//数据库关闭
		con.close();
	}
	//几种不同的增删改查方法
	public static String[] Select(String Columnname, String tablename, String[]result, int resultnum) throws SQLException {
		String sql = "select  from ";
		StringBuffer sb = new StringBuffer();
		Statement statement = DB_Operation.con.createStatement();
		sb.append(sql).insert(7, Columnname);
		sb.append(sql).insert(13+Columnname.length(), tablename);
		String sqltemp = sb.substring(0, sb.length()-sql.length());
		ResultSet rs = statement.executeQuery(sqltemp);
		if(rs.next()) {
			String []information = new String[resultnum];
			for(int i=0; i<resultnum; i++)
				information[i] = rs.getString(result[i]);
			return information;
		}
		else return null;
	}
	public static String[][] SelectMore(String Columnname, String tablename, String[]result, int resultnum) throws SQLException {
		String sql = "select  from ";
		StringBuffer sb = new StringBuffer();
		Statement statement = DB_Operation.con.createStatement();
		sb.append(sql).insert(7, Columnname);
		sb.append(sql).insert(13+Columnname.length(), tablename);
		String sqltemp = sb.substring(0, sb.length()-sql.length());
		ResultSet rs = statement.executeQuery(sqltemp);
		String[][]information = new String[100][resultnum];
		for(int i=0;i<100;i++){
			if(rs.next()==false) break;
			for(int j=0; j<resultnum; j++) 
				information[i][j] = rs.getString(result[j]);
		}
		return information;
	}
	public static String Select(String Columnname, String tablename, String condition, String result) throws SQLException {
		String sql = "select  from  where ";
		StringBuffer sb = new StringBuffer();
		Statement statement = DB_Operation.con.createStatement();
		sb.append(sql).insert(7, Columnname);
		sb.append(sql).insert(13+Columnname.length(), tablename);
		sb.append(sql).insert(20+Columnname.length()+tablename.length(), condition);
		String sqltemp = sb.substring(0, sb.length()-sql.length()*2);
		ResultSet rs = statement.executeQuery(sqltemp);
		if(rs.next()) {
			String information = rs.getString(result);
			return information;
		}
		else return null;
	}	
	public static String[] Select(String Columnname, String tablename, String condition, String[]result, int resultnum) throws SQLException {
		String sql = "select  from  where ";
		StringBuffer sb = new StringBuffer();
		Statement statement = DB_Operation.con.createStatement();
		sb.append(sql).insert(7, Columnname);
		sb.append(sql).insert(13+Columnname.length(), tablename);
		sb.append(sql).insert(20+Columnname.length()+tablename.length(), condition);
		String sqltemp = sb.substring(0, sb.length()-sql.length()*2);
		ResultSet rs = statement.executeQuery(sqltemp);
		if(rs.next()) {
			String []information = new String[resultnum];
			for(int i=0; i<resultnum; i++) 
				information[i] = rs.getString(result[i]);
			return information;
		}
		else return null;
	}
	public static String Select(String Columnname, String tablename, String condition, String value, String result) throws SQLException {
		String sql = "select  from  where =";
		StringBuffer sb = new StringBuffer();
		Statement statement = DB_Operation.con.createStatement();
		sb.append(sql).insert(7, Columnname);
		sb.append(sql).insert(13+Columnname.length(), tablename);
		sb.append(sql).insert(20+Columnname.length()+tablename.length(), condition);
		sb.append(sql).insert(21+Columnname.length()+tablename.length()+condition.length(), value);
		String sqltemp = sb.substring(0, sb.length()-sql.length()*3);
		ResultSet rs = statement.executeQuery(sqltemp);
		if(rs.next()) {
			String information = rs.getString(result);
			return information;
		}
		else return null;
	}
	public static String[] Select(String Columnname, String tablename, String condition, String value, String[]result, int resultnum) throws SQLException {
		String sql = "select  from  where =";
		StringBuffer sb = new StringBuffer();
		Statement statement = DB_Operation.con.createStatement();
		sb.append(sql).insert(7, Columnname);
		sb.append(sql).insert(13+Columnname.length(), tablename);
		sb.append(sql).insert(20+Columnname.length()+tablename.length(), condition);
		sb.append(sql).insert(21+Columnname.length()+tablename.length()+condition.length(), value);
		String sqltemp = sb.substring(0, sb.length()-sql.length()*3);
		ResultSet rs = statement.executeQuery(sqltemp);
		if(rs.next()) {
			String []information = new String[resultnum];
			for(int i=0; i<resultnum; i++) 
				information[i] = rs.getString(result[i]);
			return information;
		}
		else return null;
	}
	public static boolean Insert(String tablename, String[]Columnname, int Columnnum, String[]value) throws SQLException {
		if(Columnnum<=0) return false;
		String sql = "insert into () values('')";
		StringBuffer sb = new StringBuffer();
		for(int i=1; i<Columnnum; i++) 
			sb.append(sql).insert(13, ",");
		for(int i=1; i<Columnnum; i++) 
			sb.append(sql).insert(23+Columnnum, ",''");
		sb.append(sql).insert(12, tablename);
		int length = tablename.length();
		int i,j;
		for(i=0; i<Columnnum; length+=Columnname[i++].length())
			sb.append(sql).insert(13+i+length, Columnname[i]);
		for(j=0; j<Columnnum; length+=value[j++].length()) {
			if(value[j]==null) value[j]="null";
			sb.append(sql).insert(22+i+j*3+length, value[j]);
		}
		String sqltemp = sb.substring(0, sb.length()-sql.length()*(4*Columnnum-2));
		Statement statement = DB_Operation.con.createStatement();
		if(statement.executeUpdate(sqltemp)>0) return true;
		else return false;
	}
	public static boolean Update(String tablename, String[]Columnname, int Columnnum, String[]value, String condition, String convalue) throws SQLException {
		if(Columnnum<=0) return false;
		String sql = "update  set  where =''";
		StringBuffer sb = new StringBuffer();
		sb.append(sql).insert(7, tablename);
		int length = tablename.length();
		int i;
		for(i=1; i<Columnnum; i++) 
			sb.append(sql).insert(12+length, ",");
		String[]MixStr = new String[Columnnum];
		for(i=0; i<Columnnum; length+=MixStr[i++].length()) {
			if(value[i]==null) value[i]="null";
			MixStr[i] = Columnname[i]+"='"+value[i]+"'";
			sb.append(sql).insert(12+i+length, MixStr[i]);
		}
		sb.append(sql).insert(18+i+length, condition);
		sb.append(sql).insert(20+i+length+condition.length(), convalue);
		String sqltemp = sb.substring(0, sb.length()-sql.length()*(2*Columnnum+1));
		Statement statement = DB_Operation.con.createStatement();
		if(statement.executeUpdate(sqltemp)>0) return true;
		else return false;
	}
	public static boolean Delete(String tablename, String condition, String convalue) throws SQLException {
		String sql = "delete from  where =''";
		StringBuffer sb = new StringBuffer();
		sb.append(sql).insert(12, tablename);
		sb.append(sql).insert(19+tablename.length(), condition);
		sb.append(sql).insert(21+tablename.length()+condition.length(), convalue);
		String sqltemp = sb.substring(0, sb.length()-sql.length()*2);
		Statement statement = DB_Operation.con.createStatement();
		if(statement.executeUpdate(sqltemp)>0) return true;
		else return false;
	}
}