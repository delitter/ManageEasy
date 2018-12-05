package com.manageeasy.me.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Daos.NotificationsMapper;
import com.manageeasy.me.Models.Notifications;
import com.manageeasy.me.Models.QueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public QueryModel selectByType(int id, int pageNum, int pageSize){//传0获取所有
        PageHelper.startPage(pageNum, pageSize);
        List<Notifications> notifications;
        if(id == 0)
            notifications = notificationsMapper.selectAll();
        else
            notifications = notificationsMapper.selectByType(id);
        return new QueryModel(notifications, ((Page)notifications).getTotal());
    }

    public QueryModel selectByNPtype(int ntid, int ptid, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Notifications> notifications = notificationsMapper.selectByNPtype(ntid, ptid);
        return new QueryModel(notifications, ((Page)notifications).getTotal());
    }

    public Notifications selectFullInfo(int id){
        return notificationsMapper.selectByPrimaryKey(id);
    }
}
