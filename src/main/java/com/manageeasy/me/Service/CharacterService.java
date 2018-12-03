package com.manageeasy.me.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Models.Characters;
import com.manageeasy.me.Models.QueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.manageeasy.me.Daos.CharactersMapper;

import java.util.Date;
import java.util.List;

@Service
@Controller
public class CharacterService {

	@Autowired
	private CharactersMapper charactersMapper;
	
	public Characters add(Characters characters){
		characters.setcRegtime(new Date());
		charactersMapper.insert(characters);
		return characters;
	}

	public String delete(int id){
		charactersMapper.deleteByPrimaryKey(id);
		return "Success!";
	}

	public Characters update(Characters characters){
		characters.setcUptime(new Date());
		charactersMapper.updateByPrimaryKey(characters);
		return characters;
	}

	public QueryModel selectByComment(String cComment, int pageNum, int pageSize){
		if(pageNum != 0)
			PageHelper.startPage(pageNum, pageSize);
		List<Characters> characters;
		if(cComment == null || cComment == ""){
			characters = charactersMapper.selectAll();
		}
		else {
			characters = charactersMapper.selectByComment(cComment);
		}
		if(pageNum != 0)
			return new QueryModel(characters, ((Page)characters).getTotal());
		else
			return new QueryModel(characters, characters.size());
	}
}
