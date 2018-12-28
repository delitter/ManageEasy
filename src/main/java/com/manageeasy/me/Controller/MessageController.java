package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Messages;
import com.manageeasy.me.Models.QueryModel;
import com.manageeasy.me.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin2.message.Message;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<String> count() throws JSONException {
        JSONObject res = new JSONObject();
        res.accumulate("count",messageService.count());
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type","application/json;carset=UTF-8");
        return new ResponseEntity<>(res.toString(), header, HttpStatus.OK);
    }

    @RequestMapping(value = "/queryByType", method = RequestMethod.GET)
    public ResponseEntity<QueryModel> queryByType(@RequestParam int mtId, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(messageService.selectByType(mtId, pageNum, pageSize), HttpStatus.OK);
    }
}
