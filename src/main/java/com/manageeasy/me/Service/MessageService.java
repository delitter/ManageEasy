package com.manageeasy.me.Service;

import com.manageeasy.me.Daos.MessagesMapper;
import com.manageeasy.me.Models.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessagesMapper messagesMapper;

    public Messages add(Messages messages){
        messages.setmTime(new Date());
        messagesMapper.insert(messages);
        return messages;
    }

    public int count(){
        return messagesMapper.countAll();
    }
}
