package com.company;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Author: Leo
 * @Description: TODO
 * @DateTime: 2022/5/21 9:32
 **/

/**
 * 查询结果集
 */
public class JDBCTest03 {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc02");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        try {
            //1. 注册驱动
            Class.forName("...");

            //2.获取连接
            conn = DriverManager.getConnection(url,user,password);

            //3.获取数据库操作对象
            stmt = conn.createStatement();

            //4.执行sql
            String sql = "...";
            rs = stmt.executeQuery(sql);//query代表的是查询,专门执行DQL语句的的方法

            //5.处理查询结果集

            while(rs.next()) {

                    //光标指向的行有数据
                    //取数据
                    //JDBC中所有的下标从1开始，不是从0开始
                    String empno = rs.getString(1);//getString()的方法的特点是：无论数据库中的数据类型是什么，都以String的形式输出
                    String ename = rs.getString(2);
                    String sal = rs.getString(3);
                    //上面得也可以不用第几列，可以用列的名字获取

                    System.out.println(empno+","+ename+","+sal);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            if(rs!=null) {
                rs.close();
            }
            if (stmt!=null) {
                stmt.close();
            }
            if (conn!=null) {
                conn.close();
            }
        }




    }
}
