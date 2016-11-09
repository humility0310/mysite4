package com.bit2016.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.service.BoardService;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String index(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword, Model model) {

		Map<String, Object> map = boardService.getList(page, keyword);
		model.addAttribute("map", map);
		return "board/list";

	}

	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long boardNo, Model model) {

		model.addAttribute("vo", boardService.get(boardNo));
		return "board/view";
	}

	@RequestMapping("/writeform")
	public String insertform() {
		return "board/write";
	}

	@Auth
	@RequestMapping("/write")
	public String insert(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo, Model model) {

		// model.addAttribute("a", authUser.getNo());
		vo.setUserNo(authUser.getNo());
		boardService.insert(vo);
		return "redirect:/board";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute BoardVo vo, @AuthUser UserVo authUser) {
		Long userNo = authUser.getNo();
		System.out.println(vo.getUserName());
		boardService.delete(vo.getNo(), userNo);
		return "redirect:/board";
	}
}
