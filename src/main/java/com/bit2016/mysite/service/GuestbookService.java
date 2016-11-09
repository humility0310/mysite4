package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> getMessageList() {
		return guestbookDao.getList();
	}

	public List<GuestbookVo> getMessageList(int page) {
		return guestbookDao.getList(page);
	}

	public boolean deleteMessage(GuestbookVo vo) {
		return guestbookDao.delete(vo);
	}

	public void writeMessage(GuestbookVo vo) {
		Long no = guestbookDao.insert(vo);
		System.out.println(no);
	}

	public GuestbookVo writeMessage2(GuestbookVo vo) {
		Long no = guestbookDao.insert(vo);
		GuestbookVo vo2 = guestbookDao.getByNo(no);
		return vo2;
	}

}