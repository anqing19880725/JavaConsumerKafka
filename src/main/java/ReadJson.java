import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import pojo.AfterColumnList;
import pojo.JsonRootBean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {
    public class ConnectionTest {
            private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
            private String dbUser = "test";
            private String dbPwd = "test";
            private Connection con = null;
            public Connection getDBConnect() {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    try {
                        Connection con = DriverManager.getConnection(dbURL, dbUser, dbPwd);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return con;
            }

//            public void testInsert() {
//                PreparedStatement ps1 = null;
//                String sql = "insert into t_user(ID,USERNAME,PASSWORD1,EMAIL) VALUES(?,?,?,?)";
//                try {
//                    ps1 = con.prepareStatement(sql);
//                    ps1.setString(1, "2");
//                    ps1.setString(2, "ww");
//                    ps1.setString(3, "12345");
//                    ps1.setString(4, "181562207@qq.com");
//                    ps1.execute();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                if (ps1 != null) {
//                    try {
//                        ps1.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    if (con != null) {
//                        try {
//                            con.close();
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }

//        public void closeSources(PreparedStatement ps, Connection con) {
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

//            public void testUpdate() {
//                PreparedStatement ps2 = null;
//                String sql2 = "update T_USER SET USERNAME = ? where ID =? ";
//                try {
//                    ps2 = con.prepareStatement(sql2);
//                    ps2.setString(1, "newgg");
//                    ps2.setString(2, "1");
//                    ps2.execute();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                closeSources(ps2, con);
//            }

        public void main(String[] args) throws Exception {
            String jsonPath = "src/main/resources/Json";
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;
            try {
//            InputStreamReader inputStreamReader = new InputStreamReader();
                FileReader fileReader = new FileReader(jsonPath);
                BufferedReader br = new BufferedReader(fileReader);
                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println("line = " + line);
//                JsonParser jsonParser = new JsonParser();
//                JsonElement jsonElement = JsonParser.parseString(line);
//                System.out.println(object);
//                JsonArray arr = object.get("AfterColumnList").getAsJsonArray();
//                ArrayList<String> put = new ArrayList<>();
//                while(arr.iterator().hasNext()){
//                    System.out.println(arr.iterator().hasNext());
//                }

                    Gson gson = new Gson();
                    JsonRootBean jsonRootBean = gson.fromJson(line, JsonRootBean.class);
                    String owner = jsonRootBean.getOwner();
                    String tableName = jsonRootBean.getTableName();
                    String operationType = jsonRootBean.getOperationType();
                    JsonArray abiao = new JsonArray();
                    JsonArray bbiao = new JsonArray();
                    JsonArray cbiao = new JsonArray();
                    abiao.add(owner);
                    abiao.add(tableName);
                    bbiao.add(owner);
                    bbiao.add(operationType);
                    cbiao.add(tableName);
                    cbiao.add(operationType);
                    System.out.println("这是A表 + " + abiao);
                    System.out.println("这是B表 + " + bbiao);
                    System.out.println("这是C表 + " + cbiao);

//                System.out.println(jsonRootBean);
//                String tableName = jsonRootBean.getTableName();
//                System.out.println(tableName);
                    List<AfterColumnList> afterColumnList = jsonRootBean.getAfterColumnList();
                    System.out.println(afterColumnList);

                    for (int i = 0; i <= afterColumnList.size(); i++) {
                        System.out.println(afterColumnList.get(0).getSystemid());
                        System.out.println(afterColumnList.get(0).getProject());
                        System.out.println(afterColumnList.get(0).is$is_login_id());
                        System.out.println(afterColumnList.get(0).get$browser_version());
                        System.out.println(afterColumnList.get(0).getCurrenturl());
                    }
//                JSONObject jsonObject = JSON.parseObject(line);
//                JsonRootBean jsonRootBean = JSON.toJavaObject(j                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         sonObject, JsonRootBean.class);
//                System.out.println("jsonRootBean = " + jsonRootBean);


                    String sql1 = "insert into t_user1(owner,tableName) VALUES(?,?)";
                    ps1 = con.prepareStatement(sql1);
                    ps1.setString(1, jsonRootBean.getOwner());
                    ps1.setString(2, jsonRootBean.getTableName());

                    String sql2 = "insert into t_user2(operationType,columnNum) VALUES(?,?)";
                    ps2 = con.prepareStatement(sql2);
                    ps2.setString(1, jsonRootBean.getOperationType());
                    ps2.setString(2, jsonRootBean.getColumnNum());

                    String sql3 = "insert into t_user3(currenturl,/$browser_version) VALUES(?,?)";
                    ps3 = con.prepareStatement(sql3);
                    ps3.setString(1,afterColumnList.get(0).get$browser_version());
                    ps3.setString(2, afterColumnList.get(0).getCurrenturl());

                    ps1.close();
                    ps2.close();
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}