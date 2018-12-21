package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.MajortypeMapper;
import com.manageeasy.me.Models.Majortype;
import com.manageeasy.me.Models.QueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajortypeService {
    private MajortypeMapper majortypeMapper;
    @Autowired
    public MajortypeService(MajortypeMapper majortypeMapper){
        this.majortypeMapper = majortypeMapper;
    }

    public List<Majortype> selectAll(){
        return majortypeMapper.selectAll();
    }
}
