package com.isoft.dbutil;

import com.isoft.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    public DBUtils(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studb?usicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false","root","root");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public int login(User user){
        try {
            System.out.println(user.getUname());
            System.out.println(user.getUpwd());
            pstmt = conn.prepareStatement("select * from tb_user where uname=? and upwd=md5(?)");
            pstmt.setString(1,user.getUname());
            pstmt.setString(2,user.getUpwd());
            rs = pstmt.executeQuery();
            if (rs.next())
                return 1;
            else
                return 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<User>findAllUser(){
        try {
            String sql="select * from tb_user";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<User> list = new ArrayList<User>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUname(rs.getString(2) );
                user.setUpwd(rs.getString(3));
                user.setLastLoginTime(rs.getString(4));
                user.setRole(rs.getString(5));
                list.add(user);
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
