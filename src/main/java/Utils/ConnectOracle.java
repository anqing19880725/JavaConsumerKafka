package Utils;

import java.sql.*;

public class ConnectOracle {

    //获取连接的三个基本信息
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String dbUser = "test";
    private String dbPwd = "test";

    // 获取数据库连接方法, 返回Connection对象
    private Connection con = null;

    //创建数据库连接
    public Connection getDBConnect() {
        try {
// 加载数据库驱动 不同的数据库下面这个驱动是不同的，这个千万要注意!!!
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dbURL, dbUser, dbPwd);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
