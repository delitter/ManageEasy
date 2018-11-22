package com.manageeasy.me.Service;

import com.github.pagehelper.PageHelper;
import com.manageeasy.me.Models.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageeasy.me.Daos.CharactersMapper;

import java.util.Date;
import java.util.List;

@Service
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

	public List<Characters> selectByComment(String cComment, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		if(cComment == null || cComment == "")
			return charactersMapper.selectAll();
		else
			return charactersMapper.selectByComment(cComment);
	}
}
