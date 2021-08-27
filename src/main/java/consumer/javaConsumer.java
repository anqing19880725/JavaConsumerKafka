package consumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


import Utils.ConnectOracle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import pojo.AfterColumnList;
import pojo.JsonRootBean;


/**
 * Title: KafkaConsumerTest
 * Description:
 * kafka消费者 demo
 * 手动提交测试
 * Version:1.0.0
 *
 * @author yangjia
 * @date 2021年8月26日
 */

public class javaConsumer implements Runnable {

    private KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private String topic;
    private static final String GROUPID = "groupMin";

    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;

    Gson gson = new Gson();

    public javaConsumer(String topicName) {
        this.topic = topicName;
        init();
    }

    @Override
    public void run() {
        System.out.println("---------开始消费---------");
        int messageNo = 1;
        List<String> list = new ArrayList<String>();
        List<Long> list2 = new ArrayList<Long>();

        //获取连接的三个基本信息
        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUser = "test";
        String dbPwd = "test";

        // 获取数据库连接方法, 返回Connection对象
        Connection con = null;

// 加载数据库驱动 不同的数据库下面这个驱动是不同的，这个千万要注意!!!
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(dbURL, dbUser, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (; ; ) {
                msgList = consumer.poll(100);
                if (null != msgList && msgList.count() > 0) {
                    for (ConsumerRecord<String, String> record : msgList) {
                        if (messageNo % 10 == 0) {
                            JsonArray abiao = new JsonArray();
                            JsonArray bbiao = new JsonArray();
                            JsonArray cbiao = new JsonArray();
//                            System.out.println(messageNo+"=======receive: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
                            JsonRootBean jsonRootBean = gson.fromJson(record.value(), JsonRootBean.class);
                            String owner = jsonRootBean.getOwner();
                            String tableName = jsonRootBean.getTableName();
                            String operationType = jsonRootBean.getOperationType();
                            abiao.add(owner);
                            abiao.add(tableName);
                            bbiao.add(operationType);
                            bbiao.add(operationType);
                            cbiao.add(tableName);
                            cbiao.add(operationType);
                            System.out.println("这是A表 + " + abiao);
                            System.out.println("这是B表 + " + bbiao);
                            System.out.println("这是C表 + " + cbiao);

                            List<AfterColumnList> afterColumnList = jsonRootBean.getAfterColumnList();
//                            for (int i = 0; i < afterColumnList.size(); i++) {
//                                System.out.println(afterColumnList.get(0).getSystemid() + "offset===" + record.offset());
//                                System.out.println(afterColumnList.get(0).getProject() + "offset===" + record.offset());
//                                System.out.println(afterColumnList.get(0).is$is_login_id() + "offset===" + record.offset());
//                            }

                            String sql1 = "insert into t_user1(owner,tableName) VALUES(?,?)";
                            try {
                                ps1 = con.prepareStatement(sql1);
                                ps1.setString(1, jsonRootBean.getOwner());
                                ps1.setString(2, jsonRootBean.getTableName());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            String sql2 = "insert into t_user2(operationType,columnNum) VALUES(?,?)";
                            try {
                                ps2 = con.prepareStatement(sql2);
                                ps2.setString(1, jsonRootBean.getOperationType());
                                ps2.setString(2, jsonRootBean.getColumnNum());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            String sql3 = "insert into t_user3(currenturl,/$browser_version) VALUES(?,?)";
                            try {
                                ps3 = con.prepareStatement(sql3);
                                ps3.setString(1, afterColumnList.get(0).get$browser_version());
                                ps3.setString(2, afterColumnList.get(0).getCurrenturl());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        list.add(record.value());
                        list2.add(record.offset());
                        messageNo++;
                    }
                    if (list.size() == 50) {
                        // 手动提交
                        consumer.commitSync();
                        System.out.println("成功提交" + list.size() + "条,此时的offset为:" + list2.get(49));
                    } else if (list.size() > 50) {
                        consumer.close();
                        init();
                        list.clear();
                        list2.clear();
                    }
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private void init() {
        Properties props = new Properties();
        //kafka消费的的地址
        props.put("bootstrap.servers", "10.182.143.146:21005");
        //组名 不同组名可以重复消费
        props.put("group.id", GROUPID);
        //是否自动提交
        props.put("enable.auto.commit", "false");
        //超时时间
        props.put("session.timeout.ms", "30000");
        //一次最大拉取的条数
        props.put("max.poll.records", 10);
//		earliest当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
//		latest
//		当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
//		none
//		topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        props.put("auto.offset.reset", "latest");
        //序列化
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        //订阅主题列表topic
        this.consumer.subscribe(Arrays.asList(topic));
        System.out.println("初始化!");
    }


    public static void main(String args[]) {
        javaConsumer test1 = new javaConsumer("DMGR_PUB_SYS0240");
        Thread thread1 = new Thread(test1);
        thread1.start();

//        ConnectOracle connora = new ConnectOracle();
    }
}
