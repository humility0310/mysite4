package com.bit2016.mysite.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.service.GuestbookService;
import com.bit2016.mysite.vo.GuestbookVo;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@ResponseBody
	@RequestMapping("/list")
	public Object list(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page) {

		List<GuestbookVo> list = guestbookService.getMessageList(page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);

		return map;
	}

	@ResponseBody
	@RequestMapping("/insert")
	public Map<String, Object> insert(@ModelAttribute GuestbookVo vo) {

		GuestbookVo vo2 = guestbookService.writeMessage2(vo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo2);

		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@ModelAttribute GuestbookVo vo) {
		boolean result = guestbookService.deleteMessage(vo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put( "data", result ? vo.getNo() : -1 );
		return map;
	}
}