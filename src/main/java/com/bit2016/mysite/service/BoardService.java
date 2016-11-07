package com.bit2016.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.BoardDao;
import com.bit2016.mysite.vo.BoardVo;

@Service
public class BoardService {

	private static final int LIST_SIZE = 5; //리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5; //페이지 리스트의 페이지 수
	
	@Autowired
	private BoardDao boardDao;
	
	public void getList(){
		
		
		//1-1. 파라미터 page 가져오기
		/*		int currentPage = WebUtil.checkIntParam( request.getParameter( "p" ), 1 );
				//1-2. 파라미터 kwd 가져오기
				String keyword = WebUtil.checkNullParam( request.getParameter( "kwd" ), "" );


				//3. 페이징을 위한 기본 데이터 계산
				int totalCount = boardDao.getTotalCount( keyword ); 
				int pageCount = (int)Math.ceil( (double)totalCount / LIST_SIZE );
				int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE );
				int currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );
				
				//4. 파라미터 page 값  검증
				if( currentPage < 1 ) {
					currentPage = 1;
					currentBlock = 1;
				} else if( currentPage > pageCount ) {
					currentPage = pageCount;
					currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );
				}
				
				//5. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
				int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1)*PAGE_SIZE + 1;
				int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;
				int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0;
				int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + LIST_SIZE : pageCount;
				
				//6. 리스트 가져오기
				List<BoardVo> list = dao.getList( keyword, currentPage, LIST_SIZE );

			
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				//7. request 범위에 저장
				map.put( "list", list );
				
				map.put( "totalCount", totalCount );
				map.put( "listSize", LIST_SIZE );
				map.put( "currentPage", currentPage );
				map.put( "beginPage", beginPage );
				map.put( "endPage", endPage );
				map.put( "prevPage", prevPage );
				map.put( "nextPage", nextPage );
				map.put( "keyword", keyword );
				
				//8. 포워딩
				WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
				return map;*/
	}
	
	
}
