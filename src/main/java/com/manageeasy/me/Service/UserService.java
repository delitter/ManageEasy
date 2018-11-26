package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.UsersMapper;
import com.manageeasy.me.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    public Users add(Users users){
        users.setuRegtime(new Date());
        usersMapper.insert(users);
        return users;
    }

    public String delete(int id){
        usersMapper.deleteByPrimaryKey(id);
        return "Success!";
    }

    public Users update(Users users){
        usersMapper.updateByPrimaryKey(users);
        return users;
    }

    public List<Users> selectByCid(int id){
        if(id == 0)
            return usersMapper.selectAll();
        else
            return usersMapper.selectByCid(id);
    }

    public Users selectByName(String name){
        return usersMapper.selectByName(name);
    }
}
