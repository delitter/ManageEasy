package com.manageeasy.me.Controller;

import com.manageeasy.me.Daos.MessagesMapper;
import com.manageeasy.me.Models.Messages;
import com.manageeasy.me.Models.Projects;
import com.manageeasy.me.Service.FileService;
import com.manageeasy.me.Service.MessageService;
import com.manageeasy.me.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MessageService messageService;

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

    @RequestMapping(value = "/queryByUPt", method = RequestMethod.GET)
    public ResponseEntity<List<Projects>> queryByUPt(@RequestParam int uId, @RequestParam int type){
        return new ResponseEntity<>(projectService.selectByUPt(uId, type), HttpStatus.OK);
    }

    @RequestMapping(value = "/queryBySPt", method = RequestMethod.POST)
    public ResponseEntity<List<Projects>> queryBySPt(@RequestParam int state, @RequestParam int type){
        return new ResponseEntity<>(projectService.selectBySPt(state, type), HttpStatus.OK);
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
