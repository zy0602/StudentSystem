package com.isoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDAO_2 {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> searchUserByRole(String role) {
        String sql = "select * from tb_user inner join tb_login on tb_user.uname=tb_login.uname and  role like ?";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, new String[]{"%" + role + "%"});
        return list;
    }

    public int addUserInfo(String uname, String runame, String role) {
        String sql1 = "insert into tb_login(uname,role) values(?,?)";
        String sql2 = "insert into tb_user(uname,runame) values(?,?)";
        int rowNum1 = jdbcTemplate.update(sql1, uname, role);
        int rowNum2 = jdbcTemplate.update(sql2, uname, runame);
        if (rowNum1 > 0 && rowNum2 > 0)
            return 1;
        else
            return 0;
    }

    public int updateUserInfo(String uname, String runame, String role) {
        String sql1 = "update tb_login set role=? where uname=?";
        String sql2 = "update tb_user set runame=? where uname=?";
        int rowNum1 = jdbcTemplate.update(sql1, uname, role);
        int rowNum2 = jdbcTemplate.update(sql2, uname, runame);
        System.out.println(rowNum2);
        if (rowNum1 > 0 || rowNum2 > 0)
            return 1;
        else
            return 0;
    }

    public int deleteUserById(String id) {
        String sql = "delete from tb_login where id=?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    public List<Map<String, Object>> findAllUser() {
//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
        String sql = "select * from tb_user inner join tb_login on tb_user.uname=tb_login.uname";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    public int login(String uname,String upwd) {
        String sql = "select * from tb_login where uname=? and upwd=md5(?)";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new String[]{uname,upwd});
//        System.out.println("sql=" + maps.size());
        return maps.size();
    }
}
