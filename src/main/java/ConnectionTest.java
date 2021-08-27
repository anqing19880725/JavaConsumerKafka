//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class ConnectionTest {
//    private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
//    private String dbUser = "test";
//    private String dbPwd = "test";
//
//    private Connection con = null;
//
//    public Connection getDBConnect() {
//
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            try {
//                Connection con = DriverManager.getConnection(dbURL, dbUser, dbPwd);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return con;
//    }
//
//
//    public void testInsert() {
//        PreparedStatement ps1 = null;
//        String sql = "insert into t_user(ID,USERNAME,PASSWORD1,EMAIL) VALUES(?,?,?,?)";
//        try {
//            ps1 = con.prepareStatement(sql);
//            ps1.setString(1, "2");
//            ps1.setString(2, "ww");
//            ps1.setString(3, "12345");
//            ps1.setString(4, "181562207@qq.com");
//            ps1.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (ps1 != null) {
//            try {
//                ps1.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void closeSources(PreparedStatement ps, Connection con) {
//        try {
//            if (ps != null)
//                ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (con != null)
//                con.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void testUpdate(){
//        PreparedStatement ps2 = null;
//        String sql2 = "update T_USER SET USERNAME = ? where ID =? ";
//
//        try {
//            ps2 = con.prepareStatement(sql2);
//            ps2.setString(1,"newgg");
//            ps2.setString(2,"1");
//            ps2.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        closeSources(ps2,con);
//    }
//}