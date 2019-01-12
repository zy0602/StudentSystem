package com.isoft.dao;

import com.isoft.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public class UserDAO_1 {
//    @Autowired
//    JdbcTemplate jt;

    public List<User> searchUserByRole(String role){
    ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
    String sql = "select * from tb_user where role like ?";
    List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),new String[]{"%"+role+"%"});
    return list;
    }

    public int addUserInfo(User user){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
        String sql = "insert into tb_user(uname,upwd,role) values(?,md5(?),?)";
        int rowNum = jdbcTemplate.update(sql,user.getUname(), "000000",user.getRole());
        return rowNum;
    }

    public int deleteUserById(String id){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        String sql = "delete from tb_user where id=?";
        int update = jt.update(sql,new String[]{id});
        return update;
    }
    public List<User> findAllUser(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        String sql = "select * from tb_user";
        List<User> userList = jt.queryForList(sql,User.class);
        return userList;
    }
    public int login(User user){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        String sql = "select * from tb_user where uname=? and upwd=md5(?)";
        List<Map<String,Object>> maps = jt.queryForList(sql,new String[]{user.getUname(),user.getUpwd()});
        return maps.size();
    }
}
