package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.DepartmentsMapper;
import com.manageeasy.me.Models.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentsMapper departmentsMapper;

    public Departments selectById(int id){
        return departmentsMapper.selectByPrimaryKey(id);
    }

    public List<Departments> selectAll(){
        return departmentsMapper.selectAll();
    }
}
