package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Characters;
import com.manageeasy.me.Service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Characters> add(@RequestBody Characters characters){
        return new ResponseEntity<>(characterService.add(characters), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Characters> update(@RequestBody Characters characters){
        return new ResponseEntity<>(characterService.update(characters), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam int id){
        return new ResponseEntity<>(characterService.delete(id),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<List<Characters>> query(
            @RequestParam String content, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(
                characterService.selectByComment(content, pageNum, pageSize),HttpStatus.ACCEPTED);
    }

}
