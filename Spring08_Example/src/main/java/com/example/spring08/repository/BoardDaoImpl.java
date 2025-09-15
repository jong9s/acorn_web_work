package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring08.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDaoImpl implements BoardDao{
	
	// 의존 객체
	private final SqlSession session;

	@Override
	public List<BoardDto> selectPage(BoardDto dto) {
		
		/*
		 *  1. mapper's namespace : board
		 *  2. sql's id : selectPage
		 *  3. parameterType : BoardDto
		 *  4. resultType : BoardDto (select 된 row 하나를 어떤 type 으로 받을지를 결정해준다)
		 */
		
		return session.selectList("board.selectPage", dto);
	}

	@Override
	public int getCount(BoardDto dto) {
		// resultType : int
		return session.selectOne("board.getCount", dto);
	}

	@Override
	public void insert(BoardDto dto) {
		// 이 메소드를 호출하는 시점에 dto.num 은 0 이지만
		session.insert("board.insert", dto);
		// 이 메소드가 리턴된 이후에는 dto.num 에는 저장된 글 번호가 들어있다.
		
	}

	@Override
	public BoardDto getByNum(int num) {
		/*
		 *  parameterType : int
		 *  resultType : boardDto
		 */
		return session.selectOne("board.getByNum", num);
	}

	@Override
	public int delete(int num) {

		return session.delete("board.delete", num);
	}

	@Override
	public int update(BoardDto dto) {

		return session.update("board.update", dto);
	}

	@Override
	public BoardDto getByDto(BoardDto dto) {
		
		return session.selectOne("board.getByDto", dto);
	}

}
