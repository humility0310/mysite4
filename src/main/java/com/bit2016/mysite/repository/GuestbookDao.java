package com.bit2016.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	@Autowired
	private SqlSession sqlSession;

	public boolean delete(GuestbookVo vo) {
		int result = 0;
		result = sqlSession.delete("guestbook.delete", vo);
		return result ==1;
	}

	public Long insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
		return vo.getNo();
	}

	public List<GuestbookVo> getList() {
		return sqlSession.selectList("guestbook.getList");
	}

	public List<GuestbookVo> getList(int page) {
		return sqlSession.selectList("guestbook.getListByPage", page);
	}
	
	public GuestbookVo getByNo(Long no){
		GuestbookVo vo = sqlSession.selectOne("guestbook.getByNo", no);
		return vo;
	}
}