package com.bit2016.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	public void delete(GuestbookVo vo) {
		sqlSession.delete("guestbook.delete", vo);
	}

	public void insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
	}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}

	public GuestbookVo getList(Long no) {

		return sqlSession.selectOne("guestbook.getListByNoAndNameAndContent", no);
	}

	public List<GuestbookVo> getList(int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("page", page);

		return sqlSession.selectOne("guestbook.getListByPage", map);
	}
}
