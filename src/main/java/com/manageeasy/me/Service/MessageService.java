package com.manageeasy.me.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Daos.MessagesMapper;
import com.manageeasy.me.Models.Messages;
import com.manageeasy.me.Models.QueryModel;
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

    public QueryModel selectByType(int mtId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Messages> messages = messagesMapper.selectByType(mtId);
        return new QueryModel(messages, ((Page)messages).getTotal());
    }
}
