package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.ProjectsMapper;
import com.manageeasy.me.Models.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectsMapper projectsMapper;

    /*public Projects add(Projects projects){

    }*/
}
