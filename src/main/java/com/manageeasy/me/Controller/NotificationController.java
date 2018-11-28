package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Notifications;
import com.manageeasy.me.Models.Projecttype;
import com.manageeasy.me.Service.FileService;
import com.manageeasy.me.Service.NotificationService;
import com.manageeasy.me.Service.ProjectTypeService;
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
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ProjectTypeService projectTypeService;

    private static String filePath = "";

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Notifications> add(@RequestBody Notifications notifications){
        notifications.setnFiles(filePath);
        filePath = "";
        return new ResponseEntity<>(notificationService.add(notifications), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<String> file(@RequestParam MultipartFile file) throws IOException {
        filePath = fileService.addFile(file);
        return new ResponseEntity<>(filePath, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Notifications> update(@RequestBody Notifications notifications){
        return new ResponseEntity<>(notifications, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam int id){
        return new ResponseEntity<>(notificationService.delete(id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<List<Notifications>> query(
            @RequestParam int ntid, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(notificationService.selectByType(ntid, pageNum, pageSize), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/queryWithProj", method = RequestMethod.GET)
    public ResponseEntity<List<Notifications>> queryWithProj(
            @RequestParam int ntid, @RequestParam int ptid, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(notificationService.selectByNPtype(ntid, ptid, pageNum, pageSize), HttpStatus.ACCEPTED);
    }

    public class NotiFullInfo{
        private Notifications notifications;
        private Projecttype projecttype;

        public Notifications getNotifications() {
            return notifications;
        }

        public void setNotifications(Notifications notifications) {
            this.notifications = notifications;
        }

        public Projecttype getProjecttype() {
            return projecttype;
        }

        public void setProjecttype(Projecttype projecttype) {
            this.projecttype = projecttype;
        }
    }

    @RequestMapping(value = "/queryFullInfo", method = RequestMethod.GET)
    public ResponseEntity<NotiFullInfo> queryFullInfo(int id){
        NotiFullInfo notiFullInfo = new NotiFullInfo();
        notiFullInfo.setNotifications(notificationService.selectFullInfo(id));
        notiFullInfo.setProjecttype(projectTypeService.selectById(notiFullInfo.getProjecttype().getPtId()));
        return new ResponseEntity<>(notiFullInfo, HttpStatus.ACCEPTED);
    }
}
