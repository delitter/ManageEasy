package com.manageeasy.me.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Daos.UsersMapper;
import com.manageeasy.me.Models.QueryModel;
import com.manageeasy.me.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    final UsersMapper usersMapper;
    @Autowired
    public UserService(UsersMapper usersMapper){
        this.usersMapper = usersMapper;
    }

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

    public QueryModel selectByCid(int id, int pageNum, int pageSize){
        if(pageNum != 0)
            PageHelper.startPage(pageNum, pageSize);
        List<Users> users;
        if(id == 0)
            users = usersMapper.selectAll();
        else
            users = usersMapper.selectByCid(id);
        if(pageNum != 0)
            return new QueryModel(users, ((Page)users).getTotal());
        else
            return new QueryModel(users, users.size());
    }

    public Users selectByName(String name){
        return usersMapper.selectByName(name);
    }
}
