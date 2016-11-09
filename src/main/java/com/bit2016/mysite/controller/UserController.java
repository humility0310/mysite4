package com.bit2016.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/loginform")
	public String loginForm() {
		return "user/loginform";
	}

	@RequestMapping("/joinform")
	public String joinForm() {
		return "user/joinform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {

		userService.join(vo);

		System.out.println(vo);

		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}


	@RequestMapping("/modifyform")
	public String modifyFrom(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// 접근제한
		if (authUser == null) {
			return "redirect:/user/loginform";
		}

		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}

	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 접근제한
		if (authUser == null) {
			return "redirect:/user/loginform";
		}

		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());

		return "redirect:/user/modifyform?update=success";
	}

	/*
	 * @ExceptionHandler(UserDaoException.class) public String
	 * handerUserDaoException() { // 1. logging (파일에 내용 저장) // 2. 사용자에게
	 * 안내(error) 페이지 return "error/500"; }
	 */
}