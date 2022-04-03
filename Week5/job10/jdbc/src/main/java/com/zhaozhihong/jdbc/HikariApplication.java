package com.zhaozhihong.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author : zhaozh
 * @date : 2022/4/3 19:03
 */
public class HikariApplication {
    public static void main(String[] args) throws Exception{
        // 如何获得属性文件的输入流？
        // 通常情况下使用类的加载器的方式进行获取：
        try (InputStream is = HikariApplication.class.getClassLoader().getResourceAsStream("hikari.properties")) {
            // 加载属性文件并解析：
            Properties props = new Properties();
            props.load(is);
            HikariConfig config = new HikariConfig(props);
            HikariDataSource sHikariDataSource = new HikariDataSource(config);
            Connection connection = sHikariDataSource.getConnection();


            Statement statement = connection.createStatement();
            // create table
            //String createUsersSql = "create table " + "User " + "("
            //        + " id" + " int(32) primary key not null auto_increment,"
            //        + " userName" + " varchar(32) not null,"
            //        + " password" + " varchar(16) not null"
            //        + ");";

            //System.out.println(createUsersSql);

            //statement.execute(createUsersSql);

            // insert
            String insertUserSql = "insert into user(userName, password) values ('111', '222')";
            statement.execute(insertUserSql);

            // update
            String updateUserSql = "update user set password='333' where userName='111'";
            statement.execute(updateUserSql);

            String selectUsrSql = "select * from user";
            ResultSet rs = statement.executeQuery(selectUsrSql);
            // Extract data from result set
            while (rs.next()) {
                System.out.println(rs.getString("id") + "-" + rs.getString("userName") + "-" + rs.getString("password"));
            }

            // delete
            String deleteUserSql = "delete from user where userName='111'";
            statement.execute(deleteUserSql);

            for (int i=0; i<100 ; i++){
                statement.execute(insertUserSql);
            }

            if (statement != null) {
                statement.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
