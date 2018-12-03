package com.manageeasy.me.Controller;

import com.manageeasy.me.Models.Characters;
import com.manageeasy.me.Models.QueryModel;
import com.manageeasy.me.Service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/character")
public class CharacterController {

    final CharacterService characterService;
    @Autowired
    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Characters> add(@RequestBody Characters characters){
        return new ResponseEntity<>(characterService.add(characters), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Characters> update(@RequestBody Characters characters){
        return new ResponseEntity<>(characterService.update(characters), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam int id){
        return new ResponseEntity<>(characterService.delete(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<QueryModel> query(
            @RequestParam String content, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(
                characterService.selectByComment(content, pageNum, pageSize),HttpStatus.OK);
    }
}
