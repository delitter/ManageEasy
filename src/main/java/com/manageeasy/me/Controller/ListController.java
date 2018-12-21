package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.*;
import com.manageeasy.me.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/List")
public class ListController {
    private CharacterService characterService;
    private DepartmentService departmentService;
    private MajortypeService majortypeService;
    private NotificationtypeService notificationtypeService;
    private ProjectLevelService projectLevelService;
    private ProjectTypeService projectTypeService;

    @Autowired
    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setMajortypeService(MajortypeService majortypeService) {
        this.majortypeService = majortypeService;
    }

    @Autowired
    public void setNotificationtypeService(NotificationtypeService notificationtypeService) {
        this.notificationtypeService = notificationtypeService;
    }

    @Autowired
    public void setProjectLevelService(ProjectLevelService projectLevelService) {
        this.projectLevelService = projectLevelService;
    }

    @Autowired
    public void setProjectTypeService(ProjectTypeService projectTypeService) {
        this.projectTypeService = projectTypeService;
    }

    @RequestMapping(value = "/character", method = RequestMethod.GET)
    public ResponseEntity<List<Characters>> getCharacter(){
        return new ResponseEntity<>(characterService.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public ResponseEntity<List<Departments>> getDepartment(){
        return new ResponseEntity<>(departmentService.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/major", method = RequestMethod.GET)
    public ResponseEntity<List<Majortype>> getMajor(){
        return new ResponseEntity<>(majortypeService.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/notificationType", method = RequestMethod.GET)
    public ResponseEntity<List<Notificationtype>> getNotificationType(){
        return new ResponseEntity<>(notificationtypeService.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/projectLevel", method = RequestMethod.GET)
    public ResponseEntity<List<Projectlevel>> getProjectLevel(){
        return new ResponseEntity<>(projectLevelService.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/projectType", method = RequestMethod.GET)
    public ResponseEntity<List<Projecttype>> getProjectType(){
        return new ResponseEntity<>(projectTypeService.selectAll(), HttpStatus.OK);
    }
}
