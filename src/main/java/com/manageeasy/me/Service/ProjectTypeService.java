package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.ProjecttypeMapper;
import com.manageeasy.me.Models.Projecttype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTypeService {
    @Autowired
    private ProjecttypeMapper projecttypeMapper;

    public Projecttype selectById(int id){
        return projecttypeMapper.selectByPrimaryKey(id);
    }

    public List<Projecttype> selectAll(){
        return projecttypeMapper.selectAll();
    }
}
