package com.manageeasy.me.Controller;

import com.manageeasy.me.Daos.MessagesMapper;
import com.manageeasy.me.Models.*;
import com.manageeasy.me.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    private FileService fileService;
    @Autowired
    public void setFileService(FileService fileService){
        this.fileService = fileService;
    }

    private MessageService messageService;
    @Autowired
    public void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private ProjectLevelService projectLevelService;
    @Autowired
    public void setProjectLevelService(ProjectLevelService projectLevelService){
        this.projectLevelService = projectLevelService;
    }

    private ProjectTypeService projectTypeService;
    @Autowired
    public void setProjectTypeService(ProjectTypeService projectTypeService){
        this.projectTypeService = projectTypeService;
    }

    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    private static String de = "";
    private static String pr = "";
    private static String me = "";
    private static String fi = "";

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Projects> add(@RequestBody Projects projects){
        projects.setDeFile(de);
        projects.setPrFile(pr);
        de = "";
        pr = "";
        return new ResponseEntity<>(projectService.add(projects), HttpStatus.OK);
    }

    @RequestMapping(value = "/addDe", method = RequestMethod.POST)
    public ResponseEntity<String> addDe(@RequestParam MultipartFile file) throws IOException, JSONException {
        de = fileService.addFile(file);
        JSONObject res = new JSONObject();
        res.accumulate("address", de);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addPr", method = RequestMethod.POST)
    public ResponseEntity<String> addPr(@RequestParam MultipartFile file) throws IOException, JSONException {
        pr = fileService.addFile(file);
        JSONObject res = new JSONObject();
        res.accumulate("address", pr);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addMe", method = RequestMethod.POST)
    public ResponseEntity<String> addMe(@RequestParam MultipartFile file) throws IOException, JSONException {
        me = fileService.addFile(file);
        JSONObject res = new JSONObject();
        res.accumulate("address", me);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addFi", method = RequestMethod.POST)
    public ResponseEntity<String> addFi(@RequestParam MultipartFile file) throws IOException, JSONException {
        fi = fileService.addFile(file);
        JSONObject res = new JSONObject();
        res.accumulate("address", fi);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    //只是修改基本信息，不是修改状态
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Projects> update(@RequestBody Projects projects){
        return new ResponseEntity<>(projectService.update(projects), HttpStatus.OK);
    }

    class ProjResp{
        private Projects project;
        private String level;
        private Users user;
        private String department;
        private String projecttype;

        public Projects getProject() {
            return project;
        }

        public void setProject(Projects project) {
            this.project = project;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Users getUser() {
            return user;
        }

        public void setUser(Users user) {
            this.user = user;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getProjecttype() {
            return projecttype;
        }

        public void setProjecttype(String projecttype) {
            this.projecttype = projecttype;
        }
    }
    //只有管理员能获得所有的，其他只能获得自己的
    @RequestMapping(value = "/queryByUSPt", method = RequestMethod.GET)
    //@RequestParam int state, @RequestParam int type, int pageNum, int pageSize
    public ResponseEntity<QueryModel> queryBySPt(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null)
            return new ResponseEntity<>(new QueryModel("please login!", 0), HttpStatus.BAD_REQUEST);
        int state = Integer.valueOf(request.getParameter("state"));
        int type = Integer.valueOf(request.getParameter("type"));
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Users user = (Users) session.getAttribute("user");
        QueryModel temp;
        if(user.getcId() == 1)
            temp= projectService.selectByUSPt(0, state, type, pageNum, pageSize);
        else
            temp= projectService.selectByUSPt(user.getuId(), state, type, pageNum, pageSize);
        List<ProjResp> projResps = new ArrayList<>();
        List<Projects> projects = (List<Projects>)temp.data;
        for(Projects p : projects){
            ProjResp projResp = new ProjResp();
            projResp.setProject(p);
            projResp.setUser(userService.selectByKey(p.getuId()));
            projResp.setDepartment(departmentService.selectById(projResp.getUser().getdId()).getdName());
            projResp.setLevel(projectLevelService.selectByKey(p.getPlId()).getPlName());
            projResp.setProjecttype(projectTypeService.selectById(p.getPtId()).getPtName());
            projResps.add(projResp);
        }
        return new ResponseEntity<>(new QueryModel(projResps, temp.totalCount), HttpStatus.OK);
    }

    //修改项目状态-审核项目
    @RequestMapping(value = "/setState", method = RequestMethod.POST)
    public ResponseEntity<Projects> setState(@RequestBody Projects projects){
        Messages messages = new Messages();
        Projects original = projectService.selectByKey(projects.getpId());
        if(original.getpState() <= 5){
            messages.setMtId(1);
        }
        else if(original.getpState() <= 8){
            messages.setMtId(2);
        }
        else
            messages.setMtId(3);
        messages.setmContent("项目"+projects.getpId()+"从状态"+original.getpState()+"转变到状态"+projects.getpState());
        messageService.add(messages);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    //设定，提交完文件之后统一到审核状态
    //int level, String name
    @RequestMapping(value = "/saveMF", method = RequestMethod.GET)
    public ResponseEntity<Object> saveMe(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Projects projects;
        if(session == null)
            return new ResponseEntity<>("please login!", HttpStatus.BAD_REQUEST);
        int level = Integer.valueOf(request.getParameter("level"));
        String name = request.getParameter("name");
        Users users = (Users) session.getAttribute("user");
        projects = projectService.selectByNULv(level, users.getuId(), name);
        if(!me.equals("")) {
            projects.setMeFile(me);
            projects.setpState(6);
            me = "";
        }
        else if(!fi.equals("")){
            projects.setFiFile(fi);
            projects.setpState(9);
            fi="";
        }
        else
            return new ResponseEntity<>("no file to save!", HttpStatus.BAD_REQUEST);
        projectService.update(projects);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
