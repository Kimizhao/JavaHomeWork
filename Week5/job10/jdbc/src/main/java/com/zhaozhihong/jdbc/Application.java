package com.zhaozhihong.jdbc;

import java.sql.*;

/**
 * @author : zhaozh
 * @date : 2022/4/3 18:39
 */
public class Application {
    public static void main(String[] args) throws Exception{

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        Connection connection = DriverManager.getConnection(url,"root","root");

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



        PreparedStatement ps = connection.prepareStatement("select * from user where ID=?");
        ps.setInt(1, 50);
        ResultSet rs1 = ps.executeQuery();
        while (rs1.next()) {
            System.out.println(rs1.getString("id") + "-" + rs1.getString("userName") + "-" + rs1.getString("password"));
        }



        if (statement != null) {
            statement.close();
        }

        if (ps != null) {
            ps.close();
        }
    }
}
