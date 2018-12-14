package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.ProjectlevelMapper;
import com.manageeasy.me.Models.Projectlevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectLevelService {
    final ProjectlevelMapper projectlevelMapper;
    @Autowired
    public ProjectLevelService(ProjectlevelMapper projectlevelMapper){
        this.projectlevelMapper = projectlevelMapper;
    }

    public Projectlevel selectByKey(int id){
        return projectlevelMapper.selectByPrimaryKey(id);
    }
}
