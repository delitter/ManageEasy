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
        ArrayList<JudgeQueryModel> judgeResps = new ArrayList<>();
        for(Judge j : judges){
            JudgeQueryModel judgeResp = new JudgeQueryModel();
            judgeResp.setjEndtime(j.getjEndtime());
            judgeResp.setjScore(j.getjScore());
            judgeResp.setjComment(j.getjComment());
            Projects p = projectService.selectByKey(j.getpId());
            judgeResp.setpId(p.getpId());
            judgeResp.setPtId(p.getPtId());
            judgeResp.setPlId(p.getPlId());
            judgeResp.setpName(p.getpName());
            judgeResp.setpStart(p.getpStart());
            judgeResp.setpEnd(p.getpEnd());
            judgeResp.setuId(p.getuId());
            judgeResp.setuPhone(p.getuPhone());
            judgeResp.setuEmail(p.getuEmail());
            judgeResp.setMaId(p.getMaId());
            judgeResp.setDeFile(p.getDeFile());
            judgeResp.setPrFile(p.getPrFile());
            judgeResp.setMeFile(p.getMeFile());
            judgeResp.setFiFile(p.getFiFile());
            judgeResp.setpState(p.getpState());
            judgeResp.setfReason(p.getfReason());
            judgeResp.setProjectlevel(projectLevelService.selectByKey(p.getPlId()).getPlName());
            Users u = userService.selectByKey(p.getuId());
            judgeResp.setuName(u.getuName());
            judgeResp.setDepartment(departmentService.selectById(u.getdId()).getdName());
            judgeResp.setMastername(userService.selectByKey(j.getuId()).getuName());
            judgeResp.setProjecttype(projectTypeService.selectById(p.getPtId()).getPtName());
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
