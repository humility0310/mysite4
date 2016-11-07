package com.bit2016.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.repository.BoardDao;

@Controller
@RequestMapping("/board")
public class BoardController {

	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("")
	public String list(Model model){
		//model.addAttribute("p", p);
		//model.addAttribute("kwd", kwd);
		
		//Map<String, Object> map = boardService.getList(page, Keyworkd);
		
		//boardDao.getList(kwd, p, 5);
//		map.put( "list", list );
//		
//		model.addAttribute( "totalCount", totalCount );
//		model.addAttribute( "listSize", LIST_SIZE );
//		model.addAttribute( "currentPage", currentPage );
//		model.addAttribute( "beginPage", beginPage );
//		model.addAttribute( "endPage", endPage );
//		model.addAttribute( "prevPage", prevPage );
//		model.addAttribute( "nextPage", nextPage );
//		model.addAttribute( "keyword", keyword );
		boardDao.getList("", 1, 5);
		return "/board/list";
	}
}
