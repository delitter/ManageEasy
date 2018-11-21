package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Characters;
import com.manageeasy.me.Service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/character")
@ResponseBody
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Characters add(@RequestBody Characters characters){
        return characterService.add(characters);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Characters update(@RequestBody Characters characters){
        return characterService.update(characters);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam int id){
        return characterService.delete(id);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List<Characters> query(@RequestParam String content, @RequestParam int pageNum, int pageSize){
        return characterService.selectByContent(content, pageNum, pageSize);
    }

}
