package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.NotificationtypeMapper;
import com.manageeasy.me.Models.Notificationtype;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationtypeService {
    private NotificationtypeMapper notificationtypeMapper;
    public NotificationtypeService(NotificationtypeMapper notificationtypeMapper){
        this.notificationtypeMapper = notificationtypeMapper;
    }

    public List<Notificationtype> selectAll(){
        return notificationtypeMapper.selectAll();
    }
}
