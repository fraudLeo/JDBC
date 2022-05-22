package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @Author: Leo
 * @Description: TODO
 * @DateTime: 2022/5/21 9:07
 **/
public class JDBCTest02 {
    public static void main(String[] args) {

        //使用资源绑定器绑定属性配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc02");
        String driver =bundle.getString("driver");
        String url =bundle.getString("url");
        String user =bundle.getString("user");
        String password =bundle.getString("password");

        Connection conn = null;//连接对象
        Statement stmt = null;//执行sql语句的对象，为了执行 Statement 对象，被发送到数据库的 SQL 语句将被作为参数提供给 Statement 的方法：
        try{
//        1.注册驱动
            Class.forName("om.mysql.jdbc.Driver");
//        2.获取连接

            conn = DriverManager.getConnection(url,user,password);
//        3.获取数据库操作对象
            stmt = conn.createStatement();
//        4.执行sql语句
            String sql = "delete ......";
            //        5.处理查询结果集
            int count =stmt.executeUpdate(sql);
            System.out.println(count==1?"success":"false");

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            //        6.释放资源

            if(stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }




    }


}
