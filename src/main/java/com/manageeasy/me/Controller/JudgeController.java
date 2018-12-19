package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.*;
import com.manageeasy.me.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/judge")
public class JudgeController {
    final JudgeService judgeService;
    @Autowired
    public JudgeController(JudgeService judgeService){
        this.judgeService = judgeService;
    }

    private ProjectService projectService;
    @Autowired
    public void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    private ProjectLevelService projectLevelService;
    @Autowired
    public void setProjectLevelService(ProjectLevelService projectLevelService){
        this.projectLevelService = projectLevelService;
    }

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    private ProjectTypeService projectTypeService;
    @Autowired
    public void setProjectTypeService(ProjectTypeService projectTypeService){
        this.projectTypeService = projectTypeService;
    }

    class JudgeResp{
        private Judge judge;
        private Projects projects;
        private String projectlevel;
        private Users users;
        private String department;
        private String mastername;
        private String projecttype;

        public Judge getJudge() {
            return judge;
        }

        public void setJudge(Judge judge) {
            this.judge = judge;
        }

        public Projects getProjects() {
            return projects;
        }

        public void setProjects(Projects projects) {
            this.projects = projects;
        }

        public String getProjectlevel() {
            return projectlevel;
        }

        public void setProjectlevel(String projectlevel) {
            this.projectlevel = projectlevel;
        }

        public Users getUsers() {
            return users;
        }

        public void setUsers(Users users) {
            this.users = users;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getMastername() {
            return mastername;
        }

        public void setMastername(String mastername) {
            this.mastername = mastername;
        }

        public String getProjecttype() {
            return projecttype;
        }

        public void setProjecttype(String projecttype) {
            this.projecttype = projecttype;
        }
    }

    //@RequestParam int state, @RequestParam int ptid, @RequestParam int pageNum, @RequestParam int pageSize
    //只有管理员能获得所有的，其他只能获得自己的
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<QueryModel> query(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null)
            return new ResponseEntity<>(new QueryModel("please login!", 0), HttpStatus.BAD_REQUEST);
        Users users = (Users)session.getAttribute("user");
        int state = Integer.valueOf(request.getParameter("state"));
        int ptid = Integer.valueOf(request.getParameter("ptid"));
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        QueryModel temp;
        if(users.getcId() == 1)
            temp = judgeService.selectBySPt(0, state, ptid, pageNum, pageSize);
        else
            temp = judgeService.selectBySPt(users.getuId(), state, ptid, pageNum, pageSize);
        List<Judge> judges = (List<Judge>)temp.data;
        ArrayList<JudgeResp> judgeResps = new ArrayList<>();
        for(Judge j : judges){
            JudgeResp judgeResp = new JudgeResp();
            judgeResp.setJudge(j);
            judgeResp.setProjects(projectService.selectByKey(j.getpId()));
            judgeResp.setProjectlevel(projectLevelService.selectByKey(judgeResp.getProjects().getPlId()).getPlName());
            judgeResp.setUsers(userService.selectByKey(judgeResp.getProjects().getuId()));
            judgeResp.setDepartment(departmentService.selectById(judgeResp.getUsers().getdId()).getdName());
            judgeResp.setMastername(userService.selectByKey(j.getuId()).getuName());
            judgeResp.setProjecttype(projectTypeService.selectById(judgeResp.getProjects().getPtId()).getPtName());
            judgeResps.add(judgeResp);
        }
        return new ResponseEntity<>(new QueryModel(judgeResps, temp.totalCount), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Judge> add(@RequestParam int id, @RequestBody Date endtime){
        Messages messages = new Messages();
        messages.setMtId(1);
        messages.setmContent("项目"+id+"从状态1转变到状态3");
        return new ResponseEntity<>(judgeService.add(id, endtime), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Judge> update(@RequestBody Judge judge){
        return new ResponseEntity<>(judgeService.update(judge), HttpStatus.OK);
    }
}
