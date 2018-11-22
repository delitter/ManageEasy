package com.manageeasy.me.Service;

import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Daos.NotificationsMapper;
import com.manageeasy.me.Models.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotificationsMapper notificationsMapper;

    public Notifications add(Notifications notifications){
        notifications.setnRegtime(new Date());
        notificationsMapper.insert(notifications);
        return notifications;
    }

    public Notifications update(Notifications notifications){
        notifications.setnUptime(new Date());
        notificationsMapper.updateByPrimaryKey(notifications);
        return notifications;
    }

    public String delete(int id){
        notificationsMapper.deleteByPrimaryKey(id);
        return "Success!";
    }

    public List<Notifications> selectByType(int id, int pageNum, int pageSize){//传0获取所有
        PageHelper.startPage(pageNum, pageSize);
        if(id == 0)
            return notificationsMapper.selectAll();
        else
            return notificationsMapper.selectByType(id);
    }

    public List<Notifications> selectByNPtype(int ntid, int ptid, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return notificationsMapper.selectByNPtype(ntid, ptid);
    }
}
