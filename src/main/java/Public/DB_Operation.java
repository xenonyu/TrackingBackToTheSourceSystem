package main.java.Public;

import main.java.abnormal_process.AbnormalJson;

import java.sql.*;
import java.util.*;
import java.util.stream.IntStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

//对数据库的公共操作
public class DB_Operation {
    public static Connection con;
    static String driver = "com.mysql.jdbc.Driver";
    static String databaseuser = "root";
    static String password = "root19123!@#&";
    private static Map<String, Connection> DB_CONN = new HashMap<String, Connection>();

    public static Connection GetConnection(String DB_name) throws ClassNotFoundException, SQLException {
        //数据库连接
        if (DB_CONN.containsKey(DB_name)) {
            return DB_CONN.get(DB_name);
        }
        Class.forName(driver);
        String url = "jdbc:mysql://10.10.27.230:3306/" + DB_name + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        System.out.println(url);
        DriverManager.setLoginTimeout(1);
        Properties properties = new Properties();
        properties.put("connectTimeout", "2000");
        properties.put("user", databaseuser);
        properties.put("password", password);
        try{
            con = DriverManager.getConnection(url, properties);
        } catch (Exception ex) {
            System.out.print(ex);
            url = "jdbc:mysql://202.120.39.22:33060/" + DB_name;
            properties.put("password", "ccflab");
            con = DriverManager.getConnection(url, properties);
        }
        DB_CONN.put(DB_name, con);
        return con;
    }

    //几种不同的增删改查方法
    public static List<Map<String, Object>> Select(Connection conn, String ColumnNames, String tableName, String condition) throws SQLException {
        PreparedStatement ps;
        String sql = "select " + ColumnNames + " from " + tableName + " where " + condition;
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        List<Map<String, Object>> row = new ArrayList<>();
        while (rs.next()) {//判断
            //创建map容器
            Map<String, Object> col = new HashMap<>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //判断数据类型
                switch (rs.getMetaData().getColumnType(i)) {
                    case Types.VARCHAR:
                        col.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                        break;
                    case Types.INTEGER:
                        col.put(rs.getMetaData().getColumnName(i), rs.getInt(i));
                        break;
                    default:
                        break;
                }
            }
            //添加map数据
            row.add(col);
        }
        return row;
    }
    public static boolean Update(Connection conn, AbnormalJson abJson) throws SQLException {
        String sql = "update abbehavior.abbehavior set threatID=?, originID=?, timeStamp=?, threatType=?, uploadSysID=?, threatUserID=?, threatEnterpriseID=?,threatCredenID=?,threatIP=?,threatedIP=?, threatIPSegment=?,otherMsg=? where originID=? and threatType=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, abJson.getThreatID());
        pstmt.setInt(2, abJson.getOriginID());
        pstmt.setString(3, abJson.getTimeStamp());
        pstmt.setInt(4, abJson.getThreatType());
        pstmt.setString(5, abJson.getUploadSysID());
        pstmt.setString(6, abJson.getThreatUserID());
        pstmt.setString(7, abJson.getThreatEnterpriseID());
        pstmt.setString(8, abJson.getThreatCredenID());
        pstmt.setString(9, abJson.getThreatIP());
        pstmt.setString(10, abJson.getThreatedIP());
        pstmt.setString(11, abJson.getThreatIPSegment());
        pstmt.setString(12, abJson.getOtherMsg());

        pstmt.setInt(13, abJson.getOriginID());
        pstmt.setInt(14, abJson.getThreatType());

        int res=pstmt.executeUpdate();
        pstmt.close();
        if(res>0){
            System.out.println("更新数据成功");
            return true;
        }
        else return false;
    }

    public static boolean Update(Connection conn, String tableName, String[] columnName, Map<String, Object> value, String condition) {
        try {
            //sql语句
            StringBuilder sql = new StringBuilder("update ");
            sql.append(tableName).append(" set ");
            IntStream.range(0, columnName.length).forEach(i ->
                    sql.append(columnName[i]).append("=?,")
            );
            sql.deleteCharAt(sql.length()-1).append(" where ").append(condition);
            //预置对象
            PreparedStatement pstmt=conn.prepareStatement(sql.toString());
            IntStream.range(0,columnName.length).forEach(i -> {
                try {
                    pstmt.setString(i+1, String.valueOf(value.get(columnName[i])));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            //执行sql语句，返回影响行数
            int res=pstmt.executeUpdate();
            pstmt.close();
            if(res>0){
                System.out.println("更新数据成功");
                return true;
            }
            else return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static boolean UpdateAbnormal(Connection conn, AbnormalJson abJson) throws SQLException {
        String sql = "replace into abbehavior.abbehavior values(?, ?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, null);
        pstmt.setString(2, abJson.getThreatID());
        pstmt.setInt(3, abJson.getOriginID());
        pstmt.setString(4, abJson.getTimeStamp());
        pstmt.setInt(5, abJson.getThreatType());
        pstmt.setString(6, abJson.getUploadSysID());
        pstmt.setString(7, abJson.getThreatUserID());
        pstmt.setString(8, abJson.getThreatEnterpriseID());
        pstmt.setString(9, abJson.getThreatCredenID());
        pstmt.setString(10, abJson.getThreatIP());
        pstmt.setString(11, abJson.getThreatedIP());
        pstmt.setString(12, abJson.getThreatIPSegment());
        pstmt.setString(13, abJson.getOtherMsg());
        String test = pstmt.toString();
        int res=pstmt.executeUpdate();
        pstmt.close();
        if(res>0){
            System.out.println("refresh" + ": " + abJson.getThreatType() + " ID: " + abJson.getOriginID() );
            return true;
        }
        else return false;
    }
    public static boolean UpdateAbnormal(Connection conn, String tablename, String[] Columnname, Map<String, Object> value) {
        try {
            //sql语句
            StringBuilder sql = new StringBuilder("insert into ");
            sql.append(tablename).append("(");
            IntStream.range(0, Columnname.length).forEach(i ->
                sql.append(Columnname[i]).append(",")
            );
            sql.deleteCharAt(sql.length()-1).append(") values (");
            IntStream.range(0, Columnname.length).forEach(i ->
                    sql.append("?,")
            );
            sql.deleteCharAt(sql.length()-1);
            sql.append(")");
            //预置对象
            PreparedStatement pstmt=conn.prepareStatement(sql.toString());
            IntStream.range(0,Columnname.length).forEach(i -> {
                try {
                    pstmt.setString(i+1, String.valueOf(value.get(Columnname[i])));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            //执行sql语句，返回影响行数
            int res=pstmt.executeUpdate();
            pstmt.close();
            if(res>0){
                System.out.println("更新数据成功");
                return true;
            }
            else return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Delete(String tablename, String condition, String convalue) throws SQLException {
        String sql = "delete from  where =''";
        StringBuilder sb = new StringBuilder();
        sb.append(sql).insert(12, tablename);
        sb.append(sql).insert(19 + tablename.length(), condition);
        sb.append(sql).insert(21 + tablename.length() + condition.length(), convalue);
        String sqltemp = sb.substring(0, sb.length() - sql.length() * 2);
        Statement statement = DB_Operation.con.createStatement();
        return statement.executeUpdate(sqltemp) > 0;
    }
}