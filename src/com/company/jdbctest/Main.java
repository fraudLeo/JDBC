package com.company.jdbctest;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Author: Leo
 * @Description: TODO
 * @DateTime: 2022/5/22 10:03
 **/
public class Main {
    public static void main(String[] args) {

        ResourceBundle bundle = ResourceBundle.getBundle("com/company/jdbctest/jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {
            //1. 注册驱动
            Class.forName(driver);
            //2. 获取连接资源
            conn = DriverManager.getConnection(url,user,password);//drivermanager是试图从已注册的
            //3. 获取数据库操作对象
            stmt = conn.createStatement();
            //4. 执行sql
            String sql1 = "INSERT INTO jdbctest (id,name,info) values(1,'zs','Im zs');";//这里会有字段错误，如果用中文的话。
            String sql2 = "INSERT INTO jdbctest (id,name,info) values(2,'ls','Im ls');";
            String sql3 = "select * from jdbctest";
            int count1 = stmt.executeUpdate(sql1);
            int count2 = stmt.executeUpdate(sql2);
            rs = stmt.executeQuery(sql3);
            System.out.println("------sql1,sql2执行情况------");
            System.out.println("sql1:"+(count1==1?"执行成功":"执行失败")+" sql2:"+(count1==1?"执行成功":"执行失败"));
            System.out.println("-----------查询结果----------");
            //5. 查询结果集
            while(rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String info = rs.getString("info");
                System.out.println("id:"+id+" name:"+name+" info:"+info);
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6. 释放资源
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
