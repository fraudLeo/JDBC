package com.company.loginTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @Author: Leo
 * @Description: TODO
 * @DateTime: 2022/5/22 18:53
 **/
public class Login {
    public static void main(String[] args) {
        boolean p1 = procession1();
        while (p1) {
            procession2();
        }

    }

    public static void judge(String getName, String getPassword) {
        System.out.println("----正在查询----");
        ResourceBundle bundle = ResourceBundle.getBundle("com/company/jdbctest/jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            conn = DriverManager.getConnection(url,user,password);
            //3.获取数据库对象
            stmt = conn.createStatement();

            //5.查询结果集
            /*
                还有种处理方法，
                就是接受输入的name和password,然后使用sql的String
                拼接，然后执行query，rs.next() 如果返回true 就代表有否则无
             */
            //这句的意思是：将sql语句发送给DBMS database manage statement，有DBMS进行sql编译。
            rs = stmt.executeQuery("SELECT * FROM login");
            while(rs.next()) {
                String name = rs.getString("name");
                String p = rs.getString("password");
                if (name.equals(getName)) {
                    if (p.equals(getPassword)) {
                        System.out.println("登陆成功！");
                        return;
                    }
                }

            }

            System.out.println("登陆失败！");
            procession3();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    public static boolean procession1() {
        System.out.printf("请选择你需要的功能：\n" +
                          "1.登录\n" +
                          "2.退出\n");
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt()==1) {
           return true;
        } else {
            return false;
        }
    }
    public static void procession2() {
        System.out.println("----请在下面两行输入你的用户名和密码----");
        Scanner sc = new Scanner(System.in);
        judge(sc.next(),sc.next());
    }
    private static void procession3() {
        System.out.println("请问您是否想继续？\n" +
                           "1.重试\n" +
                           "2.放弃");
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt()==1) {
            procession1();
        }else {
            System.out.println("进程已结束");
            System.exit(0);
        }
    }
}

