package com.bit2016.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.mysite.service.GuestbookService;
import com.bit2016.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("list", guestbookService.getList());

		return "/guestbook/list";
	}

	@RequestMapping("/insert")
	public String insert(@ModelAttribute GuestbookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook/";
	}

	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") int no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo) {
		guestbookService.delete(vo);
		return "redirect:/guestbook/";
	}
}
