package Public;

import abnormal_process.AbnormalJson;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

//对数据库的公共操作
public class DB_Operation {
    public static Connection con;
    static String driver = "com.mysql.jdbc.Driver";
    static String databaseuser = "root";
    static String password = "ccflab";

    public static Connection Connect(String DB_name) throws ClassNotFoundException, SQLException {
        //数据库连接
        Class.forName(driver);
        String url = "jdbc:mysql://202.120.39.22:33060/" + DB_name + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        con = DriverManager.getConnection(url, databaseuser, password);
        return con;
    }

    public static void Close() throws SQLException {
        //数据库关闭
        con.close();
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
    public static boolean Insert(Connection conn, AbnormalJson abJson) throws SQLException {
        String sql = "replace into abbehavior.abbehavior values(?,?,?,?,?,?,?,?,?,?,?,?)";
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

        int res=pstmt.executeUpdate();
        pstmt.close();
        if(res>0){
            System.out.println("更新数据成功");
            return true;
        }
        else return false;
    }
    public static boolean Insert(Connection conn, String tablename, String[] Columnname, Map<String, Object> value) {
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