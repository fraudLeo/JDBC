package com.company;

/**
 * @Author: Leo
 * @Description: TODO
 * @DateTime: 2022/5/20 17:35
 **/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC是什么？
 *  JAVA DataBase Connectivity（Java语言连接数据库）
 *
 *  JDBC的本质是什么？
 *  ＪＤＢＣ是ＳＵＮ公司指定的一套接口（ｉｎｔｅｒｆａｃｅ）
 *  接口都有调用者和实现者
 *  面向接口调用，面向接口写实现类，这都属于面向接口编程
 *  为什么要面向接口编程？
 *      解耦合：降低耦合度，提高程序扩展里
 *      多态机制就是非长典型的：面向抽象编程，不面向具体　
 *      建议：　
 *      Animal a = new cat();
 *      Animal b = new Dog();
 *      而不是：
 *      Cat a = new cat();
 *      Dog b = new Dog();
 *
 *
        JDBC编程六步：
            1.注册驱动
            2.获取连接
            3.获取数据库操作对象
            4.执行sql语句
            5.处理查询结果集
            6.释放资源
 */
public class JDBCTest01 {
    public static void main(String[] args) {
        Connection conn = null;//连接对象
        Statement stmt = null;//执行sql语句的对象，为了执行 Statement 对象，被发送到数据库的 SQL 语句将被作为参数提供给 Statement 的方法：
        try{
//        1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //这里可以直接：class.forName("om.mysql.jdbc.Driver"),因为这个源代码里面有static静态代码块，里面写了上面这句，只要让他动起来就行了
//        2.获取连接
            conn = DriverManager.getConnection("jdbc://localhost:3006/...","root","123456");
//        3.获取数据库操作对象
            stmt = conn.createStatement();
//        4.执行sql语句
            String sql = "delete ......";
            //        5.处理查询结果集
            int count =stmt.executeUpdate(sql);
            System.out.println(count==1?"success":"false");

        }catch (SQLException e) {
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
