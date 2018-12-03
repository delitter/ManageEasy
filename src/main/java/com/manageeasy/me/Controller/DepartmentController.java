package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Departments;
import com.manageeasy.me.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<List<Departments>> query(){
        return new ResponseEntity<>(departmentService.selectAll(), HttpStatus.OK);
    }
}
