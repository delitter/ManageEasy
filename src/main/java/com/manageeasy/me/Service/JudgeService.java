package com.manageeasy.me.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Daos.JudgeMapper;
import com.manageeasy.me.Daos.ProjectsMapper;
import com.manageeasy.me.Daos.UsersMapper;
import com.manageeasy.me.Models.Judge;
import com.manageeasy.me.Models.Projects;
import com.manageeasy.me.Models.QueryModel;
import com.manageeasy.me.Models.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JudgeService {
    final JudgeMapper judgeMapper;
    @Autowired
    public JudgeService(JudgeMapper judgeMapper){
        this.judgeMapper = judgeMapper;
    }

    private UsersMapper usersMapper;
    @Autowired
    public void setUsersMapper(UsersMapper usersMapper){
        this.usersMapper = usersMapper;
    }

    private ProjectsMapper projectsMapper;
    @Autowired
    public void setProjectsMapper(ProjectsMapper projectsMapper){
        this.projectsMapper = projectsMapper;
    }

    public Judge add(int pid, Date endtime){
        Judge judge = new Judge();
        List<Users> users = usersMapper.selectByCid(2);
        judge.setjEndtime(endtime);
        judge.setpId(pid);
        judge.setuId(users.get((int)(Math.random()*(users.size()))).getuId());
        Projects projects = projectsMapper.selectByPrimaryKey(pid);
        projects.setpState(0);
        projectsMapper.updateByPrimaryKey(projects);
        return judge;
    }

    public Judge update(Judge judge){
        judgeMapper.updateByPrimaryKey(judge);
        Projects projects = projectsMapper.selectByPrimaryKey(judge.getpId());
        projects.setpState(4);
        projectsMapper.updateByPrimaryKey(projects);
        return judge;
    }

    public QueryModel selectBySPt(int state, int ptid, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Judge> judges = judgeMapper.selectByState(state, ptid);
        return new QueryModel<>(judges, ((Page)judges).getTotal());
    }
}
