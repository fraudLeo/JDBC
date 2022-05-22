package com.company.loginTest;

/**
 * @Author: Leo
 * @Description: 模拟登录
 * @DateTime: 2022/5/22 11:03
 **/

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * 实现功能：
 *  1. 需求： 模拟用户的登录功能
 *  2. 业务描述：
 *      程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码
 *      用户输入用户名和密码之后，提交信息，java收集到用户信息，JAVA程序连接到数据库，判断是否合法
 *
 *de
 *  当我们使用preparedStatement的时候，可以避免sql注入的问题
 *  而且他的编译效率也高，会在编译阶段做类型的检查
 *  使用的是占位符
 *  写完sql语句后就直接给到编译，只不过里面的参数用的是占位符。之后再使用setString来进行填补
 *
 *  两者使用差别：当你仅仅需要拼接的时候就可以使用statement 其他的时候都是用ps，因为拼接的时候会带上“”
 */
public class LoginRegister {
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
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            conn = DriverManager.getConnection(url,user,password);
            //3.获取数据库对象
            stmt = conn.createStatement();
            //4.sql语句执行
            String sql1 = "INSERT INTO login(name,password,info) values('admin1','111111','ADMIN1')";
            String sql2 = "INSERT INTO login(name,password,info) values('admin2','222222','ADMIN2')";
            String sql3 = "INSERT INTO login(name,password,info) values('admin3','333333','ADMIN3')";
            String sql4 = "INSERT INTO login(name,password,info) values('admin4','444444','ADMIN4')";
            ArrayList<String> objects = new ArrayList<>();
            objects.add(sql1);
            objects.add(sql2);
            objects.add(sql3);
            objects.add(sql4);
            System.out.println("----执行结果----");
            int count = 1;
            for (String obj:objects) {
                int flag = stmt.executeUpdate(obj);
                System.out.println("sql"+(count++)+": "+(flag==1?"成功执行":"执行失败")+" ");
            }

            //5.查询结果集
            System.out.println("----查询结果集----");
            rs = stmt.executeQuery("SELECT * FROM login");
            while(rs.next()) {
                String name = rs.getString("name");
                String p = rs.getString("password");
                String i = rs.getString("info");
                System.out.println("name: "+name+" password: "+p+" info: "+i);
            }
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
}
