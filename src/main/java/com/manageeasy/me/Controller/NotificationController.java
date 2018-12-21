package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Notifications;
import com.manageeasy.me.Models.Projecttype;
import com.manageeasy.me.Models.QueryModel;
import com.manageeasy.me.Service.FileService;
import com.manageeasy.me.Service.NotificationService;
import com.manageeasy.me.Service.ProjectTypeService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    final NotificationService notificationService;
    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    private FileService fileService;
    @Autowired
    public void setFileService(FileService fileService){
        this.fileService = fileService;
    }

    private ProjectTypeService projectTypeService;
    @Autowired
    public void setProjectTypeService(ProjectTypeService projectTypeService){
        this.projectTypeService = projectTypeService;
    }

    private static String filePath = "";

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Notifications> add(@RequestBody Notifications notifications){
        notifications.setnFiles(filePath);
        filePath = "";
        return new ResponseEntity<>(notificationService.add(notifications), HttpStatus.OK);
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<String> file(@RequestParam MultipartFile file) throws IOException, JSONException {
        filePath = fileService.addFile(file);
        JSONObject res = new JSONObject();
        res.accumulate("address", filePath);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Notifications> update(@RequestBody Notifications notifications){
        if(!filePath.equals(""))
            notifications.setnFiles(filePath);
        filePath = "";
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam int id){
        return new ResponseEntity<>(notificationService.delete(id), HttpStatus.OK);
    }

    class NotiResp{
        private Notifications notifications;
        private Projecttype projecttype;

        public void setNotifications(Notifications notifications) {
            this.notifications = notifications;
        }

        public void setProjecttype(Projecttype projecttype) {
            this.projecttype = projecttype;
        }

        public Notifications getNotifications() {
            return notifications;
        }

        public Projecttype getProjecttype() {
            return projecttype;
        }
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<QueryModel> query(
            @RequestParam int ntid, @RequestParam int pageNum, @RequestParam int pageSize){
        QueryModel temp = notificationService.selectByType(ntid, pageNum, pageSize);
        List<Notifications> notifications = (List<Notifications>)temp.data;
        ArrayList<NotiResp> notiResps = new ArrayList<>();
        for (Notifications n : notifications){
            NotiResp notiResp = new NotiResp();
            notiResp.setNotifications(n);
            notiResp.setProjecttype(projectTypeService.selectById(n.getPtId()));
            notiResps.add(notiResp);
        }
        return new ResponseEntity<>(new QueryModel<>(notiResps, temp.totalCount), HttpStatus.OK);
    }

    @RequestMapping(value = "/queryWithProj", method = RequestMethod.GET)
    public ResponseEntity<QueryModel> queryWithProj(
            @RequestParam int ntid, @RequestParam int ptid, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(notificationService.selectByNPtype(ntid, ptid, pageNum, pageSize), HttpStatus.OK);
    }

    @RequestMapping(value = "/queryFullInfo", method = RequestMethod.GET)
    public ResponseEntity<NotiResp> queryFullInfo(@RequestParam int id){
        NotiResp notiFullInfo = new NotiResp();
        notiFullInfo.setNotifications(notificationService.selectFullInfo(id));
        notiFullInfo.setProjecttype(projectTypeService.selectById(notiFullInfo.getNotifications().getPtId()));
        return new ResponseEntity<>(notiFullInfo, HttpStatus.OK);
    }
}
