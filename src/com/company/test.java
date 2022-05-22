package com.company;

import java.util.*;

/**
 * @Author: Leo
 * @Description: TODO
 * @DateTime: 2022/5/22 18:32
 **/
public class test {
    public static void main(String[] args) {
        String sql1 = "INSERT INTO login(name,password,info) values('admin1','111111','ADMIN1')";
        String sql2 = "INSERT INTO login(name,password,info) values('admin2','222222','ADMIN2')";
        String sql3 = "INSERT INTO login(name,password,info) values('admin3','333333','ADMIN3')";
        String sql4 = "INSERT INTO login(name,password,info) values('admin4','444444','ADMIN4')";
        List objects = new ArrayList();
        objects.add(sql1);
        objects.add(sql2);
        objects.add(sql3);
        objects.add(sql4);
        for (Object obj:
             objects) {
            System.out.println(obj.toString());
        }
    }
}

