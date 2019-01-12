package com.isoft.service;

import com.isoft.dao.UserDAO;
import com.isoft.dao.UserDAO_2;
import com.isoft.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserDAO_2 userDAO_2;

    public List<Map<String,Object>> searchUserByRole(String role){
        return userDAO_2.searchUserByRole(role);
    }

    public boolean deleteUserById(String id){
        int i = userDAO_2.deleteUserById(id);
        if (i>0)
            return true;
        else
            return false;
    }

    public List<Map<String,Object>> findAllUser(){
        return userDAO_2.findAllUser();
    }

    public boolean login(User user){
        int temp = userDAO.login(user);
        System.out.println("temp="+temp);
        if (temp == 0)
            return false;
        else
            return true;
    }

    public boolean login1(String uname,String upwd){
        int temp = userDAO_2.login(uname,upwd);
        if (temp == 0)
            return false;
        else
            return true;
    }

    public boolean addUserInfo(String uname, String runame, String role) {
        int i = userDAO_2.addUserInfo(uname,runame,role);
        if (i>0)
            return true;
        else
            return false;
    }

    public boolean updateUserInfo(String uname, String runame, String role) {
        int i = userDAO_2.updateUserInfo(uname,runame,role);
        if (i>0)
            return true;
        else
            return false;
    }
}
