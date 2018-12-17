package com.manageeasy.me.Controller;

import com.manageeasy.me.Daos.MessagesMapper;
import com.manageeasy.me.Models.*;
import com.manageeasy.me.Service.FileService;
import com.manageeasy.me.Service.MessageService;
import com.manageeasy.me.Service.ProjectService;
import com.manageeasy.me.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Departments departments;

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
    public ResponseEntity<String> addDe(@RequestParam MultipartFile file) throws IOException {
        de = fileService.addFile(file);
        return new ResponseEntity<>(de, HttpStatus.OK);
    }

    @RequestMapping(value = "/addPr", method = RequestMethod.POST)
    public ResponseEntity<String> addPr(@RequestParam MultipartFile file) throws IOException {
        pr = fileService.addFile(file);
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMe", method = RequestMethod.POST)
    public ResponseEntity<String> addMe(@RequestParam MultipartFile file) throws IOException {
        me = fileService.addFile(file);
        return new ResponseEntity<>(me, HttpStatus.OK);
    }

    @RequestMapping(value = "/addFi", method = RequestMethod.POST)
    public ResponseEntity<String> addFi(@RequestParam MultipartFile file) throws IOException {
        fi = fileService.addFile(file);
        return new ResponseEntity<>(fi, HttpStatus.OK);
    }

    //只是修改基本信息，不是修改状态
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Projects> update(@RequestBody Projects projects){
        return new ResponseEntity<>(projectService.update(projects), HttpStatus.OK);
    }

    class ProjResp{
        private Projects project;
        private Projectlevel level;
        private Users user;
        private Departments department;

        public Projects getProject() {
            return project;
        }

        public void setProject(Projects project) {
            this.project = project;
        }

        public Projectlevel getLevel() {
            return level;
        }

        public void setLevel(Projectlevel level) {
            this.level = level;
        }

        public Users getUser() {
            return user;
        }

        public void setUser(Users user) {
            this.user = user;
        }

        public Departments getDepartment() {
            return department;
        }

        public void setDepartment(Departments department) {
            this.department = department;
        }
    }
    @RequestMapping(value = "/queryByUSPt", method = RequestMethod.POST)
    //@RequestParam int state, @RequestParam int type
    public ResponseEntity<QueryModel> queryBySPt(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null)
            return new ResponseEntity<>(new ArrayList<Projects>(), HttpStatus.BAD_REQUEST);
        int state = Integer.valueOf(request.getParameter("state"));
        int type = Integer.valueOf(request.getParameter("type"));
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Users user = (Users) session.getAttribute("user");
        QueryModel temp = projectService.selectByUSPt(user.getuId(), state, type, pageNum, pageSize)
        List<ProjResp> projResps = new ArrayList<>();
        List<Projects> projects = (List<Projects>)temp.data;
        for(Projects p : projects){
            ProjResp projResp = new ProjResp();
            projResp.setProject(p);
            projResp.setUser();
            projResp.setDepartment();
        }
        return new ResponseEntity<>(, HttpStatus.OK);
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
        messages.setmContent("项目从状态"+original.getpState()+"转变到状态"+projects.getpState());
        messageService.add(messages);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
