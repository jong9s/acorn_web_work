package com.example.spring08.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.spring08.controller.ExceptionController;
import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;
import com.example.spring08.repository.BoardDao;
import com.example.spring08.repository.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final ExceptionController exceptionController;
	
	// 하나의 서비스에서 여러 개의 dao 에 의존하는 경우도 많다
	private final BoardDao boardDao;

	// pageNum 또는 keyword 에 해당하는 글 목록과 추가 정보를 BoardListResponse 객체에 담아서
	// 리턴하는 메소드
	@Override
	public BoardListResponse getBoardList(int pageNum, BoardDto dto) {
		
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
		
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;

		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT; //공차수열
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT; //등비수열 
		
		//하단 시작 페이지 번호 (정수를 정수로 나누면 소수점이 버려진 정수가 나온다)
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		// 전체글의 개수
		int totalRow = boardDao.getCount(dto);
		// 전체 페이지의 개수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		// 끝 페이지 번호가 이미 전체 페이지 개수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount) {
			endPageNum = totalPageCount; // 보정해준다.
		}
		// startRowNum 과 endRowNum 을 BoardDto 객체에 담아서
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		// 글 목록 얻어오기 (검색 키워드가 있다면 조건에 맞는 목록만 얻어낸다)
		List<BoardDto> list = boardDao.selectPage(dto);
		
		// query 문자열을 미리 구성해서 view page 에 전달하도록 한다
		/*
		 *  검색 키워드가 없으면 query="" 빈 문자열
		 *  검색 키워드가 있으면 query="&search=검색조건&keyword=검색어" 형식의 문자열
		 */
		String query = "";
		if(dto.getKeyword() != null) {
			query = "&search=" + dto.getSearch() + "&keyword=" + dto.getKeyword();
		}
		
		// 한줄 코딩으로 BoardListResponse 객체를 만들어서 리턴하기
		return BoardListResponse.builder()
				.list(list)
				.pageNum(pageNum)
				.keyword(dto.getKeyword())
				.search(dto.getSearch())
				.query(query)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.totalRow(totalRow)
				.build();
	}
	
	@Override
	public void createContent(BoardDto dto) {
		boardDao.insert(dto);
		
	}
	
	@Override
	public void updateContent(BoardDto dto) {
		// 글 작성자와 로그인 된 유저 이름이 같은지 확인하는 로직
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String writer = boardDao.getByNum(dto.getNum()).getWriter();
		
		if(!writer.equals(userName)) {
			throw new RuntimeException("본인 게시글만 수정할 수 있습니다");
		}
		
		int rowCount = boardDao.update(dto);
		if(rowCount == 0) {
			throw new RuntimeException("수정 실패!");
		}
		
		
	}

	@Override
	public void deleteContent(int num) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String writer = boardDao.getByNum(num).getWriter();
		
		if(!writer.equals(userName)) {
			throw new RuntimeException("본인 게시글만 삭제할 수 있습니다");
		}
		// 삭제하고 삭제된 row 의 개수를 리턴받는다. 정상 삭제 시 1, 삭제 실패시 0 이 리턴된다.
		int rowCount = boardDao.delete(num);
		if(rowCount == 0) {
			throw new RuntimeException("삭제 실패!");
		}
		
	}
	
	@Override
	public BoardDto getDetail(BoardDto dto) {
		
		return boardDao.getByDto(dto);
	}
	

	@Override
	public BoardDto getData(int num) {
		
		return boardDao.getByNum(num);
	}
}
