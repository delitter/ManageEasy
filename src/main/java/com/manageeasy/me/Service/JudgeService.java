package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.JudgeMapper;
import com.manageeasy.me.Daos.ProjectsMapper;
import com.manageeasy.me.Daos.UsersMapper;
import com.manageeasy.me.Models.Judge;
import com.manageeasy.me.Models.Projects;
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
    @Autowired
    private JudgeMapper judgeMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ProjectsMapper projectsMapper;

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
        return judge;
    }
}
