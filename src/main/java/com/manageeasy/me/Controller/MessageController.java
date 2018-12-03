package com.manageeasy.me.Controller;

import com.manageeasy.me.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> count(){
        return new ResponseEntity<>(messageService.count(), HttpStatus.OK);
    }
}
