package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.ProjectsMapper;
import com.manageeasy.me.Models.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectsMapper projectsMapper;

    private String deFile;
    private String prFile;
    private String meFile;
    private String fiFile;

    public Projects add(Projects projects){
        projectsMapper.insert(projects);
        return projects;
    }

    public Projects update(Projects projects){
        projectsMapper.updateByPrimaryKey(projects);
        return projects;
    }

    public List<Projects> selectByUPt(int uId, int pType){
        return projectsMapper.selectByUPt(uId, pType);
    }

    public List<Projects> selectBySPt(int state, int pType){
        return projectsMapper.selectBySPt(state, pType);
    }

    public Projects selectByKey(int id){
        return projectsMapper.selectByPrimaryKey(id);
    }
}
