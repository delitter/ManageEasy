package com.manageeasy.me.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Daos.ProjectsMapper;
import com.manageeasy.me.Models.Projects;
import com.manageeasy.me.Models.QueryModel;
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


    public QueryModel selectByUSPt(int uid, int state, int pType, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Projects> projects;
        if(uid != 0)
            projects = projectsMapper.selectByUSPt(uid, state, pType);
        else
            projects = projectsMapper.selectBySPt(state, pType);
        return new QueryModel(projects, ((Page)projects).getTotal());
    }

    public Projects selectByKey(int id){
        return projectsMapper.selectByPrimaryKey(id);
    }

    public Projects selectByNULv(int level, int uid, String name){
        return projectsMapper.selectByNULv(level, uid, name);
    }
}
